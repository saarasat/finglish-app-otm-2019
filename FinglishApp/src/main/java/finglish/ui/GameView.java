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

        Label answerCheck = new Label("");
        Label questionLabel = new Label(question.getQuestion());
        Button option1 = new Button(question.getOption());
        Button option2 = new Button(question.getOption());
        Button option3 = new Button(question.getOption());
        Button option4 = new Button(question.getOption());
        Button next = new Button("Seuraava");
        
        setting.setAlignment(Pos.CENTER);
        setting.setVgap(10);
        setting.setHgap(10);
        setting.setPadding(new Insets(10, 10, 10, 10));

        setting.add(answerCheck, 0, 0);
        setting.add(questionLabel, 0, 1);
        setting.add(option1, 0, 2);
        setting.add(option2, 0, 3);
        setting.add(option3, 0, 4);
        setting.add(option4, 0, 5);
        setting.add(next, 0,9);

        option1.setOnMouseClicked((event) -> {
            if (question.checkIfCorrect(option1.getText())) {
                answerCheck.setText("Yay, oikein!");
            } else {
                answerCheck.setText("Sori, väärä vastaus");
            }
        });

        option2.setOnMouseClicked((event) -> {
            if (question.checkIfCorrect(option2.getText())) {
                answerCheck.setText("Yay, oikein!");
            } else {
                answerCheck.setText("Sori, väärä vastaus");
            }
        });

        option3.setOnMouseClicked((event) -> {
            if (question.checkIfCorrect(option3.getText())) {
                answerCheck.setText("Yay, oikein!");
            } else {
                answerCheck.setText("Sori, väärä vastaus");
            }
        });

        option4.setOnMouseClicked((event) -> {
            if (question.checkIfCorrect(option4.getText())) {
                answerCheck.setText("Yay, oikein!");
            } else {
                answerCheck.setText("Sori, väärä vastaus");
            }
        });
        
        next.setOnMouseClicked((event) -> {
           question = gameService.getTheNextQuestion();            
        });
        

        return setting;
    }

}
