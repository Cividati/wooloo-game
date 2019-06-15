package namelessgame.Gameplay;

/**
 *
 * @author Henrique Barcia Lang
 */
public class Item implements Cloneable {
    private int id;
    private int str;
    private int agi;
    private int con;
    private int heal;
    private int slot;
    private int minLevel;
    
    private int count = 1;
    private boolean stackable;
    
    private String name;
    private String icon;
    
    public Item() {}
    
    public Item(int id, int str, int agi, int con, int heal, int slot, int minLevel, int count, boolean stackable, String name, String icon)
    {
        setId(id);
        setStr(str);
        setAgi(agi);
        setCon(con);
        setHeal(heal);
        setSlot(slot);
        setMinLevel(minLevel);
        setCount(count);
        setStackable(stackable);
        setName(name);
        setIcon(icon);
    }
    
    @Override
    public Object clone() throws
        CloneNotSupportedException 
    { 
        return super.clone(); 
    } 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getAgi() {
        return agi;
    }

    public void setAgi(int agi) {
        this.agi = agi;
    }

    public int getCon() {
        return con;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = "/namelessgame/img/items/" + icon + ".png";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPotion()
    {
        return getHeal() > 0;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isStackable() {
        return stackable;
    }

    public void setStackable(boolean stackable) {
        this.stackable = stackable;
    }
    
    public String getDescr()
    {
        return "<html>" + getName() +               
                (isStackable() ? (" - " + getCount() + "x unit(s).") : "") + "<br><br>" +
                (isPotion() ? ("Heals for <font color=\"green\">" + getHeal() + "</font> health.") : ("" +
                "Strenght: " + getStr() + "<br>" +
                "Agility: " + getAgi() + "<br>" +
                "Constitution: " + getCon() + "<br>")) +
                "</html>";
    }
    
    public String getDescr(Player player)
    {
        int strDiff = player.getStr(this) - player.getStr();
        int agiDiff = player.getAgi(this) - player.getAgi();
        int conDiff = player.getCon(this) - player.getCon();
        int hpDiff = player.getMaxHealth(this) - player.getMaxHealth();

        String strFont = strDiff > 0 ? "\"green\"" : "\"red\"";
        String agiFont = agiDiff > 0 ? "\"green\"" : "\"red\"";
        String conFont = conDiff > 0 ? "\"green\"" : "\"red\"";
        String hpFont = hpDiff > 0 ? "\"green\"" : "\"red\"";

        return "<html>" + getName() +               
                (isStackable() ? (" - " + getCount() + "x unit(s).") : "") + "<br><br>" +
                (isPotion() ? ("Heals for <font color=\"green\">" + getHeal() + "</font> health.") : ("" +
                "Strenght: " + getStr() + " (<font color=" + strFont + ">" + (strDiff > 0 ? ("+" + strDiff) : strDiff) + "</font>)<br>" +
                "Agility: " + getAgi() + " (<font color=" + agiFont + ">" + (agiDiff > 0 ? ("+" + agiDiff) : agiDiff) + "</font>)<br>" +
                "Constitution: " + getCon() + " (<font color=" + conFont + ">" + (conDiff > 0 ? ("+" + conDiff) : conDiff) + "</font>)<br>" +
                "<font color =" + hpFont + ">" + (hpDiff > 0 ? ("+" + hpDiff) : hpDiff) + " health points</font><br>")) +
                "</html>";
    }
    
}
