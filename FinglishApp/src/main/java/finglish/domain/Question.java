
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
    
    
    /**
    * Checks if the answer user has clicked on is correct.
    *
    * @param answered the String-form answer option on the button the user has chosen.
    * 
    * @return boolean-information on whether the chosen answer matched the correct answer for this question.
    */
            
    public boolean checkIfCorrect(String answered) {
        return answered.equals(this.correctAnswer);
    }
    
    /**
    * Gets all four options for a question.
    *
    * Uses a counter to return all the four options for this question out of the option-array.
    * 
    * @return A answer option as a String. Or an empty String if no options are left.
    */
         
    public String getOption() {   
        if (counter < 4) {
            counter++;
            return answerOptions[counter - 1];    
        }
        return "";
    }
    
    
    /**
    * Shifts the order of the answer options.
    *
    * Randomizes the order of the option-array, so that the users would encounter the same options in different order.
    * 
    */
    
    public void shuffleOptions() {
        this.counter = 0;
        String[] options = new String[4];
        ArrayList<Integer> indexes = new ArrayList<>();
        int index;
        
        for (int i = 0; i < 4; i++) {
            options[i] = answerOptions[i];
        }
        
        for (int i = 0; i< 4; i++) {
            index = randomizer(4);
            while (indexes.contains(index)) {
                index = randomizer(4);;
            }
            answerOptions[i] = options[index];
            indexes.add(index);
        }
    }
    
    private int randomizer(int i) {
        Random random = new Random();
        return random.nextInt(i);
    }
    
    @Override
    public String toString() {
        return getQuestion();
    }
  
}
