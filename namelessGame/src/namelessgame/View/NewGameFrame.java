package namelessgame.View;

import java.util.HashMap;
import namelessgame.Database.PlayerDAO;
import namelessgame.Gameplay.Game;
import namelessgame.Gameplay.Player;

/**
 *
 * @author sin
 */
public class NewGameFrame extends javax.swing.JFrame {

    /**
     * Creates new form newGameFrame
     */
    public NewGameFrame() {
        initComponents();
        
        charAvatar.setVisible(false);
    }
    
    private char sex = 'U';
    private int nextIndexMale = 0;
    private int nextIndexFemale = 0;
    
    String[] maleImages = {"kirito", "klein","Naofumi"};
    String[] femaleImages = {"Silica","Minna","asuna"};
    
    String chosenImg;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MaleButton = new javax.swing.JButton();
        FemButton = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();
        CreateCharButton = new javax.swing.JButton();
        nameField = new javax.swing.JTextField();
        name = new javax.swing.JLabel();
        charAvatar = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("    New character");
        setBackground(new java.awt.Color(204, 204, 204));
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/wooloo.png")).getImage());
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(null);

        MaleButton.setFont(new java.awt.Font("OscineW04-Light", 0, 18)); // NOI18N
        MaleButton.setText("Male");
        MaleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaleButtonActionPerformed(evt);
            }
        });
        getContentPane().add(MaleButton);
        MaleButton.setBounds(440, 460, 100, 50);

        FemButton.setFont(new java.awt.Font("OscineW04-Light", 0, 18)); // NOI18N
        FemButton.setText("Female");
        FemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FemButtonActionPerformed(evt);
            }
        });
        getContentPane().add(FemButton);
        FemButton.setBounds(730, 460, 100, 50);

        BackButton.setFont(new java.awt.Font("OscineW04-Light", 0, 24)); // NOI18N
        BackButton.setText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        getContentPane().add(BackButton);
        BackButton.setBounds(1090, 640, 130, 50);

        CreateCharButton.setFont(new java.awt.Font("OscineW04-Light", 0, 36)); // NOI18N
        CreateCharButton.setText("Create");
        CreateCharButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateCharButtonActionPerformed(evt);
            }
        });
        getContentPane().add(CreateCharButton);
        CreateCharButton.setBounds(540, 620, 180, 70);
        CreateCharButton.getAccessibleContext().setAccessibleDescription("");

        getContentPane().add(nameField);
        nameField.setBounds(500, 570, 270, 30);

        name.setFont(new java.awt.Font("OscineW04-Light", 0, 24)); // NOI18N
        name.setForeground(new java.awt.Color(255, 255, 255));
        name.setText("Nickname");
        getContentPane().add(name);
        name.setBounds(580, 530, 110, 40);

        charAvatar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        charAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/avatars/Naofumi.png"))); // NOI18N
        charAvatar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(charAvatar);
        charAvatar.setBounds(480, 40, 300, 400);

        background.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/bg.jpg"))); // NOI18N
        background.setText("jLabel2");
        background.setMinimumSize(new java.awt.Dimension(1280, 720));
        background.setPreferredSize(new java.awt.Dimension(1280, 720));
        getContentPane().add(background);
        background.setBounds(0, 0, 1280, 720);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Ação realizada ao clicar no botão de sexo male
     * @param evt 
     */
    private void MaleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaleButtonActionPerformed
        Game.playNewAudio("click1", false);
        
        sex = 'M';
        
        if(nextIndexMale >= maleImages.length)
            nextIndexMale = 0;
        
        chosenImg = maleImages[nextIndexMale++];
        
        charAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/avatars/" + chosenImg + ".png")));    
        charAvatar.setVisible(true);
    }//GEN-LAST:event_MaleButtonActionPerformed

    /**
     * Ação realizada ao clicar no botão de sexo female
     * @param evt 
     */
    private void FemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FemButtonActionPerformed
        Game.playNewAudio("click1", false); 
        
        sex = 'F';
           
        if(femaleImages.length == 0)
            return;
        
        if(nextIndexFemale >= femaleImages.length)
            nextIndexFemale = 0;
        
        chosenImg = femaleImages[nextIndexFemale++];
        
        charAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/avatars/" + chosenImg + ".png")));    
        charAvatar.setVisible(true);
    }//GEN-LAST:event_FemButtonActionPerformed

    /**
     * Ação realizada ao clicar em back 
     * @param evt 
     */
    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        Game.playNewAudio("click1", false);
        
        this.dispose();

        MenuFrame menuBack = new MenuFrame();
        menuBack.setVisible(true);    
    }//GEN-LAST:event_BackButtonActionPerformed

    /**
     * Ação realizada ao clicar em create
     * @param evt 
     */
    private void CreateCharButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateCharButtonActionPerformed
        Game.playNewAudio("click1", false);
        
        String characterName = nameField.getText();
        
        if(characterName.length() < 1 || characterName.length() > 10)
        {
            Game.sendErrorMessage("Your nickname must contain at least 1 character and less than 10.");
            
            return;
        }
        else if(sex == 'U')
        {
            Game.sendErrorMessage("You must select a gender for your character.");
            return;
        }
        
        Player player = new Player(-1, characterName, chosenImg, sex, 1, 0, 0, 0, 5, 5, 5, new HashMap<>());

        (new PlayerDAO()).insertPlayer(player);
        Game.setPlayer(player);
        
        this.dispose();
        GameFrame gameBack = new GameFrame();
        gameBack.setVisible(true);
    }//GEN-LAST:event_CreateCharButtonActionPerformed

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
            java.util.logging.Logger.getLogger(NewGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new NewGameFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JButton CreateCharButton;
    private javax.swing.JButton FemButton;
    private javax.swing.JButton MaleButton;
    private javax.swing.JLabel background;
    private javax.swing.JLabel charAvatar;
    private javax.swing.JLabel name;
    private javax.swing.JTextField nameField;
    // End of variables declaration//GEN-END:variables
}
