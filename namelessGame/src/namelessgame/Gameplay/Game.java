package namelessgame.Gameplay;

import Test.BattleFrame;
import Test.GameFrame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import namelessgame.Database.ItemDAO;

public class Game {
    private static Player loggedPlayer;
    
    /** Player max level **/
    final public static int MAX_LEVEL = 100;
    
    /** Stash max size **/
    final public static int MAX_STASH_SIZE = 30;
    
    /** Inventory max size **/
    final public static int MAX_INVENTORY_SIZE = 12;
    
    /** Max units that a stack of item can have (if it is stackable) **/
    final public static int MAX_STACKABLE_AMOUNT = 100;
    
    /** Stash and inventory GridLayout information (used on StashFrame.java) **/
    final public static int STASH_COLUMNS = 5;
    final public static int INVENTORY_COLUMNS = 3;
    
    /** Equipment slots index **/
    final public static int INVENTORY = 0;
    final public static int HEAD = 1;
    final public static int BODY = 2;
    final public static int WEAPON = 3;
    final public static int SHIELD = 4;
    final public static int LEGS = 5;
    final public static int BOOTS = 6;
    
    private static List<Dungeon> dungeons = null;
    private static List<ShopItem> shop = new ArrayList<>();
    private static Map<String, Long> sell = new HashMap<>();
    private static List<Item> loot;
    
    private static Dungeon exploredDungeon;
    private static int roundNow;

    public static Player getPlayer() {
        return loggedPlayer;
    }

    public static void setPlayer(Player loggedPlayer) {
        Game.loggedPlayer = loggedPlayer;
    }
    
    public static void sendErrorMessage(String message)
    {
        javax.swing.JOptionPane.showMessageDialog(null, message, "Error", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void sendSuccessMessage(String message)
    {
        javax.swing.JOptionPane.showMessageDialog(null, message, "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    public static List<Dungeon> getDungeons() {
        return dungeons;
    }

    public static void setDungeons(List<Dungeon> dungeons) {
        Game.dungeons = dungeons;
    }

    public static List<ShopItem> getShop() {
        return shop;
    }

    public static void setShop(List<ShopItem> shop) {
        Game.shop = shop;
    }

    public static Map<String, Long> getSell() {
        return sell;
    }

    public static void setSell(Map<String, Long> sell) {
        Game.sell = sell;
    }
    
    public static void addItemToShop(String name, long price)
    {
        ShopItem newItem = (new ItemDAO()).loadShopItemByName(name, price); 
        
        if(newItem == null)
            return;
        
        shop.add(newItem);
    }
    
    public static void addItemToBuy(String name, long price)
    {
        sell.put(name, price);
    }
    
    // Add items to shop
    public static void fillShop()
    {
        // item_name, price
        addItemToShop("Heavy Armor", 0);
        addItemToShop("Light Armor", 0);
        addItemToShop("Medium Armor", 0);
        addItemToShop("Helmet", 0);
        addItemToShop("Shield+++", 100);
        addItemToShop("Shield", 0);
        addItemToShop("Shield+", 20);
        addItemToShop("Shield++", 60);
        addItemToShop("Spear", 0);
        addItemToShop("Sword", 0);
        addItemToShop("Crossbow", 0);
        addItemToShop("Bow", 0);
        addItemToShop("Dagger", 0);
        addItemToShop("Dagger+", 20);
        addItemToShop("Dagger++", 60);
        addItemToShop("Axe", 0);
        addItemToShop("Legs", 0);
        addItemToShop("Boots", 0);
        addItemToShop("Boots+", 20);
        addItemToShop("Boots++", 60);
        addItemToShop("HP Potion", 10);
        addItemToShop("Small Health Potion", 0);
        addItemToShop("Small Health Potion", 10);
        
        addItemToBuy("Sword", 50);
        addItemToBuy("Small Health Potion", 20);
        
    }

    public static List<Item> getLoot() {
        return loot;
    }

    public static void setLoot(List<Item> loot) {
        Game.loot = loot;
    }
    
    public static void addLoot(Item item)
    {
        getLoot().add(item);
    }
    
    public static void exploreDungeon()
    {
        try
        {
            Monster target = exploredDungeon.getMonsters().get(roundNow);
            
            (new BattleFrame(target, exploredDungeon.getBackground())).setVisible(true);
        }
        catch(IndexOutOfBoundsException e)
        {
            (new GameFrame()).setVisible(true);

            sendSuccessMessage("You successfully cleared this dungeon! Good job.");
        }
    }
    
    public static void advanceDungeon()
    {
        roundNow++;
        
        exploreDungeon();
    }
    
    public static void startExploringDungeon(Dungeon dungeon) 
    {
        exploredDungeon = dungeon;
        roundNow = 0;
        
        getPlayer().setHealth(getPlayer().getMaxHealth());
        
        loot = new ArrayList<>();
        
        exploreDungeon();
        
    }
    
}
