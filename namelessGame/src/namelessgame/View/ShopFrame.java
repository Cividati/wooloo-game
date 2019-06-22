package namelessgame.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import namelessgame.Exception.StashFullException;
import namelessgame.Gameplay.Game;
import namelessgame.Gameplay.Item;
import namelessgame.Gameplay.Player;
import namelessgame.Gameplay.ShopItem;

/**
 *
 * @author Henrique Barcia Lang
 */
public class ShopFrame extends javax.swing.JFrame {

    /**
     * Creates new form ShopFrame
     */
    
    private Player player = Game.getPlayer();
    
    private Map<javax.swing.JButton, ShopItem> shopMap = new HashMap<>();
    private Map<javax.swing.JButton, Item> sellMap = new HashMap<>();
    private List<Item> inventory = player.getInventory();
    
    /**
     * Quando um item stackable é comprado após lógica em ItemSliderFrame.java
     * @param item ShopItem - item comprado
     * @param count int - quantidade do item selecionada
     */
    public void shopSliderAction(ShopItem item, int count)
    {
        long totalPrice = item.getPrice() * count;
        
        long pGold = player.getGold() - totalPrice;
        
        setEnabled(true);
            
        if(pGold < 0)
        {
            Game.sendErrorMessage("You don't have enough gold.");
            
            return;
        }
        
        try
        {
            item.setCount(count);
            player.addItemToStash(item);

            player.setGold(pGold);
            playerGold.setText(pGold + " G");
            Game.sendSuccessMessage("You bought " + count + "x " + item.getName() + "(s).");
        }
        catch(StashFullException e)
        {
            Game.sendErrorMessage("You don't have space in your stash.");
        }
        
    }
    
    /**
     * Quando um item stackable é vendido após lógica em ItemSliderFrame.java
     * @param item ShopItem - item vendido
     * @param count int - quantidade do item selecionada
     */
    public void sellSliderAction(Item item, int count)
    {
        long totalPrice = Game.getSell().get(item.getName()) * count;
        
        long pGold = player.getGold() + totalPrice;
        
        setEnabled(true);

        item.setCount(item.getCount() - count);
        
        if(item.getCount() <= 0)
            inventory.remove(item);

        player.setGold(pGold);
        playerGold.setText(pGold + " G");
        updateSellingItems();
        Game.sendSuccessMessage("You sold " + count + "x " + item.getName() + "(s).");
    }
    
    public ShopFrame() {
        initComponents();
        
        playerGold.setText(player.getGold() + " G");
    }
    
    /**
     * Ação realizada ao clicar em um item comprável
     * @param evt 
     */
    private void ShopItemActionPerformed(java.awt.event.ActionEvent evt) {                                               
        javax.swing.JButton itemButton = (javax.swing.JButton) evt.getSource();
        
        ShopItem item = shopMap.get(itemButton);
        String itemInfo = "";
        
        if(item == null)
            return;
        
        final javax.swing.ImageIcon icon = new javax.swing.ImageIcon(item.getIcon());
        
        itemInfo += item.getName() + " (lv. " + item.getMinLevel() + ")\n\n";
        
        if(item.getHeal() > 0)
            itemInfo += "Heals for " + item.getHeal() + " health.";
        else
            itemInfo += "Attack: " + item.getStr() + "\n"
                      + "Agility: " + item.getAgi() + "\n"
                      + "Constitution: " + item.getCon();
        
        itemInfo += "\n\n\tPrice per unit: " + item.getPrice() + "g";
        
        int decision = javax.swing.JOptionPane.showConfirmDialog(null, itemInfo, "Buy", javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE, icon);
        
        if(decision == 1)
            return;
        
        if(item.isStackable())
        {
            ItemSliderFrame amountSelector = new ItemSliderFrame(this, item, Game.MAX_STACKABLE_AMOUNT, true);
            
            setEnabled(false);
            amountSelector.setVisible(true);
        }
        else
        {
            long pGold = player.getGold() - item.getPrice();
            
            if(pGold < 0)
            {
                Game.sendErrorMessage("You don't have enough gold.");

                return;
            }

            try
            {
                player.addItemToStash(item);

                Game.sendSuccessMessage("You bought a(n) " + item.getName() + ".");
                player.setGold(pGold);
                playerGold.setText(pGold + " G");
            }
            catch(StashFullException e)
            {
                Game.sendErrorMessage("You don't have space in your stash.");
            }
        }

    }
    
