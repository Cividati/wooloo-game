package Test;

//import namelessgame.UI.MenuFrame;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import namelessgame.Database.PlayerDAO;
import namelessgame.Gameplay.Game;
import namelessgame.Gameplay.Player;

/**
 *
 * @author sin
 */
public class LoadGameFrame extends javax.swing.JFrame {

    /**
     * Creates new form loadGameFrame
     */
    private int chosenId = -1;
    public List<Player> playerList;
    
    public void setupPlayerInfo()
    {
        if(chosenId >= playerList.size())
            return;
        
        try
        {
            Player player = playerList.get(chosenId);
            
            playerNameLabel.setText("Name: " + player.getName() + " (" + player.getSex() + ")");
            levelLabel.setText("Level: " + player.getLevel());
            playerGoldLabel.setText(Long.toString(player.getGold()));
            strLabel.setText("Strength: " + player.getStr());
            constLabel.setText("Constitution: " + player.getCon());
            agiLabel.setText("Agility: " + player.getAgi());
            expBar.setValue((player.getExp() / player.getExpNeededToLevelUp()) * 100);
            expBar.setToolTipText(player.getExp() + " / " + player.getExpNeededToLevelUp());
            
            // TODO set player avatar
            // TODO show equipment?
            
            charAvatar.setVisible(true);
            expBar.setVisible(true);
        }
        catch(ArrayIndexOutOfBoundsException e) {}
    }
    
    public void clearPlayerInfo()
    {
        playerNameLabel.setText("Name: ");
        levelLabel.setText("Level: ");
        playerGoldLabel.setText("");
        strLabel.setText("Strength: ");
        constLabel.setText("Constitution: ");
        agiLabel.setText("Agility: ");
        expBar.setValue(0);
        expBar.setToolTipText("");
    }

    public LoadGameFrame() {
        initComponents();
        
        charAvatar.setVisible(false);
        expBar.setVisible(false);
        expBar.setStringPainted(true);
        
        playerList = (new PlayerDAO()).loadPlayers();
        
        Collections.sort(playerList);

        for (int i = 0; i < playerList.size(); i++) 
        {
            String name = playerList.get(i).getName();
            int level = playerList.get(i).getLevel();
            
            JButton charButton = null;

            switch (i) 
            {
                case 0:
                    charButton = Char1;

                    break;
                case 1:
                    charButton = Char2;

                    break;
                case 2:
                    charButton = Char3;

                    break;
                case 3:
                    charButton = Char4;

                    break;
                case 4:
                    charButton = Char5;

                    break;
                case 5:
                    charButton = Char6;

                    break;
                case 6:
                    charButton = Char7;

                    break;
                case 7:
                    charButton = Char8;

                    break;
            }
            
            if(charButton != null)
                charButton.setText(name + " - lv. " + level);

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

        name1 = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        playerGoldLabel = new javax.swing.JLabel();
        levelLabel = new javax.swing.JLabel();
        goldLabel = new javax.swing.JLabel();
        playerNameLabel = new javax.swing.JLabel();
        strLabel = new javax.swing.JLabel();
        constLabel = new javax.swing.JLabel();
        agiLabel = new javax.swing.JLabel();
        expBar = new javax.swing.JProgressBar();
        BackButton = new javax.swing.JButton();
        Char4 = new javax.swing.JButton();
        Char1 = new javax.swing.JButton();
        Char2 = new javax.swing.JButton();
        Char3 = new javax.swing.JButton();
        Char5 = new javax.swing.JButton();
        Char6 = new javax.swing.JButton();
        Char7 = new javax.swing.JButton();
        Char8 = new javax.swing.JButton();
        charAvatar = new javax.swing.JLabel();
        BG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        getContentPane().setLayout(null);

        name1.setBackground(new java.awt.Color(0, 0, 0));
        name1.setFont(new java.awt.Font("OscineW04-Light", 0, 48)); // NOI18N
        name1.setForeground(new java.awt.Color(51, 51, 51));
        name1.setText("Your saves");
        getContentPane().add(name1);
        name1.setBounds(180, 20, 280, 70);

        deleteButton.setFont(new java.awt.Font("OscineW04-Light", 0, 24)); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        getContentPane().add(deleteButton);
        deleteButton.setBounds(1030, 630, 120, 50);

        loadButton.setFont(new java.awt.Font("OscineW04-Light", 0, 24)); // NOI18N
        loadButton.setText("Load");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });
        getContentPane().add(loadButton);
        loadButton.setBounds(910, 630, 110, 50);

