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
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 *
 * @author saarasat
 */
public class AdminView {
    
    private GameService gameService;
    private UserDao userDao;
    private ArrayList<User> users;
    private User user;
    private GridPane adminSetting;
    private Label usernameLabel;
    

    public AdminView (GameService gameService, UserDao userDao, ArrayList<User> users, User user) {
        this.gameService = gameService;
        this.userDao = userDao;
        this.users = userDao.getAll();
        this.user = user;
       
    }
    
    public Parent getView() {
        
        adminSetting = new GridPane();        
        usernameLabel = new Label("Käyttäjätunnus");
        
        adminSetting.setAlignment(Pos.CENTER);
        adminSetting.setVgap(10);
        adminSetting.setHgap(10);
        adminSetting.setPadding(new Insets(10,10,10,10));
        resetUserList();
        
        return adminSetting;
        
    }
    
    public void resetUserList() {
        
        adminSetting.getChildren().clear();
        
        if (user == null) {
            
        }
        
        adminSetting.add(usernameLabel, 0, 2);
        
        if (this.user.getAdmin() == 1) {
            for (int i = 0; i < users.size(); i++) {
                adminSetting.add(createUserNode(users.get(i), 1), 0, i+3);
            }
        } else {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId() == user.getId()) {
                    adminSetting.add(createUserNode(users.get(i), 1), 0, i+3);
                }
            }
            usernameLabel.setText("Haluatko poistaa oman käyttäjätilisi?");
        }
     
    }
    
    public Node createUserNode(User user, int admin) {
        HBox userBox = new HBox(10);
        Label userLabel  = new Label(user.getUsername());
        userLabel.setMinHeight(30);
        Button deleteButton = new Button("Poista");
        if (admin == 0) {
            deleteButton.setText("Poista tämä käyttäjätili");
        } 
        deleteButton.setOnAction(e->{
            gameService.removeUser(user.getId());
            resetUserList();
        });
                
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        userBox.setPadding(new Insets(0,5,0,5));
        
        userBox.getChildren().addAll(userLabel, spacer, deleteButton);
        return userBox;
    }


    
}
