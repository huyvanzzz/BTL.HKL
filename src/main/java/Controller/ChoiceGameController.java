package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import org.example.dictionary.ChooseTheRightWordGame;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class ChoiceGameController extends Controller implements Initializable {
    @FXML
    private Button A;
    @FXML
    private Button B;
    @FXML
    private Button C;
    @FXML
    private Button D;
    @FXML
    private Label question;
    private String answer;
    private int seconds = 0;
    @FXML
    private Label label2;
    @FXML
    private Label label1;
    @FXML
    private Label label;
    @FXML
    private Pane paneAlert;
    private final ChooseTheRightWordGame chooseTheRightWordGame = new ChooseTheRightWordGame();

    public void read() {
        chooseTheRightWordGame.readAnswer();
        chooseTheRightWordGame.readQuestion();
        chooseTheRightWordGame.readOption();
    }

    public void setABCD() {
        read();
        ArrayList<String> question1 = chooseTheRightWordGame.getQues();
        ArrayList<String> ans = chooseTheRightWordGame.getAns();
        ArrayList<ArrayList<String>> option = chooseTheRightWordGame.getOptions();
        Random random = new Random();
        int a = random.nextInt(45); // Số 45 là số lớn hơn index cao nhất bạn muốn random
        A.setText(option.get(a).get(0));
        B.setText(option.get(a).get(1));
        C.setText(option.get(a).get(2));
        D.setText(option.get(a).get(3));
        question.setText(question1.get(a));
        answer = ans.get(a);
    }

    @FXML
    public void handleButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();

        // Kiểm tra đáp án
        if (buttonText.equals(answer)) {
            setCorrectButtonStyle(clickedButton);
            clickedButton.setStyle(((Button) event.getSource()).getStyle());
            point += 100;
            label1.setText("Point: " + point);
            mediaPlayer.stop();
        } else {
            times--;
            setIncorrectButtonStyle(clickedButton);
            clickedButton.setStyle(((Button) event.getSource()).getStyle());
            addCorrectAnswerButton(A);
            addCorrectAnswerButton(B);
            addCorrectAnswerButton(C);
            addCorrectAnswerButton(D);
            label1.setText("Point: " + point);
            mediaPlayer.stop();
            if (times == 0) {
                paneAlert.setVisible(true);
                label2.setText("LastPoint: " + point);
            }
        }
    }

    public void setColor() {
        A.setStyle("-fx-border-color: black;");
        B.setStyle("-fx-border-color: black;");
        C.setStyle("-fx-border-color: black;");
        D.setStyle("-fx-border-color: black;");
    }

    // Đặt kiểu cho nút khi chọn đáp án đúng
    public void setCorrectButtonStyle(Button button) {
        button.setStyle("-fx-background-color: green;");
    }

    // Đặt kiểu cho nút khi chọn đáp án sai
    public void setIncorrectButtonStyle(Button button) {
        button.setStyle("-fx-background-color: red;");
    }

    public void addCorrectAnswerButton(Button x) {
        if (x.getText().equals(answer)) {
            x.setStyle("-fx-background-color: green;");
        }
    }

    public boolean check(Button a) {
        return a.getStyle().contains("-fx-background-color: green;");
    }

    public void refresh() {
        if (times >= 0 && (check(A) || check(B) || check(C) || check(D))) {
            label.setText("Times:" + times);
            setABCD();
            setColor();
            SoundChill();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label1.setText("Point: " + point);
        label.setText("Times: " + times);
        paneAlert.setVisible(false);
        setABCD();
        SoundChill();
        mediaPlayer.setOnEndOfMedia(() -> {
            addCorrectAnswerButton(A);
            addCorrectAnswerButton(B);
            addCorrectAnswerButton(C);
            addCorrectAnswerButton(D);
        });
    }

    @Override
    protected void Exit(ActionEvent e) throws IOException {
        super.switchScene(e, "/org/Game.fxml");
        mediaPlayer.stop();
    }

    @Override
    protected void switchToGameScene(ActionEvent e) throws IOException {
        super.switchScene(e, "/org/GameChoice.fxml");
    }
}




