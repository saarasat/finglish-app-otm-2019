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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CreateUserView {

    private User userToAdd;
    private GameService gameService;
    
    public CreateUserView(GameService gameService) {
        this.gameService = gameService;
    }

    public Parent getView() {

        GridPane setting = new GridPane();

        TextField usernameText = new TextField();
        TextField passwordOneText = new TextField();
        TextField passwordTwoText = new TextField();
        Button createUserButton = new Button("Luo tunnus");
        Label messageLabel = new Label("");
        
        setting.setAlignment(Pos.CENTER);
        setting.setVgap(10);
        setting.setHgap(10);
        setting.setPadding(new Insets(10, 10, 10, 10));

        setting.add(messageLabel,0,0);
        setting.add(new Label("Käyttäjänimi:"), 0, 1);
        setting.add(new Label("Salasana:"), 0, 2);
        setting.add(new Label("Vahvista salasana:"), 0, 3);
        setting.add(usernameText, 1, 1);
        setting.add(passwordOneText, 1, 2);
        setting.add(passwordTwoText, 1, 3);
        setting.add(createUserButton, 1, 5);

        createUserButton.setOnAction((event) -> {
            String username = usernameText.getText();
            String passwordOne = passwordOneText.getText();
            String passwordTwo = passwordTwoText.getText();
            System.out.println(username);
            System.out.println(passwordOne);
            System.out.println(passwordTwo);
            
            if (!gameService.validateInput(username)) {
                messageLabel.setText("Käyttäjänimen oltava 3-60 merkkiä");
            } else if (!gameService.validateInput(passwordOne) ){
                messageLabel.setText("Salasanan oltava 3-60 merkkiä");
            } else if ( gameService.addUser(username, passwordOne) ){
                messageLabel.setText("Tili luotu! loggaa ineen");
                usernameText.clear();
                passwordOneText.clear();
                passwordTwoText.clear();
            } else {
                messageLabel.setText("Käyttäjänimen oltava uniikki");
            }
 
        });  
        
        return setting;

    }
}