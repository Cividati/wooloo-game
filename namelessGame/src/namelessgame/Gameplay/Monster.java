package namelessgame.Gameplay;

import java.util.List;

/**
 *
 * @author Henrique Barcia Lang
 */
public class Monster extends Creature {
    private int id;
    private int expGiven;
    private int goldMin;
    private int goldMax;
    private int round;
    
    private List<LootItem> loots;
    
    public Monster(String name, int str, int agi, int con, int expGiven, int goldMin, int goldMax, int round, String avatar)
    {
        setName(name);
        setStr(str);
        setAgi(agi);
        setCon(con);
        setExpGiven(expGiven);
        setGoldMin(goldMin);
        setGoldMax(goldMax);
        setRound(round);
        setAvatar(avatar);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getExpGiven() {
        return expGiven;
    }

    public void setExpGiven(int expGiven) {
        this.expGiven = expGiven;
    }

    public int getGoldMin() {
        return goldMin;
    }

    public void setGoldMin(int goldMin) {
        this.goldMin = goldMin;
    }

    public int getGoldMax() {
        return goldMax;
    }

    public void setGoldMax(int goldMax) {
        this.goldMax = goldMax;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public List<LootItem> getLoots() {
        return loots;
    }

    public void setLoots(List<LootItem> loots) {
        this.loots = loots;
    }

    @Override
    public String getAvatar()
    {
        return "/namelessgame/img/monsters/" + avatar + ".png";
    }
}

