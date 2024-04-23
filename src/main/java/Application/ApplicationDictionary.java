package Application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.example.dictionary.DictionaryManagement;


public class ApplicationDictionary extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/Main.fxml"));
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