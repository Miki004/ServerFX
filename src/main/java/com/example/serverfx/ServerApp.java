package com.example.serverfx;

import com.example.serverfx.ServerController.StartServerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Punto di ingresso principale per l'applicazione JavaFX.
 * Questa classe configura il palco principale e carica l'interfaccia utente dal file FXML.
 */
public class ServerApp extends Application {

    /**
     * Punto di ingresso principale per l'applicazione JavaFX.
     * Inizializza il palco principale, carica il file FXML e configura la scena.
     *
     * @param stage il palco principale per questa applicazione, su cui verrà impostata la scena.
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/serverfx/StartServer.fxml"));
        Parent root = loader.load();
        stage.setTitle("H-CLUS-SERVER");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        StartServerController controller= loader.getController();
        controller.setStage(stage);
    }

    /**
     * Il metodo principale per avviare l'applicazione JavaFX.
     *
     * @param args argomenti della riga di comando passati all'applicazione.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
