package com.example.serverfx.ServerController;

import com.example.serverfx.Server.MainTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller per l'avvio del server.
 * Gestisce l'inizio del server e l'interazione con l'interfaccia utente.
 */
public class StartServerController {

    private Stage stage;
    private MainTest main = new MainTest();
    private ServerController controller;

    /**
     * Avvia il server e cambia la scena dell'interfaccia utente.
     * Carica la scena del server e avvia il server in un nuovo thread.
     *
     * @param event l'evento che ha attivato l'avvio del server.
     * @throws IOException se si verifica un errore durante il caricamento del file FXML.
     */
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

    /**
     * Aggiorna l'area di testo con l'indirizzo IP del client.
     *
     * @param ip l'indirizzo IP da visualizzare nell'area di testo.
     */
    public void writeOnArea(String ip) {
        controller.appendIP(ip);
    }

    /**
     * Imposta la finestra principale dell'applicazione.
     *
     * @param stage la finestra principale da impostare.
     */
    public void setStage(Stage stage) {
        this.stage=stage;
    }
}
