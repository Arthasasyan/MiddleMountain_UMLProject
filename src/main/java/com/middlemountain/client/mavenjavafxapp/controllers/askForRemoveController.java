package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.model.Employee;
import com.middlemountain.model.Good;
import com.middlemountain.service.MagicService;
import com.middlemountain.service.Service;
import com.middlemountain.service.TestService;
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
    void initialize() throws Exception {
        service = new MagicService();
        cancelAuthAction.setOnAction(event -> {
            Controller controller = new Controller();
            controller.cancelButton(cancelAuthAction);
        });

        enterAuthAction.setOnAction(event -> {
            String name = enterNameChange.getText();
            if(!name.equals("")) {
                if (managerController.flag == 0) {
                    try {
                        service.deleteEmployee(service.getEmployee(name));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (managerController.flag == 1) {
                    try {
                        Good good = new Good();
                        service.createGood(good.setName(name));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            } else System.out.println("Error");
        });
    }

}
