

package finglish.dao;
import finglish.domain.User;
import java.util.ArrayList;

public interface UserDao {
    
    User create(User user) throws Exception;
    ArrayList<User> getAll();
    User findByUsername(String username);  
}
