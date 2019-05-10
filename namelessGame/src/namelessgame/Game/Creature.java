package namelessgame.Game;

/**
 *
 * @author Henrique Barcia Lang
 */
public abstract class Creature {

    private String name;

    /* Status base (sem equip. no caso de players) */
    private int str;
    private int agi;
    private int inte;
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

    public int getInte() {
        return inte;
    }
    public void addInte(int inte) {
        this.inte += inte;
    }
    

    public void setInte(int inte) {
        this.inte = inte;
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
