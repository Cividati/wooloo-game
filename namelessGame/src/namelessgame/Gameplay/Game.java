package namelessgame.Gameplay;

import namelessgame.View.BattleFrame;
import namelessgame.View.GameFrame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import namelessgame.Database.ItemDAO;
import namelessgame.Threads.Audio;

/**
 * Classe com constantes e métodos de uso geral no jogo.
 * @author Henrique Barcia Lang
 */
public class Game {
    private static Player loggedPlayer;
    
    /** Level máximo do personagem **/
    final public static int MAX_LEVEL = 100;
    
    /** Capacidade do stash **/
    final public static int MAX_STASH_SIZE = 30;
    
    /** Capacidade do inventário **/
    final public static int MAX_INVENTORY_SIZE = 12;
    
    /** Tamanho máximo de um stack de item **/
    final public static int MAX_STACKABLE_AMOUNT = 100;
    
    /** Quantidade de colunas do GridLayout do stash e inventário **/
    final public static int STASH_COLUMNS = 5;
    final public static int INVENTORY_COLUMNS = 3;
    
    final public static int HEAD = 1;
    final public static int BODY = 2;
    final public static int WEAPON = 3;
    final public static int SHIELD = 4;
    final public static int LEGS = 5;
    final public static int BOOTS = 6;
    
    private static List<Dungeon> dungeons = null;
    private static List<ShopItem> shop = new ArrayList<>();
    private static Map<String, Long> sell = new HashMap<>();
    private static List<Item> loot;
    
    private static Dungeon exploredDungeon;
    private static int roundNow;
    
    private static Audio audio;

    /**
     * Retorna o personagem logado
     * @return Player - personagem logado
     */
    public static Player getPlayer() {
        return loggedPlayer;
    }

    /**
     * Estabelece o personagem logado
     * @param loggedPlayer Player - personagem logado
     */
    public static void setPlayer(Player loggedPlayer) {
        Game.loggedPlayer = loggedPlayer;
    }
    
