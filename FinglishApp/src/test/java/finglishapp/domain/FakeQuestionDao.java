/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglishapp.domain;

import finglish.dao.QuestionDao;
import finglish.domain.Question;
import java.util.ArrayList;
import java.util.List;
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
public class FakeQuestionDao implements QuestionDao {
    
    ArrayList<Question> allQuestions;

    public FakeQuestionDao() {
        allQuestions = new ArrayList<>();
    }   
   
    @Override
    public ArrayList<Question> getAll() {
        return allQuestions;
    }
    
    @Override
    public Question create(Question question) {
        question.setId(allQuestions.size()+1);
        allQuestions.add(question);
        return question;
    }   
}
