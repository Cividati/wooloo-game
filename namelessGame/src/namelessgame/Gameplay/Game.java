package namelessgame.Gameplay;

import namelessgame.Exception.StashFullException;

public class Game {
    private static Player loggedPlayer;
    
    final public static int MAX_LEVEL = 100;
    final public static int MAX_STASH_SIZE = 10;
    final public static int MAX_INVENTORY_SIZE = 3;
    
    final public static int INVENTORY = 0;
    final public static int HEAD = 1;
    final public static int BODY = 2;
    final public static int WEAPON = 3;
    final public static int SHIELD = 4;
    final public static int LEGS = 5;
    final public static int BOOTS = 6;

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

}
