package com.devcolibri.mavenjavafxapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginController {

    @FXML
    private Button enterAuthAction;

    @FXML
    private Button cancelAuthAction;

    @FXML
    private TextField loginInputAction;

    @FXML
    private PasswordField passwordInputAction;

    @FXML
    void initialize() {
        enterAuthAction.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Stage oldStage = (Stage)enterAuthAction.getScene().getWindow();
                oldStage.close();
                Parent root = FXMLLoader.load(getClass().getResource("../fxml/worker.fxml"));
                stage.setTitle("Окно работника");
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        cancelAuthAction.setOnAction(event -> {
            try {
                Stage stage = (Stage)cancelAuthAction.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
