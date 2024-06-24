package Controller;

import Entity.Articles;
import Repository.MySQLRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.articlesfx.HelloApplication;

import java.io.IOException;
import java.net.URL;

public class SwitchScenes {
    public TextField idSave;
    public TextField desSave;
    public TextField contentSave;
    public TextField titleSave;
    private Scene scene;
    private Stage stage;
    private Parent root;
    MySQLRepository mySQLRepository = new MySQLRepository();
    @FXML
    public void getScenesMenu(ActionEvent event) throws IOException {
        try{
            root  = FXMLLoader.load(getClass().getResource("table-form.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void getSceneSave() {

        try {
            root = FXMLLoader.load(getClass().getResource("save-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) root.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void processSave() {
        Articles article = new Articles();
        article.setTitle(titleSave.getText());
        article.setDes(desSave.getText());
        article.setContent(contentSave.getText());
        mySQLRepository.save(article);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Article Save Success");
        alert.show();
        reset();
    }
    @FXML
    private void reset() {
        idSave.clear();
        titleSave.clear();
        desSave.clear();
        contentSave.clear();
    }
}
