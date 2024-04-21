package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.example.dictionary.TranslateAPI;

public class ApiController extends Controller {
    private String in = "en";
    private String out = "vi";

    private int inX = 150;
    private int outX = 550;

    @FXML
    private TextArea TransIn, TransOut;

    @FXML
    private Label LangIn, LangOut;

    public void Sound(ActionEvent e) {

    }

    public void translateWord(ActionEvent e) throws IOException {
        String word = TransIn.getText();
        String Trans = TranslateAPI.googleTranslate(in, out, word);
        TransOut.setText(Trans);
    }

    public void swapTrans(ActionEvent e) {
        String temp = in;
        in = out;
        out = temp;
        TransIn.setText("");
        TransOut.setText("");

        int sw = inX;
        inX = outX;
        outX = sw;
        LangIn.setLayoutX(inX);
        LangOut.setLayoutX(outX);
    }
}
