/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglish.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import finglish.domain.Question;

/**
 *
 * @author saarasat
 */
public class FileQuestionDao implements QuestionDao {
    
    private ArrayList<Question> questions;
    private String file;
    
    public FileQuestionDao(String file) throws Exception {
        questions = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] pieces = reader.nextLine().split(";");
                int id = Integer.parseInt(pieces[0]);
                Question q = new Question(id, pieces[1], pieces[2], pieces[3], pieces[4], pieces[5], pieces[6]);
                questions.add(q);
                q.setId(id);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
            System.out.println("No file");
        }
    }
    
    private int generateId() {
        return questions.size() + 1;
    }
    
    @Override
    public ArrayList<Question> getAll() {
        return questions;
    }
    
    @Override
    public Question create(Question question) throws Exception {
        question.setId(generateId());
        questions.add(question);
        saveNewQuestion();
        return question;
    }
    
    private void saveNewQuestion() throws Exception{
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Question question : questions) {
                writer.write(question.getId() + 
                        ";" + question.getQuestion() 
                        + ";" + question.getOption() 
                        + ";" + question.getOption() 
                        + ";" + question.getOption() 
                        + ";" + question.getOption() 
                        + ";" + question.getOption()
                        + ";" + question.getCorrectAnswer() + "\n");
                System.out.println(question.getQuestion());
            }
        }
    }   
    
}
