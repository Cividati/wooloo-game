/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namelessgame.View;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import namelessgame.Gameplay.Creature;
import namelessgame.Gameplay.Game;
import namelessgame.Gameplay.Item;
import namelessgame.Gameplay.LootItem;
import namelessgame.Gameplay.Monster;
import namelessgame.Gameplay.Player;
import namelessgame.Threads.Attack;
import namelessgame.Threads.HealthBar;
import namelessgame.Threads.Shake;

/** 
 * Classe que cria o frame do sistema de batalha, bem como sua implementação no back-end.
 * @author Henrique Barcia Lang
 */
public class BattleFrame extends javax.swing.JFrame {

    /**
     * Creates new form BattleFrame
     */
    
    private String log = "";
    
    private JTextArea display = new JTextArea();
    
    private Player player = Game.getPlayer();
    private Creature target;
    
    private List<Item> inventory = player.getInventory();
    private Map<javax.swing.JButton, Item> potionMap = new HashMap<>();
    
    public BattleFrame() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    UIManager.getLookAndFeelDefaults().put("nimbusOrange", (new Color(255, 0, 0)));
                    
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StatusFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        initComponents();
    }
    
    /** 
     * Inicia a batalha entre o jogador e o oponente.
     * @param target Creature - criatura (player ou monstro) que o jogador irá enfrentar
     * @param background String - nome do background
     */
    public BattleFrame(Creature target, String background) { 
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    UIManager.getLookAndFeelDefaults().put("nimbusOrange", (new Color(255, 0, 0)));
                    
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StatusFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        initComponents();
        
        this.target = target;
        
        display.setEditable(false);
        displayMessage("\t\tWhile exploring the dungeon you found a(n) " + target.getName() + "!\n");
        
        playerName.setText(player.getName());
        playerLevel.setText(Integer.toString(player.getLevel()));
        playerHp.setText(Integer.toString(player.getHealth()));
        playerMaxHp.setText(Integer.toString(player.getMaxHealth()));
        playerHpBar.setValue((int) (((double) player.getHealth() / player.getMaxHealth()) * 100));
        playerAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource(player.getAvatar())));
        
        targetName.setText(target.getName());
        targetHp.setText(Integer.toString(target.getHealth()));
        targetMaxHp.setText(Integer.toString(target.getMaxHealth()));
        targetHpBar.setValue((int) (((double) target.getHealth() / target.getMaxHealth()) * 100));
        targetAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource(target.getAvatar())));
        
        //backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(background)));
        
    }
    
    /**
     * Realiza um ataque de attacker em target.
     * @param attacker Creature - criatura que está atacando
     * @param target Creature - criatura que está recebendo o ataque
     * @return int - tempo total que o ataque levou para ser realizado
     */
    public int doCombat(Creature attacker, Creature target)
    {
        if(attacker.getHealth() <= 0 || target.getHealth() <= 0)
            return 0;
        
        int damageDealt;
        int hits = attacker.getHits();
        
        String damageMessage = "";
        
        setEnabled(false);
        
        // diminuir barra de HP
        // att texto de HP
        // tremer qm tomou dano
        // mover qm deu dano
        
        attack(attacker, hits);
        shake(target, hits); 
        
        int totalHits = 0;
        
        for(int i = 1; i <= hits; i++)
        {
            // You dealt 3000 damage to x
            // You took 3000 damage from x
            damageDealt = attacker.getDamageToTarget(target);
            
            damageMessage += (attacker == player ? "You dealt " : "You took ") + damageDealt + " damage " + (attacker == player ? "to " : "from ") + this.target.getName() + "!\n";
            
            target.addHealth(-damageDealt);
            
            totalHits++;
            
            if(target.getHealth() <= 0)
                break;
        }
        
        updateHealthBar(target, totalHits * Shake.getShakes() * 100);
        
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final String displayDamage = damageMessage;
        
        ScheduledFuture<?> countdown = scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                // after updating hp bar
                
                setEnabled(true);
                
                displayMessage(displayDamage);
                
                if(target.getHealth() <= 0)
                {
                    // target is dead
                    
                    if(target != player)
                    {
                        // player won
                        dispose();
                        
                        Monster monster = (Monster) target;
                        Random random = new Random();
                        
                        int exp = monster.getExpGiven();
                        int gold = random.nextInt(monster.getGoldMax() + 1 - monster.getGoldMin()) + monster.getGoldMin();                          
                        
                        generateLoot(monster, random);
                        
                        player.addExp(exp);
                        player.addGold(gold);
                        
                        (new VictoryFrame(gold, exp)).setVisible(true);
   
                    }
                    else
                    {
                        dispose();
                        
                        (new DefeatFrame()).setVisible(true);
                    }
                }
            }}, totalHits * Shake.getShakes() * 100, TimeUnit.MILLISECONDS);
        
        scheduler.shutdown();
        
        return totalHits * Shake.getShakes() * 100;
    }
    
    /**
     * Gera o loot provindo de monster
     * @param monster Monster - monstro cujo loot será gerado
     * @param random Random - gerador de números aleatórios
     */
    public void generateLoot(Monster monster, Random random)
    {
        for(LootItem item : monster.getLoots()) 
        {
            int itemChance = item.getChance();
            int randValue = (random.nextInt(100) + 1);

            if(randValue <= itemChance)
            {
                try {
                    Item loot = (Item) item.clone();                                   
                    loot.setCount(random.nextInt(item.getCountMax() + 1 - item.getCountMin()) + item.getCountMin());

                    Game.addLoot(loot);                              
                } catch (CloneNotSupportedException ex) {
                    System.out.println("Error when cloning item on monster loot..." + ex.getMessage());
                }
            }

        }
    }
    
    /**
     * Realiza a animação de ataque
     * @param creature Creature - criatura que está atacando
     * @param times int - quantos ataques estão sendo realizados
     */
    public void attack(Creature creature, int times)
    {
        Attack attacking = new Attack();
        
        attacking.setLabel(creature == player ? playerAvatar : targetAvatar);
        attacking.setFrame(this);
        attacking.setTimes(times);
        
        attacking.start();
    }
    
    /**
     * Realiza a animação quando a criatura recebe um ataque
     * @param creature Creature - criatura que está recebendo o ataque
     * @param times int - quantos ataques a criatura está recebendo
     */
    public void shake(Creature creature, int times)
    {
        Shake shaking = new Shake();
        
        shaking.setLabel(creature == player ? playerAvatar : targetAvatar);
        shaking.setFrame(this);
        shaking.setTimes(times);
        
        shaking.start();
    }
    
    /**
     * Realiza a animação de atualizar a barra de HP
     * @param creature Creature - criatura que está tendo sua barra de HP atualizada
     * @param totalTime int - tempo total que a animação deverá levar
     */
    public void updateHealthBar(Creature creature, int totalTime)
    {
        JLabel hpLabel = creature == player ? playerHp : targetHp;
        JProgressBar hpBar = creature == player ? playerHpBar : targetHpBar;
                
        int initialHealth = Integer.parseInt(hpLabel.getText());
        int finalHealth = creature.getHealth();
        
        if(finalHealth < 0)
            finalHealth = 0;
        
        int diffHp = initialHealth - finalHealth;
        
        if(diffHp == 0)
            return;
        
        // totaltime - hpdiff
        // x - 1 hp
        int timePerHealthPoint = totalTime / Math.abs(diffHp);
        
        HealthBar updating = new HealthBar();
        
        updating.setHpBar(hpBar);
        updating.setHpLabel(hpLabel);
        updating.setMaxHealth(creature.getMaxHealth());
        updating.setDiffHealth(diffHp);
        updating.setTimePerHealthPoint(timePerHealthPoint);
        
        updating.start();    
    }
    
    /**
     * Mostra uma mensagem na área de texto.
     * @param message String - mensagem a ser mostrada
     */
    public void displayMessage(String message)
    {
        log += "\n" + message;
        
        display.setText(log);
       
        if(scrollPane.getViewport().getComponentCount() == 0 || !(scrollPane.getViewport().getComponent(0) instanceof JTextArea))
        {
            scrollPane.getViewport().removeAll();
            scrollPane.getViewport().setView(display);
            
            scrollPane.revalidate();
            scrollPane.repaint();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playerAvatar = new javax.swing.JLabel();
        infoLevel = new javax.swing.JLabel();
        playerLevel = new javax.swing.JLabel();
        playerHp = new javax.swing.JLabel();
        playerName = new javax.swing.JLabel();
        targetAvatar = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        optionPanel = new javax.swing.JPanel();
        runButton = new javax.swing.JButton();
        attackButton = new javax.swing.JButton();
        potionButton = new javax.swing.JButton();
        playerHpBar = new javax.swing.JProgressBar();
        hpDivider = new javax.swing.JLabel();
        playerMaxHp = new javax.swing.JLabel();
        targetHp = new javax.swing.JLabel();
        targetHpBar = new javax.swing.JProgressBar();
        targetName = new javax.swing.JLabel();
        targetMaxHp = new javax.swing.JLabel();
        targetHpDivider = new javax.swing.JLabel();
        backgroundLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("    Battle");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/wooloo.png")).getImage());
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        getContentPane().setLayout(null);

        playerAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/avatars/kirito.png"))); // NOI18N
        getContentPane().add(playerAvatar);
        playerAvatar.setBounds(110, 100, 300, 400);

        infoLevel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        infoLevel.setForeground(new java.awt.Color(255, 255, 255));
        infoLevel.setText("Lv.");
        getContentPane().add(infoLevel);
        infoLevel.setBounds(350, 10, 40, 30);

        playerLevel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        playerLevel.setForeground(new java.awt.Color(255, 255, 255));
        playerLevel.setText("0");
        getContentPane().add(playerLevel);
        playerLevel.setBounds(380, 10, 30, 30);

        playerHp.setBackground(new java.awt.Color(255, 255, 255));
        playerHp.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        playerHp.setForeground(new java.awt.Color(255, 255, 255));
        playerHp.setText("0");
        getContentPane().add(playerHp);
        playerHp.setBounds(130, 70, 50, 30);

        playerName.setBackground(new java.awt.Color(255, 255, 255));
        playerName.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        playerName.setForeground(new java.awt.Color(255, 255, 255));
        playerName.setText("test");
        getContentPane().add(playerName);
        playerName.setBounds(130, 10, 120, 30);

        targetAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/avatars/klein.png"))); // NOI18N
        getContentPane().add(targetAvatar);
        targetAvatar.setBounds(870, 100, 300, 400);
        getContentPane().add(scrollPane);
        scrollPane.setBounds(10, 510, 760, 200);

        optionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Options"));

        runButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        runButton.setText("Run");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        attackButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        attackButton.setText("Attack");
        attackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attackButtonActionPerformed(evt);
            }
        });

        potionButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        potionButton.setText("Potions");
        potionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                potionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout optionPanelLayout = new javax.swing.GroupLayout(optionPanel);
        optionPanel.setLayout(optionPanelLayout);
        optionPanelLayout.setHorizontalGroup(
            optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, optionPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(attackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(potionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        optionPanelLayout.setVerticalGroup(
            optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(attackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(potionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        getContentPane().add(optionPanel);
        optionPanel.setBounds(780, 510, 490, 200);

        playerHpBar.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(playerHpBar);
        playerHpBar.setBounds(120, 40, 280, 30);

        hpDivider.setBackground(new java.awt.Color(255, 255, 255));
        hpDivider.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        hpDivider.setForeground(new java.awt.Color(255, 255, 255));
        hpDivider.setText("/");
        getContentPane().add(hpDivider);
        hpDivider.setBounds(180, 70, 10, 30);

        playerMaxHp.setBackground(new java.awt.Color(255, 255, 255));
        playerMaxHp.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        playerMaxHp.setForeground(new java.awt.Color(255, 255, 255));
        playerMaxHp.setText("0");
        getContentPane().add(playerMaxHp);
        playerMaxHp.setBounds(220, 70, 60, 30);

        targetHp.setBackground(new java.awt.Color(255, 255, 255));
        targetHp.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        targetHp.setForeground(new java.awt.Color(255, 255, 255));
        targetHp.setText("0");
        getContentPane().add(targetHp);
        targetHp.setBounds(890, 70, 50, 30);

        targetHpBar.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(targetHpBar);
        targetHpBar.setBounds(880, 40, 280, 30);

        targetName.setBackground(new java.awt.Color(255, 255, 255));
        targetName.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        targetName.setForeground(new java.awt.Color(255, 255, 255));
        targetName.setText("test");
        getContentPane().add(targetName);
        targetName.setBounds(890, 10, 120, 30);

        targetMaxHp.setBackground(new java.awt.Color(255, 255, 255));
        targetMaxHp.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        targetMaxHp.setForeground(new java.awt.Color(255, 255, 255));
        targetMaxHp.setText("0");
        getContentPane().add(targetMaxHp);
        targetMaxHp.setBounds(980, 70, 60, 30);

        targetHpDivider.setBackground(new java.awt.Color(255, 255, 255));
        targetHpDivider.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        targetHpDivider.setForeground(new java.awt.Color(255, 255, 255));
        targetHpDivider.setText("/");
        getContentPane().add(targetHpDivider);
        targetHpDivider.setBounds(940, 70, 10, 30);

        backgroundLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/dungeons/bg.png"))); // NOI18N
        backgroundLabel.setToolTipText("");
        backgroundLabel.setAlignmentY(0.0F);
        getContentPane().add(backgroundLabel);
        backgroundLabel.setBounds(0, 0, 1920, 1080);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Ação realizada ao clicar no botão de ataque.
     * @param evt 
     */
    private void attackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attackButtonActionPerformed
        Game.playSound("click1");
        int myAgility = player.getAgi();
        int targetAgility = target.getAgi();
        
        Creature firstToAttack;
        Creature secondToAttack;
        
        if(myAgility > targetAgility)
        {
            firstToAttack = player;
            secondToAttack = target;
        }
        else if(myAgility < targetAgility)
        {
            firstToAttack = target;
            secondToAttack = player;
        }
        else
        {
            int random = (new Random()).nextInt(2);
            
            firstToAttack = random == 0 ? player : target;
            secondToAttack = random == 0 ? target : player;
        }
        
        int duration = doCombat(firstToAttack, secondToAttack);

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        
        ScheduledFuture<?> countdown = scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                doCombat(secondToAttack, firstToAttack);
            }}, duration, TimeUnit.MILLISECONDS);
        
        scheduler.shutdown();
    }//GEN-LAST:event_attackButtonActionPerformed

    /**
     * Ação realizada ao clicar no botão de poção.
     * @param evt 
     */
    private void potionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_potionButtonActionPerformed
        // preencher scroll pane com botões das potions
        // clicou na potion -> efeito de heal
        //                  -> mostra log
        Game.playSound("click1");
        javax.swing.JPanel potionPanel = new javax.swing.JPanel();
        
        scrollPane.getViewport().removeAll();
        potionMap.clear();
        
        for(Item item : inventory)
        {
            if(!item.isPotion())
                continue;
            
            javax.swing.JButton itemButton = new javax.swing.JButton();
            
            itemButton.setIcon(new javax.swing.ImageIcon(getClass().getResource(item.getIcon())));
            itemButton.setToolTipText(item.getDescr());
            
            itemButton.addActionListener(new java.awt.event.ActionListener() {           
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    PotionActionPerformed(evt);
                }
            });

            potionMap.put(itemButton, item);

            potionPanel.add(itemButton);
        }
        
        potionPanel.setLayout(new java.awt.GridLayout(Game.MAX_INVENTORY_SIZE / Game.INVENTORY_COLUMNS, Game.INVENTORY_COLUMNS));
        potionPanel.setSize(300, 300);
        potionPanel.setVisible(true);
        add(potionPanel);
        
        scrollPane.getViewport().add(potionPanel, null);
        
    }//GEN-LAST:event_potionButtonActionPerformed

    /**
     * Ação realizada ao clicar em uma poção.
     * @param evt 
     */
    private void PotionActionPerformed(java.awt.event.ActionEvent evt) {                                               
        javax.swing.JButton itemButton = (javax.swing.JButton) evt.getSource();
        
        Item item = potionMap.get(itemButton);
        
        if(item == null)
            return;
        
        item.setCount(item.getCount() - 1);
        
        if(item.getCount() <= 0)
            inventory.remove(item);
        
        int healAmount = item.getHeal();
        
        int hpInitial = player.getHealth();
        
        player.addHealth(healAmount);
        
        int hpFinal = player.getHealth();
        
        int time = (hpFinal - hpInitial) * 50;
        
        // 50ms per health point
        updateHealthBar(player, time);
        
        setEnabled(false);
        
        displayMessage("You recovered " + healAmount + " HP.\n");
        
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        
        ScheduledFuture<?> countdown = scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                setEnabled(true);
                
                doCombat(target, player);     
            }}, time, TimeUnit.MILLISECONDS);
        
        scheduler.shutdown();
    }
    
    /**
     * Ação realizada ao clicar no botão de fugir.
     * @param evt 
     */
    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runButtonActionPerformed
        Game.playSound("click1");
        int myStatsMean = (player.getStr() + player.getAgi() + player.getCon()) / 3;
        int targetMean = (target.getStr() + target.getAgi() + target.getCon()) / 3;
        
        if(myStatsMean >= targetMean)
        {
            this.dispose();
            
            (new GameFrame()).setVisible(true);
            
            Game.sendSuccessMessage("You were able to escape the dungeon.");
        }
        else
        {
            Game.sendErrorMessage("You couldn't escape the dungeon.");
            
            doCombat(target, player);
        }
    }//GEN-LAST:event_runButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BattleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BattleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BattleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BattleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BattleFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton attackButton;
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JLabel hpDivider;
    private javax.swing.JLabel infoLevel;
    private javax.swing.JPanel optionPanel;
    private javax.swing.JLabel playerAvatar;
    private javax.swing.JLabel playerHp;
    private javax.swing.JProgressBar playerHpBar;
    private javax.swing.JLabel playerLevel;
    private javax.swing.JLabel playerMaxHp;
    private javax.swing.JLabel playerName;
    private javax.swing.JButton potionButton;
    private javax.swing.JButton runButton;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel targetAvatar;
    private javax.swing.JLabel targetHp;
    private javax.swing.JProgressBar targetHpBar;
    private javax.swing.JLabel targetHpDivider;
    private javax.swing.JLabel targetMaxHp;
    private javax.swing.JLabel targetName;
    // End of variables declaration//GEN-END:variables
}
