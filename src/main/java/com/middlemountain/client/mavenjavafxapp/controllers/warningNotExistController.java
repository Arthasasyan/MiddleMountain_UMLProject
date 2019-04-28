package com.middlemountain.client.mavenjavafxapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class warningNotExistController {

    @FXML
    private Button createOrder;

    @FXML
    private Button cancelCreateOrder;

    @FXML
    void initialize() {
        askForSearchController searchController = new askForSearchController();
        cancelCreateOrder.setOnAction(event -> {
            Controller controller = new Controller();
            controller.cancelButton(cancelCreateOrder);
        });

        createOrder.setOnAction(event -> {
            Stage oldStage = (Stage)createOrder.getScene().getWindow();
            oldStage.hide();
            searchController.openForm(event);
        });
    }

}