    /**
     * Ação realizada ao clicar em um item vendível
     * @param evt 
     */
    private void SellItemActionPerformed(java.awt.event.ActionEvent evt) { 
        javax.swing.JButton itemButton = (javax.swing.JButton) evt.getSource();
        
        Item item = sellMap.get(itemButton);
        String itemInfo = "";
        
        if(item == null)
            return;
        
        final javax.swing.ImageIcon icon = new javax.swing.ImageIcon(item.getIcon());
        
        itemInfo += item.getName() + (item.isStackable() ? " - " + item.getCount() + "x unit(s)." : "") + "\n\n";
        
        if(item.getHeal() > 0)
            itemInfo += "Heals for " + item.getHeal() + " health.";
        else
            itemInfo += "Attack: " + item.getStr() + "\n"
                      + "Agility: " + item.getAgi() + "\n"
                      + "Constitution: " + item.getCon();
        
        itemInfo += "\n\n\tPrice per unit: " + Game.getSell().get(item.getName()) + "g";
        
        int decision = javax.swing.JOptionPane.showConfirmDialog(null, itemInfo, "Sell", javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE, icon);
        
        if(decision == 1)
            return;
        
        if(item.isStackable())
        {
            ItemSliderFrame amountSelector = new ItemSliderFrame(this, item, item.getCount());
            
            setEnabled(false);
            amountSelector.setVisible(true);
        }
        else
        {
            long pGold = player.getGold() + Game.getSell().get(item.getName());
            
            inventory.remove(item);

            Game.sendSuccessMessage("You sold a(n) " + item.getName() + ".");
            player.setGold(pGold);
            playerGold.setText(pGold + " G");       
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

        shopScrollPane = new javax.swing.JScrollPane();
        goldLabel = new javax.swing.JLabel();
        infoLabel = new javax.swing.JLabel();
        sellerLabel = new javax.swing.JLabel();
        playerGold = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        buyButton = new javax.swing.JButton();
        sellButton = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("    Shop");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/wooloo.png")).getImage());
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        getContentPane().setLayout(null);
        getContentPane().add(shopScrollPane);
        shopScrollPane.setBounds(100, 130, 650, 460);

        goldLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/icons/gold.png"))); // NOI18N
        getContentPane().add(goldLabel);
        goldLabel.setBounds(770, 130, 40, 30);

        infoLabel.setBackground(new java.awt.Color(0, 0, 0));
        infoLabel.setFont(new java.awt.Font("OscineW04-Light", 0, 48)); // NOI18N
        infoLabel.setForeground(new java.awt.Color(255, 255, 255));
        infoLabel.setText("Shop");
        getContentPane().add(infoLabel);
        infoLabel.setBounds(640, 20, 102, 70);

        sellerLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/erhard.png"))); // NOI18N
        getContentPane().add(sellerLabel);
        sellerLabel.setBounds(960, 30, 280, 570);

        playerGold.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 24)); // NOI18N
        playerGold.setForeground(new java.awt.Color(255, 255, 0));
        playerGold.setText("0 G");
        getContentPane().add(playerGold);
        playerGold.setBounds(810, 130, 80, 30);

        backButton.setFont(new java.awt.Font("OscineW04-Light", 0, 24)); // NOI18N
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton);
        backButton.setBounds(1040, 620, 130, 50);

        buyButton.setText("Buy");
        buyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyButtonActionPerformed(evt);
            }
        });
        getContentPane().add(buyButton);
        buyButton.setBounds(110, 60, 130, 50);

        sellButton.setText("Sell");
        sellButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellButtonActionPerformed(evt);
            }
        });
        getContentPane().add(sellButton);
        sellButton.setBounds(250, 60, 130, 50);

        background.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/bg.jpg"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 1280, 720);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Ação realizada ao clicar em back
     * @param evt 
     */
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        Game.playSound("click1");
        
        this.dispose();

        GameFrame gameBack = new GameFrame();
        gameBack.setVisible(true);  
    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * Ação realizada ao clicar em sell
     * @param evt 
     */
    private void sellButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellButtonActionPerformed
        Game.playSound("click1");
        updateSellingItems();
    }//GEN-LAST:event_sellButtonActionPerformed

    /**
     * Ação realizada ao clicar em buy
     * @param evt 
     */
    private void buyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyButtonActionPerformed
        Game.playSound("click1");
        updateBuyingItems();
    }//GEN-LAST:event_buyButtonActionPerformed

    /**
     * Atualiza o front-end dos itens compráveis
     */
    public void updateBuyingItems()
    {
        javax.swing.JPanel shopPanel = new javax.swing.JPanel();
        
        shopScrollPane.getViewport().removeAll();
        
        for (ShopItem item : Game.getShop()) {
            javax.swing.JButton itemButton = new javax.swing.JButton();
            
            itemButton.setIcon(new javax.swing.ImageIcon(getClass().getResource(item.getIcon())));
            itemButton.setToolTipText(item.getName() + " (" + item.getPrice() + "g)");
            
            itemButton.addActionListener(new java.awt.event.ActionListener() {           
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ShopItemActionPerformed(evt);
                }
            });

            shopMap.put(itemButton, item);

            shopPanel.add(itemButton);
        }
        
        shopPanel.setLayout(new java.awt.GridLayout(Game.getShop().size() / 4, 4));
        shopPanel.setSize(300, 300);
        shopPanel.setVisible(true);
        add(shopPanel);
        
        shopScrollPane.getViewport().add(shopPanel, null);
    }
    
    /**
     * Atualiza o front-end dos itens vendíveis
     */
    public void updateSellingItems()
    {
        javax.swing.JPanel sellPanel = new javax.swing.JPanel();
        
        shopScrollPane.getViewport().removeAll();
        
        for (Item item : inventory) {
            if(Game.getSell().get(item.getName()) == null)
                continue;
            
            javax.swing.JButton itemButton = new javax.swing.JButton();
            
            itemButton.setIcon(new javax.swing.ImageIcon(getClass().getResource(item.getIcon())));
            itemButton.setToolTipText(item.getName() + " (" + Game.getSell().get(item.getName()) + "g)");
            
            itemButton.addActionListener(new java.awt.event.ActionListener() {           
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    SellItemActionPerformed(evt);
                }
            });

            sellMap.put(itemButton, item);

            sellPanel.add(itemButton);
        }
        
        sellPanel.setLayout(new java.awt.GridLayout(Game.getSell().size() / 4, 4));
        sellPanel.setSize(300, 300);
        sellPanel.setVisible(true);
        add(sellPanel);
        
        shopScrollPane.getViewport().add(sellPanel, null);
    }
    
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
            java.util.logging.Logger.getLogger(ShopFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShopFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShopFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShopFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShopFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel background;
    private javax.swing.JButton buyButton;
    private javax.swing.JLabel goldLabel;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JLabel playerGold;
    private javax.swing.JButton sellButton;
    private javax.swing.JLabel sellerLabel;
    private javax.swing.JScrollPane shopScrollPane;
    // End of variables declaration//GEN-END:variables
}
