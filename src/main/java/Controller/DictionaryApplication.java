package Controller;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.dictionary.DictionaryManagement;

public class DictionaryApplication extends Application {

    public static void main(String[] args) {
        DictionaryManagement.insertFromFile();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
