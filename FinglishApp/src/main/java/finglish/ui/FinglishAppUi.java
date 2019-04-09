/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglish.ui;

import finglish.dao.FileQuestionDao;
import finglish.dao.QuestionDao;
import finglish.dao.FileUserDao;
import finglish.dao.UserDao;



import java.util.List;
import java.util.Properties;
import finglish.domain.GameService;
import finglish.domain.User;
import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
 

public class FinglishAppUi extends Application {
    
    private GameService gameService;
    private FileQuestionDao questionDao;
    private FileUserDao userDao;
    private ArrayList<User> users;
    
    @Override
    public void init() throws Exception {
        String questionFile = "questions.txt";
        String userFile = "users.txt";
        this.questionDao = new FileQuestionDao(questionFile);
        this.userDao = new FileUserDao(userFile);
        gameService = new GameService(questionDao);
        
    }
    
    
    @Override 
    public void start(Stage startScreen) throws Exception {    
        
        GameView gameView = new GameView(gameService);
        AddQuestionsView addQuestionsView = new AddQuestionsView(gameService);
        LoginView loginView = new LoginView();
        
        BorderPane setting = new BorderPane();

        HBox loginBox = new HBox();
        loginBox.setPadding(new Insets(40,40,40,40));
        loginBox.setSpacing(10);
        
        Button loginButton = new Button("Kirjaudu");
        Label loginInfo = new Label("");
        
        HBox menu = new HBox();
        menu.setPadding(new Insets(20,20,20,20));
        menu.setSpacing(10);
        
        Button playAGame = new Button("Pelaa");
        Button addAQuestion = new Button("Lisää kysymys");
        Button logOut = new Button("Kirjaudu ulos"); 
        
        loginBox.getChildren().addAll(loginInfo, loginButton);
        menu.getChildren().addAll(playAGame, addAQuestion);
        
        playAGame.setOnMouseClicked((event) -> {
            setting.setCenter(gameView.getView());
            gameService.startANewGame();
        });
        
        addAQuestion.setOnAction((event) -> setting.setCenter(addQuestionsView.getView()));
       
        this.users = userDao.getAll();
        
        loginButton.setOnMouseClicked((event) -> {
            String username = loginView.getUsername();
            System.out.println(username);
            User user = userDao.findByUsername(loginView.getUsername());
            if (user == null) {
                loginInfo.setText("Käyttäjätunnusta ei löydy");
            }
            String password = user.getPassword();
            if (loginView.getPassword().equals(password)) {
                setting.setCenter(menu);
                setting.setBottom(logOut);
            }
        });
        
        logOut.setOnMouseClicked((event) -> {
        
            setting.setTop(loginInfo);
            setting.setBottom(loginBox);
            setting.setCenter(loginView.getView());
            
        });
        
        setting.setTop(loginInfo);
        setting.setBottom(loginBox);
        setting.setCenter(loginView.getView());

        
        Scene start = new Scene(setting, 700, 400);
        startScreen.setScene(start);
        startScreen.show();
           
    }
    
    
    public static void main(String[] args) {
        launch(args);
        System.out.println("terve");
    }
    
}
