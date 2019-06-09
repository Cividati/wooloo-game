package Test;

/**
 *
 * @author sin
 */
public class MenuFrame extends javax.swing.JFrame {

    /**
     * Creates new form menuFrame
     */
    public MenuFrame() {
        initComponents();
        this.setSize(1280, 720);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        newGameButton = new javax.swing.JButton();
        loadGameButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("OscineW04-Light", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("MENU");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(580, 20, 160, 60);

        exitButton.setFont(new java.awt.Font("OscineW04-Light", 0, 36)); // NOI18N
        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(exitButton);
        exitButton.setBounds(130, 510, 300, 100);

        newGameButton.setFont(new java.awt.Font("OscineW04-Light", 0, 36)); // NOI18N
        newGameButton.setText("New Game");
        newGameButton.setMinimumSize(new java.awt.Dimension(300, 100));
        newGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameButtonActionPerformed(evt);
            }
        });
        getContentPane().add(newGameButton);
        newGameButton.setBounds(130, 210, 300, 100);

        loadGameButton.setFont(new java.awt.Font("OscineW04-Light", 0, 36)); // NOI18N
        loadGameButton.setText("Load Game");
        loadGameButton.setMinimumSize(new java.awt.Dimension(300, 100));
        loadGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadGameButtonActionPerformed(evt);
            }
        });
        getContentPane().add(loadGameButton);
        loadGameButton.setBounds(130, 360, 300, 100);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/PATTERN-BRANCO.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 1270, 740);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void newGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameButtonActionPerformed
        this.dispose();
        
        NewGameFrame newG = new NewGameFrame();
        newG.setVisible(true);
    }//GEN-LAST:event_newGameButtonActionPerformed

    private void loadGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadGameButtonActionPerformed
        this.dispose();
        
        LoadGameFrame loadG = new LoadGameFrame();
        loadG.setVisible(true);
        loadG.setSize(1280, 720);
    }//GEN-LAST:event_loadGameButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton loadGameButton;
    private javax.swing.JButton newGameButton;
    // End of variables declaration//GEN-END:variables
}
