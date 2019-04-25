
package finglish.domain;

import java.util.ArrayList;
import java.util.Random;

public class Question {
    
    private String question;
    private String[] answerOptions;
    private String correctAnswer;
    private int id;
    private int counter;
    
    public Question(int id, String question, String first, String second, String third, String fourth, String correctAnswer) {
        this.id = id;
        this.question = question;
        this.answerOptions = new String[4];
        this.answerOptions[0] = first;
        this.answerOptions[1] = second;
        this.answerOptions[2] = third;
        this.answerOptions[3] = fourth;
        this.correctAnswer = correctAnswer;       
    }       

    public Question(String question, String first, String second, String third, String fourth, String correctAnswer) {
        this.question = question;
        this.answerOptions = new String[4];
        this.answerOptions[0] = first;
        this.answerOptions[1] = second;
        this.answerOptions[2] = third;
        this.answerOptions[3] = fourth;
        this.correctAnswer = correctAnswer;       
    }        
    
    public int getId() {
        return this.id;
    }
    
    public String getQuestion() {
        return this.question;
    }
    
    public String[] getAnswerOptions() {
        return this.answerOptions;
    }
        
    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    public int getOptionCounter() {
        return this.counter;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setQuestion(String question) {
        this.question = question;
    }
            
    public boolean checkIfCorrect(String answered) {
        return answered.equals(this.correctAnswer);
    }
    
    public String getOption() {   
        if (counter < 4) {
            counter++;
            return answerOptions[counter - 1];    
        }
        return "";
    }
    
    public void shuffleOptions() {
        int index;
        String handled;
        Random random = new Random();
        for (int i = 3; i > 0; i--) {
            index = random.nextInt(i + 1);
            handled = answerOptions[index];
            answerOptions[index] = answerOptions[i];
            answerOptions[i] = handled;
        }
    }
    
    @Override
    public String toString() {
        return getQuestion();
    }
  
}
