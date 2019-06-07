package namelessgame.Gameplay;

import java.util.List;
import namelessgame.Exception.StashFullException;

public class Game {
    private static Player loggedPlayer;
    
    /** Player max level **/
    final public static int MAX_LEVEL = 100;
    
    /** Stash max size **/
    final public static int MAX_STASH_SIZE = /*30*/80;
    
    /** Inventory max size **/
    final public static int MAX_INVENTORY_SIZE = /*12*/40;
    
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
    
}
