package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.client.mavenjavafxapp.MainApp;
import com.middlemountain.service.MagicService;
import com.middlemountain.service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AskForRemoveController {

    @FXML
    private Button enterRemoveAction;

    @FXML
    private Button cancelAuthAction;

    @FXML
    private TextField enterNameChange;

    @FXML
    private TextField enterIdChange;


    @FXML
    void initialize() throws Exception {
        cancelAuthAction.setOnAction(event -> {
            Controller controller = new Controller();
            controller.cancelButton(cancelAuthAction);
        });

        enterRemoveAction.setOnAction(event -> {
            Stage oldStage = (Stage)enterRemoveAction.getScene().getWindow();
            oldStage.hide();
            String name = enterNameChange.getText();
            String id = enterIdChange.getText();
            if (ManagerController.flag == 0) {
                try {
                    if ( !name.equals("-") ) {
                        MainApp.service.deleteEmployee(MainApp.service.getEmployee(name));
                    } else if( !id.equals("-")) {
                        MainApp.service.deleteEmployee(MainApp.service.getEmployee(Integer.parseInt(id)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (ManagerController.flag == 1) {
                try {
                    if ( !name.equals("-") ) {
                        MainApp.service.deleteGood(MainApp.service.getGood(name));
                    } else if( !id.equals("-")) {
                        MainApp.service.deleteGood(MainApp.service.getGood(Integer.parseInt(id)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            ManagerController.flag = -1;
        });
    }

}
