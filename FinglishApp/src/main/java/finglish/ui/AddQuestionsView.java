/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglish.ui;

import finglish.domain.GameService;
import finglish.domain.Question;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


    public class AddQuestionsView {
    
        private Question questionToAdd;
        private GameService gameService;
        
        public AddQuestionsView(GameService gameService) {
            this.gameService = gameService;
        }
        
        public Parent getView() {
        
        GridPane setting = new GridPane();
        
        TextField questionText = new TextField();
        TextField firstOptionText = new TextField();
        TextField secondOptionText = new TextField();
        TextField thirdOptionText = new TextField();
        TextField fourthOptionText = new TextField();
        TextField correctOptionText = new TextField();
        Button addQuestion = new Button("Lisää");
        
        setting.setAlignment(Pos.CENTER);
        setting.setVgap(10);
        setting.setHgap(10);
        setting.setPadding(new Insets(10,10,10,10));
        
        setting.add(new Label("Kysymys"), 0, 0);
        setting.add(new Label("Vaihtoehto 1:"), 0, 1);
        setting.add(new Label("Vaihtoehto 2:"), 0, 2);
        setting.add(new Label("Vaihtoehto 3:"), 0, 3);
        setting.add(new Label("Vaihtoehto 4:"), 0, 4);
        setting.add(new Label("Oikea vaihtoehto"), 0, 5);
        setting.add(questionText, 1, 0);
        setting.add(firstOptionText, 1, 1);
        setting.add(secondOptionText, 1, 2);
        setting.add(thirdOptionText, 1, 3);
        setting.add(fourthOptionText, 1, 4);
        setting.add(correctOptionText, 1, 5);
        setting.add(addQuestion, 1, 6);
        
        addQuestion.setOnMouseClicked((event) -> {
            
            this.questionToAdd = new Question(
                questionText.getText(),
                firstOptionText.getText(),
                secondOptionText.getText(),
                thirdOptionText.getText(),
                fourthOptionText.getText(),
                correctOptionText.getText());           
            
            gameService.addQuestion(questionToAdd);
            
            questionText.clear();
            firstOptionText.clear();
            secondOptionText.clear();
            thirdOptionText.clear();
            fourthOptionText.clear();
            correctOptionText.clear();        
        });
        
        return setting;
        
    }
}
