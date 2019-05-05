
package finglishapp.domain;


import finglish.domain.Question;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class QuestionTest {
    
    Question question;
    
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
    
    @Test
    public void firstOptionIsReturnedAfterShuffling() {
        this.question.shuffleOptions();
        ArrayList<String> options = new ArrayList<>();
        options.add(this.question.getOption());
        options.add(this.question.getOption());
        options.add(this.question.getOption());
        options.add(this.question.getOption());
        assertTrue(options.contains("first option"));    
    }

    @Test
    public void secondOptionIsReturnedAfterShuffling() {
        this.question.shuffleOptions();
        ArrayList<String> options = new ArrayList<>();
        options.add(this.question.getOption());
        options.add(this.question.getOption());
        options.add(this.question.getOption());
        options.add(this.question.getOption());
        assertTrue(options.contains("second option"));    
    }

    @Test
    public void thirdOptionIsReturnedAfterShuffling() {
        this.question.shuffleOptions();
        ArrayList<String> options = new ArrayList<>();
        options.add(this.question.getOption());
        options.add(this.question.getOption());
        options.add(this.question.getOption());
        options.add(this.question.getOption());
        assertTrue(options.contains("third option"));    
    }

    @Test
    public void fourthOptionIsReturnedAfterShuffling() {
        this.question.shuffleOptions();
        ArrayList<String> options = new ArrayList<>();
        options.add(this.question.getOption());
        options.add(this.question.getOption());
        options.add(this.question.getOption());
        options.add(this.question.getOption());
        assertTrue(options.contains("fourth option"));    
    }
    
    @Test
    public void methodToStringReturnsTheQuestion() {
        assertEquals("question", question.toString());
    }

}
