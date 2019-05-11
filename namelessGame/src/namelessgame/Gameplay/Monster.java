package namelessgame.Gameplay;

/**
 *
 * @author Henrique Barcia Lang
 */
public class Monster extends Creature {
    private int expGiven;
    
    public Monster()
    {
        
    }

    public int getMonsterExpGiven() {
        return expGiven;
    }

    public void setMonsterExpGiven(int expGiven) {
        this.expGiven = expGiven;
    }
    
}

