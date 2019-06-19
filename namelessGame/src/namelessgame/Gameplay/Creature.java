package namelessgame.Gameplay;

import java.util.Random;

/**
 * Classe que representa uma criatura.
 * @author Henrique Barcia Lang
 */
public abstract class Creature {
    private String name;
    protected String avatar;
    private int HP;

    /* Status base (sem equip. no caso de players) */
    private int str;
    private int agi;
    private int con;

    /* *********** */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStr() {
        return str;
    }

    public void addStr(int str) {
        this.str += str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getAgi() {
        return agi;
    }

    public void addAgi(int agi) {
        this.agi += agi;
    }

    public void setAgi(int agi) {
        this.agi = agi;
    }

    public int getCon() {
        return con;
    }

    public void addCon(int con) {
        this.con += con;
    }

    public void setCon(int con) {
        this.con = con;
    }
    
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }
    
    public abstract String getAvatar();
    
    public int getMaxHealth()
    {
        return (3 * getCon()) + getStr();
    }
    
    public static int getMaxHealth(int str, int con)
    {
        return (3 * con) + str;
    }
    
    public int getHealth() {
        return HP;
    }
    
    public void addHealth(int HP)
    {
        this.HP += HP;
        
        if(this.HP > this.getMaxHealth())
            this.HP = this.getMaxHealth();
    }

    public void setHealth(int HP) {
        this.HP = HP;
        
        if(this.HP > this.getMaxHealth())
            this.HP = this.getMaxHealth();
    }
    
    public int getMaxHits()
    {
        return 1 + (getAgi() / 100);
    }
    
    public int getHits()
    {
        return (new Random()).nextInt(getMaxHits()) + 1;
    }
    
    public int getOffense()
    {
        return (int) (0.35 * getStr() + 0.15 * getAgi() + 0.1 * getCon());
    }
    
    public int getDefense()
    {
        return (int) (0.4 * getCon() + 0.2 * getAgi());
    }
    
    public int getDamageToTarget(Creature target)
    {
        int damage = (2 * getOffense()) - target.getDefense();
        
        Random random = new Random();
        
        if(damage < 0)
            damage = random.nextInt(5);
        
        return (int) (random.nextInt((int) (0.4 * damage + 1)) + damage * 0.8); 
    }
}
