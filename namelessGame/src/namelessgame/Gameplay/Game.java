package namelessgame.Gameplay;

import namelessgame.Exception.GameIdNotFound;

public class Game {
    private static Player loggedPlayer;

    public static Player getPlayer() {
        return loggedPlayer;
    }

    public static void setPlayer(Player loggedPlayer) {
        Game.loggedPlayer = loggedPlayer;
    }
    
    public static Player getPlayerById(int uid) throws GameIdNotFound
    {
        // TODO
        Player p = new Player("Test", 'M');
        
        return p;
    }
    
    public static void deletePlayerById(int uid) throws GameIdNotFound
    {
        System.out.println("Deleting character...");
    }

}