        playerGoldLabel.setBackground(new java.awt.Color(0, 0, 0));
        playerGoldLabel.setFont(new java.awt.Font("OscineW04-Light", 0, 24)); // NOI18N
        playerGoldLabel.setForeground(new java.awt.Color(255, 255, 0));
        getContentPane().add(playerGoldLabel);
        playerGoldLabel.setBounds(970, 120, 90, 20);

        levelLabel.setBackground(new java.awt.Color(0, 0, 0));
        levelLabel.setFont(new java.awt.Font("OscineW04-Light", 0, 24)); // NOI18N
        levelLabel.setText("Level: ");
        getContentPane().add(levelLabel);
        levelLabel.setBounds(880, 230, 160, 32);

        goldLabel.setBackground(new java.awt.Color(0, 0, 0));
        goldLabel.setFont(new java.awt.Font("OscineW04-Light", 0, 24)); // NOI18N
        goldLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/gold.png"))); // NOI18N
        goldLabel.setText("Gold:");
        getContentPane().add(goldLabel);
        goldLabel.setBounds(880, 110, 90, 40);

        playerNameLabel.setBackground(new java.awt.Color(0, 0, 0));
        playerNameLabel.setFont(new java.awt.Font("OscineW04-Light", 0, 24)); // NOI18N
        playerNameLabel.setText("Name:");
        getContentPane().add(playerNameLabel);
        playerNameLabel.setBounds(880, 180, 350, 20);

        strLabel.setBackground(new java.awt.Color(0, 0, 0));
        strLabel.setFont(new java.awt.Font("OscineW04-Light", 0, 24)); // NOI18N
        strLabel.setText("Strength: ");
        getContentPane().add(strLabel);
        strLabel.setBounds(880, 320, 160, 30);

        constLabel.setBackground(new java.awt.Color(0, 0, 0));
        constLabel.setFont(new java.awt.Font("OscineW04-Light", 0, 24)); // NOI18N
        constLabel.setText("Constituition:");
        getContentPane().add(constLabel);
        constLabel.setBounds(1090, 360, 190, 30);

        agiLabel.setBackground(new java.awt.Color(0, 0, 0));
        agiLabel.setFont(new java.awt.Font("OscineW04-Light", 0, 24)); // NOI18N
        agiLabel.setText("Agility: ");
        getContentPane().add(agiLabel);
        agiLabel.setBounds(1090, 320, 210, 30);

        expBar.setForeground(new java.awt.Color(204, 0, 255));
        getContentPane().add(expBar);
        expBar.setBounds(880, 260, 340, 30);

        BackButton.setFont(new java.awt.Font("OscineW04-Light", 0, 24)); // NOI18N
        BackButton.setText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        getContentPane().add(BackButton);
        BackButton.setBounds(80, 600, 130, 50);

