package namelessgame.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import namelessgame.Gameplay.Dungeon;
import namelessgame.Gameplay.LootItem;
import namelessgame.Gameplay.Monster;

/**
 *
 * @author Henrique Barcia Lang
 */
public class DungeonDAO extends DAO {
    public List<Dungeon> loadDungeons()
    {
        List<Dungeon> dungeonList = new ArrayList<>();
        
        String query = "SELECT * FROM dungeon;";
        String monsterQuery = "SELECT * FROM monster WHERE dungeon_id = ?;";
        String lootQuery = "SELECT item_id, count_min, count_max, chance FROM monster_has_item WHERE monster_id = ?;";
        
        if(!connectToDatabase())
            return dungeonList;
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            while(rs.next())
            {               
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String descr = rs.getString("descr");
                int minLv = rs.getInt("min_lv");
                String background = rs.getString("background");
                
                dungeonList.add(new Dungeon(id, minLv, name, descr, background));  
            }
            
            for(Dungeon dungeon : dungeonList)
            {
                List<Monster> monsterList = new ArrayList<>();
                
                pst = con.prepareStatement(monsterQuery);
                pst.setString(1, Integer.toString(dungeon.getId()));
                rs = pst.executeQuery();
            
                while(rs.next())
                {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int str = rs.getInt("str");
                    int agi = rs.getInt("agi");
                    int cons = rs.getInt("con");
                    int exp = rs.getInt("exp");
                    int goldMin = rs.getInt("gold_min");
                    int goldMax = rs.getInt("gold_max");
                    int round = rs.getInt("round");
                    String icon = rs.getString("icon");
                    
                    monsterList.add(new Monster(id, name, str, agi, cons, exp, goldMin, goldMax, round, icon));
                }
                
                for(Monster monster : monsterList)
                {
                    List<LootItem> lootList = new ArrayList<>();
                    
                    pst = con.prepareStatement(lootQuery);
                    pst.setString(1, Integer.toString(monster.getId()));
                    rs = pst.executeQuery();
                    
                    while(rs.next())
                    {
                        int itemId = rs.getInt("item_id");
                        int countMin = rs.getInt("count_min");
                        int countMax = rs.getInt("count_max");
                        int chance = rs.getInt("chance");

                        LootItem item = new LootItem(((new ItemDAO()).loadItemById(itemId)));
                        
                        item.setCountMin(countMin);
                        item.setCountMax(countMax);
                        item.setChance(chance);

                        lootList.add(item);
                    }
                    
                    monster.setLoots(lootList);
                }
                
                Collections.sort(monsterList);
                dungeon.setMonsters(monsterList);
            }
            
        } catch (SQLException e) {
            System.err.println("Error when loading dungeons from database..." + e.getMessage());
        }
        
        return dungeonList;
    }
}
