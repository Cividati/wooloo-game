package namelessgame.Game;

/**
 *
 * @author Henrique Barcia Lang
 */
public class Player extends Creature {
    private char sex;
    private int level; 
    private int exp;
    private long gold;
    private int statusPoints;                       // Pontos de status disponíveis para distribuir.
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Player(String name, char sex)
    {
        this.setName(name);
        
        this.sex = sex;
    }

    public char getPlayerSex() {
        return sex;
    }

    public void setPlayerSex(char sex) {
        this.sex = sex;
    }

    public int getPlayerLevel() {
        return level;
    }

    public void addPlayerLevel(int level)
    {
        this.level += level;
    }
    
    public void setPlayerLevel(int level) {
        this.level = level;
    }
    
    public int getPlayerExp() {
        return exp;
    }

    public void addPlayerExp(int exp)
    {
        this.exp += exp;
        
        // se exp > exp_requerida_próx_level, avançar 1 lv.
    }
    
    public void setPlayerExp(int exp) {
        this.exp = exp;
    }

    public long getPlayerGold() {
        return gold;
    }
    
    public void addPlayerGold(long gold)
    {
        this.gold += gold;
    }

    public void setPlayerGold(long gold) {
        this.gold = gold;
    }

    public int getPlayerStatusPoints() {
        return statusPoints;
    }
    
    public void addPlayerStatusPoints(int statusPoints)
    {
        this.statusPoints += statusPoints;
    }

    public void setPlayerStatusPoints(int statusPoints) {
        this.statusPoints = statusPoints;
    }

}

