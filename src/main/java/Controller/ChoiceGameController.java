package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.dictionary.ChooseTheRightWordGame;

import java.net.URL;
import java.util.ArrayList;
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
        int a = chooseTheRightWordGame.Random(45);
        A.setText(option.get(a).get(0));
        B.setText(option.get(a).get(1));
        C.setText(option.get(a).get(2));
        D.setText(option.get(a).get(3));
        question.setText(question1.get(a));
        answer = ans.get(a);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setABCD();
    }
}




