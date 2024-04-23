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
import org.example.dictionary.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class SearchController extends Controller implements Initializable {
    String current;
    @FXML
    private Label word, explain;
    @FXML
    private TextField searchField;
    private final ObservableList<String> searchResult = FXCollections.observableArrayList();

    public void updateList() {
        searchField.setOnKeyReleased(event -> {
            String keyword = searchField.getText().toLowerCase();

            // Tạo danh sách mới để lưu trữ kết quả tìm kiếm
            ObservableList<String> searchResult = FXCollections.observableArrayList();
            DictionaryCommandline.dictionarySearcher(keyword);
            searchResult.addAll(Dictionary.searcher);
            // Hiển thị kết quả tìm kiếm trên ListView
            listView.setItems(searchResult);
        });
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DictionaryManagement.sortList();
        for (Word word1 : Dictionary.dictionary.wordArrayList) {
            listView.getItems().add(word1.getWord_target());
        }
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                current = listView.getSelectionModel().getSelectedItem();
                word.setText(current);
                for (Word word1 : Dictionary.dictionary.wordArrayList) {
                    if (current.equals(word1.getWord_target())) {
                        explain.setText(word1.getWord_explain());
                        break;
                    }
                }
            }
        });
        updateList();
    }

    public void RemoveWord(ActionEvent e) {
        if (word.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR");
            alert.getDialogPane().setHeaderText("Missing");
            alert.getDialogPane().setContentText("ERROR");
            alert.showAndWait();
            word.setText("");
            explain.setText("");
            return;
        }
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.initModality(Modality.APPLICATION_MODAL);
        alert1.initOwner(stage);
        alert1.getDialogPane().setHeaderText("REMOVE WORD");
        alert1.getDialogPane().setContentText("This word already added, are you sure you want to update?");
        Optional<ButtonType> optional = alert1.showAndWait();
        if (optional.get() == ButtonType.OK) {
            DictionaryManagement.delete(word.getText());
            DictionaryManagement.dictionaryExportToFile();
            word.setText("");
            explain.setText("");
            int selectedID = listView.getSelectionModel().getSelectedIndex();
            listView.getItems().remove(selectedID);
        }
    }

    public void sound(ActionEvent e) {
        String x = word.getText();
        if (!x.isEmpty()) {
            TextToSpeech.speak(x);
        }
    }
}



