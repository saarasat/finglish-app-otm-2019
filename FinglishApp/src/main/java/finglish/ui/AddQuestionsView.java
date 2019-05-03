/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglish.ui;

import finglish.domain.GameService;
import finglish.domain.Question;
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
        
 
        
        Label messageLabel = new Label("");
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
        
        setting.add(messageLabel,0,0);
        setting.add(new Label("Kysymys"), 0, 1);
        setting.add(new Label("Vaihtoehto 1:"), 0, 2);
        setting.add(new Label("Vaihtoehto 2:"), 0, 3);
        setting.add(new Label("Vaihtoehto 3:"), 0, 4);
        setting.add(new Label("Vaihtoehto 4:"), 0, 5);
        setting.add(new Label("Oikea vaihtoehto"), 0, 6);
        setting.add(questionText, 1, 1);
        setting.add(firstOptionText, 1, 2);
        setting.add(secondOptionText, 1, 3);
        setting.add(thirdOptionText, 1, 4);
        setting.add(fourthOptionText, 1, 5);
        setting.add(correctOptionText, 1, 6);
        setting.add(addQuestion, 1, 7);
        
        addQuestion.setOnMouseClicked((event) -> {
            
            String question = questionText.getText();
            String first = firstOptionText.getText();
            String second = secondOptionText.getText();
            String third = thirdOptionText.getText();
            String fourth = fourthOptionText.getText();
            String correct = correctOptionText.getText();
            
            if (!gameService.validateInput(question) || !gameService.validateInput(first) || 
                    !gameService.validateInput(second) || !gameService.validateInput(third) || 
                    !gameService.validateInput(fourth) || !gameService.validateInput(correct)) {
                messageLabel.setText("Kaikkien syötteiden pitää olla 3-100 merkkiä");
            } else if (!(correct.equals(first) || correct.equals(second) || correct.equals(third) || correct.equals(fourth))){
                messageLabel.setText("Yhden vaihtoehdon pitää olla oikein");
            } else {
            
                this.questionToAdd = new Question(
                    questionText.getText(),
                    firstOptionText.getText(),
                    secondOptionText.getText(),
                    thirdOptionText.getText(),
                    fourthOptionText.getText(),
                    correctOptionText.getText());           
            
                gameService.addQuestion(questionToAdd);
                
                messageLabel.setText("Kysymys lisätty!");
                questionText.clear();
                firstOptionText.clear();
                secondOptionText.clear();
                thirdOptionText.clear();
                fourthOptionText.clear();
                correctOptionText.clear();        
            }     
        });
        
        return setting;
        
    }
}
