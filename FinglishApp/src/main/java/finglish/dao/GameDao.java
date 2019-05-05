
package finglish.dao;

import finglish.domain.Game;

import java.util.ArrayList;

public interface GameDao {
    
    Game create(Game game) throws Exception;
    ArrayList<Game> getAll();
    boolean findById(int accountId);
    
}
