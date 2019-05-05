
package finglish.ui;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginView {
    
    private TextField usernameText; 
    private TextField passwordText; 
   
    public Parent getView() {

        GridPane setting = new GridPane();
                
        Label usernameLabel = new Label("Käyttäjätunnus:");
        usernameText = new TextField(); 
        Label passwordLabel = new Label("Salasana:");
        passwordText = new TextField();
        
        setting.setAlignment(Pos.CENTER);
        setting.setVgap(10);
        setting.setHgap(10);
        
        setting.add(usernameLabel, 0, 2);
        setting.add(usernameText, 0, 3);
        setting.add(passwordLabel, 0, 4);
        setting.add(passwordText, 0, 5);
          
        return setting;
        
    }
    
    public String getUsername() {
        return usernameText.getText();
    }

    public String getPassword() {
        return passwordText.getText();
    }    
    
}
