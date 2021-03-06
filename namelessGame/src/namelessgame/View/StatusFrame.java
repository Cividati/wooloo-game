package namelessgame.View;

import java.awt.Color;
import java.util.Map;
import javax.swing.UIManager;
import namelessgame.Gameplay.Game;
import namelessgame.Gameplay.Item;
import namelessgame.Gameplay.Player;

/**
 * Classe que cria o frame de status do jogador.
 * @author Henrique Barcia Lang
 */
public class StatusFrame extends javax.swing.JFrame {
    private int statusPoints;
    private int str;
    private int agi;
    private int con;
    private int maxHp;
    
    /**
     * Creates new form StatusFrame
     */
    public StatusFrame() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    UIManager.getLookAndFeelDefaults().put("nimbusOrange", (new Color(185, 0, 185)));
                    
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StatusFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        Player player = Game.getPlayer();
        
        initComponents();
        
        statusPoints = player.getStatusPoints();
        str = player.getStr();
        agi = player.getAgi();
        con = player.getCon();
        maxHp = player.getMaxHealth();
        
        playerName.setText(player.getName());
        playerGold.setText(player.getGold() + " G");
        playerStr.setText(Integer.toString(str));
        playerAgi.setText(Integer.toString(agi));
        playerConst.setText(Integer.toString(con));
        playerHp.setText(Integer.toString(maxHp));
        playerLevel.setText(Integer.toString(player.getLevel()));
        playerExp.setStringPainted(true);
        playerExp.setValue((int) (((double) player.getExp() / player.getTotalExpToLevelUp()) * 100));    
        playerExp.setToolTipText(player.getExp() + " / " + player.getTotalExpToLevelUp());
        playerPoints.setText(Integer.toString(statusPoints));
        playerAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource(player.getAvatar())));
        
        String path;
        Map<Integer, Item> playerEquip = player.getEquip();
        
        path = playerEquip.get(Game.HEAD) != null ? (playerEquip.get(Game.HEAD)).getIcon() : "/namelessgame/img/slots/head.png";       
        playerHead.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));
        if(playerEquip.get(Game.HEAD) != null)
            playerHead.setToolTipText(playerEquip.get(Game.HEAD).getDescr());
        
        path = playerEquip.get(Game.BODY) != null ? (playerEquip.get(Game.BODY)).getIcon() : "/namelessgame/img/slots/body.png";
        playerBody.setIcon(new javax.swing.ImageIcon(getClass().getResource(path))); 
        if(playerEquip.get(Game.BODY) != null)
            playerBody.setToolTipText(playerEquip.get(Game.BODY).getDescr());
        
        path = playerEquip.get(Game.WEAPON) != null ? (playerEquip.get(Game.WEAPON)).getIcon() : "/namelessgame/img/slots/right-hand.png";
        playerWeapon.setIcon(new javax.swing.ImageIcon(getClass().getResource(path))); 
        if(playerEquip.get(Game.WEAPON) != null)
            playerWeapon.setToolTipText(playerEquip.get(Game.WEAPON).getDescr());
        
        path = playerEquip.get(Game.SHIELD) != null ? (playerEquip.get(Game.SHIELD)).getIcon() : "/namelessgame/img/slots/left-hand.png";
        playerShield.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));  
        if(playerEquip.get(Game.SHIELD) != null)
            playerShield.setToolTipText(playerEquip.get(Game.SHIELD).getDescr());
        
        path = playerEquip.get(Game.LEGS) != null ? (playerEquip.get(Game.LEGS)).getIcon() : "/namelessgame/img/slots/legs.png";
        playerLegs.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));  
        if(playerEquip.get(Game.LEGS) != null)
            playerLegs.setToolTipText(playerEquip.get(Game.LEGS).getDescr());
        
        path = playerEquip.get(Game.BOOTS) != null ? (playerEquip.get(Game.BOOTS)).getIcon() : "/namelessgame/img/slots/feet.png";
        playerBoots.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));  
        if(playerEquip.get(Game.BOOTS) != null)
            playerBoots.setToolTipText(playerEquip.get(Game.BOOTS).getDescr());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        infoLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pointsLabel = new javax.swing.JLabel();
        playerStr = new javax.swing.JLabel();
        addStrButton = new javax.swing.JButton();
        addConstButton = new javax.swing.JButton();
        agiLabel = new javax.swing.JLabel();
        addAgiButton = new javax.swing.JButton();
        strLabel = new javax.swing.JLabel();
        playerPoints = new javax.swing.JLabel();
        playerAgi = new javax.swing.JLabel();
        playerConst = new javax.swing.JLabel();
        constLabel = new javax.swing.JLabel();
        confirmButton = new javax.swing.JButton();
        hpLabel = new javax.swing.JLabel();
        playerHp = new javax.swing.JLabel();
        equipmentsPanel = new javax.swing.JPanel();
        playerLegs = new javax.swing.JLabel();
        playerBody = new javax.swing.JLabel();
        playerShield = new javax.swing.JLabel();
        playerHead = new javax.swing.JLabel();
        playerWeapon = new javax.swing.JLabel();
        playerBoots = new javax.swing.JLabel();
        playerPanel = new javax.swing.JPanel();
        playerName = new javax.swing.JLabel();
        levelLabel = new javax.swing.JLabel();
        playerAvatar = new javax.swing.JLabel();
        playerExp = new javax.swing.JProgressBar();
        nameLabel = new javax.swing.JLabel();
        goldLabel = new javax.swing.JLabel();
        playerGold = new javax.swing.JLabel();
        playerLevel = new javax.swing.JLabel();
        backgroundFrame = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("    Status");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/wooloo.png")).getImage());
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        getContentPane().setLayout(null);

        backButton.setFont(new java.awt.Font("OscineW04-Light", 0, 24)); // NOI18N
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton);
        backButton.setBounds(1060, 590, 140, 70);

        infoLabel.setBackground(new java.awt.Color(255, 255, 255));
        infoLabel.setFont(new java.awt.Font("OscineW04-Light", 0, 48)); // NOI18N
        infoLabel.setForeground(new java.awt.Color(255, 255, 255));
        infoLabel.setText("Status");
        getContentPane().add(infoLabel);
        infoLabel.setBounds(570, 20, 130, 70);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Attributes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OscineTrialW01-Regular", 0, 18), new java.awt.Color(102, 102, 102))); // NOI18N

        pointsLabel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        pointsLabel.setForeground(new java.awt.Color(102, 102, 102));
        pointsLabel.setText("Points:");

        playerStr.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        playerStr.setForeground(new java.awt.Color(0, 0, 0));
        playerStr.setText("0");

        addStrButton.setText("+");
        addStrButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStrButtonActionPerformed(evt);
            }
        });

        addConstButton.setText("+");
        addConstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addConstButtonActionPerformed(evt);
            }
        });

        agiLabel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        agiLabel.setForeground(new java.awt.Color(102, 102, 102));
        agiLabel.setText("Agility:");

        addAgiButton.setText("+");
        addAgiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAgiButtonActionPerformed(evt);
            }
        });

        strLabel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        strLabel.setForeground(new java.awt.Color(102, 102, 102));
        strLabel.setText("Strength:");

        playerPoints.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        playerPoints.setForeground(new java.awt.Color(51, 204, 0));
        playerPoints.setText("+0");

        playerAgi.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        playerAgi.setForeground(new java.awt.Color(0, 0, 0));
        playerAgi.setText("0");

        playerConst.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        playerConst.setForeground(new java.awt.Color(0, 0, 0));
        playerConst.setText("0");

        constLabel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        constLabel.setForeground(new java.awt.Color(102, 102, 102));
        constLabel.setText("Constitution:");

        confirmButton.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        confirmButton.setText("Confirm");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        hpLabel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        hpLabel.setForeground(new java.awt.Color(102, 102, 102));
        hpLabel.setText("Max HP:");

        playerHp.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        playerHp.setForeground(new java.awt.Color(0, 0, 0));
        playerHp.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(strLabel)
                                    .addComponent(agiLabel))
                                .addGap(55, 55, 55)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(playerAgi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(playerStr, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(hpLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(playerHp, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(constLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(playerConst, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addStrButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addAgiButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addConstButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(pointsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playerPoints, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pointsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playerPoints, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(strLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playerStr, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addStrButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playerAgi, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addAgiButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addConstButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(constLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playerConst, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playerHp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(920, 180, 280, 340);

        equipmentsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Equipments", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OscineTrialW01-Regular", 0, 18), new java.awt.Color(102, 102, 102))); // NOI18N
        equipmentsPanel.setLayout(null);

        playerLegs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/slots/legs.png"))); // NOI18N
        equipmentsPanel.add(playerLegs);
        playerLegs.setBounds(90, 140, 40, 40);

        playerBody.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/slots/body.png"))); // NOI18N
        equipmentsPanel.add(playerBody);
        playerBody.setBounds(90, 90, 40, 50);

        playerShield.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/slots/right-hand.png"))); // NOI18N
        equipmentsPanel.add(playerShield);
        playerShield.setBounds(140, 100, 40, 30);

        playerHead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/slots/head.png"))); // NOI18N
        equipmentsPanel.add(playerHead);
        playerHead.setBounds(90, 50, 40, 40);

        playerWeapon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/slots/left-hand.png"))); // NOI18N
        equipmentsPanel.add(playerWeapon);
        playerWeapon.setBounds(40, 100, 50, 30);

        playerBoots.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/slots/feet.png"))); // NOI18N
        equipmentsPanel.add(playerBoots);
        playerBoots.setBounds(90, 190, 50, 30);

        getContentPane().add(equipmentsPanel);
        equipmentsPanel.setBounds(140, 220, 210, 250);

        playerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Player", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OscineTrialW01-Regular", 0, 18), new java.awt.Color(102, 102, 102))); // NOI18N
        playerPanel.setLayout(null);

        playerName.setBackground(new java.awt.Color(255, 255, 255));
        playerName.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        playerName.setForeground(new java.awt.Color(0, 0, 0));
        playerName.setText("test");
        playerPanel.add(playerName);
        playerName.setBounds(70, 30, 120, 30);

        levelLabel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        levelLabel.setForeground(new java.awt.Color(102, 102, 102));
        levelLabel.setText("Lv.:");
        playerPanel.add(levelLabel);
        levelLabel.setBounds(40, 80, 40, 30);

        playerAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/erhard.png"))); // NOI18N
        playerPanel.add(playerAvatar);
        playerAvatar.setBounds(20, 130, 300, 400);

        playerExp.setForeground(new java.awt.Color(0, 0, 0));
        playerPanel.add(playerExp);
        playerExp.setBounds(110, 80, 210, 30);

        nameLabel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(102, 102, 102));
        nameLabel.setText("Name:");
        nameLabel.setPreferredSize(new java.awt.Dimension(1280, 720));
        playerPanel.add(nameLabel);
        nameLabel.setBounds(10, 30, 60, 30);

        goldLabel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        goldLabel.setForeground(new java.awt.Color(102, 102, 102));
        goldLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/icons/gold.png"))); // NOI18N
        goldLabel.setText("Gold:");
        playerPanel.add(goldLabel);
        goldLabel.setBounds(190, 20, 90, 50);

        playerGold.setFont(new java.awt.Font("OscineTrialW01-Regular", 1, 14)); // NOI18N
        playerGold.setForeground(new java.awt.Color(255, 255, 0));
        playerGold.setText("0 G");
        playerPanel.add(playerGold);
        playerGold.setBounds(280, 30, 50, 30);

        playerLevel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        playerLevel.setForeground(new java.awt.Color(0, 0, 0));
        playerLevel.setText("0");
        playerPanel.add(playerLevel);
        playerLevel.setBounds(70, 80, 30, 30);

        getContentPane().add(playerPanel);
        playerPanel.setBounds(470, 100, 340, 560);

        backgroundFrame.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backgroundFrame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/bg.jpg"))); // NOI18N
        getContentPane().add(backgroundFrame);
        backgroundFrame.setBounds(0, 0, 1280, 720);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Ação realizada ao clicar em back
     * @param evt 
     */
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        Game.playNewAudio("click1", false);
        
        this.dispose();
        
        GameFrame gameBack = new GameFrame();
        gameBack.setVisible(true);   
    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * Ação realizada ao clicar em '+' (strenght)
     * @param evt 
     */
    private void addStrButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStrButtonActionPerformed
        Game.playNewAudio("click1", false);
        
        if(statusPoints <= 0)
        {
            Game.sendErrorMessage("You do not have points to distribute.");
            
            return;
        }
        
        str++;
        maxHp = Player.getMaxHealth(str, con);
        statusPoints--;
        
        playerStr.setText(Integer.toString(str));
        playerHp.setText(Integer.toString(maxHp));
        playerPoints.setText(Integer.toString(statusPoints));
    }//GEN-LAST:event_addStrButtonActionPerformed

    /**
     * Ação realizada ao clicar em '+' (agility)
     * @param evt 
     */
    private void addAgiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAgiButtonActionPerformed
        Game.playNewAudio("click1", false);
        
        if(statusPoints <= 0)
        {
            Game.sendErrorMessage("You do not have points to distribute.");
            
            return;
        }
        
        agi++;
        statusPoints--;
        
        playerAgi.setText(Integer.toString(agi));
        playerPoints.setText(Integer.toString(statusPoints));
    }//GEN-LAST:event_addAgiButtonActionPerformed

    /**
     * Ação realizada ao clicar em '+' (constitution)
     * @param evt 
     */
    private void addConstButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addConstButtonActionPerformed
        Game.playNewAudio("click1", false);
        
        if(statusPoints <= 0)
        {
            Game.sendErrorMessage("You do not have points to distribute.");
            
            return;
        }
        
        con++;
        maxHp = Player.getMaxHealth(str, con);
        statusPoints--;
        
        playerConst.setText(Integer.toString(con));
        playerHp.setText(Integer.toString(maxHp));
        playerPoints.setText(Integer.toString(statusPoints));
    }//GEN-LAST:event_addConstButtonActionPerformed

    /**
     * Ação realizada ao clicar em confirm
     * @param evt 
     */
    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        Game.playNewAudio("click1", false);
        
        Player player = Game.getPlayer();
        
        if(player.getStatusPoints() == statusPoints)
        {
            Game.sendErrorMessage("You didn't distribute any points.");
            
            return;
        }
        
        player.setStr(player.getBaseStr() + (str - player.getStr()));
        player.setAgi(player.getBaseAgi() + (agi - player.getAgi()));
        player.setCon(player.getBaseCon() + (con - player.getCon()));
        player.setStatusPoints(statusPoints);
        
        Game.sendSuccessMessage("Point(s) distributed.");
    }//GEN-LAST:event_confirmButtonActionPerformed

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
            java.util.logging.Logger.getLogger(StatusFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StatusFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StatusFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StatusFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StatusFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAgiButton;
    private javax.swing.JButton addConstButton;
    private javax.swing.JButton addStrButton;
    private javax.swing.JLabel agiLabel;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel backgroundFrame;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLabel constLabel;
    private javax.swing.JPanel equipmentsPanel;
    private javax.swing.JLabel goldLabel;
    private javax.swing.JLabel hpLabel;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel playerAgi;
    private javax.swing.JLabel playerAvatar;
    private javax.swing.JLabel playerBody;
    private javax.swing.JLabel playerBoots;
    private javax.swing.JLabel playerConst;
    private javax.swing.JProgressBar playerExp;
    private javax.swing.JLabel playerGold;
    private javax.swing.JLabel playerHead;
    private javax.swing.JLabel playerHp;
    private javax.swing.JLabel playerLegs;
    private javax.swing.JLabel playerLevel;
    private javax.swing.JLabel playerName;
    private javax.swing.JPanel playerPanel;
    private javax.swing.JLabel playerPoints;
    private javax.swing.JLabel playerShield;
    private javax.swing.JLabel playerStr;
    private javax.swing.JLabel playerWeapon;
    private javax.swing.JLabel pointsLabel;
    private javax.swing.JLabel strLabel;
    // End of variables declaration//GEN-END:variables
}
