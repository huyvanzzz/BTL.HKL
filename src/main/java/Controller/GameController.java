package Controller;

import javafx.event.ActionEvent;

import java.io.IOException;

public class GameController extends Controller{
    public void switchToChoiceGame(ActionEvent e) throws IOException {
      super.switchScene(e,"/org/GameChoice.fxml");
    }
}
