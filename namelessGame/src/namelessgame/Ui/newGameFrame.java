package namelessgame.Ui;

import java.util.List;
import java.util.ArrayList;
import namelessgame.Game.Player;

/**
 *
 * @author sin
 */
public class newGameFrame extends javax.swing.JFrame {

    /**
     * Creates new form newGameFrame
     */
    public newGameFrame() {
        initComponents();
    }
    
    public static List<Player> playerList = new ArrayList<>();
    
    private char sex = 'M';
    private int nextIndexMale = 0;
    private int nextIndexFemale = 0;
    
    String[] maleImages = {"/img/kirito.png", "/img/Naofumi.png"};
    String[] femaleImages = {};
    

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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
        MaleButton.setBounds(460, 460, 100, 50);

        FemButton.setFont(new java.awt.Font("OscineW04-Light", 0, 18)); // NOI18N
        FemButton.setText("Female");
        FemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FemButtonActionPerformed(evt);
            }
        });
        getContentPane().add(FemButton);
        FemButton.setBounds(750, 460, 100, 50);

        BackButton.setFont(new java.awt.Font("OscineW04-Light", 0, 18)); // NOI18N
        BackButton.setText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        getContentPane().add(BackButton);
        BackButton.setBounds(1130, 660, 100, 30);

        CreateCharButton.setFont(new java.awt.Font("OscineW04-Light", 0, 36)); // NOI18N
        CreateCharButton.setText("Create");
        CreateCharButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateCharButtonActionPerformed(evt);
            }
        });
        getContentPane().add(CreateCharButton);
        CreateCharButton.setBounds(560, 620, 180, 70);

        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });
        getContentPane().add(nameField);
        nameField.setBounds(520, 570, 270, 30);

        name.setFont(new java.awt.Font("OscineW04-Light", 0, 24)); // NOI18N
        name.setForeground(new java.awt.Color(51, 51, 51));
        name.setText("Nickname");
        getContentPane().add(name);
        name.setBounds(600, 530, 110, 40);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/kirito.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(380, -160, 660, 620);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/PATTERN-BRANCO.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 1280, 750);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MaleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaleButtonActionPerformed
        sex = 'M';
        
        nextIndexMale++;
        
        if(nextIndexMale >= maleImages.length)
            nextIndexMale = 0;
        
        String chosenImg = maleImages[nextIndexMale];
        
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource(chosenImg)));      
    }//GEN-LAST:event_MaleButtonActionPerformed

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFieldActionPerformed

    private void FemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FemButtonActionPerformed
        sex = 'F';
        
        if(femaleImages.length == 0)
            return;
        
        nextIndexFemale++;
        
        if(nextIndexFemale >= femaleImages.length)
            nextIndexFemale = 0;
        
        String chosenImg = femaleImages[nextIndexFemale];
        
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource(chosenImg)));     
    }//GEN-LAST:event_FemButtonActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        this.dispose();
        
        menuFrame menuBack = new menuFrame();
        menuBack.setVisible(true);
        menuBack.setSize(1280, 720);
    }//GEN-LAST:event_BackButtonActionPerformed

    private void CreateCharButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateCharButtonActionPerformed
        String characterName = nameField.getText();
        
        if(characterName.length() < 1 || characterName.length() > 25)
        {
            javax.swing.JOptionPane.showMessageDialog(null, "Your nickname must contain at least 1 character and less than 25.", "Error", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
            return;
        }
        
        System.out.println("Character created successfully.");
        playerList.add(new Player(characterName, sex));
        
        this.dispose();
        menuFrame menuBack = new menuFrame();
        menuBack.setVisible(true);
        menuBack.setSize(1280, 720);
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
            java.util.logging.Logger.getLogger(newGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(newGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(newGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(newGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new newGameFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JButton CreateCharButton;
    private javax.swing.JButton FemButton;
    private javax.swing.JButton MaleButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel name;
    private javax.swing.JTextField nameField;
    // End of variables declaration//GEN-END:variables
}