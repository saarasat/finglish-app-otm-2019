/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglish.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author saarasat
 */
public class LoginView {
    
    
    public LoginView() {
       
    }
    
    public Parent getView() {

        GridPane setting = new GridPane();
                
        Label usernameLabel = new Label("Username:");
        TextField usernameText = new TextField(); 
        Label passwordLabel = new Label("Password:");
        TextField passwordText = new TextField();        
        Button login = new Button("Login");
        
        setting.setAlignment(Pos.CENTER);
        setting.setVgap(10);
        setting.setHgap(10);
        setting.setPadding(new Insets(10,10,10,10));
        
        setting.add(usernameLabel, 0, 0);
        setting.add(usernameText, 0, 1);
        setting.add(passwordLabel, 0, 2);
        setting.add(passwordText, 0, 3);
        
        return setting;
        
        
    }

    
    
}