    /**
     * Mostra uma mensagem de erro
     * @param message String - mensagem a ser mostrada
     */
    public static void sendErrorMessage(String message)
    {
        javax.swing.JOptionPane.showMessageDialog(null, message, "Error", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Mostra uma mensagem de sucesso
     * @param message String - mensagem a ser msotrada
     */
    public static void sendSuccessMessage(String message)
    {
        javax.swing.JOptionPane.showMessageDialog(null, message, "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Retorna as dungeons do jogo
     * @return List_Dungeon - lista com as dungeons do jogo
     */
    public static List<Dungeon> getDungeons() {
        return dungeons;
    }

    /**
     * Estabelece as dungeons do jogo
     * @param dungeons List_Dungeon - lista com dungeons
     */
    public static void setDungeons(List<Dungeon> dungeons) {
        Game.dungeons = dungeons;
    }

    /**
     * Retorna os itens que podem ser comprados na loja
     * @return List_ShopItem - lista com os itens compráveis na loja
     */
    public static List<ShopItem> getShop() {
        return shop;
    }

    /**
     * Estabelece os itens compráveis na loja
     * @param shop List_ShopItem - lista com itens
     */
    public static void setShop(List<ShopItem> shop) {
        Game.shop = shop;
    }

    /**
     * Retorna os itens que podem ser vendidos na loja
     * @return List_ShopItem - lista com os itens vendíveis na loja
     */
    public static Map<String, Long> getSell() {
        return sell;
    }

    /**
     * Estabelece os itens vendíveis na loja
     * @param sell List_ShopItem - lista com itens
     */
    public static void setSell(Map<String, Long> sell) {
        Game.sell = sell;
    }
    
    /**
     * Adiciona um item comprável na loja
     * @param name String - nome do item
     * @param price long - preço do item
     */
    public static void addItemToShop(String name, long price)
    {
        ShopItem newItem = (new ItemDAO()).loadShopItemByName(name, price); 
        
        if(newItem == null)
            return;
        
        shop.add(newItem);
    }
    
    /**
     * Adiciona um item vendível na loja
     * @param name String - nome do item
     * @param price long - preço do item
     */
    public static void addItemToSell(String name, long price)
    {
        sell.put(name, price);
    }
    
    /**
     * Preenche a loja com os itens compráveis e vendíveis
     */
    public static void fillShop()
    {
        // item_name, price
        addItemToShop("Heavy Armor", 0);
        addItemToShop("Light Armor", 0);
        addItemToShop("Medium Armor", 0);
        addItemToShop("Helmet", 0);
        addItemToShop("Shield+++", 100);
        addItemToShop("Shield", 0);
        addItemToShop("Shield+", 20);
        addItemToShop("Shield++", 60);
        addItemToShop("Spear", 0);
        addItemToShop("Sword", 0);
        addItemToShop("Crossbow", 0);
        addItemToShop("Bow", 0);
        addItemToShop("Dagger", 0);
        addItemToShop("Dagger+", 20);
        addItemToShop("Dagger++", 60);
        addItemToShop("Axe", 0);
        addItemToShop("Legs", 0);
        addItemToShop("Boots", 0);
        addItemToShop("Boots+", 20);
        addItemToShop("Boots++", 60);
        addItemToShop("HP Potion", 10);
        addItemToShop("Small Health Potion", 0);
        addItemToShop("Small Health Potion", 10);
        
        addItemToSell("Sword", 50);
        addItemToSell("Small Health Potion", 20);
        
    }

    /**
     * Retorna o loot acumulado na dungeon pelo personagem até o momento
     * @return List_Item - lista com o loot acumulado
     */
    public static List<Item> getLoot() {
        return loot;
    }

    /**
     * Estabelece o loot acumulado na dungeon pelo personagem até o momento
     * @param loot List_Item - lista com itens
     */
    public static void setLoot(List<Item> loot) {
        Game.loot = loot;
    }
    
    /**
     * Adiciona um item à lista de loot acumulado
     * @param item Item - loot a ser adicionado na lista de loot acumulado
     */
    public static void addLoot(Item item)
    {
        getLoot().add(item);
    }
    
    /**
     * Inicia uma batalha contra o próximo monstro da dungeon
     */
    public static void exploreDungeon()
    {
        try
        {
            Monster target = exploredDungeon.getMonsters().get(roundNow);
            
            (new BattleFrame(target, exploredDungeon.getBackground())).setVisible(true);
        }
        catch(IndexOutOfBoundsException e)
        {
            (new GameFrame()).setVisible(true);

            sendSuccessMessage("You successfully cleared this dungeon! Good job.");
        }
    }
    
    /**
     * Avança um round na dungeon
     */
    public static void advanceDungeon()
    {
        roundNow++;
        
        exploreDungeon();
    }
    
    /**
     * Inicia a dungeon
     * @param dungeon Dungeon - dungeon a ser explorada
     */
    public static void startExploringDungeon(Dungeon dungeon) 
    {
        exploredDungeon = dungeon;
        roundNow = 0;
        
        getPlayer().setHealth(getPlayer().getMaxHealth());
        
        loot = new ArrayList<>();
        
        exploreDungeon();
    }

    /**
     * Retorna o áudio principal
     * @return Audio - áudio principal sendo tocado no momento
     */
    public static Audio getAudio() {
        return audio;
    }

    /**
     * Estabelece o áudio principal
     * @param audio Audio - áudio sendo tocado
     */
    public static void setAudio(Audio audio) {
        Game.audio = audio;
    }
    
    /**
     * Toca um novo áudio além do principal
     * @param audioName String - nome do áudio a ser tocado
     * @param isMusic boolean - é uma música ou não
     * @return Audio - novo áudio sendo tocado
     */
    public static Audio playNewAudio(String audioName, boolean isMusic)
    {
        Audio newAudio = new Audio(audioName, isMusic);
        
        newAudio.start();
        
        return newAudio;
    }

    /**
     * Toca uma nova música e a estabelece como aúdio principal
     * @param audioName String - nome da música
     */
    public static void playMusic(String audioName)
    {        
        if(audio != null && audio.getAudioName().equals(audioName) && audio.isMusic())
            return;

        Audio newAudio = new Audio(audioName, true);
        newAudio.start();
        
        stopAudio();
        setAudio(newAudio);
    }
    
    /**
     * Toca um novo som e o estabelece como áudio principal
     * @param audioName String - nome do som
     */
    public static void playSound(String audioName)
    {
        if(audio != null && audio.getAudioName().equals(audioName) && !audio.isMusic())
            return;
        
        Audio newAudio = new Audio(audioName);
        newAudio.start();
        
        stopAudio();
        setAudio(newAudio);
    }
    
    /**
     * Interrompe o áudio principal
     */
    public static void stopAudio()
    {
        Audio currentAudio = getAudio();
        
        if(currentAudio != null)
            currentAudio.stopAudio();
    }
    
    
}
