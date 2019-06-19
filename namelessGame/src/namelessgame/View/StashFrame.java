package namelessgame.View;

import namelessgame.Gameplay.ItemLabel;
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
import namelessgame.Exception.InventoryFullException;
import namelessgame.Exception.NotEnoughLevelException;
import namelessgame.Exception.StashFullException;
import namelessgame.Gameplay.Game;
import namelessgame.Gameplay.Item;
import namelessgame.Gameplay.Player;

/**
 * Classe que cria o frame do stash, inventário e equipamento do jogador e a inter-relação entre eles
 * @author Henrique Barcia Lang
 */
public class StashFrame extends javax.swing.JFrame {
    
    private Player player = Game.getPlayer();
    
    private List<Item> stash = player.getStash();
    private List<Item> inventory = player.getInventory(); 
    
    private javax.swing.JPanel stashPanel = new javax.swing.JPanel();
    private javax.swing.JPanel inventoryPanel = new javax.swing.JPanel();
    private DataFlavor dataFlavor = new DataFlavor(Item.class,
                    Item.class.getSimpleName());
    
    /**
     * Quando um item stackable é movido para o stash após lógica em ItemSliderFrame.java
     * @param fromContainer List_Item - container onde o item estava armazenado
     * @param toContainer List_Item - container onde o item será armazenado
     * @param item Item - item movido
     * @param toItem Item - caso haja, é o item no qual aquele movido foi jogado em cima
     * @param count int - quantidade do item movida
     * @throws CloneNotSupportedException - exceção pode ser lançada ao clonar item
     */
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
                                         (toContainer == stash ? "stash" : "inventory") + " is full.");

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
            catch(StashFullException e)
            {
                Game.sendErrorMessage("Your stash is full.");
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
    
    /**
     * Atualiza front-end do equipamento do jogador
     * @param slot int - slot de equipamento que será atualizado
     */
    public void updatePlayerEquipment(int slot)
    {
        if(slot < Game.HEAD || slot > Game.BOOTS)
            return;
        
        String defaultPath = "/namelessgame/img/slots/head.png";
        Item item = (player.getEquip()).get(slot);
        ItemLabel equipRef = playerHead;
        
        switch(slot)
        {
            case Game.HEAD:
                defaultPath = "/namelessgame/img/slots/head.png";
                equipRef = playerHead;
                
                break;
                
            case Game.BODY:
                defaultPath = "/namelessgame/img/slots/body.png";
                equipRef = playerBody;
                
                break;
                
            case Game.WEAPON:
                defaultPath = "/namelessgame/img/slots/left-hand.png";
                equipRef = playerWeapon;
                
                break;
                
            case Game.SHIELD:
                defaultPath = "/namelessgame/img/slots/right-hand.png";
                equipRef = playerShield;
                
                break;
                
            case Game.LEGS:
                defaultPath = "/namelessgame/img/slots/legs.png";
                equipRef = playerLegs;
                
                break;
                
            case Game.BOOTS:
                defaultPath = "/namelessgame/img/slots/feet.png";
                equipRef = playerBoots;
                
                break;
                
        }
        
        DragSource itemDragSource;
        
        String path = item != null ? item.getIcon() : defaultPath;
        equipRef.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));  
        equipRef.setItem(item);
        
        if(item != null)
            equipRef.setToolTipText(item.getDescr());
        
        itemDragSource = new DragSource();
        itemDragSource.createDefaultDragGestureRecognizer(equipRef,
                       DnDConstants.ACTION_COPY, new DragGestureListImp());

        new MyDropTargetListImp(equipRef, null);
    }
    
    /**
     * Atualiza front-end do inventário do jogador
     */
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
            DragSource itemDragSource;    
            
            path = item == null ? "/namelessgame/img/slots/back.png" : item.getIcon();
            
            itemLabel.setItem(item);          
            itemLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));
            
            if(item != null)
                itemLabel.setToolTipText(item.getDescr(player));
          
            inventoryPanel.add(itemLabel);
            
            itemDragSource = new DragSource();
            itemDragSource.createDefaultDragGestureRecognizer(itemLabel,
                           DnDConstants.ACTION_COPY, new DragGestureListImp());
            
            new MyDropTargetListImp(itemLabel, inventoryPanel);
        }
    }
    
    /**
     * Atualiza front-end do stash do jogador
     */
    public void updatePlayerStash()
    {
        String path;
        
        stashPanel.removeAll();
        stashPanel.revalidate();
        stashPanel.repaint();
        
        for(int i = 0; i < Game.MAX_STASH_SIZE; i++)
        {
            Item item = null;
            
            if(i < stash.size())
                item = stash.get(i);                    
            
            ItemLabel itemLabel = new ItemLabel();
            DragSource itemDragSource;                   
            
            path = item == null ? "/namelessgame/img/slots/back.png" : item.getIcon();
            
            itemLabel.setItem(item);          
            itemLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));
            
            if(item != null)
                itemLabel.setToolTipText(item.getDescr(player));
            
            stashPanel.add(itemLabel);
            
            itemDragSource = new DragSource();
            itemDragSource.createDefaultDragGestureRecognizer(itemLabel,
                           DnDConstants.ACTION_COPY, new DragGestureListImp());
            
            new MyDropTargetListImp(itemLabel, stashPanel);
        }
        
    }
    
    /**
     * Pega o panel do componente passado
     * @param comp Component - componente
     * @return panel JPanel - panel do componente
     */
    public JPanel getComponentPanel(Component comp)
    {
        JPanel panel = null;
        
        for(Component c : stashPanel.getComponents())
        {
            if(c == comp)
                return stashPanel;
        }
        for(Component c : inventoryPanel.getComponents())
        {
            if(c == comp)
                return inventoryPanel;
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
        if(container == stash)
            updatePlayerStash();
        else
            updatePlayerInventory();
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

                    if (toPanel == null) {
                        // dropped on equip
                        
                        int toSlot = -1;                                   
                        
                        // From equipment
                        if(fromPanel == null)
                        {
                            Game.sendErrorMessage("You can't move that here.");
                       
                            event.dropComplete(true);
                            return;
                        }
                        
                        // From stash/inventory

                        // check if moved item can be equiped here
                        // check item level
                        // can't: 
                        // error message
                        // can:
                        // remove moved item from origin
                        // remove item from equipment
                        // equip moved item
                        // add item to origin

                        if(label == playerHead)
                            toSlot = Game.HEAD;                          
                        else if(label == playerBody)
                            toSlot = Game.BODY;
                        else if(label == playerWeapon)
                            toSlot = Game.WEAPON;
                        else if(label == playerShield)
                            toSlot = Game.SHIELD;
                        else if(label == playerLegs)
                            toSlot = Game.LEGS;
                        else if(label == playerBoots)
                            toSlot = Game.BOOTS;

                        if(toSlot == -1 || movedItem.getSlot() != toSlot)
                        {
                            Game.sendErrorMessage("You can't move that here.");
                                                     
                            event.dropComplete(true);
                            return;
                        }
                        else if(movedItem.isStackable())
                        {
                            System.out.println("Support for stackable items on equipment is not yet implemented.");
                            
                            Game.sendErrorMessage("You can't move that here.");
                            
                            event.dropComplete(true);
                            return;
                        }
                                       
                        fromItemContainer = fromPanel == stashPanel ? stash : inventory;
                        
                        try
                        {
                            player.equipItem(movedItem, fromItemContainer);
                            
                            updatePlayerEquipment(toSlot);                          
                            updateContainer(fromItemContainer);
                        }
                        catch(NotEnoughLevelException e)
                        {
                            Game.sendErrorMessage("You don't have enough level to equip this item (" + movedItem.getMinLevel() + ").");

                        }

                    } else {
                        // dropped on stash/inventory
                        
                        if(fromPanel != null)
                            fromItemContainer = fromPanel == stashPanel ? stash : inventory;
                        
                        toItemContainer = toPanel == stashPanel ? stash : inventory;
                        
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
                            ItemSliderFrame amountSelector = new ItemSliderFrame(StashFrame.this, fromItemContainer, toItemContainer, movedItem, localItem, movedItem.getCount());
                           
                            amountSelector.setVisible(true);
                            setEnabled(false);
                        }
                        else
                        {
                            if(fromItemContainer != toItemContainer && player.isContainerFull(toItemContainer))
                            {
                                Game.sendErrorMessage("Your " +
                                                             (toItemContainer == stash ? "stash" : "inventory") + " is full.");

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
                            else
                            {
                                equip.remove(movedItem.getSlot());

                                updatePlayerEquipment(movedItem.getSlot());
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
                System.out.println("Some exception occurred when moving an item..." + e.getMessage());
                event.rejectDrop();
            }
        }
    }
    
    public StashFrame() {
        initComponents();
        
        for(int i = Game.HEAD; i <= Game.BOOTS; i++)
            updatePlayerEquipment(i);
        
        updatePlayerStash();
        updatePlayerInventory();
        
        stashPanel.setLayout(new GridLayout(Game.MAX_STASH_SIZE / Game.STASH_COLUMNS, Game.STASH_COLUMNS));
        stashPanel.setVisible(true);
        
        add(stashPanel);
        stashScrollPane.getViewport().add(stashPanel, null);
        
        inventoryPanel.setLayout(new GridLayout(Game.MAX_INVENTORY_SIZE / Game.INVENTORY_COLUMNS, Game.INVENTORY_COLUMNS));
        inventoryPanel.setVisible(true);
        
        add(inventoryPanel);
        inventoryScrollPane.getViewport().add(inventoryPanel, null);
 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        infoLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        playerHead = new namelessgame.Gameplay.ItemLabel();
        playerBody = new namelessgame.Gameplay.ItemLabel();
        playerWeapon = new namelessgame.Gameplay.ItemLabel();
        playerShield = new namelessgame.Gameplay.ItemLabel();
        playerLegs = new namelessgame.Gameplay.ItemLabel();
        playerBoots = new namelessgame.Gameplay.ItemLabel();
        inventoryScrollPane = new javax.swing.JScrollPane();
        stashScrollPane = new javax.swing.JScrollPane();
        backButton = new javax.swing.JButton();
        backgroundLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("    Stash");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/wooloo.png")).getImage());
        setResizable(false);
        getContentPane().setLayout(null);

        infoLabel.setBackground(new java.awt.Color(0, 0, 0));
        infoLabel.setFont(new java.awt.Font("OscineW04-Light", 0, 40)); // NOI18N
        infoLabel.setForeground(new java.awt.Color(255, 255, 255));
        infoLabel.setText("Stash");
        getContentPane().add(infoLabel);
        infoLabel.setBounds(350, 10, 100, 50);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Equipments", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OscineTrialW01-Regular", 0, 14))); // NOI18N
        jPanel1.setLayout(null);

        playerHead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/slots/head.png"))); // NOI18N
        jPanel1.add(playerHead);
        playerHead.setBounds(90, 30, 34, 30);

        playerBody.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/slots/body.png"))); // NOI18N
        jPanel1.add(playerBody);
        playerBody.setBounds(90, 70, 34, 34);

        playerWeapon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/slots/left-hand.png"))); // NOI18N
        jPanel1.add(playerWeapon);
        playerWeapon.setBounds(40, 70, 34, 40);

        playerShield.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/slots/right-hand.png"))); // NOI18N
        jPanel1.add(playerShield);
        playerShield.setBounds(140, 70, 34, 40);

        playerLegs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/slots/legs.png"))); // NOI18N
        jPanel1.add(playerLegs);
        playerLegs.setBounds(90, 110, 34, 30);

        playerBoots.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/slots/feet.png"))); // NOI18N
        jPanel1.add(playerBoots);
        playerBoots.setBounds(90, 150, 34, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(290, 80, 200, 200);

        inventoryScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Inventory", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OscineTrialW01-Regular", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        getContentPane().add(inventoryScrollPane);
        inventoryScrollPane.setBounds(500, 300, 250, 180);

        stashScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Stash", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OscineTrialW01-Regular", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        getContentPane().add(stashScrollPane);
        stashScrollPane.setBounds(30, 300, 250, 180);

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton);
        backButton.setBounds(360, 430, 70, 50);

        backgroundLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/bg.jpg"))); // NOI18N
        getContentPane().add(backgroundLabel);
        backgroundLabel.setBounds(0, 0, 790, 530);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.dispose();

        GameFrame gameBack = new GameFrame();
        gameBack.setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

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
            java.util.logging.Logger.getLogger(StashFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StashFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StashFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StashFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StashFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JScrollPane inventoryScrollPane;
    private javax.swing.JPanel jPanel1;
    private namelessgame.Gameplay.ItemLabel playerBody;
    private namelessgame.Gameplay.ItemLabel playerBoots;
    private namelessgame.Gameplay.ItemLabel playerHead;
    private namelessgame.Gameplay.ItemLabel playerLegs;
    private namelessgame.Gameplay.ItemLabel playerShield;
    private namelessgame.Gameplay.ItemLabel playerWeapon;
    private javax.swing.JScrollPane stashScrollPane;
    // End of variables declaration//GEN-END:variables
}
