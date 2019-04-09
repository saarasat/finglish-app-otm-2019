/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglish.ui;

import finglish.dao.FileQuestionDao;
import finglish.dao.QuestionDao;

import java.util.List;
import java.util.Properties;
import finglish.domain.GameService;
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
 

public class FinglishAppUi extends Application {
    
    private GameService gameService;
    private FileQuestionDao questionDao; 
    
    @Override
    public void init() throws Exception {
        String file = "questions.txt";        
        FileQuestionDao questionDao = new FileQuestionDao(file);
        gameService = new GameService(questionDao);
    }
    
    
    @Override 
    public void start(Stage startScreen) throws Exception {    
        
        GameView gameView = new GameView(gameService);
        AddQuestionsView addQuestionsView = new AddQuestionsView(gameService);
        
        BorderPane setting = new BorderPane();
        
        HBox menu = new HBox();
        menu.setPadding(new Insets(20,20,20,20));
        menu.setSpacing(10);
        
        Button playAGame = new Button("Play");
        Button addAQuestion = new Button("Add a question");
           
        menu.getChildren().addAll(playAGame, addAQuestion);
        
        playAGame.setOnMouseClicked((event) -> {
            setting.setCenter(gameView.getView());
            gameService.startANewGame();
        });
        
        addAQuestion.setOnAction((event) -> setting.setCenter(addQuestionsView.getView()));
       
        setting.setTop(menu);
        
        Scene start = new Scene(setting, 400, 400);
        startScreen.setScene(start);
        startScreen.show();
           
    }
    
    
    public static void main(String[] args) {
        launch(args);
        System.out.println("terve");
    }
    
}
