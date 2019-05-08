package namelessgame.Game;

/**
 *
 * @author Henrique Barcia Lang
 */
public abstract class Creature {
    private String name;
    
    /* Status base (sem equip. no caso de players) */
    
    private int str;
    private int dex;
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

    public int getDex() {
        return dex;
    }
    
    public void addDex(int dex) {
        this.dex += dex;
    }

    public void setDex(int dex) {
        this.dex = dex;
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
}

