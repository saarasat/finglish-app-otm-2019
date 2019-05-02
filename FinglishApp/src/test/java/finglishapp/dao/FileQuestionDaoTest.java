package finglishapp.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import finglish.dao.FileQuestionDao;
import finglish.dao.QuestionDao;
import finglish.domain.Question;
import finglishapp.domain.FakeQuestionDao;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author saarasat
 */
public class FileQuestionDaoTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
  
    File questionFile;
    FileQuestionDao questionDao;
    
    @Before
    public void setUp() throws Exception {
        questionFile = testFolder.newFile("testQuestions.txt");
        questionDao = new FileQuestionDao("testQuestions.txt");
        
    }
    
    @Test
    public void methodForGettingQuestionsWorksInitially() {
        List<Question> questions = questionDao.getAll();
        assertEquals(0, questions.size());      
    }    
    
}
