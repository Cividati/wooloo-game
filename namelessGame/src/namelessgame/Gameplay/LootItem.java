package namelessgame.Gameplay;

/**
 * Classe que representa um item saqueado de um monstro.
 * @author Henrique Barcia Lang
 */
public class LootItem extends Item {
    private int countMin;
    private int countMax;
    private int chance;
    
    public LootItem(Item item)
    {
        super(item);
    }

    public int getCountMin() {
        return countMin;
    }

    public void setCountMin(int countMin) {
        this.countMin = countMin;
    }

    public int getCountMax() {
        return countMax;
    }

    public void setCountMax(int countMax) {
        this.countMax = countMax;
    }
    
    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }
}

