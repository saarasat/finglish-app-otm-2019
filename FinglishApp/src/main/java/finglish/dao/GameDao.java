
package finglish.dao;

import java.util.ArrayList;
import finglish.domain.Game;

public interface GameDao {
    
    Game create(Game game) throws Exception;
    
    ArrayList<Game> getAll();  
    
}
