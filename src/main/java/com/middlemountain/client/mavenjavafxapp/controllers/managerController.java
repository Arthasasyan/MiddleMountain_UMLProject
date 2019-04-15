package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.model.Good;
import com.middlemountain.service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class managerController {

    @FXML
    private Button createGoodAction;

    @FXML
    private Button changeGoodAction;

    @FXML
    private Button deleteGoodAction;

    @FXML
    private Button createPersonAction;

    @FXML
    private Button changePersonAction;

    @FXML
    private Button deletePersonAction;

    @FXML
    private Button exitButtonAction;

    @FXML
    void initialize(Good good) {
        deleteGoodAction.setOnAction(event -> {
            Service service = null;
            try {
                service.deleteGood(good);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
