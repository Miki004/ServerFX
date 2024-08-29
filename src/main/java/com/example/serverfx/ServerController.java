package com.example.serverfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

public class ServerController {

    @FXML
    private TextArea areaIP;

    public void initialize() {
        // Blocca l'input da tastiera nell'areaIP
        areaIP.setEditable(false); // Rende l'area non modificabile
        areaIP.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            event.consume(); // Blocca l'evento della tastiera
        });
    }

    public void appendIP(String ip) {
        areaIP.clear();
        areaIP.appendText(ip);
    }
    public void terminate(ActionEvent event) {
        System.exit(0);
    }


}
