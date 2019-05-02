
package finglish.dao;

import finglish.domain.User;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

 
public class FileUserDao implements UserDao {
    private ArrayList<User> users;
    private String file;
    
    public FileUserDao(String file) throws Exception {
        users = new ArrayList<>();
        this.file = file;
        
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] pieces = reader.nextLine().split(";");
                int id = Integer.parseInt(pieces[0]);
                User u = new User(pieces[1], pieces[2], Integer.valueOf(pieces[3]));
                users.add(u);
                u.setId(id);
                System.out.println(u.getUsername());
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
            System.out.println("No file");
        }
    }
    
    /**
    * Generates a serial Id for the user.
    * 
    * @return an Integer value to be used as the User id.
    */
    
    private int generateId() {
        int lastId = users.get(users.size() - 1).getId();
        return lastId + 1;
    }
    
    /**
    * Uses the username to find a user out of all users.
    * 
    * @return a User which has the same username as the one searched for.
    */
    
    @Override
    public User findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    
    @Override
    public String findById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user.getUsername();
            }
        }
        return null;
    }
    
 


    
    
    /**
    * Gets all the users stored.
    * 
    * @return an arraylist of all users.
    */
        
    @Override
    public ArrayList<User> getAll() {
        return users;
    }
    
       
    /**
    * Adds a User for permanent keeping.
    * Overrides the method of UserDao for create.
    * 
    * @param user takes in an object of the User-class, without the id, which is generated in this method.
    * 
    * @return a successfully created and saved new User.
    */
    
    @Override
    public User create(User user) throws Exception {
        user.setId(generateId());
        users.add(user);
        saveNewUser();
        return user;
    }
    
    /**
    * Saves the new user for permanent keeping.
    * Uses a filewriter to write the user in the user-file. Private method for this class.
    * 
    */
    
    private void saveNewUser() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (User user : users) {
                writer.write(user.getId() 
                    + ";" + user.getUsername() 
                    + ";" + user.getPassword() 
                    + ";" + user.getAdmin() + "\n");
            }
        }
    }
    
    @Override
    public void deleteUser(int id) throws Exception {
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
            }
        }
        saveNewUser();
    }
    
    
}
