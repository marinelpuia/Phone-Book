package sample;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class ShowTheBlog {
    @FXML
    private WebView webView;
    @FXML
    private WebEngine webEngine;

    public void showBlog() {
        Stage stage = new Stage();
        final WebView webView = new WebView();
        webView.getEngine().load("https://www.puiamarinel.ro/");
        stage.setTitle("Author: Marinel Puia - Personal Website -");
        stage.setScene(new Scene(webView, 1300, 600));
        stage.show();

    }
}
