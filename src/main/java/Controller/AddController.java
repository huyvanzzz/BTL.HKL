package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.dictionary.DictionaryManagement;
import org.example.dictionary.Word;

import java.util.Optional;

public class AddController extends Controller {
    @FXML
    private TextField WordField;
    @FXML
    private TextField ExplainField;

    public void AddOrUpdateWord(ActionEvent e) {
        String wordText = WordField.getText();
        String explainText = ExplainField.getText();

        if (wordText.isEmpty() || explainText.isEmpty()) {
            showAlert("Missing", "ERROR");
            return;
        }

        Stage stage = (Stage) AnchorPane.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);

        boolean isNewWord = !DictionaryManagement.dictionaryLookup(wordText);
        alert.getDialogPane().setHeaderText(isNewWord ? "ADD WORD" : "UPDATE WORD");
        alert.getDialogPane().setContentText("What is your choice??");
        Optional<ButtonType> optional = alert.showAndWait();

        if (optional.isPresent() && optional.get() == ButtonType.OK) {
            Word word = new Word(wordText, explainText);
            if (isNewWord) {
                DictionaryManagement.addword(word);
            } else {
                DictionaryManagement.fix(wordText, explainText);
            }
            DictionaryManagement.dictionaryExportToFile();
            WordField.setText("");
            ExplainField.setText("");
        }
    }

    private void showAlert(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR, contentText);
        alert.getDialogPane().setHeaderText(headerText);
        alert.showAndWait();
    }
}