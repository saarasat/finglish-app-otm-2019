/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglish.ui;


import finglish.domain.GameService;
import finglish.domain.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CreateUserView {

    private GameService gameService;

    
    public CreateUserView(GameService gameService) {
        this.gameService = gameService;
    }

    public Parent getView() {

        GridPane setting = new GridPane();

        TextField usernameText = new TextField();
        TextField passwordOneText = new TextField();
        TextField passwordTwoText = new TextField();
        CheckBox adminCheckBox = new CheckBox();
        Button createUserButton = new Button("Luo tunnus");
        Label messageLabel = new Label("");
        Label adminLabel = new Label("Ylläpitäjä:");
        Label usernameLabel = new Label("Käyttäjänimi:");
        Label passwordLabel = new Label("Salasana:");
        Label password2Label = new Label("Vahvista salasana:");      
        
        setting.setAlignment(Pos.CENTER);
        setting.setVgap(10);
        setting.setHgap(10);
            
        setting.add(messageLabel,0,0);
        setting.add(usernameLabel, 0, 1);
        setting.add(passwordLabel, 0, 2);
        setting.add(password2Label, 0, 3);
        setting.add(adminLabel, 0, 4);
        setting.add(usernameText, 1, 1);
        setting.add(passwordOneText, 1, 2);
        setting.add(passwordTwoText, 1, 3);
        setting.add(adminCheckBox, 1, 4);
        setting.add(createUserButton, 1, 6);
  

        createUserButton.setOnAction((event) -> {
            String username = usernameText.getText();
            String passwordOne = passwordOneText.getText();
            String passwordTwo = passwordTwoText.getText();
            int adminStatus = 0;
            if (adminCheckBox.isSelected()) {
                adminStatus = 1;
            } 
            
            if (!gameService.validateInput(username)) {
                messageLabel.setText("Käyttäjänimen oltava 3-60 merkkiä");
            } else if (!gameService.validateInput(passwordOne) ){
                messageLabel.setText("Salasanan oltava 3-60 merkkiä");
            } else if (!passwordOne.equals(passwordTwo)) {
                messageLabel.setText("Salasanat eivät täsmää");
            } else if ( gameService.addUser(username, passwordOne, adminStatus) ){
                messageLabel.setText("Tili luotu! loggaa ineen");
                setting.getChildren().clear();
                setting.add(messageLabel, 1,3);
            } else {
                messageLabel.setText("Käyttäjänimen oltava uniikki");
            }
 
        });  
        
        return setting;

    }
}