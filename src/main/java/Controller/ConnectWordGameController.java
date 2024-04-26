package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.example.dictionary.Dictionary;
import org.example.dictionary.DictionaryCommandline;
import org.example.dictionary.DictionaryManagement;
import org.example.dictionary.Word;

import java.io.File;
import java.net.URL;
import java.util.*;

public class ConnectWordGameController extends Controller implements Initializable {
    @FXML
    private TextField textField;
    @FXML
    private Label label;
    private List<Word> words = new ArrayList<>(Dictionary.dictionary.wordArrayList);
    private String a;
    private String b;
    private String c;

    public void hints(ActionEvent e) {

    }

    public void change() {
        if (check(c) && a.equalsIgnoreCase(label.getText())) {
            label.setText(b);
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
                a = textField.getText().charAt(0) + "";
                b = textField.getText().charAt(textField.getText().length() - 1) + "";
                c = textField.getText();
            }

            // Kiểm tra xem ký tự cuối cùng có phải là ký tự Enter (hoặc ký tự khác) không
            if (event.getCode() == KeyCode.ENTER) {
                change();
                textField.setText("");
            }
        });
    }
}
