package Controller;

import Entity.Articles;
import Repository.MySQLRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.articlesfx.HelloApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
public class ArticlesController implements Initializable {
    public TableView tableView;
    public TableColumn txtContentView;
    public TableColumn txtDescriptionView;
    public TableColumn txtTitleView;
    public TableColumn txtId;
    public TextField idInfo;
    public TextField titleInfo;
    public TextArea desInfo;
    public TextArea contentInfo;

    private ObservableList<Articles> articlesList;
    private MySQLRepository mySQLRepository = new MySQLRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Articles> articles = mySQLRepository.finAllArticles();
        articlesList = FXCollections.observableArrayList(articles);
        txtId.setCellValueFactory(new PropertyValueFactory<Articles, Integer>("id"));
        txtTitleView.setCellValueFactory(new PropertyValueFactory<Articles, String>("title"));
        txtDescriptionView.setCellValueFactory(new PropertyValueFactory<Articles, String>("des"));
        txtContentView.setCellValueFactory(new PropertyValueFactory<Articles, String>("content"));
        tableView.setItems(articlesList);
    }
    @FXML
    public void selected(MouseEvent mouseEvent) {
        int index = -1;
        index = tableView.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        idInfo.setText(txtId.getCellData(index).toString());
        titleInfo.setText(txtTitleView.getCellData(index).toString());
        desInfo.setText(txtDescriptionView.getCellData(index).toString());
        contentInfo.setText(txtContentView.getCellData(index).toString());
    }

    public void update(ActionEvent actionEvent) {
        Articles articles = new Articles();
        articles.setId(Integer.parseInt(idInfo.getText()));
        articles.setTitle(titleInfo.getText());
        articles.setDes(desInfo.getText());
        articles.setContent(contentInfo.getText());
        mySQLRepository.update(articles);
    }
    public void delete(ActionEvent actionEvent) {
        Articles articles = new Articles();
        articles.setId(Integer.parseInt(idInfo.getText()));
        articles.setTitle(titleInfo.getText());
        articles.setDes(desInfo.getText());
        articles.setContent(contentInfo.getText());
        mySQLRepository.delete(articles);
    }

}

