package com.example.serverfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        stage = stage;
        stage.setTitle("H-CLUS-Server");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/serverfx/StartServer.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        StartServerController controller= loader.getController();
        controller.setStage(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
