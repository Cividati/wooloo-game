package namelessgame.Gameplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import namelessgame.Exception.InventoryFullException;
import namelessgame.Exception.NotEnoughLevelException;
import namelessgame.Exception.StashFullException;

/**
 * Classe que representa um personagem.
 * @author Henrique Barcia Lang
 */
public class Player extends Creature implements Comparable<Player> {
    private int id;
    private char sex;
    private int level; 
    private int exp;
    private long gold;
    
    // Available attribute points to distribute.
    private int statusPoints;
    
    private List<Item> stash = new ArrayList<>();
    private List<Item> inventory = new ArrayList<>();
    private Map<Integer, Item> equip = new HashMap<>();
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Player(int id, String name, String avatar, char sex, int level, int exp, long gold, int statusPoints, int str, int agi, int con, Map<Integer, Item> equip)
    {
        setId(id);
        setName(name);
        setSex(sex);
        setLevel(level);
        setExp(exp);
        setGold(gold);
        
        setStatusPoints(statusPoints);
        setStr(str);
        setAgi(agi);
        setCon(con);
        
        setEquip(equip);
        setAvatar(avatar);
    }
    
    /**
     * Adiciona um item ao container
     * @param item Item - item a ser adicionado
     * @param toContainer List_Item - container que terá o item adicionado
     */
    public void addItemToContainer(Item item, List<Item> toContainer)
    {
        if(toContainer == getStash())
        {
            if((getStash()).size() == Game.MAX_STASH_SIZE)
                throw new StashFullException();
        }
        else if(toContainer == getInventory())
        {
            if((getInventory()).size() == Game.MAX_INVENTORY_SIZE)
                throw new InventoryFullException();
        }
        
        // Adds to first index of the container (so the player can reorganize it easily)
        toContainer.add(0, item);
    }
    
    /**
     * Retorna se o container está cheio ou não
     * @param toContainer List_Item - container
     * @return boolean - o container está cheio ou não
     */
    public boolean isContainerFull(List<Item> toContainer)
    {
        if(toContainer == getStash())
            return (getStash()).size() == Game.MAX_STASH_SIZE;
        else if(toContainer == getInventory())
            return (getInventory()).size() == Game.MAX_INVENTORY_SIZE;
        
        return false;
    }
    
    /**
     * Adiciona um item ao stash
     * @param item Item - item a ser adicionado
     */
    public void addItemToStash(Item item)
    {
        if((getStash()).size() == Game.MAX_STASH_SIZE)
            throw new StashFullException();
        
        (getStash()).add(0, item);
    }
    
    /**
     * Adiciona um item ao inventário
     * @param item Item - item a ser adicionado
     */
    public void addItemToInventory(Item item)
    {
        if((getInventory()).size() == Game.MAX_INVENTORY_SIZE)
            throw new InventoryFullException();
        
        (getInventory()).add(0, item);
    }
    
    /**
     * Equipa um item
     * @param item Item - item a ser equipado
     * @param fromContainer List_Item - container de origem do item
     */
    public void equipItem(Item item, List<Item> fromContainer)
    {
        int slot = item.getSlot();
        
        Map<Integer, Item> playerEquip = getEquip();
        
        if(getLevel() < item.getMinLevel())
            throw new NotEnoughLevelException();
        
        // Remove item from container
        fromContainer.remove(item);
        
        // There's an item equipped, store it on the container
        if(playerEquip.get(slot) != null)
            fromContainer.add(0, playerEquip.get(slot));
        
        // Equip item
        playerEquip.put(slot, item);
    }
    
    /**
     * Remove um item do stash
     * @param item Item - item a ser removido
     */
    public void removeItemFromStash(Item item)
    {
        (getStash()).remove(item);
    }
    
    /**
     * Remove um item do inventário
     * @param item Item - item a ser removido
     */
    public void removeItemFromInventory(Item item)
    {
        (getInventory()).remove(item);
    }
    
