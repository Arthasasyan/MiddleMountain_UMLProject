package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.client.mavenjavafxapp.MainApp;
import com.middlemountain.model.Employee;
import com.middlemountain.model.Good;
import com.middlemountain.service.MagicService;
import com.middlemountain.service.Service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AskForChangeController {
    public static Employee currentEmployee;
    public static Good currentGood;

    @FXML
    private Button enterChangeAction;

    @FXML
    private Button cancelChangeAction;

    @FXML
    private TextField enterNameChange;

    @FXML
    private TextField enterIdChange;

    @FXML
    void initialize() throws Exception {
        cancelChangeAction.setOnAction(event -> {
            Controller controller = new Controller();
            controller.cancelButton(cancelChangeAction);
        });

        enterChangeAction.setOnAction(event -> {
            Stage oldStage = (Stage)enterChangeAction.getScene().getWindow();
            oldStage.hide();
            String name = enterNameChange.getText();
            String id = enterIdChange.getText();
            if ( ManagerController.flag == 0 ) {
                if (!name.equals("-")) {
                    Stage stage = new Stage();
                    try {
                        currentEmployee = MainApp.service.getEmployee(name);
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
                }
                else if(!id.equals("-")){
                    Stage stage = new Stage();
                    try {
                        currentEmployee = MainApp.service.getEmployee(Integer.parseInt(id));
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
                }
            } else if (ManagerController.flag == 1 ) {
                if (!name.equals("-")) {
                    Stage stage = new Stage();
                    try {
                        currentGood = MainApp.service.getGood(name);
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
                }
                else if(!id.equals("-")) {
                    Stage stage = new Stage();
                    try {
                        currentGood = MainApp.service.getGood(Integer.parseInt(id));
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("formForGood.fxml"));
                        stage.setTitle("Magic Shop");
                        stage.setResizable(false);
                        stage.setScene(new Scene(root));
                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

}
