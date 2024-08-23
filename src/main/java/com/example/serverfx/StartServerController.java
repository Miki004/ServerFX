package com.example.serverfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller per la vista di avvio del server. Gestisce l'interazione con l'interfaccia utente per avviare il server e aggiornare l'interfaccia.
 */
public class StartServerController {
    private Stage stage;
    private MainTest main = new MainTest();
    private ServerController controller;

    /**
     * Gestisce l'evento di avvio del server.
     * Carica la vista del server, imposta la scena e avvia il server.
     *
     * @param event l'evento che ha attivato il metodo (solitamente un'azione di clic su un pulsante).
     * @throws IOException se si verifica un errore durante il caricamento della vista o la configurazione della scena.
     */
    public void startServer(ActionEvent event) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/com/example/serverfx/ServerScene.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
        main.start();
        main.setController(this);

    }

    /**
     * Aggiunge un indirizzo IP all'area di testo nella vista del server.
     *
     * @param ip l'indirizzo IP da aggiungere all'area di testo.
     */
    public void writeOnArea(String ip) {
        controller.appendIP(ip);
    }

    /**
     * Imposta lo stage principale dell'applicazione.
     *
     * @param stage lo stage da impostare.
     */
    public void setStage(Stage stage) {
        this.stage=stage;
    }
}
