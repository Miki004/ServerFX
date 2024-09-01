package com.example.serverfx.ServerController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ServerController {

    @FXML
    private TextArea areaIP;

    public void appendIP(String ip) {
        areaIP.setEditable(false);
        areaIP.clear();
        areaIP.appendText(ip);
    }
    public void terminate(ActionEvent event) {
        System.exit(0);
    }


}
