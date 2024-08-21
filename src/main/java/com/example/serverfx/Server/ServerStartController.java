package com.example.serverfx.Server;

import com.example.serverfx.MainTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ServerStartController {
    private final Stage stage = new Stage();
    private MainTest main = new MainTest();

    public void startServer(ActionEvent event) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/com/example/serverfx/ServerScene.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
      //  main.initServer();
        stage.show();
    }
}
