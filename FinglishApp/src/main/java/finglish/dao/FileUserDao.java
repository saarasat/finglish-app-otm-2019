/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
            Scanner reader = new Scanner(new File("users.txt"));
            while (reader.hasNextLine()) {
                String[] pieces = reader.nextLine().split(";");
                User u = new User(pieces[2], pieces[3]);
                u.setId(Integer.valueOf(pieces[1]));
                users.add(u);
            }
        } catch (Exception e) {
            System.out.println("No userfile");
        }
        User testi = new User("testaaja", "salasana");
        User tokaTesti = new User("testaaja2", "salasana2");
        users.add(testi);
        users.add(tokaTesti);
    }
    
    private int generateId() {
        return users.size() + 1;
    }
    
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
    public ArrayList<User> getAll() {
        return users;
    }
    
    @Override
    public User create(User user) throws Exception {
        user.setId(generateId());
        users.add(user);
        
        return user;
    }
    
    
}
