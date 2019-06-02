package namelessgame.Database;

import java.sql.SQLException;
import namelessgame.Gameplay.Player;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import namelessgame.Gameplay.Game;
import namelessgame.Gameplay.Item;

/**
 *
 * @author Henrique Barcia Lang
 */
public class PlayerDAO extends DAO {  
    public void insertPlayer(Player player)
    {
        if(!connectToDatabase())
            return;
        
        String playerQuery = "INSERT INTO player(name, sex) VALUES (?, " + player.getSex() + ");";
        String inventoryQuery = "INSERT INTO inventory(player_id) VALUES ((SELECT MAX(id) FROM player));";
        String stashQuery = "INSERT INTO stash(player_id) VALUES ((SELECT MAX(id) FROM player));";
        
        // TODO load player stash, inventory and equip
        try {
            pst = con.prepareStatement(playerQuery);
            pst.setString(1, player.getName());
            pst.execute();
            
            pst = con.prepareStatement(inventoryQuery);
            pst.execute();
            
            pst = con.prepareStatement(stashQuery);
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
        
        Map<Integer, Item> equip = player.getEquip();
        List<Item> inventory = player.getInventory();
        List<Item> stash = player.getStash();
        
        String playerQuery = "UPDATE player SET level = " + player.getLevel() + ", exp = " + player.getExp() + ", gold = " + player.getGold() + 
                        ", status_points = " + player.getStatusPoints() + ", str = " + player.getBaseStr() + ", agi = " + player.getBaseAgi() +
                        ", con = " + player.getBaseCon() + ", head = " + (equip.get(Game.HEAD) != null ? equip.get(Game.HEAD).getName() : "NULL") +
                        ", body = " + (equip.get(Game.BODY) != null ? equip.get(Game.BODY).getName() : "NULL") + ", weapon = " + (equip.get(Game.WEAPON) != null ? equip.get(Game.WEAPON).getName() : "NULL") +
                        ", shield = " + (equip.get(Game.SHIELD) != null ? equip.get(Game.SHIELD).getName() : "NULL") + ", legs = " + (equip.get(Game.LEGS) != null ? equip.get(Game.LEGS).getName() : "NULL") +
                        ", boots = " + (equip.get(Game.BOOTS) != null ? equip.get(Game.BOOTS).getName() : "NULL") + " WHERE id = " + player.getId() + ";";
                       
        String deleteInventory = "DELETE ii.* FROM inventory_has_item AS ii INNER JOIN inventory AS i ON ii.stash_id = i.id WHERE i.player_id = " + player.getId() + ";";
        String deleteStash = "DELETE si.* FROM stash_has_item AS si INNER JOIN stash AS s ON si.stash_id = s.id WHERE s.player_id = " + player.getId() + ";";     
        
        String updateInventory = "INSERT INTO inventory_has_item VALUES ";
        String updateStash = "INSERT INTO stash_has_item VALUES ";
        
        try {
            pst = con.prepareStatement(playerQuery);
            pst.executeUpdate();
            
            pst = con.prepareStatement(deleteInventory);
            pst.execute();
            
            pst = con.prepareStatement(deleteStash);
            pst.execute();
            
            if(inventory.size() > 0)
            {
                boolean firstTime = true;
                
                for(Item item : inventory)
                {   
                    if(!firstTime)
                        updateInventory += ", ";
                    else
                        firstTime = false;
                    
                    updateInventory += "(" + player.getId() + ", " + item.getId() + ", " + item.getCount() + ")";
                }
                
                updateInventory += ";";
                
                pst = con.prepareStatement(updateInventory);
                pst.execute();
            }
            
            if(stash.size() > 0)
            {
                boolean firstTime = true;
                
                for(Item item : stash)
                {   
                    if(!firstTime)
                        updateStash += ", ";
                    else
                        firstTime = false;
                    
                    updateStash += "(" + player.getId() + ", " + item.getId() + ", " + item.getCount() + ")";
                }
                
                updateStash += ";";
                
                pst = con.prepareStatement(updateStash);
                pst.execute();
            }
            
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
        String inventoryQuery = "SELECT ii.item_id, ii.count FROM inventory_use_item AS ii INNER JOIN inventory AS i ON ii.inventory_id = i.id WHERE i.player_id = ?;";
        String stashQuery = "SELECT si.item_id, si.count FROM stash_use_item AS si INNER JOIN stash AS s ON si.inventory_id = s.id WHERE s.player_id = ?;";
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
                
                Item head = null;
                Item body = null;
                Item weapon = null;
                Item shield = null;
                Item legs = null;
                Item boots = null;
                
                Map<Integer, Item> equip = new HashMap<>();              
                
                String[] parts = {"head", "body", "weapon", "shield", "legs", "boots"};
                
                for(String part : parts)
                {
                    int itemId = rs.getInt(part);
                    int toSlot;
                    
                    if(rs.wasNull())
                        continue;
                    
                    switch(part)
                    {
                        case "head":
                            toSlot = Game.HEAD;
                            
                            break;
                        case "body":
                            toSlot = Game.BODY;
                            
                            break;
                        case "weapon":
                            toSlot = Game.WEAPON;
                            
                            break;
                        case "shield":
                            toSlot = Game.SHIELD;
                            
                            break;
                        case "legs":
                            toSlot = Game.LEGS;
                            
                            break;
                        case "boots":
                            toSlot = Game.BOOTS;
                            
                            break;
                        default:
                            System.out.println("Error when loading item to equip...");
                            
                            continue;
                    }
                    
                    equip.put(toSlot, (new ItemDAO()).loadItemById(itemId, 1));

                }
                
                playerList.add(new Player(id, name, sex, level, exp, gold, statusPoints, str, agi, cons, equip));
            }
            
            for(Player player : playerList)
            {
                List<Item> inventory = new ArrayList<>();
                List<Item> stash = new ArrayList<>();
                
                pst = con.prepareStatement(inventoryQuery);
                pst.setString(1, Integer.toString(player.getId()));
                rs = pst.executeQuery();
            
                while(rs.next())
                {
                    int itemId = rs.getInt("item_id");
                    int count = rs.getInt("count");

                    Item item = (new ItemDAO()).loadItemById(itemId, count);

                    inventory.add(item);
                }
                
                player.setInventory(inventory);
                
                pst = con.prepareStatement(stashQuery);
                pst.setString(1, Integer.toString(player.getId()));
                rs = pst.executeQuery();
                
                while(rs.next())
                {
                    int itemId = rs.getInt("item_id");
                    int count = rs.getInt("count");

                    Item item = (new ItemDAO()).loadItemById(itemId, count);

                    stash.add(item);
                }
                
                player.setStash(stash);               
            }
   
            st.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error when loading players from database..." + e.getMessage());
        }
        
        return playerList;
    }
}
