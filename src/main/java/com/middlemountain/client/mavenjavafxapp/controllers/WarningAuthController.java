package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.client.mavenjavafxapp.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WarningAuthController {

    @FXML
    private Button okButtonWarning;

    @FXML
    void initialize() {
        MainApp.oldestStage.show();
        okButtonWarning.setOnAction(event -> {
            Controller controller = new Controller();
            controller.cancelButton(okButtonWarning);
        });
    }
}
