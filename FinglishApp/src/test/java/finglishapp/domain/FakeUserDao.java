
package finglishapp.domain;

import finglish.dao.UserDao;
import finglish.domain.User;

import java.util.ArrayList;
    
public class FakeUserDao implements UserDao {
    
    ArrayList<User> allUsers;

    public FakeUserDao() {
        allUsers = new ArrayList<>();
    }   
   
    @Override
    public ArrayList<User> getAll() {
        return allUsers;
    }
    
    @Override
    public User create(User user) {
        user.setId(allUsers.size()+1);
        allUsers.add(user);
        return user;
    }
    
    @Override
    public User findByUsername(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    
    @Override
    public String findById(int id) {
        for (User user : allUsers) {
            if (user.getId() == id) {
                return user.getUsername();
            }
        }
        return null;
    } 
    
    @Override
    public void delete(int id) {
        for (User user : allUsers) {
            if (user.getId() == id) {
                System.out.println(user.getId());
                System.out.println(id);
                allUsers.remove(user);
            }
        }
    }
    
}