package namelessgame.Exception;

/**
 *
 * @author Henrique Barcia Lang
 */
public class GameIdNotFound extends RuntimeException {
    public GameIdNotFound()
    {
        super("Game ID not found in database...");
    }
}

