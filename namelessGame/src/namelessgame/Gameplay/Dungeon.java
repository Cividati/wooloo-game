package namelessgame.Gameplay;

import java.util.List;

/**
 *
 * @author Henrique Barcia Lang
 */
public class Dungeon {
    private int id;
    private int minLv;
    private String name;
    private String descr;
    
    private List<Monster> monsters;
    
    public Dungeon(int id, int minLv, String name, String descr)
    {
        setId(id);
        setMinLv(minLv);
        setName(name);
        setDescr(descr);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinLv() {
        return minLv;
    }

    public void setMinLv(int minLv) {
        this.minLv = minLv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }
    
}
