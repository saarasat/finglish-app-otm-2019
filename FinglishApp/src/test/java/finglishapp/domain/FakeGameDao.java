
package finglishapp.domain;

import finglish.dao.GameDao;
import finglish.domain.Game;
import java.util.ArrayList;


public class FakeGameDao implements GameDao {
    
    ArrayList<Game> allGames;

    public FakeGameDao() {
        allGames = new ArrayList<>();
    }   
   
    @Override
    public ArrayList<Game> getAll() {
        return allGames;
    }
    
    @Override
    public Game create(Game game) {
        game.setId(allGames.size()+1);
        allGames.add(game);
        return game;
    } 
    
    @Override
    public boolean findById(int accountId) {
        return true;
    }
    
}