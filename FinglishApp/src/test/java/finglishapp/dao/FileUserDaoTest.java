
package finglishapp.dao;

import finglish.dao.FileUserDao;
import finglish.dao.UserDao;
import finglish.domain.User;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import static org.junit.Assert.*;


public class FileUserDaoTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File userFile;
    UserDao userDao;    
    
    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("test_users.txt");
        
        try(FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
            file.write("1;Käyttäjä;Salasana;1\n");
            file.write("2;Käyttäjä2;Salasana2;0\n");
        }        
        userDao = new FileUserDao(userFile.getAbsolutePath());
    }
 
    @Test
    public void allUsersAreReadFromAFile() {
        ArrayList<User> allUsers = userDao.getAll();
        assertEquals(2, allUsers.size());
    }
    
    
    @Test
    public void usersAreReadCorrectlyFromAFile() {
        ArrayList<User> allUsers = userDao.getAll();
        assertEquals("Käyttäjä", allUsers.get(0).getUsername());
        assertEquals("Käyttäjä2", allUsers.get(1).getUsername());      
    }
    
    @Test
    public void usersIdsAreSetCorrectlyAfterReadingFromAFile() {
        ArrayList<User> allUsers = userDao.getAll();
        assertEquals(1, allUsers.get(0).getId());
        assertEquals(2, allUsers.get(1).getId());      
    }
    
    @Test
    public void existingUserCanBeFoundById() {
        
        assertEquals("Käyttäjä", userDao.findById(1));
        assertEquals("Käyttäjä2", userDao.findById(2));      
    }
    
    @Test
    public void nonExistingUserCanNotBeFoundById() {   
        assertEquals(null, userDao.findById(3));
    }
    
    @Test
    public void existingUserCanBeFoundByString() {
        assertEquals("Käyttäjä", userDao.findByUsername("Käyttäjä").getUsername());
        assertEquals("Käyttäjä2", userDao.findByUsername("Käyttäjä2").getUsername());      
    }

    @Test
    public void nonExistingUserIsNotFoundByString() {
        assertEquals(null, userDao.findByUsername("Käyttäjä3"));
    }
    
    @Test
    public void aValidUserCanBeCreated() throws Exception {
        assertEquals("Käyttäjä3", userDao.create(new User("Käyttäjä3", "Salasana3", 1)).getUsername());
        
    }
    
    @Test
    public void userCanBeDeleted() throws Exception {   
        this.userDao.delete(1);
        assertEquals(null, userDao.findById(1));        
    }
    
    @After
    public void tearDown() {
        userFile.delete();
    }
       
}
