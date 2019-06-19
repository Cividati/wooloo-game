package namelessgame.Database;

import java.sql.SQLException;
import namelessgame.Gameplay.Item;
import namelessgame.Gameplay.ShopItem;

/**
 * Classe que carrega itens do banco de dados.
 * @author Henrique Barcia Lang
 */
public class ItemDAO extends DAO {
    
    /**
     * Carrega um ShopItem pelo nome
     * @param name String - nome do item
     * @param price long - preço do item
     * @return ShopItem - item carregado
     */
    public ShopItem loadShopItemByName(String name, long price)
    {
        String query = "SELECT * FROM item WHERE name = '" + name + "';";
        ShopItem item = null;

        if(!connectToDatabase())
            return item;
       
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            if(rs.next())
            {
                int id = rs.getInt("id");
                int str = rs.getInt("str");
                int agi = rs.getInt("agi");
                int cons = rs.getInt("con");
                int heal = rs.getInt("heal");
                int slot = rs.getInt("slot");
                int minLv = rs.getInt("min_lv");
                
                String icon = rs.getString("icon");
                
                boolean stackable = rs.getBoolean("stackable");
                
                item = new ShopItem(id, str, agi, cons, heal, slot, minLv, 1, stackable, name, icon, price);
            }
            
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error when loading item by name...");
        } 
       
        return item;
    }
    
    /**
     * Carrega um Item pelo ID
     * @param id int - ID do item
     * @return Item - item carregado
     */
    public Item loadItemById(int id)
    {
        String query = "SELECT * FROM item WHERE id = " + id + ";";
        Item item = null;

        if(!connectToDatabase())
            return item;
       
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            if(rs.next())
            {
                int str = rs.getInt("str");
                int agi = rs.getInt("agi");
                int cons = rs.getInt("con");
                int heal = rs.getInt("heal");
                int slot = rs.getInt("slot");
                int minLv = rs.getInt("min_lv");
                
                String name = rs.getString("name");
                String icon = rs.getString("icon");
                
                boolean stackable = rs.getBoolean("stackable");
                
                item = new Item(id, str, agi, cons, heal, slot, minLv, 1, stackable, name, icon);
            }
            
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error when loading item by id (without count)...");
        } 
       
        return item;
    }
    
    /**
     * Carrega um Item pelo ID e inserir sua quantidade
     * @param id int - ID do item
     * @param count int - quantidade do item
     * @return Item - item carregado
     */
    public Item loadItemById(int id, int count)
    {
        String query = "SELECT * FROM item WHERE id = " + id + ";";
        Item item = null;
        
        if(!connectToDatabase())
            return item;
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            if(rs.next())
            {
                int str = rs.getInt("str");
                int agi = rs.getInt("agi");
                int cons = rs.getInt("con");
                int heal = rs.getInt("heal");
                int slot = rs.getInt("slot");
                int minLv = rs.getInt("min_lv");
                
                String name = rs.getString("name");
                String icon = rs.getString("icon");
                
                boolean stackable = rs.getBoolean("stackable");
                
                item = new Item(id, str, agi, cons, heal, slot, minLv, count, stackable, name, icon);
            }
            
            st.close();
        } catch (SQLException ex) {
            System.out.println("Error when loading item by id...");
        } 
        
        return item;
    }
}

