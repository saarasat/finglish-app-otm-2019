
package finglish.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import finglish.domain.GameService;
import finglish.domain.Question;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class GameView {

    private GameService gameService;
    private Question question;

    private Label questionLabel;
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

        GridPane setting = new GridPane();

        gameService.startANewGame();
        question = gameService.getTheNextQuestion();
        toggleGroup = new ToggleGroup();

        answerCheck = new Label("");
        questionsAnswered = new Label(this.gameService.getAmountOfAnswered());
        questionLabel = new Label(question.getQuestion());
        option1 = new RadioButton();
        option2 = new RadioButton();
        option3 = new RadioButton();
        option4 = new RadioButton();

        option1.setText(question.getOption());
        option2.setText(question.getOption());
        option3.setText(question.getOption());
        option4.setText(question.getOption());

        next = new Button("Seuraava");

        setting.setAlignment(Pos.CENTER);
        setting.setVgap(10);
        setting.setHgap(10);
        setting.setPadding(new Insets(10, 10, 10, 10));

        setting.add(questionsAnswered, 0, 0);
        setting.add(answerCheck, 0, 1);
        setting.add(questionLabel, 0, 2);
        setting.add(option1, 0, 3);
        setting.add(option2, 0, 4);
        setting.add(option3, 0, 5);
        setting.add(option4, 0, 6);
        setting.add(next, 0, 10);

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
            if (!answeredAlready) {
                if (question.checkIfCorrect(option1.getText())) {
                    answerCheck.setText("Yay, oikein!");
                    gameService.setACorrectAnswer(question);
                } else {
                    answerCheck.setText("Sori, väärä vastaus");
                    gameService.setWrongAnswer(question);
                }
                answeredAlready = true;
            }
        });

        option2.setOnMouseClicked((event) -> {
            if (!answeredAlready) {
                if (question.checkIfCorrect(option2.getText())) {
                    answerCheck.setText("Yay, oikein!");
                    gameService.setACorrectAnswer(question);
                } else {
                    answerCheck.setText("Sori, väärä vastaus");
                    gameService.setWrongAnswer(question);
                }
                answeredAlready = true;
            }
        });

        option3.setOnMouseClicked((event) -> {
            if (!answeredAlready) {
                if (question.checkIfCorrect(option3.getText())) {
                    answerCheck.setText("Yay, oikein!");
                    gameService.setACorrectAnswer(question);
                } else {
                    answerCheck.setText("Sori, väärä vastaus");
                    gameService.setWrongAnswer(question);
                }
                answeredAlready = true;
            }
        });

        option4.setOnMouseClicked((event) -> {
            if (!answeredAlready) {
                if (question.checkIfCorrect(option4.getText())) {
                    answerCheck.setText("Yay, oikein!");
                    gameService.setACorrectAnswer(question);
                } else {
                    answerCheck.setText("Sori, väärä vastaus");
                    gameService.setWrongAnswer(question);
                }
                answeredAlready = true;
            }
        });

        return setting;
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
            questionsAnswered.setText(this.gameService.getAmountOfAnswered());

        } else {
            next.setVisible(false);
            questionLabel.setText(this.gameService.getTotalScore());
            questionsAnswered.setText("");
            disableRadioButton();
        }
    }
    
 

}
