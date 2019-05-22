package namelessgame.Gameplay;

import namelessgame.Exception.GameIdNotFound;

public class Game {
    private static Player loggedPlayer;
    
    final public static int MAX_LEVEL = 100;

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

