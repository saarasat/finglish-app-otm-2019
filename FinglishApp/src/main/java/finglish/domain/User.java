/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglish.domain;

/**
 *
 * @author saarasat
 */
public class User {
    private String username;
    private String password;
    private int id;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;      
    }
       
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
       
}
