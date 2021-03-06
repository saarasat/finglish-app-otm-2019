
package finglish.domain;

public class Game implements Comparable<Game>{
    
    private int id;
    private int accountId;
    private int questionCounter;
    private int correctAnswers;
    private int wrongAnswers;
    
    public Game() {
        this.questionCounter = 0;
        this.correctAnswers = 0;
        this.wrongAnswers = 0;
    }
    
    public Game(int accountId, int correctAnswers, int questionCounter) {
        this.accountId = accountId;
        this.questionCounter = questionCounter;
        this.correctAnswers = correctAnswers;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getAccountId() {
        return this.accountId;
    }
    
    public int getAmountOfCorrectAnswers() {
        return correctAnswers;
    }
    
    public void setACorrectAnswer() {
        this.correctAnswers++;
    }
    
    public void setAWrongAnswer() {
        this.wrongAnswers++;
    }
    
    public void setQuestionCounter(){
        this.questionCounter++;
    }
    
    public int getQuestionCounter() {
        return questionCounter;
    }
    
    @Override
    public int compareTo(Game other) {
        return other.getAmountOfCorrectAnswers() - this.getAmountOfCorrectAnswers();
    }
      
}
