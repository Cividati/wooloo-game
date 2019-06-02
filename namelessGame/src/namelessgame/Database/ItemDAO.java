/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package namelessgame.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import namelessgame.Gameplay.Item;

/**
 *
 * @author Henrique Barcia Lang
 */
public class ItemDAO extends DAO {
    public Item loadItemByName(String name)
    {
        String query = "SELECT * FROM item WHERE name = " + name + ";";
        Item item = null;

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
                
                item = new Item(id, str, agi, cons, heal, slot, minLv, 1, stackable, name, icon);
            }
            
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error when loading item by name...");
        } 
       
        return item;
    }
    
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
            System.out.println("Error when loading item by name...");
        } 
       
        return item;
    }
    
    public Item loadItemById(Connection con, int id, int count)
    {
        String query = "SELECT * FROM item WHERE id = " + id + ";";
        Item item = null;
        
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

