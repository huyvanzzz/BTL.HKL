package Controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.example.dictionary.*;
import org.example.dictionary.Dictionary;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ConnectWordGameController extends Controller implements Initializable {
    @FXML
    private TextField textField;
    @FXML
    private Label label, label2, label1;
    private List<Word> words = new ArrayList<>(Dictionary.dictionary.wordArrayList);
    private String a = "";
    private String b = "";
    private String h;
    @FXML
    private Label A;
    @FXML
    private Label B;
    @FXML
    private Pane paneAlert;
    @FXML
    private Button button;
    private ObservableList<String> searchResult = FXCollections.observableArrayList();

    public void hints(ActionEvent e) {
        String x = label.getText();
        searchResult = DictionaryCommandline.searchWordsWithPrefix(x);
        if (point < 200) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR");
            alert.getDialogPane().setHeaderText("Missing");
            alert.getDialogPane().setContentText("ERROR");
            alert.showAndWait();
            return;
        }
        if (point >= 200 && !searchResult.isEmpty() && !label1.isVisible()) {
            label1.setVisible(true);
            Random random = new Random();
            int randomNumber;
            if(label.getText().equalsIgnoreCase('z'+"")){
                randomNumber = random.nextInt(10);
            }
            // Sinh một số ngẫu nhiên trong phạm vi từ min đến max
            else {
                randomNumber = random.nextInt(15) ;
            }
            label1.setText(searchResult.get(randomNumber));
            point -= 200;
            A.setText("Point: " + point);
        }

    }

    public void change() {
        if (a.equalsIgnoreCase(label.getText()) && times > 0) {
            label.setText(b);
            point += 100;
            A.setText("Point: " + point);
            label1.setVisible(false);
        } else if (times == 1) {
            mediaPlayer.stop();
            paneAlert.setVisible(true);
            label2.setText("LastPoint: " + point);
        } else {
            times--;
            B.setText("Times: " + times);
        }
    }

    private boolean check(String a) {
        return DictionaryCommandline.dictionaryLookup(a);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SoundChill();
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(javafx.util.Duration.ZERO));
        mediaPlayer.play();
        B.setText("Times: " + times);
        A.setText("Point: " + point);
        paneAlert.setVisible(false);
        // Chọn một từ ngẫu nhiên để bắt đầu
        Random random = new Random();
        int index = random.nextInt(words.size());
        String startWord = words.get(index).getWord_target();
        char firstChar = startWord.toUpperCase().charAt(0);
        label.setText(firstChar + "");
        label.setText(label.getText().toUpperCase());
        textField.setOnKeyReleased(event -> {
            // Lấy ký tự cuối cùng người dùng vừa nhập vào
            if (!textField.getText().isEmpty() && check(textField.getText())) {
                String c = textField.getText();
                a = c.charAt(0) + "";
                if (c.length() >= 2) {
                    b = c.charAt(c.length() - 1) + "";
                } else {
                    b = a; // Gán giá trị của a cho b nếu chuỗi chỉ có một ký tự
                }
            }

            // Kiểm tra xem ký tự cuối cùng có phải là ký tự Enter (hoặc ký tự khác) không
            if (event.getCode() == KeyCode.ENTER) {
                change();
                textField.setText("");
            }
        });
    }

    @Override
    protected void Exit(ActionEvent e) throws IOException {
        super.switchScene(e, "/org/Game.fxml");
        mediaPlayer.stop();
    }

    @Override
    protected void switchToGameScene(ActionEvent e) throws IOException {
        super.switchScene(e, "/org/GameConnect.fxml");
    }
}
