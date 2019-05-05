
package finglishapp.domain;


import finglish.domain.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class UserTest {
    
    User user;    
    
    @Before
    public void setUp() {
        user = new User("username", "password", 1);
        this.user.setId(1);
    }
    
    @Test
    public void methodGetUsernameReturnsUsername() {
        assertEquals("username", this.user.getUsername());
    }
    
    @Test 
    public void methodGetPasswordReturnsPassword() {
        assertEquals("password", this.user.getPassword());
    }
    
    @Test 
    public void methodGetIdReturnsId() {
        assertEquals(1, this.user.getId());
    }
    
    @Test
    public void methodGetAdminIdReturnsAdmin() {
        this.user.setAdmin();
        assertEquals(1, this.user.getAdmin());
    }
    
    @Test
    public void idCanBeSet() {
        this.user.setId(1);
        assertEquals(1, this.user.getId());
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
