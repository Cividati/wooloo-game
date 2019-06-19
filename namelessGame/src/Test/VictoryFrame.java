/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.UIManager;
import namelessgame.Exception.InventoryFullException;
import namelessgame.Gameplay.Game;
import namelessgame.Gameplay.Item;
import namelessgame.Gameplay.Player;

/**
 *
 * @author Henrique Barcia Lang
 */
public class VictoryFrame extends javax.swing.JFrame {

    /**
     * Creates new form VictoryFrame
     */
    
    private int statusPoints;
    private int str;
    private int agi;
    private int con;
    private int maxHp;
    
    private Player player = Game.getPlayer();
    
    private List<Item> inventory = player.getInventory();
    private List<Item> loot = Game.getLoot();
    
    private javax.swing.JPanel inventoryPanel = new javax.swing.JPanel();
    private javax.swing.JPanel lootPanel = new javax.swing.JPanel();
    private DataFlavor dataFlavor = new DataFlavor(Item.class,
                    Item.class.getSimpleName());
    
    public VictoryFrame() {
        initComponents();
    }
    
    public VictoryFrame(int gold, int exp) {
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
        
        initComponents();
        
        statusPoints = player.getStatusPoints();
        str = player.getStr();
        agi = player.getAgi();
        con = player.getCon();
        maxHp = player.getMaxHealth();
        
        playerStr.setText(Integer.toString(str));
        playerAgi.setText(Integer.toString(agi));
        playerConst.setText(Integer.toString(con));
        playerHp.setText(Integer.toString(maxHp));
        playerPoints.setText(Integer.toString(statusPoints));
        playerLevel.setText(Integer.toString(player.getLevel()));
        playerExp.setStringPainted(true);
        playerExp.setValue((int) (((double) player.getExp() / player.getTotalExpToLevelUp()) * 100));  
        playerExp.setToolTipText(player.getExp() + " / " + player.getTotalExpToLevelUp());
        receivedGold.setText(gold + " G");
        receivedExp.setText("(+ " + exp + ")");
        
        updatePlayerInventory();
        updatePlayerLoot();
        
        inventoryPanel.setLayout(new GridLayout(Game.MAX_INVENTORY_SIZE / Game.INVENTORY_COLUMNS, Game.INVENTORY_COLUMNS));
        inventoryPanel.setVisible(true);
        
        add(inventoryPanel);
        inventoryScrollPane.getViewport().add(inventoryPanel, null);
        
        lootPanel.setLayout(new GridLayout(Game.MAX_INVENTORY_SIZE / Game.INVENTORY_COLUMNS, Game.INVENTORY_COLUMNS));
        lootPanel.setVisible(true);
        
        add(lootPanel);
        lootScrollPane.getViewport().add(lootPanel, null);
        
    }
    
    public void stashSliderAction(List<Item> fromContainer, List<Item> toContainer, Item item, Item toItem, int count) throws CloneNotSupportedException
    {
        setEnabled(true);
        
        if(fromContainer == null || toContainer == null)
        {
            System.out.println("Support for stackable items on equipment is not yet implemented...");
            
            return;
        }
        
        // * check empty space
        
        // Item -> moved item with initial count
        // toItem -> null if empty space
        // count -> chosen amount
        
        
        // toItem count = count
        // movedItem.setCount = initialCount - count;
        // if movedItem.getCount == 0:
            // delete movedItem
        // toItem -> if null:
            // add new item to toContainer with 
        
        if(fromContainer != toContainer && toItem != null && item.getId() != toItem.getId() && player.isContainerFull(toContainer))
        {
            Game.sendErrorMessage("Your " +
                                         (toContainer == inventory ? "inventory" : "loot container") + " is full.");

            return;
        }
        
        int initialMovedItemCount = item.getCount();
        int initialToItemCount = toItem == null ? 0 : toItem.getCount();
        
        int newMovedItemCount = initialMovedItemCount - count;
        int newToItemCount = initialToItemCount + count;
        
        if(toItem == null)
        {
            toItem = (Item) item.clone();
            
            try
            {
                player.addItemToContainer(toItem, toContainer);
            }
            catch(InventoryFullException e)
            {
                Game.sendErrorMessage("Your inventory is full.");
            }
            
        }
        
        item.setCount(newMovedItemCount);
        toItem.setCount(newToItemCount);
        
        if(newToItemCount > Game.MAX_STACKABLE_AMOUNT)
        {
            // moved item count = 99
            // to item count = 99
            
            // moved 99
            
            // moved item count = 0
            // to item count = 198
            
            // fix: moved item count += to item count - max count
            //      to item count = max count
            
            item.setCount(newMovedItemCount + (newToItemCount - Game.MAX_STACKABLE_AMOUNT));
            toItem.setCount(Game.MAX_STACKABLE_AMOUNT);
        }
        
        if(item.getCount() == 0)
            fromContainer.remove(item);
        
        if(fromContainer != toContainer)
            updateContainer(fromContainer);
        
        updateContainer(toContainer);

    }
    
