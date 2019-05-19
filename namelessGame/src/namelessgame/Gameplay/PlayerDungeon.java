package namelessgame.Gameplay;

/**
 *
 * @author Henrique Barcia Lang
 */
public class PlayerDungeon extends Player {
    private int HP;

    public PlayerDungeon(int id, String name, char sex, int level, int exp, long gold, int statusPoints, int str, int agi, int inte, int con) {
        super(id, name, sex, level, exp, gold, statusPoints, str, agi, inte, con);
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

}

