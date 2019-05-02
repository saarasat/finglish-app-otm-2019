/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglish.ui;

import finglish.dao.FileGameDao;
import finglish.dao.FileQuestionDao;
import finglish.dao.QuestionDao;
import finglish.dao.FileUserDao;
import finglish.dao.UserDao;

import finglish.domain.GameService;
import finglish.domain.User;

import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 

public class FinglishAppUi extends Application {
    
    private GameService gameService;
    private FileGameDao gameDao;
    private FileQuestionDao questionDao;
    private FileUserDao userDao;
    private ArrayList<User> users;
    private GameView gameView;

    
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        
        properties.load(new FileInputStream("config.properties"));
        String questionFile = properties.getProperty("questionFile");
        String userFile = properties.getProperty("userFile");
        String gameFile = properties.getProperty("gameFile");
        this.gameDao = new FileGameDao(gameFile);
        this.questionDao = new FileQuestionDao(questionFile);
        this.userDao = new FileUserDao(userFile);
        this.gameService = new GameService(1, gameDao, questionDao, userDao);
        
    }
    
    @Override 
    public void start(Stage startScreen) throws Exception {    
             
        
        // creating the scene for logging in
        GridPane loginSetting = new GridPane();
        LoginView loginView = new LoginView(gameService, userDao, users);
        VBox loginBox = new VBox();
        loginBox.setPadding(new Insets(20,20,20,20));
        loginBox.setSpacing(10);
       
        Button loginButton = new Button("Kirjaudu");
        Label loginInfo = new Label("");
        Label newUserLabel = new Label("Ei vielä tunnusta?");
        Button newUser = new Button("Luo uusi tunnus");

        loginBox.getChildren().addAll(loginInfo, loginView.getView(), loginButton, newUserLabel, newUser);
        
        loginSetting.add(loginBox, 0, 1);
        Scene loginScene = new Scene(loginSetting, 400, 400);
                 
        
        //creating the scene for creating a new User
        BorderPane newUserSetting = new BorderPane();
        CreateUserView createUserView = new CreateUserView(gameService);
        VBox userBox = new VBox();
        userBox.setPadding(new Insets(20,20,20,20));
        userBox.setSpacing(10);
        
        Button toLoginScene = new Button("Kirjaudu");
        
        userBox.getChildren().addAll(createUserView.getView(), toLoginScene);
        newUserSetting.setCenter(userBox);
        Scene newUserScene = new Scene(newUserSetting, 400,400);
        
        //creating the scene for main menu
        BorderPane menuSetting = new BorderPane();
        VBox menuBox = new VBox();
        menuBox.setPadding(new Insets(20,20,20,20));
        menuBox.setSpacing(10);
        
        Label welcomeLabel = new Label("Tervetuloa pelaamaan Finglishia!");
        Button playAGameButton = new Button("Pelaa");
        Button addAQuestionButton = new Button("Lisää kysymys");
        Button highScoreButton = new Button("Top 10");
        Button logOut = new Button("Kirjaudu ulos");         
        menuBox.getChildren().addAll(welcomeLabel, playAGameButton, addAQuestionButton, highScoreButton, logOut);
        
        menuSetting.setCenter(menuBox);
        Scene menuScene = new Scene(menuSetting, 400,400);
     
        
        //creating the scene for playing
        GridPane gameSetting = new GridPane();
        VBox gameBox = new VBox();
        gameBox.setPadding(new Insets(20,20,20,20));
        gameBox.setSpacing(10);
        
        gameView = new GameView(gameService);
        Button endGame = new Button("Lopeta peli");
        
 
        Scene gameScene = new Scene(gameSetting, 700, 400);

        
        //creating the scene for adding questions
        GridPane addQuestionSetting = new GridPane();
        VBox additionBox = new VBox();
        additionBox.setPadding(new Insets(20,20,20,20));
        additionBox.setSpacing(10);
        
        AddQuestionsView addQuestionsView = new AddQuestionsView(gameService);
        Button backToMenu = new Button("Takaisin valikkoon");
        additionBox.getChildren().addAll(addQuestionsView.getView(), backToMenu);
        
        addQuestionSetting.add(additionBox, 0, 1);
        Scene additionScene = new Scene(addQuestionSetting,700,400);
       

        //creating the scene for highscores
        GridPane highScoreSetting = new GridPane();
        VBox highScoreBox = new VBox();
        highScoreBox.setPadding(new Insets(20,20,20,20));
        highScoreBox.setSpacing(10);
        
        HighScoreView highScoreView = new HighScoreView(gameService);
        Button backToMainMenu = new Button("Takaisin valikkoon");
        highScoreBox.getChildren().addAll(highScoreView.getView(), backToMainMenu);
        
        highScoreSetting.add(highScoreBox, 0, 1);
        Scene highScoreScene = new Scene(highScoreSetting,700,400);

        
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
        
        playAGameButton.setOnAction((event) -> {
            gameBox.getChildren().addAll(gameView.getView());
            gameSetting.add(gameBox, 0, 1);
            gameSetting.add(endGame, 0, 2);
            startScreen.setScene(gameScene);
        });
        
        addAQuestionButton.setOnAction((event) -> {
            startScreen.setScene(additionScene);
        });
        
        highScoreButton.setOnAction((event) -> {
            startScreen.setScene(highScoreScene);
        });
        
        endGame.setOnAction((event) -> {
            gameService.finishAGame();
            gameBox.getChildren().clear();
            gameSetting.getChildren().clear();
            startScreen.setScene(menuScene);
        });
        
        backToMenu.setOnAction((event) -> {
           startScreen.setScene(menuScene); 
        });
    
        logOut.setOnAction((event) -> {
           startScreen.setScene(loginScene);
        });
        
        toLoginScene.setOnAction((event) -> {
            startScreen.setScene(loginScene);
        });
        
        startScreen.setScene(loginScene);
        startScreen.show();
           
    }
    
    public void resetScene() {
        
        
    }
    
  
    public static void main(String[] args) {
        launch(FinglishAppUi.class );
        System.out.println("terve");
    }
    
}
