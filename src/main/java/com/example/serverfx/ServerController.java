package com.example.serverfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Controller per la vista del server. Gestisce l'interazione con l'interfaccia utente definita nel file FXML.
 */
public class ServerController {

    @FXML
    private TextArea areaIP;

    /**
     * Aggiunge un testo (indirizzo IP) all'area di testo della vista.
     *
     * @param ip l'indirizzo IP da aggiungere all'area di testo.
     */
    public void appendIP(String ip) {
        areaIP.appendText(ip);
    }

    /**
     * Gestisce l'evento di terminazione dell'applicazione.
     * Chiude l'applicazione terminando il processo.
     *
     * @param event l'evento che ha attivato il metodo (solitamente un'azione di clic su un pulsante).
     */
    public void terminate(ActionEvent event) {

        System.exit(0);
    }


}
