package Controller;

import org.example.dictionary.Dictionary;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.dictionary.DictionaryCommandline;

import java.io.IOException;
import java.util.Optional;

public abstract class Controller {
    @FXML
    protected AnchorPane AnchorPane;
    @FXML
    protected ListView<String> listView;
    @FXML
    protected Stage stage;
    protected Scene scene;
    protected Parent root;

    @FXML
    protected void switchToSearchScene(ActionEvent e) throws IOException {
        switchScene(e, "/org/Main.fxml");
    }

    @FXML
    protected void switchToAPIScene(ActionEvent e) throws IOException {
        switchScene(e, "/org/API.fxml");
    }

    @FXML
    protected void switchToOperationScene(ActionEvent e) throws IOException {
        switchScene(e, "/org/AddWord.fxml");
    }
    @FXML
    protected void switchToGameScene(ActionEvent e) throws IOException {
        switchScene(e, "GameUI.fxml");
    }

    protected void switchScene(ActionEvent e, String path) throws IOException {
        root = FXMLLoader.load(getClass().getResource(path));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void Exit(ActionEvent e) {

        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);

        alert.getDialogPane().setHeaderText("EXIT");
        alert.getDialogPane().setContentText("You sure want to EXIT?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        } else if (result.get() == ButtonType.CANCEL) {
        }
    }
}