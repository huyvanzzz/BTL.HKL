package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.example.dictionary.TextToSpeech;
import org.example.dictionary.TranslateAPI;

public class ApiController extends Controller {
    private String inputLanguage = "en";
    private String outputLanguage = "vi";
    private int inX = 80;
    private int outX = 430;

    @FXML
    private TextArea TransInput, TransOutput;

    @FXML
    private Label LabelIn, LabelOut;

    public void sound(ActionEvent e) {
        String word = TransInput.getText();
        if (!word.isEmpty()) {
            TextToSpeech.speak(word);
        }
    }
    public void swap(ActionEvent e) {
        String x = inputLanguage;
        inputLanguage = outputLanguage;
        outputLanguage = x;
        int coordinates = inX;
        String a = TransInput.getText();
        TransInput.setText(TransOutput.getText());
        TransOutput.setText(a);
        inX = outX;
        outX = coordinates;
        LabelIn.setLayoutX(inX);
        LabelOut.setLayoutX(outX);
    }

    public void translate(ActionEvent e) {
        String word = TransInput.getText();
        String translatedText = TranslateAPI.googleTranslate(inputLanguage, outputLanguage, word);
        TransOutput.setText(translatedText);
    }


}
