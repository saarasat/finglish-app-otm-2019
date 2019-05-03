/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglishapp.domain;

import finglish.dao.GameDao;
import finglish.domain.Game;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author saarasat
 */
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
    public void delete(int id) throws Exception {
        
    }
}