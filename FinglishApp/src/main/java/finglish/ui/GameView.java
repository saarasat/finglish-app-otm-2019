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
import javafx.scene.layout.GridPane;


import finglish.domain.GameService;
import finglish.domain.Question;

public class GameView {
    
    private GameService gameService;
    private Question question;
    
    public GameView(GameService gameService) {
        this.gameService = gameService;
    }
    
    public Parent getView() {
        
        GridPane setting = new GridPane();
        
        gameService.startANewGame();
        question = gameService.getTheNextQuestion();        
        
        Label questionLabel = new Label(question.getQuestion());
        Button option1 = new Button(question.getOption());
        Button option2 = new Button(question.getOption());
        Button option3 = new Button(question.getOption());
        Button option4 = new Button(question.getOption());
        
        setting.setAlignment(Pos.CENTER);
        setting.setVgap(10);
        setting.setHgap(10);
        setting.setPadding(new Insets(10,10,10,10));
        
        setting.add(questionLabel, 0, 0);
        setting.add(option1, 0, 1);
        setting.add(option2, 0, 2);
        setting.add(option3, 0, 3);
        setting.add(option4, 0, 4);
        
        return setting;
    }
}