        Char4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Char4MousePressed(evt);
            }
        });
        getContentPane().add(Char4);
        Char4.setBounds(50, 430, 210, 80);

        Char1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Char1MousePressed(evt);
            }
        });
        getContentPane().add(Char1);
        Char1.setBounds(50, 100, 210, 80);

        Char2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Char2MousePressed(evt);
            }
        });
        getContentPane().add(Char2);
        Char2.setBounds(50, 210, 210, 80);

        Char3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Char3MousePressed(evt);
            }
        });
        getContentPane().add(Char3);
        Char3.setBounds(50, 320, 210, 80);

        Char5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Char5MousePressed(evt);
            }
        });
        getContentPane().add(Char5);
        Char5.setBounds(350, 100, 210, 80);

        Char6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Char6MousePressed(evt);
            }
        });
        getContentPane().add(Char6);
        Char6.setBounds(350, 210, 210, 80);

        Char7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Char7MousePressed(evt);
            }
        });
        getContentPane().add(Char7);
        Char7.setBounds(350, 320, 210, 80);

        Char8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Char8MousePressed(evt);
            }
        });
        getContentPane().add(Char8);
        Char8.setBounds(350, 430, 210, 80);

        charAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/kirito.gif"))); // NOI18N
        getContentPane().add(charAvatar);
        charAvatar.setBounds(580, 160, 280, 280);

        BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/PATTERN-BRANCO.png"))); // NOI18N
        BG.setText("jLabel1");
        getContentPane().add(BG);
        BG.setBounds(0, 0, 1270, 750);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        try
        {
            (new PlayerDAO()).deletePlayer(playerList.get(chosenId));
            
            int oldSize = playerList.size();         
            playerList.remove(chosenId);           
            
            if(oldSize != playerList.size())
            {
                JButton charButton = null;
                Player player;
                for (int i = 0; i < 8; i++) 
                {                   
                    String displayInfo = "";

                    switch (i) 
                    {
                        case 0:
                            charButton = Char1;

                            break;
                        case 1:
                            charButton = Char2;

                            break;
                        case 2:
                            charButton = Char3;

                            break;
                        case 3:
                            charButton = Char4;

                            break;
                        case 4:
                            charButton = Char5;

                            break;
                        case 5:
                            charButton = Char6;

                            break;
                        case 6:
                            charButton = Char7;

                            break;
                        case 7:
                            charButton = Char8;

                            break;
                    }

                    if(charButton == null)
                    {
                        System.out.println("Error when displaying characters... (not enough buttons configured)");
                        
                        break;
                    }
                    
                    if(i < playerList.size())
                    {
                        player = playerList.get(i);
                        
                        displayInfo = player.getName() + " - lv. " + player.getLevel();
                    }
                                       
                    charButton.setText(displayInfo);
                    clearPlayerInfo();
                                     
                }
                
                Game.sendSuccessMessage("You successfully deleted your character.");
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            Game.sendErrorMessage("Invalid game id.");
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        try
        {
            Game.setPlayer(playerList.get(chosenId));
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            Game.sendErrorMessage("Invalid game id.");
            
            return;
        }
        
        this.dispose();
        GameFrame gameBack = new GameFrame();
        gameBack.setVisible(true);        
    }//GEN-LAST:event_loadButtonActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        this.dispose();

        MenuFrame menuBack = new MenuFrame();
        menuBack.setVisible(true);
        menuBack.setSize(1280, 720);
    }//GEN-LAST:event_BackButtonActionPerformed

    private void Char1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Char1MousePressed
        if(Char1.getText().equals(""))
        {
            chosenId = -1;
            
            return;
        }

        chosenId = 0;
        
        setupPlayerInfo();
    }//GEN-LAST:event_Char1MousePressed

    private void Char2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Char2MousePressed
        if(Char2.getText().equals(""))
        {
            chosenId = -1;
            
            return;
        }
        
        chosenId = 1;
        
        setupPlayerInfo();
    }//GEN-LAST:event_Char2MousePressed

    private void Char3MousePressed(java.awt.event.MouseEvent evt) {
        if(Char3.getText().equals(""))
        {
            chosenId = -1;
            
            return;
        }
        
        chosenId = 2;
        
        setupPlayerInfo();
    }
	
    private void Char4MousePressed(java.awt.event.MouseEvent evt) {
        if(Char4.getText().equals(""))
        {
            chosenId = -1;
            
            return;
        }
        
        chosenId = 3;
        
        setupPlayerInfo();
    }

    private void Char5MousePressed(java.awt.event.MouseEvent evt) {
        if(Char5.getText().equals(""))
        {
            chosenId = -1;
            
            return;
        }
            
            chosenId = 4;
        
        setupPlayerInfo();
    }

    private void Char6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Char6MousePressed
        if(Char6.getText().equals(""))
        {
            chosenId = -1;
            
            return;
        }
        
        chosenId = 5;
        
        setupPlayerInfo();
    }//GEN-LAST:event_Char6MousePressed

    private void Char7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Char7MousePressed
        if(Char7.getText().equals(""))
        {
            chosenId = -1;
            
            return;
        }
        
        chosenId = 6;
        
        setupPlayerInfo();
    }//GEN-LAST:event_Char7MousePressed

    private void Char8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Char8MousePressed
        if(Char8.getText().equals(""))
        {
            chosenId = -1;
            
            return;
        }
        
        chosenId = 7;
        
        setupPlayerInfo();
    }//GEN-LAST:event_Char8MousePressed
    
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
            java.util.logging.Logger.getLogger(LoadGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoadGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoadGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoadGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoadGameFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BG;
    private javax.swing.JButton BackButton;
    private javax.swing.JButton Char1;
    private javax.swing.JButton Char2;
    private javax.swing.JButton Char3;
    private javax.swing.JButton Char4;
    private javax.swing.JButton Char5;
    private javax.swing.JButton Char6;
    private javax.swing.JButton Char7;
    private javax.swing.JButton Char8;
    private javax.swing.JLabel agiLabel;
    private javax.swing.JLabel charAvatar;
    private javax.swing.JLabel constLabel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JProgressBar expBar;
    private javax.swing.JLabel goldLabel;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JButton loadButton;
    private javax.swing.JLabel name1;
    private javax.swing.JLabel playerGoldLabel;
    private javax.swing.JLabel playerNameLabel;
    private javax.swing.JLabel strLabel;
    // End of variables declaration//GEN-END:variables
}
