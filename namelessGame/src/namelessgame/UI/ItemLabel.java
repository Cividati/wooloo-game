package namelessgame.UI;











import javax.swing.JLabel;
import namelessgame.Gameplay.Item;

/**
 *
 * @author Henrique Barcia Lang
 */
public class ItemLabel extends JLabel {
    private Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}

