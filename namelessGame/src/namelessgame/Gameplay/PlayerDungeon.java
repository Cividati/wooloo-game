package namelessgame.Gameplay;

/**
 *
 * @author Henrique Barcia Lang
 */
public class PlayerDungeon extends Player {
    private int HP;

    public PlayerDungeon(String name, char sex) {
        super(name, sex);
    }

    public int getPlayerHealth() {
        return HP;
    }
    
    public void addPlayerHealth(int HP)
    {
        this.HP += HP;
        
        if(this.HP > this.getPlayerMaxHealth())
            this.HP = this.getPlayerMaxHealth();
    }

    public void setPlayerHealth(int HP) {
        this.HP = HP;
        
        if(this.HP > this.getPlayerMaxHealth())
            this.HP = this.getPlayerMaxHealth();
    }

}

