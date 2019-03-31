/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglish.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    
    private int id; 
    private List<Question> allQuestions;
    private List<Integer> usedQuestions;
    private Random random;
    private Question question;
    private int questionCounter;
    
    public Game() {
        this.allQuestions = new ArrayList<>();
        this.usedQuestions = new ArrayList<>();
        this.random = new Random();
        this.questionCounter = 0;
    }
    
    public int getId() {
        return this.id;
    }
    
    public Question getNextQuestion() {
        int index = this.random.nextInt(allQuestions.size());
        this.question = allQuestions.get(index);
        if (!usedQuestions.contains(index)) {
            usedQuestions.add(question.getId());
            this.question.shuffleOptions();
        } else {
            getNextQuestion();
        }
        return this.question;
    }
    
    public boolean answerTheQuestion(String option) {
        if (this.question.getCorrectAnswer().equals(option)) {
            return true;
        }
        return false;
    }
    
    public void addQuestion(Question question) {
        System.out.println(question.getCorrectAnswer());
        this.allQuestions.add(question);
    }
      
}
