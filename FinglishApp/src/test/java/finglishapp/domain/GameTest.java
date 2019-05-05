
package finglishapp.domain;

import finglish.domain.Game;
import finglish.domain.Question;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class GameTest {
    
    private Game game;
    private ArrayList<Question> allQuestions;
    
    @Before
    public void setUp() {
        allQuestions = new ArrayList<>();
        allQuestions.add(new Question("question", "first option", "second option", "third option", "fourth option", "correct answer"));
        this.game = new Game();
    }
    
    @Test
    public void idCanBeSet() {
        this.game.setId(1);
        assertEquals(1, this.game.getId());
    }
    
    @Test
    public void amountOfCorrectAnswersCanBeGet() {
        this.game.setACorrectAnswer();
        this.game.setACorrectAnswer();
        assertEquals(2, this.game.getAmountOfCorrectAnswers());
    }
    
}
