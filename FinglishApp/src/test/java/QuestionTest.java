/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import finglish.domain.Question;
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
public class QuestionTest {
    
    Question question;
    
    public QuestionTest() {
    }
    
    @Before
    public void setUp() {
        question = new Question("question", "first option", "second option", "third option", "fourth option", "correct answer");
    }
    
    
    @Test
    public void methodGetQuestionReturnsThisQuestion() {
        assertEquals("question", this.question.getQuestion());
    }
    
    @Test
    public void questionCanBeSet() {
        this.question.setQuestion("question changed");
        assertEquals("question changed", this.question.getQuestion());
    }
    
    @Test
    public void answerCheckingReturnsTrueWhenTheCorrectAnswerIsGiven() {        
        assertTrue(this.question.checkIfCorrect("correct answer"));
    }

    @Test
    public void answerCheckingReturnsFalseWhenWrongAnswerIsGiven() {        
        assertFalse(this.question.checkIfCorrect("wrong answer"));
    }
    
    @Test
    public void givenOptionsCounterIsSetToZeroInitially(){
        assertEquals(0, this.question.getOptionCounter());
    }
    
    @Test
    public void firstOptionIsReturnedCorrectly() {
        assertEquals("first option", this.question.getOption());
    }
    
    @Test
    public void secondOptionIsReturnedCorrectly() {
        this.question.getOption();
        assertEquals("second option", this.question.getOption());
    }

    @Test
    public void thirdOptionIsReturnedCorrectly() {
        this.question.getOption();
        this.question.getOption();
        assertEquals("third option", this.question.getOption());
    }

    @Test
    public void fourtOptionIsReturnedCorrectly() {
        this.question.getOption();
        this.question.getOption();
        this.question.getOption();
        assertEquals("fourth option", this.question.getOption());
    }
    
    @Test
    public void thereIsNoFifthOption() {
        this.question.getOption();
        this.question.getOption();
        this.question.getOption();
        this.question.getOption();
        assertEquals("", this.question.getOption());
    }



}
