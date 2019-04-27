package com.middlemountain.client.mavenjavafxapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private Button enterButtonAction;

    @FXML
    void initialize() {
        enterButtonAction.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
                stage.setTitle("Авторизация");
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node)event.getSource()).getScene().getWindow());
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void cancelButton(Button button) {
        button.setOnAction(event -> {
            try {
                Stage stage = (Stage)button.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
