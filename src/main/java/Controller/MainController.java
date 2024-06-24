package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {
    @FXML
    public ArticlesController articlesController;
    @FXML
    public SwitchScenes switchScenes;

    @FXML
    public void processSave(ActionEvent event) {
        switchScenes.processSave();
    }
    @FXML
    public void switchScenesFileAll(ActionEvent event) {
        switchScenes.getSceneSave();
    }
}
