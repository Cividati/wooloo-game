package namelessgame.Gameplay;

/**
 *
 * @author Henrique Barcia Lang
 */
public class Player extends Creature {
    private int id;
    private char sex;
    private int level; 
    private int exp;
    private long gold;
    private int maxHP;
    
    // Available attribute points to distribute.
    private int statusPoints;
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Player(int id, String name, char sex, int level, int exp, long gold, int statusPoints, int str, int agi, int inte, int con)
    {
        this.id = id;
        
        this.setName(name);
        
        this.sex = sex;
        this.level = level;
        this.exp = exp;
        this.gold = gold;
        this.statusPoints = statusPoints;
        
        this.setStr(str);
        this.setAgi(agi);
        this.setInte(inte);
        this.setCon(con);
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
        this.level += level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public int getExp() {
        return exp;
    }

    public void addExp(int exp)
    {
        this.exp += exp;
        
        // se exp > exp_requerida_próx_level, avançar 1 lv.
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
    
    public int getMaxHealth() {
        return maxHP;
    }

    public void setMaxHealth(int maxHP) {
        this.maxHP = maxHP;
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
    
    public javax.swing.ImageIcon getAvatar()
    {
        // TODO
        
        return new javax.swing.ImageIcon();
    }

}

