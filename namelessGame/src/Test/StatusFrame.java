package Test;

import java.util.Map;
import namelessgame.Gameplay.Game;
import namelessgame.Gameplay.Item;
import namelessgame.Gameplay.Player;

/**
 *
 * @author Henrique Barcia Lang
 */
public class StatusFrame extends javax.swing.JFrame {
    private int statusPoints;
    private int str;
    private int agi;
    private int con;
    
    /**
     * Creates new form StatusFrame
     */
    public StatusFrame() {
        Player player = Game.getPlayer();
        
        initComponents();
        
        statusPoints = player.getStatusPoints();
        str = player.getStr();
        agi = player.getAgi();
        con = player.getCon();
        
        playerName.setText(player.getName());
        playerGold.setText(Long.toString(player.getGold()));
        playerStr.setText(Integer.toString(str));
        playerAgi.setText(Integer.toString(agi));
        playerConst.setText(Integer.toString(con));
        playerLevel.setText(Integer.toString(player.getLevel()));
        playerExp.setStringPainted(true);
        playerExp.setValue((player.getExp() / player.getExpNeededToLevelUp()) * 100);       
        playerExp.setToolTipText(player.getExp() + " / " + player.getExpNeededToLevelUp());
        playerPoints.setText(Integer.toString(statusPoints));
        
        // TODO set player avatar
        
        String path;
        Map<Integer, Item> playerEquip = player.getEquip();
        
        path = playerEquip.get(Game.HEAD) != null ? (playerEquip.get(Game.HEAD)).getIcon() : "/namelessgame/img/slots/head.png";       
        playerHead.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));
        
        path = playerEquip.get(Game.BODY) != null ? (playerEquip.get(Game.BODY)).getIcon() : "/namelessgame/img/slots/body.png";
        playerBody.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));  
        
        path = playerEquip.get(Game.WEAPON) != null ? (playerEquip.get(Game.WEAPON)).getIcon() : "/namelessgame/img/slots/right-hand.png";
        playerWeapon.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));  
        
        path = playerEquip.get(Game.SHIELD) != null ? (playerEquip.get(Game.SHIELD)).getIcon() : "/namelessgame/img/slots/left-hand.png";
        playerShield.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));  
        
        path = playerEquip.get(Game.LEGS) != null ? (playerEquip.get(Game.LEGS)).getIcon() : "/namelessgame/img/slots/legs.png";
        playerLegs.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));  
        
        path = playerEquip.get(Game.BOOTS) != null ? (playerEquip.get(Game.BOOTS)).getIcon() : "/namelessgame/img/slots/feet.png";
        playerBoots.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        goldLabel = new javax.swing.JLabel();
        strLabel = new javax.swing.JLabel();
        agiLabel = new javax.swing.JLabel();
        constLabel = new javax.swing.JLabel();
        playerAgi = new javax.swing.JLabel();
        playerName = new javax.swing.JLabel();
        playerGold = new javax.swing.JLabel();
        playerStr = new javax.swing.JLabel();
        playerConst = new javax.swing.JLabel();
        levelLabel = new javax.swing.JLabel();
        playerLevel = new javax.swing.JLabel();
        playerExp = new javax.swing.JProgressBar();
        playerAvatar = new javax.swing.JLabel();
        playerHead = new javax.swing.JLabel();
        playerBody = new javax.swing.JLabel();
        playerWeapon = new javax.swing.JLabel();
        playerShield = new javax.swing.JLabel();
        playerLegs = new javax.swing.JLabel();
        playerBoots = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        addConstButton = new javax.swing.JButton();
        addStrButton = new javax.swing.JButton();
        addAgiButton = new javax.swing.JButton();
        pointsLabel = new javax.swing.JLabel();
        playerPoints = new javax.swing.JLabel();
        confirmButton = new javax.swing.JButton();
        infoLabel = new javax.swing.JLabel();
        backgroundFrame = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        getContentPane().setLayout(null);

        nameLabel.setText("Name:");
        getContentPane().add(nameLabel);
        nameLabel.setBounds(10, 90, 60, 14);

        goldLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/gold.png"))); // NOI18N
        goldLabel.setText("Gold:");
        getContentPane().add(goldLabel);
        goldLabel.setBounds(48, 140, 70, 32);

        strLabel.setText("Strength:");
        getContentPane().add(strLabel);
        strLabel.setBounds(90, 190, 60, 14);

        agiLabel.setText("Agility:");
        getContentPane().add(agiLabel);
        agiLabel.setBounds(90, 220, 33, 14);

        constLabel.setText("Constitution:");
        getContentPane().add(constLabel);
        constLabel.setBounds(90, 250, 80, 14);
        getContentPane().add(playerAgi);
        playerAgi.setBounds(140, 220, 30, 20);
        getContentPane().add(playerName);
        playerName.setBounds(80, 90, 150, 20);

        playerGold.setForeground(new java.awt.Color(255, 255, 0));
        getContentPane().add(playerGold);
        playerGold.setBounds(130, 140, 60, 30);
        getContentPane().add(playerStr);
        playerStr.setBounds(150, 190, 30, 20);
        getContentPane().add(playerConst);
        playerConst.setBounds(170, 250, 30, 20);

        levelLabel.setText("Lv.:");
        getContentPane().add(levelLabel);
        levelLabel.setBounds(240, 90, 36, 14);
        getContentPane().add(playerLevel);
        playerLevel.setBounds(270, 90, 20, 20);

        playerExp.setForeground(new java.awt.Color(204, 0, 255));
        getContentPane().add(playerExp);
        playerExp.setBounds(350, 80, 210, 30);

        playerAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/kirito.gif"))); // NOI18N
        getContentPane().add(playerAvatar);
        playerAvatar.setBounds(330, 250, 240, 300);

        playerHead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/slots/head.png"))); // NOI18N
        getContentPane().add(playerHead);
        playerHead.setBounds(750, 110, 40, 40);

        playerBody.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/slots/body.png"))); // NOI18N
        getContentPane().add(playerBody);
        playerBody.setBounds(750, 150, 40, 50);

        playerWeapon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/slots/left-hand.png"))); // NOI18N
        getContentPane().add(playerWeapon);
        playerWeapon.setBounds(700, 160, 50, 30);

        playerShield.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/slots/right-hand.png"))); // NOI18N
        getContentPane().add(playerShield);
        playerShield.setBounds(800, 160, 40, 30);

        playerLegs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/slots/legs.png"))); // NOI18N
        getContentPane().add(playerLegs);
        playerLegs.setBounds(750, 200, 40, 40);

        playerBoots.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/slots/feet.png"))); // NOI18N
        getContentPane().add(playerBoots);
        playerBoots.setBounds(750, 250, 50, 30);

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton);
        backButton.setBounds(780, 530, 55, 23);

        addConstButton.setText("+");
        addConstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addConstButtonActionPerformed(evt);
            }
        });
        getContentPane().add(addConstButton);
        addConstButton.setBounds(230, 240, 37, 30);

        addStrButton.setText("+");
        addStrButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStrButtonActionPerformed(evt);
            }
        });
        getContentPane().add(addStrButton);
        addStrButton.setBounds(230, 180, 41, 30);

        addAgiButton.setText("+");
        addAgiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAgiButtonActionPerformed(evt);
            }
        });
        getContentPane().add(addAgiButton);
        addAgiButton.setBounds(230, 210, 37, 30);

        pointsLabel.setText("Points:");
        getContentPane().add(pointsLabel);
        pointsLabel.setBounds(170, 310, 41, 14);
        getContentPane().add(playerPoints);
        playerPoints.setBounds(220, 310, 30, 20);

        confirmButton.setText("Confirm");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });
        getContentPane().add(confirmButton);
        confirmButton.setBounds(190, 360, 69, 23);

        infoLabel.setBackground(new java.awt.Color(0, 0, 0));
        infoLabel.setFont(new java.awt.Font("OscineW04-Light", 0, 48)); // NOI18N
        infoLabel.setForeground(new java.awt.Color(51, 51, 51));
        infoLabel.setText("Your status");
        getContentPane().add(infoLabel);
        infoLabel.setBounds(10, 10, 280, 70);

        backgroundFrame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/PATTERN-BRANCO.png"))); // NOI18N
        getContentPane().add(backgroundFrame);
        backgroundFrame.setBounds(0, 0, 894, 592);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.dispose();
        
        GameFrame gameBack = new GameFrame();
        gameBack.setVisible(true);   
    }//GEN-LAST:event_backButtonActionPerformed

    private void addStrButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStrButtonActionPerformed
        if(statusPoints <= 0)
        {
            Game.sendErrorMessage("You do not have points to distribute.");
            
            return;
        }
        
        str++;
        statusPoints--;
        
        playerStr.setText(Integer.toString(str));
        playerPoints.setText(Integer.toString(statusPoints));
    }//GEN-LAST:event_addStrButtonActionPerformed

    private void addAgiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAgiButtonActionPerformed
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

    private void addConstButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addConstButtonActionPerformed
        if(statusPoints <= 0)
        {
            Game.sendErrorMessage("You do not have points to distribute.");
            
            return;
        }
        
        con++;
        statusPoints--;
        
        playerConst.setText(Integer.toString(con));
        playerPoints.setText(Integer.toString(statusPoints));
    }//GEN-LAST:event_addConstButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        Player player = Game.getPlayer();
        
        player.setStr(player.getBaseStr() + (str - player.getStr()));
        player.setAgi(player.getBaseAgi() + (str - player.getAgi()));
        player.setCon(player.getBaseCon() + (str - player.getCon()));
        player.setStatusPoints(statusPoints);
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
    private javax.swing.JLabel goldLabel;
    private javax.swing.JLabel infoLabel;
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
    private javax.swing.JLabel playerLegs;
    private javax.swing.JLabel playerLevel;
    private javax.swing.JLabel playerName;
    private javax.swing.JLabel playerPoints;
    private javax.swing.JLabel playerShield;
    private javax.swing.JLabel playerStr;
    private javax.swing.JLabel playerWeapon;
    private javax.swing.JLabel pointsLabel;
    private javax.swing.JLabel strLabel;
    // End of variables declaration//GEN-END:variables
}
