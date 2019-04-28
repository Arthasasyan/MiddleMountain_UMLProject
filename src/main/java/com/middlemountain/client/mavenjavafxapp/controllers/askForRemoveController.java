package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.model.Employee;
import com.middlemountain.model.Good;
import com.middlemountain.service.MagicService;
import com.middlemountain.service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class askForRemoveController {
    private Service service;

    @FXML
    private Button enterAuthAction;

    @FXML
    private Button cancelAuthAction;

    @FXML
    private TextField enterNameChange;

    @FXML
    private TextField enterIdChange;


    @FXML
    void initialize() throws Exception {
        service = new MagicService();
        cancelAuthAction.setOnAction(event -> {
            Controller controller = new Controller();
            controller.cancelButton(cancelAuthAction);
        });

        enterAuthAction.setOnAction(event -> {
            String name = enterNameChange.getText();
            String id = enterIdChange.getText();
            if (managerController.flag == 0) {
                try {
                    if ( !name.equals("-") ) {
                        service.deleteEmployee(service.getEmployee(name));
                    } else if( !id.equals("-")) {
                        service.deleteEmployee(service.getEmployee(Integer.parseInt(id)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (managerController.flag == 1) {
                try {
                    if ( !name.equals("") ) {
                        service.deleteGood(service.getGood(name));
                    } else if( !id.equals("-")) {
                        service.deleteGood(service.getGood(Integer.parseInt(id)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            managerController.flag = -1;
        });
    }

}
