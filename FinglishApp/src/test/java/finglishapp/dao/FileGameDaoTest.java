/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglishapp.dao;

import finglish.dao.FileGameDao;
import finglish.dao.GameDao;
import finglish.domain.Game;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author saarasat
 */
public class FileGameDaoTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
  
    File gameFile;
    FileGameDao gameDao;
    
    @Before
    public void setUp() throws Exception {
        gameFile = testFolder.newFile("testGames.txt");
        
        try(FileWriter file = new FileWriter(gameFile.getAbsolutePath())) {
            file.write("1;1;5;10\n");
            file.write("2;2;8;10\n");
        }     
        gameDao = new FileGameDao(gameFile.getAbsolutePath());
        
    }
    
    @Test
    public void allGamesAreReadFromAFile() {
        ArrayList<Game> allGames = gameDao.getAll();
        assertEquals(2, allGames.size());      
    }   
    
    @Test
    public void gamesAreReadCorrectlyFromAFile() {
        ArrayList<Game> allQuestions = gameDao.getAll();
        assertEquals(1, allQuestions.get(0).getId());
        assertEquals(2, allQuestions.get(1).getId());
    }
    
    @Test
    public void aValidGameCanBeCreated() throws Exception {
        Game game = gameDao.create(new Game(1, 2, 10));
        assertEquals(2, game.getAmountOfCorrectAnswers());
    }

    
    @After
    public void tearDown() {
        gameFile.delete();
    }
}