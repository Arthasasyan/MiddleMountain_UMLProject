package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.service.MagicService;
import com.middlemountain.service.Service;
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
    public static int flag = -1;
    public static Boolean update = false;

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
    public Label info;

    @FXML
    private Button exitButtonAction;

    @FXML
    void initialize() throws Exception {
        nameManager.setText(loginController.employee.getName());
        service = new MagicService();
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

        createPersonAction.setOnAction(event -> addFormPerson(createPersonAction));

        createGoodAction.setOnAction(event -> addFormGood(createGoodAction));

        changePersonAction.setOnAction(event -> {
            update = true;
            addFormPerson(changePersonAction);
        });

        changeGoodAction.setOnAction(event -> {
            update = true;
            addFormGood(changeGoodAction);
        });

        deleteGoodAction.setOnAction(event -> {
            flag = 1;
            removeForm(deleteGoodAction);
        });

        deletePersonAction.setOnAction(event -> {
            flag = 0;
            removeForm(deletePersonAction);
        });
    }

    public void removeForm(Button button) {
        button.setOnAction(event -> {
            Stage stage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("askForRemove.fxml"));
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

    public void addFormGood(Button button) {
        button.setOnAction(event -> {
            Stage stage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("formForGood.fxml"));
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

    public void addFormPerson(Button button) {
        button.setOnAction(event -> {
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
