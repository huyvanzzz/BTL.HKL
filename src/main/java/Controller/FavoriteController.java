package Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Modality;
import org.example.dictionary.Dictionary;
import org.example.dictionary.DictionaryManagement;
import org.example.dictionary.TextToSpeech;
import org.example.dictionary.Word;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class FavoriteController extends Controller implements Initializable {
    @FXML
    private ListView<String> listview;
    private String current = "";
    @FXML
    private Label word, explain;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Liên kết danh sách yêu thích với ListView
        listview.setItems(SearchController.getFavoriteWords());
        listview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    current = newValue;
                    word.setText(current);
                    for (Word word1 : Dictionary.dictionary.wordArrayList) {
                        if (current.equals(word1.getWord_target())) {
                            explain.setText(word1.getWord_explain());
                            break;
                        }
                    }
                }
            }
        });
    }
    public void RemoveWord(ActionEvent e) {
        if (word.getText().equals("Word")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR");
            alert.getDialogPane().setHeaderText("Missing");
            alert.getDialogPane().setContentText("ERROR");
            alert.showAndWait();
            word.setText("Word");
            explain.setText("Explain");
            return;
        }
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.initModality(Modality.APPLICATION_MODAL);
        alert1.initOwner(stage);
        alert1.getDialogPane().setHeaderText("REMOVE WORD");
        alert1.getDialogPane().setContentText("This word already added, are you sure you want to update?");
        Optional<ButtonType> optional = alert1.showAndWait();
        if (optional.get() == ButtonType.OK) {
            SearchController.RemoveFavorite(String.valueOf(word));
            word.setText("Word");
            explain.setText("Explain");
            int selectedID = listview.getSelectionModel().getSelectedIndex();
            listview.getItems().remove(selectedID);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("dictionaryExportToFile.txt"))) {
                for (String word : listview.getItems()) {
                    writer.write(word + "\n");
                }
                System.out.println("Data has been exported to file successfully!");
            } catch (IOException x) {
                System.err.println("Error occurred while exporting data to file: " + x.getMessage());
            }
        }
    }

    public void sound(ActionEvent e) {
        String x = word.getText();
        if (!x.isEmpty()&&!x.equals("Word")) {
            TextToSpeech.speak(x);
        }
    }
}
