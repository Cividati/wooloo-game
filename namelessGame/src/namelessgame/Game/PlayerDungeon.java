package namelessgame.Game;

/**
 *
 * @author Henrique Barcia Lang
 */
public class PlayerDungeon extends Player {
    private int HP;
    private int maxHP;

    public PlayerDungeon(String name, char sex) {
        super(name, sex);
    }

    public int getPlayerHealth() {
        return HP;
    }
    
    public void addPlayerHealth(int HP)
    {
        this.HP += HP;
        
        if(this.HP > this.maxHP)
            this.HP = this.maxHP;
    }

    public void setPlayerHealth(int HP) {
        this.HP = HP;
        
        if(this.HP > this.maxHP)
            this.HP = this.maxHP;
    }

    public int getPlayerMaxHealth() {
        return maxHP;
    }

    public void setPlayerMaxHealth(int maxHP) {
        this.maxHP = maxHP;
    }
    
    

}

