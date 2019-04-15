/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglish.ui;

import finglish.dao.UserDao;
import finglish.domain.GameService;
import finglish.domain.User;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginView {
    
    private TextField usernameText; 
    private TextField passwordText; 
    private GameService gameService;
    private UserDao userDao;
    private ArrayList<User> users;
    
    public LoginView(GameService gameService, UserDao userDao, ArrayList<User> users) {
        this.gameService = gameService;
        this.userDao = userDao;
       
    }
    
    public Parent getView() {

        GridPane setting = new GridPane();
                
        Label usernameLabel = new Label("Käyttäjätunnus:");
        usernameText = new TextField(); 
        Label passwordLabel = new Label("Salasana:");
        passwordText = new TextField(); 
                
        
        
        setting.setAlignment(Pos.CENTER);
        setting.setVgap(10);
        setting.setHgap(10);
        setting.setPadding(new Insets(10,10,10,10));
        
        setting.add(usernameLabel, 0, 2);
        setting.add(usernameText, 0, 3);
        setting.add(passwordLabel, 0, 4);
        setting.add(passwordText, 0, 5);
          
        
        return setting;
        
    }
    
    public String getUsername() {
        return usernameText.getText();
    }

    public String getPassword() {
        return passwordText.getText();
    }    
    
}
