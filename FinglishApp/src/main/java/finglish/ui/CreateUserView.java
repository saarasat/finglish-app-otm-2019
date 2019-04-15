/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finglish.ui;


import finglish.domain.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CreateUserView {

    private User newUser;
    
    public CreateUserView() {
    }

    public Parent getView() {

        GridPane setting = new GridPane();

        TextField usernameText = new TextField();
        TextField passwordText = new TextField();
        Button createButton = new Button("Luo tunnus");

        setting.setAlignment(Pos.CENTER);
        setting.setVgap(10);
        setting.setHgap(10);
        setting.setPadding(new Insets(10, 10, 10, 10));

        setting.add(new Label("Käyttäjänimi:"), 0, 1);
        setting.add(new Label("Salasana:"), 0, 2);
        setting.add(usernameText, 1, 0);
        setting.add(passwordText, 1, 1);
        setting.add(createButton, 1, 3);

        createButton.setOnMouseClicked((event) -> {

  
            usernameText.clear();
            passwordText.clear();
        });

        return setting;

    }
}