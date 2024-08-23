package com.example.serverfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe principale dell'applicazione server. Estende {@link Application} e avvia l'interfaccia utente JavaFX.
 */
public class ServerApp extends Application {

    /**
     * Metodo chiamato dal framework JavaFX per avviare l'applicazione.
     * Carica il file FXML per la vista principale e imposta la scena principale.
     *
     * @param stage il palcoscenico principale dell'applicazione.
     * @throws Exception se si verifica un errore durante il caricamento del file FXML.
     */

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/serverfx/StartServer.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
        StartServerController controller= loader.getController();
        controller.setStage(stage);
    }

    /**
     * Metodo principale per avviare l'applicazione JavaFX.
     *
     * @param args argomenti della riga di comando.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
