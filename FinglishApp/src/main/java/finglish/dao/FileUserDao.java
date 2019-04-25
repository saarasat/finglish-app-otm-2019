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
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] pieces = reader.nextLine().split(";");
                int id = Integer.parseInt(pieces[0]);
                User u = new User(pieces[1], pieces[2]);
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
        saveNewUser();
        return user;
    }
    
    private void saveNewUser() throws Exception{
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (User user : users) {
                writer.write(user.getId() 
                + ";" + user.getUsername()
                + ";" + user.getPassword() + "\n");
            }
        }
    }
    
    
}
