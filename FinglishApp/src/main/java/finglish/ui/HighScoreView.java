
package finglish.ui;

import finglish.domain.GameService;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class HighScoreView {
   
    private GameService gameService;
              
    public HighScoreView(GameService gameService) {
        this.gameService = gameService;
    }
        
    public Parent getView() {
        
        GridPane setting = new GridPane();
        
        setting.setAlignment(Pos.CENTER);
        setting.setVgap(10);
        setting.setHgap(10);
        setting.setPrefSize(400, 500);
   
        Text highscores = new Text(this.gameService.highScoreList());
        setting.add(new Label(highscores.getText()), 0, 1);

        return setting;
    }
            
}