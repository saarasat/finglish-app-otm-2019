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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 

public class FinglishAppUi extends Application {
    
    private GameService gameService;
    private FileQuestionDao questionDao;
    private FileUserDao userDao;
    private ArrayList<User> users;
    private GameView gameView;
    
    @Override
    public void init() throws Exception {
        String questionFile = "questions.txt";
        String userFile = "users.txt";
        this.questionDao = new FileQuestionDao(questionFile);
        this.userDao = new FileUserDao(userFile);
    }
    
    @Override 
    public void start(Stage startScreen) throws Exception {    
             
        this.users = userDao.getAll();
                
        // creating the scene for logging in
        BorderPane loginSetting = new BorderPane();
        LoginView loginView = new LoginView(gameService, userDao, users);
        VBox loginBox = new VBox();
        loginBox.setPadding(new Insets(20,20,20,20));
        loginBox.setSpacing(10);
       
        Button loginButton = new Button("Kirjaudu");
        Label loginInfo = new Label("");
        Label newUserLabel = new Label("Ei vielä tunnusta?");
        Button newUser = new Button("Luo uusi tunnus");

        loginBox.getChildren().addAll(loginInfo, loginView.getView(), loginButton, newUserLabel, newUser);
        
        loginSetting.setCenter(loginBox);
        Scene loginScene = new Scene(loginSetting, 400, 400);
                 
        
        //creating the scene for creating a new User
        BorderPane newUserSetting = new BorderPane();
        CreateUserView createUserView = new CreateUserView();
        VBox userBox = new VBox();
        userBox.setPadding(new Insets(20,20,20,20));
        userBox.setSpacing(10);
        
        Button createUserButton = new Button("Luo tunnus");
        
        userBox.getChildren().addAll(createUserView.getView(), createUserButton);
        newUserSetting.setCenter(userBox);
        Scene newUserScene = new Scene(newUserSetting, 400,400);
        
        //creating the scene for main menu
        BorderPane menuSetting = new BorderPane();
        VBox menuBox = new VBox();
        menuBox.setPadding(new Insets(20,20,20,20));
        menuBox.setSpacing(10);
        
        Label welcomeLabel = new Label("Tervetuloa pelaamaan Finglishia!");
        Button playAGame = new Button("Pelaa");
        Button addAQuestion = new Button("Lisää kysymys");
        Button logOut = new Button("Kirjaudu ulos");         
        menuBox.getChildren().addAll(welcomeLabel, playAGame, addAQuestion, logOut);
        
        menuSetting.setCenter(menuBox);
        Scene menuScene = new Scene(menuSetting, 400,400);
     
        
        //creating the scene for playing
        BorderPane gameSetting = new BorderPane();
        VBox gameBox = new VBox();
        gameBox.setPadding(new Insets(20,20,20,20));
        gameBox.setSpacing(10);
        
        gameService = new GameService(questionDao);
        gameView = new GameView(gameService);
        Button endGame = new Button("Lopeta peli");
        gameBox.getChildren().addAll(gameView.getView(),endGame);

        gameSetting.setCenter(gameBox);
        Scene gameScene = new Scene(gameSetting, 700, 400);

        
        //creating the scene for adding questions
        BorderPane addQuestionSetting = new BorderPane();
        VBox additionBox = new VBox();
        additionBox.setPadding(new Insets(20,20,20,20));
        additionBox.setSpacing(10);
        
        AddQuestionsView addQuestionsView = new AddQuestionsView(gameService);
        Button backToMenu = new Button("Takaisin valikkoon");
        additionBox.getChildren().addAll(addQuestionsView.getView(), backToMenu);
        
        addQuestionSetting.setCenter(additionBox);
        Scene additionScene = new Scene(addQuestionSetting,400,400);
       
        
        // alternating between scenes
        loginButton.setOnMouseClicked((event) -> {
            String username = loginView.getUsername();
            System.out.println(username);
            User user = userDao.findByUsername(loginView.getUsername());
            if (user == null) {
                loginInfo.setText("Käyttäjätunnusta ei löydy");
            }
            String password = user.getPassword();
            if (loginView.getPassword().equals(password)) {
                startScreen.setScene(menuScene);
                loginInfo.setText("");
            }
        });
        
        newUser.setOnAction((event) -> {
            startScreen.setScene(newUserScene);
        });
        
        playAGame.setOnAction((event) -> {
            startScreen.setScene(gameScene);
        });
        
        addAQuestion.setOnAction((event) -> {
            startScreen.setScene(additionScene);
        });
        
        endGame.setOnAction((event) -> {
            gameService.finishAGame();
            startScreen.setScene(menuScene);
        });
        
        backToMenu.setOnAction((event) -> {
           startScreen.setScene(menuScene); 
        });
    
        logOut.setOnAction((event) -> {
           startScreen.setScene(loginScene);
        });
        
        startScreen.setScene(loginScene);
        startScreen.show();
           
    }
    
  
    public static void main(String[] args) {
        launch(args);
        System.out.println("terve");
    }
    
}
