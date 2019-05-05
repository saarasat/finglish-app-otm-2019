
package finglish.ui;

import finglish.domain.GameService;
import finglish.domain.Question;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

public class GameView {

    private GameService gameService;
    private Question question;

    private FlowPane setting;
    private Label questionLabel;
    private Text totalScore;
    private Label answerCheck;
    private Label questionsAnswered;
    private boolean answeredAlready;
    private RadioButton option1, option2, option3, option4;
    private Button next;
    private ToggleGroup toggleGroup;

    public GameView(GameService gameService) {
        this.gameService = gameService;
    }

    public Parent getView() {

        setting = new FlowPane(Orientation.VERTICAL);
        setting.setAlignment(Pos.CENTER);
        setting.setVgap(10);
        setting.setHgap(10);
        gameService.startANewGame();
        question = gameService.getTheNextQuestion();
        toggleGroup = new ToggleGroup();

        answerCheck = new Label("");
        answerCheck.setFont(Font.font("verdana", FontPosture.REGULAR, 15));
        questionsAnswered = new Label(this.gameService.getTheQuestionNumber());
        questionsAnswered.setFont(Font.font("verdana", FontPosture.REGULAR, 15));
        questionLabel = new Label(question.getQuestion());
        questionLabel.setWrapText(true);
        questionLabel.setFont(Font.font("verdana", FontPosture.REGULAR, 15));
        totalScore = new Text();
        totalScore.setFont(Font.font("verdana", FontPosture.REGULAR, 15));

        VBox progressBox = new VBox();
        progressBox.getChildren().addAll(questionsAnswered,answerCheck, questionLabel);
        progressBox.setAlignment(Pos.CENTER);
        progressBox.setSpacing(15);
        progressBox.setPadding(new Insets(10,10,10,0));
          
        option1 = new RadioButton();
        option2 = new RadioButton();
        option3 = new RadioButton();
        option4 = new RadioButton();

        option1.setText(question.getOption());
        option2.setText(question.getOption());
        option3.setText(question.getOption());
        option4.setText(question.getOption());

        next = new Button("Seuraava");        
        VBox nextBox = new VBox();
        nextBox.getChildren().addAll(next);
        nextBox.setAlignment(Pos.CENTER);
        nextBox.setSpacing(15);
        nextBox.setPadding(new Insets(20,20,20,20));
        
        setting.getChildren().addAll(progressBox, option1, option2, option3, option4, nextBox);        
        toggleGroup.getToggles().addAll(option1, option2, option3, option4);

        next.setOnAction((event) -> {
            RadioButton answerSelected = null;

            if (toggleGroup.getSelectedToggle() != null) {
                answerSelected = (RadioButton) toggleGroup.getSelectedToggle();
            }

            question = gameService.getTheNextQuestion();
            toggleGroup.selectToggle(null);
            answerCheck.setText("");
            showQuestion(question);
        });

        option1.setOnMouseClicked((event) -> {
            chooseOption(option1.getText());
        });

        option2.setOnMouseClicked((event) -> {
            chooseOption(option2.getText());
        });

        option3.setOnMouseClicked((event) -> {
            chooseOption(option3.getText());
        });

        option4.setOnMouseClicked((event) -> {
            chooseOption(option4.getText());
        });

        return setting;
    }
    
    public void chooseOption(String option) {
        if (!answeredAlready) {
                if (question.checkIfCorrect(option)) {
                    answerCheck.setText("Yay, oikein!");
                    gameService.answerTheQuestion(true);
                } else {
                    answerCheck.setText("Sori, väärä vastaus");
                    gameService.answerTheQuestion(false);
                }
                answeredAlready = true;
            }
    }

    public void disableRadioButton() {
        option1.setVisible(false);
        option2.setVisible(false);
        option3.setVisible(false);
        option4.setVisible(false);
    }

    public void enableRadioButton() {
        option1.setVisible(true);
        option2.setVisible(true);
        option3.setVisible(true);
        option4.setVisible(true);
    }

    public void showQuestion(Question question) {
        if (question != null) {
            questionLabel.setText(question.getQuestion());
            option1.setText(question.getOption());
            option2.setText(question.getOption());
            option3.setText(question.getOption());
            option4.setText(question.getOption());
            enableRadioButton();
            answeredAlready = false;
            questionsAnswered.setText(this.gameService.getTheQuestionNumber());

        } else {
            next.setVisible(false);
            setting.getChildren().clear();
            totalScore.setText(this.gameService.getTotalScore());
            setting.getChildren().addAll(totalScore);
            disableRadioButton();
        }
    }
    
 

}
