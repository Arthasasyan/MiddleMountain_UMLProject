package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.service.MagicService;
import com.middlemountain.service.Service;
import com.middlemountain.service.TestService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class askForChangeController {
    private Service service;

    @FXML
    private Button enterAuthAction;

    @FXML
    private Button cancelAuthAction;

    @FXML
    private TextField enterNameChange;

    @FXML
    void initialize() throws Exception {
        service = new MagicService();
        cancelAuthAction.setOnAction(event -> {
            Controller controller = new Controller();
            controller.cancelButton(cancelAuthAction);
        });

    }

}
