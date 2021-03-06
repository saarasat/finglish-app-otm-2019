
package finglishapp.dao;

import finglish.dao.FileQuestionDao;
import finglish.domain.Question;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import static org.junit.Assert.*;


public class FileQuestionDaoTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
  
    File questionFile;
    FileQuestionDao questionDao;
    
    @Before
    public void setUp() throws Exception {
        questionFile = testFolder.newFile("testQuestions.txt");
        
        try(FileWriter file = new FileWriter(questionFile.getAbsolutePath())) {
            file.write("1;Kysymys1?;Vaihtoehto1a;Vaihtoehto1b;Vaihtoehto1c;Vaihtoehto1d;Oikeavastaus1\n");
            file.write("2;Kysymys2?;Vaihtoehto2a;Vaihtoehto2b;Vaihtoehto2c;Vaihtoehto2d;Oikeavastaus2\n");
        }     
        questionDao = new FileQuestionDao(questionFile.getAbsolutePath());      
    }
    
    @Test
    public void allQuestionsAreReadFromAFile() {
        ArrayList<Question> allQuestions = questionDao.getAll();
        assertEquals(2, allQuestions.size());      
    }   
    
    @Test
    public void questionsAreReadCorrectlyFromAFile() {
        ArrayList<Question> allQuestions = questionDao.getAll();
        assertEquals("Kysymys1?", allQuestions.get(0).getQuestion());
        assertEquals("Kysymys2?", allQuestions.get(1).getQuestion());
    }
    
    @Test
    public void questionIdsAreSetCorrectlyAfterReadingFromAFile() {
        ArrayList<Question> allQuestions = questionDao.getAll();
        assertEquals(1, allQuestions.get(0).getId());
        assertEquals(2, allQuestions.get(1).getId());
    }

    @Test
    public void aValidQuestionCanBeCreated() throws Exception {
        Question question = questionDao.create(new Question("Kysymys3",
                "Vaihtoehto3a", 
                "Vaihtoehto3b", 
                "Vaihtoehto3c", 
                "Vaihtoehto3d",
                "Vastaus"));
        assertEquals("Vastaus", question.getCorrectAnswer());
    }
    
    @After
    public void tearDown() {
        questionFile.delete();
    }
    
}
