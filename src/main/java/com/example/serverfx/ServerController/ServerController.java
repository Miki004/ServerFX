package com.example.serverfx.ServerController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Controller per la vista del server.
 * Gestisce le interazioni dell'utente e aggiorna l'interfaccia utente.
 */
public class ServerController {

    @FXML
    private TextArea areaIP;

    /**
     * Aggiunge l'indirizzo IP all'area di testo.
     * Imposta l'area di testo come non modificabile e inserisce l'indirizzo IP.
     *
     * @param ip l'indirizzo IP da aggiungere all'area di testo.
     */
    public void appendIP(String ip) {
        areaIP.setEditable(false);
        areaIP.clear();
        areaIP.appendText(ip);
    }

    /**
     * Termina l'applicazione quando viene attivato l'evento di terminazione.
     *
     * @param event l'evento che ha causato la terminazione dell'applicazione.
     */
    public void terminate(ActionEvent event) {
        System.exit(0);
    }


}
