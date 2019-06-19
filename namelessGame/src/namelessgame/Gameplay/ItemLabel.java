package namelessgame.Gameplay;

import javax.swing.JLabel;

/**
 * Wrapper class que encapsula um Item a um JLabel.
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