    /**
     * Remove um item do equipamento
     * @param item Item - item a ser removido
     */
    public void removeItemFromEquip(Item item)
    {
        Map<Integer, Item> playerEquip = getEquip();
        
        if(playerEquip.get(item.getSlot()) == null)
            return;
        
        if(playerEquip.get(item.getSlot()) == item)
            playerEquip.put(item.getSlot(), null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getLevel() {
        return level;
    }

    public void addLevel(int level)
    {
        if(getLevel() >= Game.MAX_LEVEL)
            return;
        
        this.level += level;
        
        if(getLevel() > Game.MAX_LEVEL)
            setLevel(Game.MAX_LEVEL);
        
        setExp(0);
        addStatusPoints(level * 5);
    }
    
    public void setLevel(int level) {
        this.level = level;
        
        if(getLevel() > Game.MAX_LEVEL)
            this.level = Game.MAX_LEVEL;
    }
    
    public int getExp() {
        return exp;
    }

    public void addExp(int exp)
    {
        if(getLevel() >= Game.MAX_LEVEL)
            return;
        
        this.exp += exp;
        
        if(getExpNeededToLevelUp() <= 0)
        {
            int remainingExp = this.exp - getTotalExpToLevelUp();
            
            addLevel(1);
            
            if(remainingExp > 0)
                addExp(remainingExp);
        }
    }
    
    /**
     * Retorna a experiência total requirida para avançar de nível
     * @return int - experiência total para avançar de nível
     */
    public int getTotalExpToLevelUp()
    {
        return getLevel() * 10;
    }
    
    /**
     * Retorna a experiência restante para avançar de nível
     * @return int - experiência restante para avançar de nível
     */
    public int getExpNeededToLevelUp()
    {
        return getTotalExpToLevelUp() - getExp();
    }
    
    public void setExp(int exp) {
        this.exp = exp;
    }

    public long getGold() {
        return gold;
    }
    
    public void addGold(long gold)
    {
        this.gold += gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public int getStatusPoints() {
        return statusPoints;
    }
    
    public void addStatusPoints(int statusPoints)
    {
        this.statusPoints += statusPoints;
    }

    public void setStatusPoints(int statusPoints) {
        this.statusPoints = statusPoints;
    }
    
    public String getPureAvatar()
    {
        return avatar;
    }
    
    @Override
    public String getAvatar()
    {
        return "/namelessgame/img/avatars/" + avatar + ".png";
    }

    public List<Item> getStash() {
        return stash;
    }

    public void setStash(List<Item> stash) {
        this.stash = stash;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public Map<Integer, Item> getEquip() {
        return equip;
    }

    public void setEquip(Map<Integer, Item> equip) {
        this.equip = equip;
    }
    
    @Override
    public int compareTo(Player o) {
        if(getLevel() > o.getLevel())
            return -1;
        else if(getLevel() < o.getLevel())
            return 1;
        else
            return 0;
            
    }
    
    public int getBaseStr()
    {
        return super.getStr();
    }
    
    public int getBaseAgi()
    {
        return super.getAgi();
    }
    
    public int getBaseCon()
    {
        return super.getCon();
    }
    
    /**
     * Retorna a strenght do personagem considerando que item está equipado
     * @param item Item - item a ser considerado que está equipado
     * @return int - strenght restante
     */
    public int getStr(Item item)
    {
        int str = getBaseStr();
        int slot = item.getSlot();
        Map<Integer, Item> playerEquip = new HashMap<>(getEquip());
        
        if(slot < Game.HEAD || slot > Game.BOOTS)
            return getStr();
        
        playerEquip.put(slot, item);
        
        for(int i = Game.HEAD; i <= Game.BOOTS; i++)
        {
            Item equipItem = playerEquip.get(i);  
            
            if(equipItem == null)
                continue;         
            
            str += equipItem.getStr();
        }
        
        return str;      
    }
    
    /**
     * Retorna a agility do personagem considerando que item está equipado
     * @param item Item - item a ser considerado que está equipado
     * @return int - agility resultante
     */
    public int getAgi(Item item)
    {
        int agi = getBaseAgi();
        int slot = item.getSlot();
        Map<Integer, Item> playerEquip = new HashMap<>(getEquip());
        
        if(slot < Game.HEAD || slot > Game.BOOTS)
            return getAgi();
        
        playerEquip.put(slot, item);
        
        for(int i = Game.HEAD; i <= Game.BOOTS; i++)
        {
            Item equipItem = playerEquip.get(i);  
            
            if(equipItem == null)
                continue;
            
            agi += equipItem.getAgi();
        }
        
        return agi;      
    }
    
    /**
     * Retorna a constitution do personagem considerando que item está equipado
     * @param item Item - item a ser considerado que está equipado
     * @return int - constitution resultante
     */
    public int getCon(Item item)
    {
        int con = getBaseCon();
        int slot = item.getSlot();
        Map<Integer, Item> playerEquip = new HashMap<>(getEquip());
        
        if(slot < Game.HEAD || slot > Game.BOOTS)
            return getCon();
        
        playerEquip.put(slot, item);
        
        for(int i = Game.HEAD; i <= Game.BOOTS; i++)
        {
            Item equipItem = playerEquip.get(i);  
            
            if(equipItem == null)
                continue;         
            
            con += equipItem.getCon();
        }
        
        return con;      
    }
    
    /**
     * Retorna a strenght total do personagem considerando equipamentos
     * @return int - strenght total
     */
    @Override
    public int getStr()
    {
        int str = getBaseStr();
        Map<Integer, Item> playerEquip = getEquip();
        
        for(int i = Game.HEAD; i <= Game.BOOTS; i++)
        {
            Item item = playerEquip.get(i);
            
            if(item == null)
                continue;
            
            str += item.getStr();
        }
        
        return str;
    }
    
    /**
     * Retorna a agility total do personagem considerando equipamentos
     * @return int - agility total
     */
    @Override
    public int getAgi()
    {
        int agi = getBaseAgi();
        Map<Integer, Item> playerEquip = getEquip();
        
        for(int i = Game.HEAD; i <= Game.BOOTS; i++)
        {
            Item item = playerEquip.get(i);
            
            if(item == null)
                continue;
            
            agi += item.getAgi();
        }
        
        return agi;
    }
    
    /**
     * Retorna a constitution total do personagem considerando equipamentos
     * @return int - constitution total
     */
    @Override
    public int getCon()
    {
        int con = getBaseCon();
        Map<Integer, Item> playerEquip = getEquip();
        
        for(int i = Game.HEAD; i <= Game.BOOTS; i++)
        {
            Item item = playerEquip.get(i);
            
            if(item == null)
                continue;
            
            con += item.getCon();
        }
        
        return con;
    }
    
    public int getMaxHealth(Item item)
    {
        return (3 * getCon(item)) + getStr(item);
    }
 
}