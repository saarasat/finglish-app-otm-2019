/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglish.ui;

import finglish.dao.FileGameDao;
import finglish.dao.FileQuestionDao;
import finglish.dao.FileUserDao;

import finglish.domain.GameService;
import finglish.domain.User;

import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Properties;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 

public class FinglishAppUi extends Application {
    
    private GameService gameService;
    private FileGameDao gameDao;
    private FileQuestionDao questionDao;
    private FileUserDao userDao;
    private ArrayList<User> users;
    private User user;
    private GameView gameView;
    private BackgroundImage background;

    
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        
        properties.load(new FileInputStream("config.properties"));
        String questionFile = properties.getProperty("questionFile");
        String userFile = properties.getProperty("userFile");
        String gameFile = properties.getProperty("gameFile");
        Image backGround = new Image("file:backgroundimage.png");
        this.background  = new BackgroundImage(backGround,
            BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
            BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.gameDao = new FileGameDao(gameFile);
        this.questionDao = new FileQuestionDao(questionFile);
        this.userDao = new FileUserDao(userFile);
        this.user = null;
        this.gameService = new GameService(this.user, gameDao, questionDao, userDao);
        this.users = userDao.getAll();
        
    }
    
    @Override 
    public void start(Stage startScreen) throws Exception {    
             
        
        // creating the scene for logging in
        VBox loginSetting = createTheSetting();
        VBox loginBox = createTheViewBox();
        LoginView loginView = new LoginView();
        
        Button loginButton = new Button("Kirjaudu");
        Label loginInfo = new Label("");
        Label newUserLabel = new Label("Ei vielä tunnusta?");
        Button newUserButton = new Button("Luo uusi tunnus");

        loginBox.getChildren().addAll(headerCreator("Finglish Quiz"), loginView.getView(), loginButton);
        loginSetting.getChildren().addAll(loginInfo, loginBox, newUserLabel, newUserButton);
        Scene loginScene = new Scene(loginSetting, 700, 500);
                 
        
        //creating the scene for creating a new User
        VBox newUserSetting = createTheSetting();
        VBox newUserBox = createTheViewBox();
        Button backFromCreatingUserButton = new Button("Kirjautumiseen");
        
        CreateUserView createUserView = new CreateUserView(gameService);
        Scene newUserScene = new Scene(newUserSetting, 700,500);
        
        //creating the scene for main menu
        VBox menuSetting = createTheSetting();
        VBox menuBox = createTheViewBox();
        
        Button playAGameButton = new Button("Pelaa");
        Button addAQuestionButton = new Button("Lisää kysymys");
        Button highScoreButton = new Button("Top 10");
        Button adminButton = new Button("Poista tili");
        Button logOutButton = new Button("Kirjaudu ulos");         
        
        Scene menuScene = new Scene(menuSetting, 700,500);
  
     
        //creating the scene for playing
        VBox gameSetting = createTheSetting();
        VBox gameBox = createTheViewBox();
        gameBox.setPrefSize(400,400);
        
        gameView = new GameView(gameService);
        Button endGameButton = new Button("Lopeta peli");
        Scene gameScene = new Scene(gameSetting, 700, 500);

        //creating the scene for adding questions
        VBox additionSetting = createTheSetting();        
        VBox additionBox = createTheViewBox();
        Button backFromAddingButton = new Button("Takaisin");
        
        AddQuestionsView addQuestionsView = new AddQuestionsView(gameService);        
        Scene additionScene = new Scene(additionSetting,700,500);
       

        //creating the scene for highscores
        VBox highScoreSetting = createTheSetting();
        VBox highScoreBox = createTheViewBox();
        
        HighScoreView highScoreView = new HighScoreView(gameService);
        Button backFromHighScoresButton = new Button("Takaisin");
        
        Scene highScoreScene = new Scene(highScoreSetting,700,500);
        
        //creating the scene for admins
        VBox adminSetting = createTheSetting();
        VBox adminBox = createTheViewBox();
        
        Button backFromAdminButton = new Button("Takaisin");
        
        Scene adminScene = new Scene(adminSetting,700,500);
        

        // alternating between scenes
        loginButton.setOnMouseClicked((event) -> {
            String username = loginView.getUsername();
            user = userDao.findByUsername(loginView.getUsername());
            if (user == null) {
                loginInfo.setText("Käyttäjätunnusta ei löydy tai salasana on väärin");
                return;
            }
            String password = user.getPassword();
            if (loginView.getPassword().equals(password)) {
                if (this.user.getAdmin() == 1) {
                    adminButton.setText("Poista käyttäjätilejä");
                    menuBox.getChildren().addAll(headerCreator("Tervetuloa pelaamaan Finglish Quizia!" + "\n"), playAGameButton, highScoreButton, addAQuestionButton, adminButton, logOutButton);
                } else {
                    menuBox.getChildren().addAll(playAGameButton, highScoreButton, adminButton, logOutButton);                    
                }
                menuSetting.getChildren().add(menuBox);
                startScreen.setScene(menuScene);
                this.gameService.setUser(user);
                loginInfo.setText("");
            }
        });
        
        newUserButton.setOnAction((event) -> {
            loginInfo.setText("");
            newUserBox.getChildren().addAll(headerCreator("Finglish Quiz"), createUserView.getView());
            newUserSetting.getChildren().addAll(newUserBox, backFromCreatingUserButton);
            startScreen.setScene(newUserScene);
        });
        
        playAGameButton.setOnAction((event) -> {
            gameBox.getChildren().addAll(headerCreator("Finglish Quiz"), gameView.getView());
            gameSetting.getChildren().addAll(gameBox, endGameButton);
            startScreen.setScene(gameScene);
        });
        
        addAQuestionButton.setOnAction((event) -> {
            additionBox.getChildren().addAll(headerCreator("Lisää kysymys peliin"), addQuestionsView.getView());    
            additionSetting.getChildren().addAll(additionBox, backFromAddingButton);
            startScreen.setScene(additionScene);
        });
        
        highScoreButton.setOnAction((event) -> {
            highScoreBox.getChildren().addAll(headerCreator("Top 10 pelit"), highScoreView.getView());
            highScoreSetting.getChildren().addAll(highScoreBox, backFromHighScoresButton);
            startScreen.setScene(highScoreScene);
        });
        
        
        adminButton.setOnAction((event) -> {
            AdminView adminView = new AdminView(gameService, userDao, users, user);
            adminBox.getChildren().addAll(headerCreator("Hallinnoi käyttäjätietoja"), adminView.getView());
            adminSetting.getChildren().addAll(adminBox, backFromAdminButton);       
            startScreen.setScene(adminScene);
        });
        
        
        endGameButton.setOnAction((event) -> {
            gameService.finishAGame();
            clearTheStage(gameSetting, gameBox, startScreen, menuScene);
        });
        
        backFromAddingButton.setOnAction((event) -> {
           clearTheStage(additionSetting, additionBox, startScreen, menuScene);
        });
        
        backFromAdminButton.setOnAction((event) -> {
            this.users = userDao.getAll();
            if (this.users.size() == 0) {
                clearTheStage(adminSetting, adminBox, startScreen, loginScene);
                clearTheStage(menuSetting, menuBox, startScreen, loginScene);
                this.user = null;
                gameService.setUser(user);
            } else if (!this.users.contains(this.user.getId())) {
                clearTheStage(adminSetting, adminBox, startScreen, loginScene);
                clearTheStage(adminSetting, adminBox, startScreen, loginScene);
                clearTheStage(menuSetting, menuBox, startScreen, loginScene);

                this.user = null;
                gameService.setUser(user);

            } else {
                clearTheStage(adminSetting, adminBox, startScreen, menuScene);
            }
        });
        
        backFromHighScoresButton.setOnAction((event) -> {
            clearTheStage(highScoreSetting, highScoreBox, startScreen, menuScene);
        });

        backFromCreatingUserButton.setOnAction((event) -> {
            clearTheStage(newUserSetting, newUserBox, startScreen, loginScene);
        });
        
        logOutButton.setOnAction((event) -> {
           clearTheStage(menuSetting, menuBox, startScreen, loginScene);
           this.user = null;
           startScreen.setScene(loginScene);
        });
        

        loginSetting.setBackground(new Background(background));
        startScreen.setScene(loginScene);
        startScreen.show();
           
    }
 
    
    private void clearTheStage(VBox setting, VBox box, Stage stage, Scene scene) {
        setting.getChildren().clear();
        box.getChildren().clear();
        stage.setScene(scene);
    }
    
    private VBox createTheSetting() {
        
        VBox setting = new VBox();
        setting.setPadding(new Insets(20,20,20,0));
        setting.setSpacing(10);
        setting.setAlignment(Pos.CENTER);
        setting.setPrefSize(400, 400);
        setting.setBackground(new Background(background));

        return setting;
    }
    
    public VBox createTheViewBox() {
               
        VBox box = new VBox();
        box.setPadding(new Insets(20,20,20,20));
        box.setSpacing(10);
        box.setPrefHeight(300);
        box.setAlignment(Pos.CENTER);
        return box;
    }
    
    private Text headerCreator(String headerText) {
        Text header = new Text(headerText);
        header.setFont(Font.font("verdana", FontPosture.REGULAR, 15));
        return header;
    }

    
    public static void main(String[] args) {
        launch(FinglishAppUi.class );
        System.out.println("terve");
    }
    
}
