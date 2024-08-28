package com.example.serverfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartServerController {

    private Stage stage;
    private MainTest main = new MainTest();
    private ServerController controller;

    public void startServer(ActionEvent event) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/com/example/serverfx/ServerScene.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        main.start();
        main.setController(this);

    }

    public void writeOnArea(String ip) {
        controller.appendIP(ip);
    }

    public void setStage(Stage stage) {
        this.stage=stage;
    }
}
