package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.service.Service;
import com.middlemountain.service.TestService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class managerController {
    private Service service;

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
    private Label nameManager;

    @FXML
    private Button exitButtonAction;

    @FXML
    void initialize() {
        service = new TestService();
        exitButtonAction.setOnAction(event -> {
            Stage oldStage = (Stage)exitButtonAction.getScene().getWindow();
            oldStage.close();
            Stage stage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
                stage.setTitle("Magic Shop");
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        createPersonAction.setOnAction(event -> {
            Stage stage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("formForEmployee.fxml"));
                stage.setTitle("Magic Shop");
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node)event.getSource()).getScene().getWindow());
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
