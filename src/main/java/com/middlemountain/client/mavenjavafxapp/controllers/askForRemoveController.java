package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.model.Employee;
import com.middlemountain.model.Good;
import com.middlemountain.service.Service;
import com.middlemountain.service.TestService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class askForChangingController {
    private Service service;

    @FXML
    private Button enterAuthAction;

    @FXML
    private Button cancelAuthAction;

    @FXML
    private TextField enterNameChange;

    @FXML
    void initialize() {
        service = new TestService();
        String name = enterNameChange.getText();
        cancelAuthAction.setOnAction(event -> {
            Controller controller = new Controller();
            controller.cancelButton(cancelAuthAction);
        });

        enterAuthAction.setOnAction(event -> {
            if(managerController.flag == 0) {
                try {
                    Employee employee = null;
                    service.deleteEmployee(employee.setName(name));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (managerController.flag == 1) {
                try {
                Good good = null;

            }
        });
    }

}
