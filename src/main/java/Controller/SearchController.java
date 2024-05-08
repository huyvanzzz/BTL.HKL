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
import org.example.dictionary.Dictionary;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SearchController extends Controller implements Initializable {
    private String current = "";
    @FXML
    private Label word, explain;
    @FXML
    private TextField searchField;
    private ObservableList<String> searchResult = FXCollections.observableArrayList();
    private static ObservableList<String> favoriteWords = FXCollections.observableArrayList();

    public void updateList() {
        searchField.setOnKeyReleased(event -> {
            String keyword = searchField.getText().toLowerCase();
            if (searchResult == null) {
                searchResult = FXCollections.observableArrayList();
            }
            searchResult.clear();
            // Tạo danh sách mới để lưu trữ kết quả tìm kiếm
            searchResult = DictionaryCommandline.searchWords(keyword);
            if(searchResult !=null){
            Collections.sort(searchResult);
            // Hiển thị kết quả tìm kiếm trên ListView
            listView.setItems(searchResult);}
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
        updateList();
    }
    public static void InsertFromFile(){

    }
    public static void RemoveFavorite(String word){
        if (favoriteWords.contains(word)) {
            favoriteWords.remove(word);
        } else {
            System.out.println("Không tìm thấy từ cần xóa trong danh sách.");
        }
    }
    public void RemoveWord(ActionEvent e) {
        if (searchField.getText().isEmpty() && word.getText().equals("Word")) {
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
            DictionaryManagement.delete(word.getText());
            DictionaryManagement.dictionaryExportToFile();
            word.setText("Word");
            explain.setText("Explain");
            int selectedID = listView.getSelectionModel().getSelectedIndex();
            listView.getItems().remove(selectedID);
        }
    }

    public void sound(ActionEvent e) {
        String x = word.getText();
        if (!x.isEmpty()&& !x.equals("Word")) {
            TextToSpeech.speak(x);
        }
    }
    public static void addFromFile(){
        favoriteWords = FXCollections.observableArrayList();
        try {
            FileReader fileReader = new FileReader("dictionaryExportToFile.txt");
            Scanner sc = new Scanner(fileReader);
            while (sc.hasNextLine()) {
                String x = sc.nextLine();
                favoriteWords.add(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addToFavorites(ActionEvent e) {
        if (word.getText().equals("Word")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR");
            alert.getDialogPane().setHeaderText("Missing");
            alert.getDialogPane().setContentText("ERROR");
            alert.showAndWait();
            word.setText("Word");
            explain.setText("Explain");
            return;
        }
        String currentWord = word.getText();
        if (!favoriteWords.contains(currentWord) && !currentWord.equals("Word")) {
            favoriteWords.add(currentWord);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("dictionaryExportToFile.txt"))) {
                for (String word : favoriteWords) {
                    writer.write(word + "\n");
                }
                System.out.println("Data has been exported to file successfully!");
            } catch (IOException x) {
                System.err.println("Error occurred while exporting data to file: " + x.getMessage());
            }
        }
    }
    public static ObservableList<String> getFavoriteWords() {
        addFromFile();
        Collections.sort(favoriteWords);
        return favoriteWords;
    }
}



