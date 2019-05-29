package namelessgame.Database;

import java.sql.SQLException;
import namelessgame.Gameplay.Player;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import namelessgame.Gameplay.Game;

/**
 *
 * @author Henrique Barcia Lang
 */
public class PlayerDAO extends DAO {  
    public void insertPlayer(Player player)
    {
        if(!connectToDatabase())
            return;
        
        // TODO load player stash, inventory and equip
        try {
            String query = "INSERT INTO player(name, sex, level, exp, gold, status_points, str, agi, con) VALUES (?, " + 
                            player.getSex() + ", " + player.getLevel() + ", " + player.getExp() + ", " +
                            player.getGold() + ", " + player.getStatusPoints() + ", " + player.getStr() + ", " + player.getAgi() + 
                            ", " + player.getCon() + ");";
            
            pst = con.prepareStatement(query);
            pst.setString(1, player.getName());
            pst.execute();
            con.close();
            
            Game.sendSuccessMessage("Character created successfully.");
        } catch (SQLException e) {
            System.err.println("Error when inserting new player on database..." + e.getMessage());
        }
        
    }
    
    public void savePlayer(Player player)
    {
        if(!connectToDatabase())
            return;
        
        String query = "UPDATE player SET level = " + player.getLevel() + ", exp = " + player.getExp() + ", gold = " + player.getGold() + 
                        ", status_points = " + player.getStatusPoints() + ", str = " + player.getStr() + ", agi = " + player.getAgi() +
                        ", con = " + player.getCon() + " WHERE id = " + player.getId() + ";";
                        
        try {
            pst = con.prepareStatement(query);
            pst.executeUpdate();
            pst.close();
            con.close();
            
            Game.sendSuccessMessage("You successfully saved your progress.");
        } catch (SQLException ex) {
            System.out.println("Error when saving player on database...");
        }
        
    }
    
    public void deletePlayer(Player player)
    { 
        if(!connectToDatabase())
            return;
        
        String query = "DELETE FROM player WHERE id = " + player.getId() + ";";
        
        try {
            pst = con.prepareStatement(query);
            pst.execute();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error when deleting player from database...");
        }
    }
    
    public List<Player> loadPlayers()
    {
        String query = "SELECT * FROM player;";
        List<Player> playerList = new ArrayList<>();
        
        if(!connectToDatabase())
            return playerList;
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            while(rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                char sex = rs.getString("sex").charAt(0);
                int level = rs.getInt("level");
                int exp = rs.getInt("exp");
                long gold = rs.getInt("gold");
                int statusPoints = rs.getInt("status_points");
                int str = rs.getInt("str");
                int agi = rs.getInt("agi");
                int cons = rs.getInt("con");
                
                playerList.add(new Player(id, name, sex, level, exp, gold, statusPoints, str, agi, cons));
            }
            
            st.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error when loading players from database..." + e.getMessage());
        }
        
        return playerList;
    }
}