    public void updatePlayerInventory()
    {
        String path;
        
        inventoryPanel.removeAll();
        inventoryPanel.revalidate();
        inventoryPanel.repaint();
        
        for(int i = 0; i < Game.MAX_INVENTORY_SIZE; i++)
        {
            Item item = null;
            
            if(i < inventory.size())
                item = inventory.get(i);                    
            
            ItemLabel itemLabel = new ItemLabel();                 
            
            path = item == null ? "/namelessgame/img/slots/back.png" : item.getIcon();
            
            itemLabel.setItem(item);          
            itemLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));
            
            if(item != null)
                itemLabel.setToolTipText(item.getDescr(player));
            
            inventoryPanel.add(itemLabel);
            
            new MyDropTargetListImp(itemLabel, inventoryPanel);
        }
  
    }
    
    public void updatePlayerLoot()
    {
        String path;
        
        lootPanel.removeAll();
        lootPanel.revalidate();
        lootPanel.repaint();
        
        for(int i = 0; i < Game.MAX_INVENTORY_SIZE; i++)
        {
            Item item = null;
            
            if(i < loot.size())
                item = loot.get(i);                    
            
            ItemLabel itemLabel = new ItemLabel();
            DragSource itemDragSource;                   
            
            path = item == null ? "/namelessgame/img/slots/back.png" : item.getIcon();
            
            itemLabel.setItem(item);   
            itemLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));
            
            if(item != null)
                itemLabel.setToolTipText(item.getDescr(player));
            
            lootPanel.add(itemLabel);
            
            itemDragSource = new DragSource();
            itemDragSource.createDefaultDragGestureRecognizer(itemLabel,
                           DnDConstants.ACTION_COPY, new DragGestureListImp());
        }
  
    }
    
     public JPanel getComponentPanel(Component comp)
    {
        JPanel panel = null;
        
        for(Component c : inventoryPanel.getComponents())
        {
            if(c == comp)
                return inventoryPanel;
        }
        for(Component c : lootPanel.getComponents())
        {
            if(c == comp)
                return lootPanel;
        }
        
        return panel;
    }
    
    class ItemPanel {
        private Item item;
        private JPanel panel;
        
        public ItemPanel(Item item, JPanel panel)
        {
            this.item = item;
            this.panel = panel;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public JPanel getPanel() {
            return panel;
        }

        public void setPanel(JPanel panel) {
            this.panel = panel;
        }
        
    }
    
    class TransferableItem implements Transferable {
        private ItemPanel item;

        public TransferableItem(ItemPanel item) {
            this.item = item;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[] { dataFlavor };
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor.equals(dataFlavor);
        }

        @Override
        public Object getTransferData(DataFlavor flavor)
                throws UnsupportedFlavorException, IOException {

            if (flavor.equals(dataFlavor))
                return item;
            else
                throw new UnsupportedFlavorException(flavor);
        }
    }
    
    class DragGestureListImp implements DragGestureListener {

        @Override
        public void dragGestureRecognized(DragGestureEvent event) {
            Cursor cursor = null;
            ItemLabel lblItem = (ItemLabel) event.getComponent();

            if (event.getDragAction() == DnDConstants.ACTION_COPY) {
                cursor = DragSource.DefaultCopyDrop;
            }
            
            Item item = lblItem.getItem();
            try
            {
                event.startDrag(cursor, new TransferableItem(new ItemPanel(item, getComponentPanel(lblItem))));
            }
            catch(java.awt.dnd.InvalidDnDOperationException e) {}
        }
    }
    
    public void updateContainer(List<Item> container)
    {
        if(container == inventory)
            updatePlayerInventory();
        else
            updatePlayerLoot();
    }
    
    class MyDropTargetListImp extends DropTargetAdapter implements
            DropTargetListener {

        private DropTarget dropTarget;
        private JPanel panel;
        private ItemLabel label;

        public MyDropTargetListImp(ItemLabel label, JPanel panel) {
            this.panel = panel;
            this.label = label;

            dropTarget = new DropTarget(label, DnDConstants.ACTION_COPY, this,
                    true, null);
        }

        @Override
        public void drop(DropTargetDropEvent event) {
            try {               
                if (event.isDataFlavorSupported(dataFlavor)) {
                    event.acceptDrop(DnDConstants.ACTION_COPY);
                    Transferable tr = event.getTransferable();
                    ItemPanel itemPanel = (ItemPanel) tr.getTransferData(dataFlavor);

                    // Dragged item
                    Item movedItem = itemPanel.getItem();

                    // Local item (item on drop location)
                    Item localItem = this.label.getItem();

                    // Clone for transfering
                    Item movedItemClone = null;           

                    JPanel fromPanel = itemPanel.getPanel();
                    JPanel toPanel = this.panel;
                    ItemLabel toLabel = this.label;

                    List<Item> fromItemContainer = null;               
                    List<Item> toItemContainer = null;    

                    Map<Integer, Item> equip = player.getEquip();

                    if(toPanel == inventoryPanel)
                    {
                        // dropped on inventory
                        
                        if(fromPanel != null)
                            fromItemContainer = fromPanel == inventoryPanel ? inventory : loot;
                        
                        toItemContainer = toPanel == inventoryPanel ? inventory : loot;
                        
                        // check if is empty space or not (empty space -> local item == null):
                            // if isn't
                                // check if item is stackable and equal to moved item
                                    // if not:
                                        // add on container
                                            // check if can add or not (enough space)
                                    // if is:
                                        // show slider to add amount
                                            
                            // if is
                                // check if there's space
                                    // if not
                                        // show error message
                                    // if there is
                                        // check if its stackable
                                            // if is                                  
                                                // show slider
                                            // if not:
                                                // add on container
                                                // remove from old position
                                            
                        if(movedItem.isStackable())
                        {
                            ItemSliderFrame amountSelector = new ItemSliderFrame(VictoryFrame.this, fromItemContainer, toItemContainer, movedItem, localItem, movedItem.getCount());
                           
                            amountSelector.setVisible(true);
                            setEnabled(false);
                        }
                        else
                        {
                            if(fromItemContainer != toItemContainer && player.isContainerFull(toItemContainer))
                            {
                                Game.sendErrorMessage("Your " +
                                                             (toItemContainer == inventory ? "inventory" : "loot container") + " is full.");

                                event.dropComplete(true);
                                return;
                            }

                            // Adds to first index of the container (so the player can reorganize it easily)
                            toItemContainer.add(0, (Item) movedItem.clone());

                            if(fromItemContainer != null)
                            {
                                fromItemContainer.remove(movedItem);

                                updateContainer(fromItemContainer);
                            }
                            
                            if(fromItemContainer != toItemContainer)
                                updateContainer(toItemContainer);
	
                        }   
                        
                    }

                    event.dropComplete(true);
                    return;
                }
                
                event.rejectDrop();
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("Some exception occurred when moving an item on victory frame..." + e.getMessage());
                event.rejectDrop();
            }
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

        infoPanel = new javax.swing.JPanel();
        goldLabel = new javax.swing.JLabel();
        levelLabel = new javax.swing.JLabel();
        playerExp = new javax.swing.JProgressBar();
        receivedExp = new javax.swing.JLabel();
        playerLevel = new javax.swing.JLabel();
        receivedGold = new javax.swing.JLabel();
        pointsLabel = new javax.swing.JLabel();
        playerPoints = new javax.swing.JLabel();
        strLabel = new javax.swing.JLabel();
        playerStr = new javax.swing.JLabel();
        addStrButton = new javax.swing.JButton();
        addAgiButton = new javax.swing.JButton();
        playerAgi = new javax.swing.JLabel();
        agiLabel = new javax.swing.JLabel();
        constLabel = new javax.swing.JLabel();
        playerConst = new javax.swing.JLabel();
        addConstButton = new javax.swing.JButton();
        hpLabel = new javax.swing.JLabel();
        playerHp = new javax.swing.JLabel();
        confirmButton = new javax.swing.JButton();
        inventoryScrollPane = new javax.swing.JScrollPane();
        lootScrollPane = new javax.swing.JScrollPane();
        nextButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 500));
        setResizable(false);
        getContentPane().setLayout(null);

        infoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Victory", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 3, 24), new java.awt.Color(0, 255, 0))); // NOI18N
        infoPanel.setLayout(null);

        goldLabel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        goldLabel.setForeground(new java.awt.Color(255, 255, 0));
        goldLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/icons/gold.png"))); // NOI18N
        goldLabel.setText("+");
        infoPanel.add(goldLabel);
        goldLabel.setBounds(20, 50, 50, 50);

        levelLabel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        levelLabel.setForeground(new java.awt.Color(102, 102, 102));
        levelLabel.setText("Lv.:");
        infoPanel.add(levelLabel);
        levelLabel.setBounds(20, 110, 40, 30);

        playerExp.setForeground(new java.awt.Color(204, 0, 255));
        infoPanel.add(playerExp);
        playerExp.setBounds(90, 110, 210, 30);

        receivedExp.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        receivedExp.setForeground(new java.awt.Color(0, 0, 0));
        receivedExp.setText("(+ 0)");
        infoPanel.add(receivedExp);
        receivedExp.setBounds(310, 110, 90, 30);

        playerLevel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        playerLevel.setForeground(new java.awt.Color(0, 0, 0));
        playerLevel.setText("0");
        infoPanel.add(playerLevel);
        playerLevel.setBounds(60, 110, 30, 30);

        receivedGold.setFont(new java.awt.Font("OscineTrialW01-Regular", 1, 14)); // NOI18N
        receivedGold.setForeground(new java.awt.Color(255, 255, 0));
        receivedGold.setText("0 G");
        infoPanel.add(receivedGold);
        receivedGold.setBounds(70, 60, 70, 30);

        getContentPane().add(infoPanel);
        infoPanel.setBounds(0, 0, 510, 180);

        pointsLabel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        pointsLabel.setForeground(new java.awt.Color(102, 102, 102));
        pointsLabel.setText("Points:");
        getContentPane().add(pointsLabel);
        pointsLabel.setBounds(610, 10, 60, 20);

        playerPoints.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        playerPoints.setForeground(new java.awt.Color(51, 204, 0));
        playerPoints.setText("+0");
        getContentPane().add(playerPoints);
        playerPoints.setBounds(680, 10, 40, 20);

        strLabel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        strLabel.setForeground(new java.awt.Color(102, 102, 102));
        strLabel.setText("Strength:");
        getContentPane().add(strLabel);
        strLabel.setBounds(550, 60, 73, 20);

        playerStr.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        playerStr.setForeground(new java.awt.Color(0, 0, 0));
        playerStr.setText("0");
        getContentPane().add(playerStr);
        playerStr.setBounds(680, 60, 38, 20);

        addStrButton.setText("+");
        addStrButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStrButtonActionPerformed(evt);
            }
        });
        getContentPane().add(addStrButton);
        addStrButton.setBounds(740, 60, 40, 30);

        addAgiButton.setText("+");
        addAgiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAgiButtonActionPerformed(evt);
            }
        });
        getContentPane().add(addAgiButton);
        addAgiButton.setBounds(740, 100, 40, 30);

        playerAgi.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        playerAgi.setForeground(new java.awt.Color(0, 0, 0));
        playerAgi.setText("0");
        getContentPane().add(playerAgi);
        playerAgi.setBounds(680, 100, 40, 20);

        agiLabel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        agiLabel.setForeground(new java.awt.Color(102, 102, 102));
        agiLabel.setText("Agility:");
        getContentPane().add(agiLabel);
        agiLabel.setBounds(550, 100, 52, 20);

        constLabel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        constLabel.setForeground(new java.awt.Color(102, 102, 102));
        constLabel.setText("Constitution:");
        getContentPane().add(constLabel);
        constLabel.setBounds(550, 140, 100, 20);

        playerConst.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        playerConst.setForeground(new java.awt.Color(0, 0, 0));
        playerConst.setText("0");
        getContentPane().add(playerConst);
        playerConst.setBounds(680, 140, 39, 20);

        addConstButton.setText("+");
        addConstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addConstButtonActionPerformed(evt);
            }
        });
        getContentPane().add(addConstButton);
        addConstButton.setBounds(740, 140, 40, 30);

        hpLabel.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        hpLabel.setForeground(new java.awt.Color(102, 102, 102));
        hpLabel.setText("Max HP:");
        getContentPane().add(hpLabel);
        hpLabel.setBounds(550, 170, 68, 20);

        playerHp.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        playerHp.setForeground(new java.awt.Color(0, 0, 0));
        playerHp.setText("0");
        getContentPane().add(playerHp);
        playerHp.setBounds(680, 170, 39, 20);

        confirmButton.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        confirmButton.setText("Confirm");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });
        getContentPane().add(confirmButton);
        confirmButton.setBounds(620, 220, 108, 55);

        inventoryScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Inventory", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OscineTrialW01-Regular", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        getContentPane().add(inventoryScrollPane);
        inventoryScrollPane.setBounds(290, 250, 250, 180);

        lootScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Loot", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OscineTrialW01-Regular", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        getContentPane().add(lootScrollPane);
        lootScrollPane.setBounds(20, 250, 250, 180);

        nextButton.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        nextButton.setText("Next");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        getContentPane().add(nextButton);
        nextButton.setBounds(620, 410, 108, 55);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addStrButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStrButtonActionPerformed
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
        maxHp = Player.getMaxHealth(str, con);
        statusPoints--;

        playerConst.setText(Integer.toString(con));
        playerHp.setText(Integer.toString(maxHp));
        playerPoints.setText(Integer.toString(statusPoints));
    }//GEN-LAST:event_addConstButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        player.setStr(player.getBaseStr() + (str - player.getStr()));
        player.setAgi(player.getBaseAgi() + (agi - player.getAgi()));
        player.setCon(player.getBaseCon() + (con - player.getCon()));
        player.setStatusPoints(statusPoints);
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        this.dispose();
        
        Game.advanceDungeon();
    }//GEN-LAST:event_nextButtonActionPerformed

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
            java.util.logging.Logger.getLogger(VictoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VictoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VictoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VictoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VictoryFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAgiButton;
    private javax.swing.JButton addConstButton;
    private javax.swing.JButton addStrButton;
    private javax.swing.JLabel agiLabel;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLabel constLabel;
    private javax.swing.JLabel goldLabel;
    private javax.swing.JLabel hpLabel;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JScrollPane inventoryScrollPane;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JScrollPane lootScrollPane;
    private javax.swing.JButton nextButton;
    private javax.swing.JLabel playerAgi;
    private javax.swing.JLabel playerConst;
    private javax.swing.JProgressBar playerExp;
    private javax.swing.JLabel playerHp;
    private javax.swing.JLabel playerLevel;
    private javax.swing.JLabel playerPoints;
    private javax.swing.JLabel playerStr;
    private javax.swing.JLabel pointsLabel;
    private javax.swing.JLabel receivedExp;
    private javax.swing.JLabel receivedGold;
    private javax.swing.JLabel strLabel;
    // End of variables declaration//GEN-END:variables
}
