package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.model.Good;
import com.middlemountain.service.Service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
