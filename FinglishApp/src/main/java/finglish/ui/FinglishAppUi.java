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
    private User user;
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
        this.user = null;
        this.users = userDao.getAll();
        
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
        Button newUserButton = new Button("Luo uusi tunnus");

        loginBox.getChildren().addAll(loginInfo, loginView.getView(), loginButton, newUserLabel, newUserButton);
        
        loginSetting.add(loginBox, 0, 1);
        Scene loginScene = new Scene(loginSetting, 400, 400);
                 
        
        //creating the scene for creating a new User
        BorderPane newUserSetting = new BorderPane();
        CreateUserView createUserView = new CreateUserView(gameService);
        VBox newUserBox = new VBox();
        newUserBox.setPadding(new Insets(20,20,20,20));
        newUserBox.setSpacing(10);
        Button backFromCreatingUserButton = new Button("Kirjaudu");
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
        Button adminButton = new Button("Hallitse käyttäjätilejä");
        Button logOutButton = new Button("Kirjaudu ulos");         
        

        
        menuSetting.setCenter(menuBox);
        Scene menuScene = new Scene(menuSetting, 400,400);
     
        
        //creating the scene for playing
        GridPane gameSetting = new GridPane();
        VBox gameBox = new VBox();
        gameBox.setPadding(new Insets(20,20,20,20));
        gameBox.setSpacing(10);
        
        gameView = new GameView(gameService);
        Button endGameButton = new Button("Lopeta peli");
        Scene gameScene = new Scene(gameSetting, 700, 400);

        
        //creating the scene for adding questions
        GridPane additionSetting = new GridPane();
        VBox additionBox = new VBox();
        additionBox.setPadding(new Insets(20,20,20,20));
        additionBox.setSpacing(10);
        
        AddQuestionsView addQuestionsView = new AddQuestionsView(gameService);
        Button backFromAddingButton = new Button("Takaisin valikkoon");
        Scene additionScene = new Scene(additionSetting,700,400);
       

        //creating the scene for highscores
        GridPane highScoreSetting = new GridPane();
        VBox highScoreBox = new VBox();
        highScoreBox.setPadding(new Insets(20,20,20,20));
        highScoreBox.setSpacing(10);
        
        HighScoreView highScoreView = new HighScoreView(gameService);
        Button backToMainMenuButton = new Button("Takaisin valikkoon");
        highScoreBox.getChildren().addAll(highScoreView.getView(), backToMainMenuButton);
        
        highScoreSetting.add(highScoreBox, 0, 1);
        Scene highScoreScene = new Scene(highScoreSetting,700,400);
        
        //creating the scene for admins
        GridPane adminSetting = new GridPane();
        VBox adminBox = new VBox();
        adminBox.setPadding(new Insets(20,20,20,20));
        adminBox.setSpacing(10);
        
        
        Button backFromAdminButton = new Button("Takaisin");
        
        Scene adminScene = new Scene(adminSetting,700,400);
        

        // alternating between scenes
        loginButton.setOnMouseClicked((event) -> {
            String username = loginView.getUsername();
            user = userDao.findByUsername(loginView.getUsername());
            if (user == null) {
                loginInfo.setText("Käyttäjätunnusta ei löydy");
            }
            String password = user.getPassword();
            if (loginView.getPassword().equals(password)) {
                if (this.user.getAdmin() == 1) {
                    menuBox.getChildren().addAll(welcomeLabel, playAGameButton, addAQuestionButton, highScoreButton, adminButton, logOutButton);
                } else {
                    menuBox.getChildren().addAll(welcomeLabel, playAGameButton, highScoreButton, adminButton, logOutButton);                    
                }
                startScreen.setScene(menuScene);
                loginInfo.setText("");
            }
        });
        
        newUserButton.setOnAction((event) -> {
            newUserBox.getChildren().addAll(createUserView.getView(), backFromCreatingUserButton);
            newUserSetting.setCenter(newUserBox);
            startScreen.setScene(newUserScene);
        });
        
        playAGameButton.setOnAction((event) -> {
            gameBox.getChildren().addAll(gameView.getView());
            gameSetting.add(gameBox, 0, 1);
            gameSetting.add(endGameButton, 0, 2);
            startScreen.setScene(gameScene);
        });
        
        addAQuestionButton.setOnAction((event) -> {
            additionBox.getChildren().addAll(addQuestionsView.getView(), backFromAddingButton);    
            additionSetting.add(additionBox, 0, 1);
            startScreen.setScene(additionScene);
        });
        
        highScoreButton.setOnAction((event) -> {
            startScreen.setScene(highScoreScene);
        });
        
        
        adminButton.setOnAction((event) -> {
            AdminView adminView = new AdminView(gameService, userDao, users, user);
            adminBox.getChildren().addAll(adminView.getView(), backFromAdminButton);
            adminSetting.add(adminBox, 0, 1);       
            startScreen.setScene(adminScene);
        });
        
        
        endGameButton.setOnAction((event) -> {
            gameService.finishAGame();
            gameBox.getChildren().clear();
            gameSetting.getChildren().clear();
            startScreen.setScene(menuScene);
        });
        
        backFromAddingButton.setOnAction((event) -> {
           additionBox.getChildren().clear();
           additionSetting.getChildren().clear();
           startScreen.setScene(menuScene); 
        });
        
        backFromAdminButton.setOnAction((event) -> {
           adminBox.getChildren().clear();
           adminSetting.getChildren().clear();
           startScreen.setScene(menuScene); 
        });
    
        logOutButton.setOnAction((event) -> {
           startScreen.setScene(loginScene);
        });
        
        backFromCreatingUserButton.setOnAction((event) -> {
            newUserBox.getChildren().clear();
            newUserSetting.getChildren().clear();
            startScreen.setScene(loginScene);
        });
        
        startScreen.setScene(loginScene);
        startScreen.show();
           
    }
    
    public static void main(String[] args) {
        launch(FinglishAppUi.class );
        System.out.println("terve");
    }
    
}
