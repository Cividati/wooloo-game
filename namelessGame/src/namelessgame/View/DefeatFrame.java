/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import namelessgame.Gameplay.Game;
import namelessgame.Gameplay.Item;
import namelessgame.Gameplay.Player;

/** 
 * Classe que cria o frame de derrota em uma batalha.
 * @author Henrique Barcia Lang
 */
public class DefeatFrame extends javax.swing.JFrame {

    /**
     * Creates new form DefeatFramer
     */
    
    private Player player = Game.getPlayer();
    
    private List<Item> inventory = player.getInventory();
    private List<Item> loot = Game.getLoot();
    
    private javax.swing.JPanel inventoryPanel = new javax.swing.JPanel();
    private javax.swing.JPanel lootPanel = new javax.swing.JPanel();
    private DataFlavor dataFlavor = new DataFlavor(Item.class,
                    Item.class.getSimpleName());
    
    public DefeatFrame() {
        initComponents();
        
        player.setExp((int) (player.getExp() * 0.9));
        
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
    
    /**
     * Quando um item stackable é movido para o inventário após lógica em ItemSliderFrame.java
     * @param fromContainer List_Item - container onde o item estava armazenado
     * @param toContainer List_Item - container onde o item será armazenado
     * @param item Item - item movido
     * @param toItem Item - caso haja, é o item no qual aquele movido foi jogado em cima
     * @param count int - quantidade do item movida
     * @throws CloneNotSupportedException - exceção pode ser lançada ao clonar item
     */
    public void inventorySliderAction(List<Item> fromContainer, List<Item> toContainer, Item item, Item toItem, int count) throws CloneNotSupportedException
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
    
    /**
     * Atualiza o front-end do inventário do jogador
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
            
            path = item == null ? "/namelessgame/img/slots/back.png" : item.getIcon();
            
            itemLabel.setItem(item);          
            itemLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));
            
            if(item != null)
                itemLabel.setToolTipText(item.getDescr(player));
            
            inventoryPanel.add(itemLabel);
            
            new MyDropTargetListImp(itemLabel, inventoryPanel);
        }
  
    }
    
    /**
     * Atualiza o front-end do loot acumulado
     */
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
    
    /**
     * Atualiza front-end do container passado
     * @param container List_Item - container
     */
    public void updateContainer(List<Item> container)
    {
        if(container == inventory)
            updatePlayerInventory();
        else
            updatePlayerLoot();
    }
    
    /**
     * Pega o panel do componente passado
     * @param comp Component - componente
     * @return panel JPanel - panel do componente
     */
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
                            ItemSliderFrame amountSelector = new ItemSliderFrame(DefeatFrame.this, fromItemContainer, toItemContainer, movedItem, localItem, movedItem.getCount());
                           
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
        infoText3 = new javax.swing.JLabel();
        infoText1 = new javax.swing.JLabel();
        infoText2 = new javax.swing.JLabel();
        okayButton = new javax.swing.JButton();
        lootScrollPane = new javax.swing.JScrollPane();
        inventoryScrollPane = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("    Defeat");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/wooloo.png")).getImage());
        setPreferredSize(new java.awt.Dimension(800, 500));
        setResizable(false);
        getContentPane().setLayout(null);

        infoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Defeat", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OscineTrialW01-Regular", 0, 24), new java.awt.Color(255, 0, 0))); // NOI18N
        infoPanel.setLayout(null);

        infoText3.setFont(new java.awt.Font("OscineW04-Light", 1, 24)); // NOI18N
        infoText3.setText("sacrifice. Simply click on 'Okay' to resume your journeys in Wooloo!");
        infoPanel.add(infoText3);
        infoText3.setBounds(30, 120, 760, 30);

        infoText1.setFont(new java.awt.Font("OscineW04-Light", 1, 24)); // NOI18N
        infoText1.setText("Alas! Brave adventurer, you have met a sad fate. But do not despair, ");
        infoPanel.add(infoText1);
        infoText1.setBounds(30, 40, 760, 30);

        infoText2.setFont(new java.awt.Font("OscineW04-Light", 1, 24)); // NOI18N
        infoText2.setText("for the gods will bring you back into the world in exchange for a small");
        infoPanel.add(infoText2);
        infoText2.setBounds(30, 80, 760, 30);

        getContentPane().add(infoPanel);
        infoPanel.setBounds(0, 0, 800, 180);

        okayButton.setFont(new java.awt.Font("OscineTrialW01-Regular", 0, 18)); // NOI18N
        okayButton.setText("Okay");
        okayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okayButtonActionPerformed(evt);
            }
        });
        getContentPane().add(okayButton);
        okayButton.setBounds(350, 420, 108, 55);

        lootScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Loot", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OscineTrialW01-Regular", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        getContentPane().add(lootScrollPane);
        lootScrollPane.setBounds(80, 220, 250, 180);

        inventoryScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Inventory", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OscineTrialW01-Regular", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        getContentPane().add(inventoryScrollPane);
        inventoryScrollPane.setBounds(480, 220, 250, 180);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/namelessgame/img/bg.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 800, 500);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Ação realizada ao clicar em okay
     * @param evt 
     */
    private void okayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okayButtonActionPerformed
        Game.playNewAudio("click1", false);
        this.dispose();

        (new GameFrame()).setVisible(true);       
    }//GEN-LAST:event_okayButtonActionPerformed

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
            java.util.logging.Logger.getLogger(DefeatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DefeatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DefeatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DefeatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DefeatFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel infoPanel;
    private javax.swing.JLabel infoText1;
    private javax.swing.JLabel infoText2;
    private javax.swing.JLabel infoText3;
    private javax.swing.JScrollPane inventoryScrollPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane lootScrollPane;
    private javax.swing.JButton okayButton;
    // End of variables declaration//GEN-END:variables
}
