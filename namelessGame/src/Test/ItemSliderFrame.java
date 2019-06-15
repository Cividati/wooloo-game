package Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.JFrame;
import namelessgame.Gameplay.Item;
import namelessgame.Gameplay.ShopItem;

/**
 *
 * @author Henrique Barcia Lang
 */
public class ItemSliderFrame extends javax.swing.JFrame implements WindowListener {

    /**
     * Creates new form ItemSliderFrame
     */
    
    private Item item;
    private Item toItem;
    
    private int count = 1;
    private int countMax;    
    
    private javax.swing.JFrame originalFrame;
    private List<Item> fromContainer;
    private List<Item> toContainer;
    
    public void setItem(Item item)
    {
        this.item = item;
    }
    
    public void setCount(int count)
    {
        this.count = count;
    }
    
    public Item getItem()
    {
        return this.item;
    }
    
    public int getCount()
    {
        return this.count;
    }

    public int getCountMax() {
        return countMax;
    }

    public void setCountMax(int countMax) {
        this.countMax = countMax;
    }

    public JFrame getOriginalFrame() {
        return originalFrame;
    }

    public void setOriginalFrame(JFrame originalFrame) {
        this.originalFrame = originalFrame;
    }

    public List<Item> getFromContainer() {
        return fromContainer;
    }

    public void setFromContainer(List<Item> fromContainer) {
        this.fromContainer = fromContainer;
    }

    public List<Item> getToContainer() {
        return toContainer;
    }

    public void setToContainer(List<Item> toContainer) {
        this.toContainer = toContainer;
    }

    public Item getToItem() {
        return toItem;
    }

    public void setToItem(Item toItem) {
        this.toItem = toItem;
    }
    
    public ItemSliderFrame() {
        initComponents();
    }

    public ItemSliderFrame(javax.swing.JFrame originalFrame, Item item, int countMax) {
        initComponents();
        
        setOriginalFrame(originalFrame);
        setItem(item);
        setCountMax(countMax);
        
        iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(item.getIcon())));
        amountLabel.setText(Integer.toString(1));
    }
    
    public ItemSliderFrame(javax.swing.JFrame originalFrame, List<Item> fromContainer, List<Item> toContainer, Item item, Item toItem, int countMax) {
        initComponents();
        
        setOriginalFrame(originalFrame);
        setItem(item);
        setToItem(toItem);
        setCountMax(countMax);
        
        setFromContainer(fromContainer);
        setToContainer(toContainer);
        
        iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(item.getIcon())));
        amountLabel.setText(Integer.toString(1));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sliderInfo = new javax.swing.JLabel();
        iconLabel = new javax.swing.JLabel();
        amountLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        amountSlider = new javax.swing.JSlider();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(250, 120));
        setResizable(false);
        getContentPane().setLayout(null);

        sliderInfo.setText("Item amount");
        getContentPane().add(sliderInfo);
        sliderInfo.setBounds(90, 10, 80, 16);
        getContentPane().add(iconLabel);
        iconLabel.setBounds(10, 20, 50, 50);
        getContentPane().add(amountLabel);
        amountLabel.setBounds(200, 30, 40, 20);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        getContentPane().add(cancelButton);
        cancelButton.setBounds(130, 60, 80, 30);

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        getContentPane().add(okButton);
        okButton.setBounds(60, 60, 60, 30);

        amountSlider.setValue(1);
        amountSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                amountSliderStateChanged(evt);
            }
        });
        getContentPane().add(amountSlider);
        amountSlider.setBounds(60, 40, 120, 16);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void amountSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_amountSliderStateChanged
        int sliderValue = amountSlider.getValue();
        
        count = (sliderValue * countMax) / 100;
        amountLabel.setText(Integer.toString(count));
    }//GEN-LAST:event_amountSliderStateChanged

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        originalFrame.setEnabled(true);
        
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        originalFrame.setEnabled(true);
        this.dispose();
        
        if(originalFrame instanceof ShopFrame)
        {
            if(!(item instanceof ShopItem))
                System.out.println("Error when downcasting Item to ShopItem on slider...");
            else
                ((ShopFrame) originalFrame).shopSliderAction((ShopItem) item, count);
        }
        else if(originalFrame instanceof StashFrame)
            try {
                ((StashFrame) originalFrame).stashSliderAction(fromContainer, toContainer, item, toItem, count);
            } catch (CloneNotSupportedException ex) {
                System.out.println("Error when cloning item...");
            }
        else
            System.out.println("Slider used without being called by a configured frame...");
        
        
        
    }//GEN-LAST:event_okButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ItemSliderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ItemSliderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ItemSliderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ItemSliderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ItemSliderFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel amountLabel;
    private javax.swing.JSlider amountSlider;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel sliderInfo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        // clicked on X button (cancel operation -> set frame enabled)
        
        originalFrame.setEnabled(true);
        
        this.dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        originalFrame.setEnabled(true);
        
        this.dispose();
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
