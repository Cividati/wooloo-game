package Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Collections;
import java.util.List;
import Test.MenuFrame;
import java.awt.Color;
import javax.swing.UIManager;
import namelessgame.Database.DungeonDAO;
import namelessgame.Database.PlayerDAO;
import namelessgame.Gameplay.Dungeon;
import namelessgame.Gameplay.Game;
import namelessgame.Gameplay.Player;

/**
 *
 * @author Henrique Barcia Lang
 */
public class GameFrame extends javax.swing.JFrame {

    /**
     * Creates new form GameFrame
     */
    public GameFrame() {
        UIManager.getLookAndFeelDefaults().put("nimbusOrange", (new Color(255, 0, 255)));
        
        Player player = Game.getPlayer();
        
        initComponents();
     
        playerName.setText(player.getName());
        playerHp.setText(Integer.toString(player.getMaxHealth()));
        playerLevel.setText(Integer.toString(player.getLevel()));
        
        playerExp.setStringPainted(true);
        playerExp.setValue((int) (((double) player.getExp() / player.getTotalExpToLevelUp()) * 100)); 
        playerExp.setToolTipText(player.getExp() + " / " + player.getTotalExpToLevelUp());
        playerAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource(player.getAvatar())));
        
        if(Game.getDungeons() == null)
        {
            List<Dungeon> dungeons = (new DungeonDAO()).loadDungeons();
            
            Collections.sort(dungeons);
            Game.setDungeons(dungeons);
        }
        
        if(Game.getShop().isEmpty())
            Game.fillShop();
 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playerPanel = new javax.swing.JPanel();
        playerHp = new javax.swing.JLabel();
        levelLabel = new javax.swing.JLabel();
        playerAvatar = new javax.swing.JLabel();
        playerExp = new javax.swing.JProgressBar();
        hpLabel = new javax.swing.JLabel();
        playerLevel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        playerName = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        statusLabel = new javax.swing.JLabel();
        shopLabel = new javax.swing.JLabel();
        stashButton = new javax.swing.JButton();
        shopButton = new javax.swing.JButton();
        dungeonLabel = new javax.swing.JLabel();
        saveLabel = new javax.swing.JLabel();
        statusButton = new javax.swing.JButton();
        dungeonButton = new javax.swing.JButton();
        stashLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        getContentPane().setLayout(null);

        playerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Player", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OscineTrialW01-Regular", 0, 18), new java.awt.Color(102, 102, 102))); // NOI18N
        playerPanel.setLayout(null);

        playerHp.setBackground(new java.awt.Color(255, 255, 255));
        playerHp.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 14)); // NOI18N
        playerHp.setForeground(new java.awt.Color(51, 51, 51));
        playerPanel.add(playerHp);
        playerHp.setBounds(280, 30, 50, 30);

        levelLabel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        levelLabel.setForeground(new java.awt.Color(102, 102, 102));
        levelLabel.setText("Lv.:");
        playerPanel.add(levelLabel);
        levelLabel.setBounds(30, 80, 40, 30);

        playerAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/erhard.png"))); // NOI18N
        playerPanel.add(playerAvatar);
        playerAvatar.setBounds(20, 130, 300, 400);

        playerExp.setForeground(new java.awt.Color(204, 0, 255));
        playerPanel.add(playerExp);
        playerExp.setBounds(110, 80, 210, 30);

        hpLabel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        hpLabel.setForeground(new java.awt.Color(102, 102, 102));
        hpLabel.setText("HP:");
        hpLabel.setPreferredSize(new java.awt.Dimension(1280, 720));
        playerPanel.add(hpLabel);
        hpLabel.setBounds(240, 30, 30, 30);

        playerLevel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 14)); // NOI18N
        playerLevel.setForeground(new java.awt.Color(51, 51, 51));
        playerLevel.setText("0");
        playerPanel.add(playerLevel);
        playerLevel.setBounds(70, 80, 40, 30);

        nameLabel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(102, 102, 102));
        nameLabel.setText("Name:");
        nameLabel.setPreferredSize(new java.awt.Dimension(1280, 720));
        playerPanel.add(nameLabel);
        nameLabel.setBounds(50, 30, 60, 30);

        playerName.setBackground(new java.awt.Color(255, 255, 255));
        playerName.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 14)); // NOI18N
        playerName.setForeground(new java.awt.Color(51, 51, 51));
        playerName.setText("test");
        playerPanel.add(playerName);
        playerName.setBounds(110, 30, 130, 30);

        getContentPane().add(playerPanel);
        playerPanel.setBounds(810, 30, 340, 560);

        backButton.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton);
        backButton.setBounds(1130, 610, 110, 60);

        statusLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/icons/iconPergaminho.png"))); // NOI18N
        getContentPane().add(statusLabel);
        statusLabel.setBounds(530, 450, 32, 32);

        shopLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/icons/gold.png"))); // NOI18N
        getContentPane().add(shopLabel);
        shopLabel.setBounds(280, 110, 32, 32);

        stashButton.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        stashButton.setText("Stash");
        stashButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stashButtonActionPerformed(evt);
            }
        });
        getContentPane().add(stashButton);
        stashButton.setBounds(490, 150, 100, 60);

        shopButton.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        shopButton.setText("Shop");
        shopButton.setPreferredSize(null);
        shopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shopButtonActionPerformed(evt);
            }
        });
        getContentPane().add(shopButton);
        shopButton.setBounds(240, 150, 110, 60);

        dungeonLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/icons/dungeon.png"))); // NOI18N
        getContentPane().add(dungeonLabel);
        dungeonLabel.setBounds(400, 290, 32, 32);

        saveLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/icons/save.png"))); // NOI18N
        getContentPane().add(saveLabel);
        saveLabel.setBounds(280, 450, 32, 32);

        statusButton.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        statusButton.setText("Status");
        statusButton.setPreferredSize(null);
        statusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusButtonActionPerformed(evt);
            }
        });
        getContentPane().add(statusButton);
        statusButton.setBounds(490, 490, 110, 60);

        dungeonButton.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        dungeonButton.setText("Dungeon");
        dungeonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dungeonButtonActionPerformed(evt);
            }
        });
        getContentPane().add(dungeonButton);
        dungeonButton.setBounds(360, 330, 110, 60);

        stashLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/icons/stash.png"))); // NOI18N
        getContentPane().add(stashLabel);
        stashLabel.setBounds(530, 110, 32, 32);

        saveButton.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        saveButton.setText("Save");
        saveButton.setPreferredSize(null);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        getContentPane().add(saveButton);
        saveButton.setBounds(240, 490, 100, 60);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/bg.jpg"))); // NOI18N
        jLabel1.setMinimumSize(new java.awt.Dimension(1280, 720));
        jLabel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1280, 720);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void shopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shopButtonActionPerformed
        this.dispose();
        
        ShopFrame newShop = new ShopFrame();
        newShop.setVisible(true);
    }//GEN-LAST:event_shopButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        (new PlayerDAO()).savePlayer(Game.getPlayer());
    }//GEN-LAST:event_saveButtonActionPerformed

    private void statusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusButtonActionPerformed
        this.dispose();
        
        StatusFrame newStatus = new StatusFrame();
        newStatus.setVisible(true);
    }//GEN-LAST:event_statusButtonActionPerformed

    private void stashButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stashButtonActionPerformed
        this.dispose();
        
        StashFrame newStash = new StashFrame();
        newStash.setVisible(true);
    }//GEN-LAST:event_stashButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.dispose();
        
        Game.setPlayer(null);
        
        MenuFrame newMenu = new MenuFrame();
        newMenu.setVisible(true);
        
    }//GEN-LAST:event_backButtonActionPerformed

    private void dungeonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dungeonButtonActionPerformed
        this.dispose();
        
        DungeonFrame newDungeon = new DungeonFrame();
        newDungeon.setVisible(true);
    }//GEN-LAST:event_dungeonButtonActionPerformed

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
                    
                    // change progress bar color
                    UIManager.getLookAndFeelDefaults().put("nimbusOrange", (new Color(255, 0, 255)));
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton dungeonButton;
    private javax.swing.JLabel dungeonLabel;
    private javax.swing.JLabel hpLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel playerAvatar;
    private javax.swing.JProgressBar playerExp;
    private javax.swing.JLabel playerHp;
    private javax.swing.JLabel playerLevel;
    private javax.swing.JLabel playerName;
    private javax.swing.JPanel playerPanel;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel saveLabel;
    private javax.swing.JButton shopButton;
    private javax.swing.JLabel shopLabel;
    private javax.swing.JButton stashButton;
    private javax.swing.JLabel stashLabel;
    private javax.swing.JButton statusButton;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables
}
