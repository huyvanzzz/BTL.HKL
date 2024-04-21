package org.example.dictionary;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/AddWord.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Dictionary Application");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DictionaryManagement.insertFromFile();
        launch(args);
    }
}