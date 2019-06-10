package namelessgame.Gameplay;

/**
 *
 * @author Henrique Barcia Lang
 */
public abstract class Creature {
    private String name;
    protected String avatar;

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
}
