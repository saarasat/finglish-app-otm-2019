
package finglish.ui;

import finglish.dao.GameDao;
import finglish.dao.UserDao;
import finglish.domain.Game;
import finglish.domain.GameService;
import finglish.domain.User;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 *
 * @author saarasat
 */
public class AdminView {
    
    private GameService gameService;
    private ArrayList<User> users;
    private User user;
    private GridPane setting;

    public AdminView (GameService gameService, ArrayList<User> users, User user) {
        this.gameService = gameService;
        this.users = users;
        this.user = user;       
    }
    
    public Parent getView() {
        
        setting = new GridPane();        
        
        setting.setAlignment(Pos.CENTER);
        setting.setVgap(10);
        setting.setHgap(10);
        resetUserList();
        
        if (user.getAdmin() == 1) {
           ScrollPane userScrollbar = new ScrollPane();  
           userScrollbar.setContent(setting);
           BorderPane adminSetting = new BorderPane(userScrollbar);
           adminSetting.setPrefSize(300, 200);
           return adminSetting;           
        } else {
            setting.add(new Label("Haluatko poistaa oman käyttäjätilisi?"),1,1);
            setting.setPrefSize(500,400);
            return setting;
        }     
    }
    
    public void resetUserList() {
        
        setting.getChildren().clear();        

        if (this.user.getAdmin() == 1) {
            for (int i = 0; i < users.size(); i++) {
                setting.add(createUserNode(users.get(i), 1), 1, i+2);
            }
        } else {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId() == user.getId()) {
                    setting.add(createUserNode(users.get(i), 0), 1, i+3);
                }
            }
        } 
    }
    
    public Node createUserNode(User user, int admin) {
        
        HBox userBox = new HBox(10);
        Label userLabel  = new Label(user.getUsername());
        userLabel.setMinHeight(30);
        Button deleteButton = new Button("Poista");
        if (admin == 0) {
            deleteButton.setText("Poista tämä käyttäjätili");
        } else if (users.size() == 1) {
            if (this.user.getId() == user.getId()) {
                deleteButton.setText("Poista tämä käyttäjätili");
            }
        }
        deleteButton.setOnAction((event)->{
            gameService.removeUser(user.getId());
            resetUserList();
        });
                
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        userBox.setPadding(new Insets(0,5,0,5));
        
        userBox.getChildren().addAll(userLabel, spacer, deleteButton);
        return userBox;
    }
    
}
