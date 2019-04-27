package com.middlemountain.client.mavenjavafxapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class workerController {

    @FXML
    private Button createOrderAction;

    @FXML
    private Button getOrderAction;

    @FXML
    private Button createEnchantAction;

    @FXML
    private Button changePersonAction;

    @FXML
    private Label nameWorker;

    @FXML
    private Label infoWorker;

    @FXML
    private Button exitButtonAction;

    @FXML
    void initialize() {
        nameWorker.setText(loginController.employee.getName());
        createOrderAction.setOnAction(event -> {

        });

        getOrderAction.setOnAction(event -> {

        });

        createEnchantAction.setOnAction(event -> {

        });

        changePersonAction.setOnAction(event -> {

        });

        exitButtonAction.setOnAction(event -> {
            Stage oldStage = (Stage)exitButtonAction.getScene().getWindow();
            oldStage.close();
            Stage stage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                stage.setTitle("Magic Shop");
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
