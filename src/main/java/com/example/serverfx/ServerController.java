package com.example.serverfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ServerController {

    @FXML
    private TextArea areaIP;
    public void appendIP(String ip) {
        areaIP.appendText(ip);
    }
    public void terminate(ActionEvent event) {

        System.exit(0);
    }


}
