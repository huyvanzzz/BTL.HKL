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
        Word x = new Word(WordField.getText(), ExplainField.getText());
        Stage stage = (Stage) AnchorPane.getScene().getWindow();
        if (WordField.getText().isEmpty() || ExplainField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR");
            alert.getDialogPane().setHeaderText("Missing");
            alert.getDialogPane().setContentText("ERROR");
            alert.showAndWait();
            return;
        }
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.initModality(Modality.APPLICATION_MODAL);// chỉ cho phép tương tác vs alert
        alert1.initOwner(stage);// cửa sổ thông báo sẽ được hiển thị trên cửa sổ gốc này và sẽ nằm trên đỉnh khi hiển thị
        if (DictionaryManagement.dictionaryLookup(WordField.getText())) {
            alert1.getDialogPane().setHeaderText("UPDATE WORD");
            alert1.getDialogPane().setContentText("What is your choice??");
            Optional<ButtonType> optional = alert1.showAndWait();
            if (optional.get() == ButtonType.OK) {
                DictionaryManagement.fix(WordField.getText(),ExplainField.getText());
            }
        } else {
            alert1.getDialogPane().setHeaderText("ADD WORD");
            alert1.getDialogPane().setContentText("What is your choice??");
            Optional<ButtonType> optional = alert1.showAndWait();
            if (optional.get() == ButtonType.OK) {
                DictionaryManagement.addword(x);
            }
        }
        DictionaryManagement.dictionaryExportToFile();
        WordField.setText("");
        ExplainField.setText("");
    }
}
