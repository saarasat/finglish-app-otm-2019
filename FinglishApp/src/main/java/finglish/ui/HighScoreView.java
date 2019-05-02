/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglish.ui;

import finglish.domain.Game;
import finglish.domain.GameService;
import finglish.domain.Question;
import java.util.ArrayList;
import java.util.Collections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class HighScoreView {
   
    private GameService gameService;
    private ArrayList<Game> games;
              
    public HighScoreView(GameService gameService) {
        this.gameService = gameService;
    }
        
    public Parent getView() {
        
        GridPane setting = new GridPane();
        
        setting.setAlignment(Pos.CENTER);
        setting.setVgap(10);
        setting.setHgap(10);
        setting.setPadding(new Insets(10,10,10,10));

       
        setting.add(new Label("Highscores"), 0, 0);

        return setting;
    }
            
}
