/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglish.domain;

import finglish.domain.Game;
import finglish.dao.QuestionDao;
import finglish.dao.UserDao;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Random;

public class GameService {

    private ArrayList<Question> allQuestions;
    private ArrayList<Integer> usedIndexes;
    private Game game;
    private QuestionDao questionDao;
    private UserDao userDao;
    private Random random;
    private Integer index;

    public GameService(QuestionDao questionDao) {
        this.questionDao = questionDao;
        this.allQuestions = questionDao.getAll();
        this.random = new Random();
    }

    public void addQuestion(Question question) {
        this.allQuestions.add(question);
    }

    public Question getTheNextQuestion() {

        if (this.game.getQuestionCounter() == 10) {
            return null;
        }
        
        if (this.game.getQuestionCounter() == 1) {
            this.usedIndexes.add(index);
        }

        index = this.random.nextInt(allQuestions.size() - 1);

        if (this.usedIndexes.size() == 0 || this.game.getQuestionCounter() == 1) {
            this.usedIndexes.add(index);
        }

        if (usedIndexes.size() != 1) {
            while (this.usedIndexes.contains(index)) {
                index = this.random.nextInt(allQuestions.size() - 1);
            }
        }

        Question question = allQuestions.get(index);
        this.usedIndexes.add(index);
        question.shuffleOptions();
         
        return question;
    }

    public void setWrongAnswer(Question question) {
        this.game.setAnAnsweredQuestion(question);

    }

    public void setACorrectAnswer(Question question) {
        this.game.setAnAnsweredQuestion(question);
        this.game.setACorrectAnswer();

    }

    public void startANewGame() {
        this.game = new Game();
        this.usedIndexes = new ArrayList<>();

    }

    public void finishAGame() {
        this.usedIndexes.clear();
    }

    public String getTotalScore() {
        return this.game.getAmountOfCorrectAnswers() + "/10 oikein";
    }
    
    public String getAmountOfAnswered() {
        return (this.game.getQuestionCounter()+1) + "/10";
    }

}
