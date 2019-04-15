/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglishapp.domain;

import finglish.dao.FileQuestionDao;
import finglish.dao.QuestionDao;
import finglish.domain.Game;
import finglish.domain.GameService;
import finglish.domain.Question;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
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
public class GameServiceTest {
    
    GameService gameService;
    FakeQuestionDao questionDao;
    Question question;
    
    public GameServiceTest() throws Exception {
        this.questionDao = new FakeQuestionDao();
        this.questionDao.create(new Question("question1", "first option1", "second option1", "third option1", "fourth option1", "correct answer1"));
        this.questionDao.create(new Question("question2", "first option2", "second option2", "third option2", "fourth option2", "correct answer2"));
        this.questionDao.create(new Question("question3", "first option3", "second option3", "third option3", "fourth option3", "correct answer3"));

    }
    
    @Before
    public void setUp() {
        gameService = new GameService(questionDao);
        this.question = new Question("question1", "first option1", "second option1", "third option1", "fourth option1", "correct answer1");
    }
    
    @Test
    public void getTheNextQuestionReturnsTheQuestionTextCorrectly() {
        this.gameService.startANewGame();
        assertTrue(this.gameService.getTheNextQuestion().getQuestion().equals("question1") || 
            this.gameService.getTheNextQuestion().getQuestion().equals("question2") ||
            this.gameService.getTheNextQuestion().getQuestion().equals("question3")
        );
    }
    
    @Test
    public void ifTenQuestionsAreAnsweredWrongScoreIsZero() {
        this.gameService.startANewGame();
        for (int i = 0; i < 10; i++) {
            this.gameService.setWrongAnswer(question);
        }
        assertEquals("0/10 oikein", this.gameService.getTotalScore());
    }
    
    @Test
    public void ifTenQuestionsAreAnsweredCorrectlyScoreIsTen() {
        this.gameService.startANewGame();
        for (int i = 0; i < 10; i++) {
            this.gameService.setCorrectAnswer(question);
        }
        assertEquals("10/10 oikein", this.gameService.getTotalScore());
    
    }
    
    @Test
    public void getQuestionNumberReturnsTheAmountCorrectlyAfterFiveQuestions() {
        this.gameService.startANewGame();
        for (int i = 0; i < 5; i++) {
            this.gameService.setCorrectAnswer(question);        
        }
        assertEquals("6/10", this.gameService.getTheQuestionNumber());
    }
        
}