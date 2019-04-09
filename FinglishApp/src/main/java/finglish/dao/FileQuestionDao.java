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
            Scanner reader = new Scanner(new File("questions.txt"));
            while (reader.hasNextLine()) {
                String[] pieces = reader.nextLine().split(";");
                Question q = new Question(pieces[1], pieces[2], pieces[3], pieces[4], pieces[5], pieces[6]);
                questions.add(q);
            }
        } catch (Exception e) {
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
        
        return question;
    }
    
}
