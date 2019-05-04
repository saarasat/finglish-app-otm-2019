/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglishapp.domain;

import finglish.domain.Game;
import finglish.domain.GameService;
import finglish.domain.Question;
import finglish.domain.User;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author saarasat
 */
public class GameServiceTest {
    
    Game game;
    User user;
    GameService gameService;
    FakeGameDao gameDao;
    FakeQuestionDao questionDao;
    FakeUserDao userDao;
    Question question;
    ArrayList<Integer> usedIndexes;
    
    public GameServiceTest() throws Exception {
        this.questionDao = new FakeQuestionDao();
        this.gameDao = new FakeGameDao();
        this.userDao = new FakeUserDao();

        

        this.questionDao.create(new Question("question1", "first option1", "second option1", "third option1", "fourth option1", "correct answer1"));
        this.questionDao.create(new Question("question2", "first option2", "second option2", "third option2", "fourth option2", "correct answer2"));
        this.questionDao.create(new Question("question3", "first option3", "second option3", "third option3", "fourth option3", "correct answer3"));
        this.questionDao.create(new Question("question4", "first option4", "second option4", "third option4", "fourth option4", "correct answer4"));
        this.questionDao.create(new Question("question5", "first option5", "second option5", "third option5", "fourth option5", "correct answer5"));
        this.questionDao.create(new Question("question6", "first option6", "second option6", "third option6", "fourth option6", "correct answer6"));
        this.questionDao.create(new Question("question7", "first option7", "second option7", "third option7", "fourth option7", "correct answer7"));
        this.questionDao.create(new Question("question8", "first option8", "second option8", "third option8", "fourth option8", "correct answer8"));
        this.questionDao.create(new Question("question9", "first option9", "second option9", "third option9", "fourth option9", "correct answer9"));
        this.questionDao.create(new Question("question10", "first option10", "second option10", "third option10", "fourth option10", "correct answer10"));

    }
    
    @Before
    public void setUp() {
        this.user = new User("Käyttäjätunnus", "Salasana", 1);
        this.userDao.create(user);
        gameService = new GameService(user, gameDao, questionDao, userDao);
        
    }
    
    @Test
    public void aValidUserCanBeAdded(){      
        assertTrue(this.gameService.addUser("Uusi", "UusiSalasana", 1));
    }
    
    @Test
    public void aValidUserCanBeAddedAndFound(){
        this.gameService.addUser("Uusi", "UusiSalasana", 1);
        assertTrue(!userDao.findByUsername("Uusi").equals(null));
    }
    
    @Test
    public void aValidUserCanBeRemoved(){
        this.gameService.addUser("Uusi", "UusiSalasana", 1);
        this.gameService.removeUser(2);
        assertEquals(null, userDao.findByUsername("Uusi"));
    }
    
    @Test
    public void tooShortUserInputDoesNotPass() {
        assertFalse(this.gameService.validateInput("aa"));
    }
    
    @Test
    public void tooLongUserInputDoesNotPass() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            list.append("11merkkiäon");
        }
        assertFalse(this.gameService.validateInput(list.toString()));
    }
    
    @Test
    public void userInputIsValidatedCorrectly() {
        assertTrue(this.gameService.validateInput("Oikean mittainen"));
    }
    
        
    @Test
    public void getTheNextQuestionMethodReturnsAValidQuestion() {
        this.gameService.startANewGame();
        assertTrue(!this.gameService.getTheNextQuestion().getQuestion().equals(null));
    }
    
   
    @Test
    public void ifTenQuestionsAreAnsweredWrongScoreIsZero() {
        this.gameService.startANewGame();
        for (int i = 0; i < 10; i++) {
            this.gameService.answerTheQuestion(false);
        }
        assertEquals("0/10 oikein, pystyt parempaan!", this.gameService.getTotalScore());
    }
    
    @Test
    public void ifTenQuestionsAreAnsweredCorrectlyScoreIsTen() {
        this.gameService.startANewGame();
        for (int i = 0; i < 10; i++) {
            this.gameService.answerTheQuestion(true);
        }
        assertEquals("10/10 oikein, täydellistä!!", this.gameService.getTotalScore());
    }
    
    @Test
    public void ifSevenQuestionsAreAnsweredCorrectlyScoreIsSeven() {
        this.gameService.startANewGame();
        for (int i = 0; i < 7; i++) {
            this.gameService.answerTheQuestion(true);
        }
        assertEquals("7/10 oikein, vau melkoinen tietäjä!", this.gameService.getTotalScore());
    }
    
    @Test
    public void ifFiveQuestionsAreAnsweredCorrectlyAndFiveWrongScoreIsFive() {
        this.gameService.startANewGame();
        for (int i = 0; i < 5; i++) {
            this.gameService.answerTheQuestion(true);
            this.gameService.answerTheQuestion(false);
        }
        assertEquals("5/10 oikein, aika hyvin jo!", this.gameService.getTotalScore());
    }
    
    
    
    @Test
    public void highScoreListIsEmptyIfNoGamesHaveBeenPlayed() {
        assertEquals("", this.gameService.highScoreList());
    }

    
    @Test 
    public void highScoreListShowsThreePlayedGamesCorrectly() {
        this.gameService.startANewGame();
        this.gameService.answerTheQuestion(true);
        this.gameService.answerTheQuestion(true);
        this.gameService.finishAGame();
        this.gameService.startANewGame();
        this.gameService.finishAGame();
        this.gameService.startANewGame();
        this.gameService.answerTheQuestion(true);
        this.gameService.finishAGame();
        
        assertEquals("1. Käyttäjätunnus  2/10" + "\n" + "\n" 
                + "2. Käyttäjätunnus  1/10" + "\n" + "\n" 
                + "3. Käyttäjätunnus  0/10" + "\n" + "\n", 
                this.gameService.highScoreList());
    }


}
