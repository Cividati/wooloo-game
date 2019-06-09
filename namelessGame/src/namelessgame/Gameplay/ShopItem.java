package namelessgame.Gameplay;

/**
 *
 * @author Henrique Barcia Lang
 */
public class ShopItem extends Item {
    private long price;

    public ShopItem(int id, int str, int agi, int cons, int heal, int slot, int minLv, int i, boolean stackable, String name, String icon, long price) {
        super(id, str, agi, cons, heal, slot, minLv, i, stackable, name, icon);
        
        setPrice(price);
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

}
