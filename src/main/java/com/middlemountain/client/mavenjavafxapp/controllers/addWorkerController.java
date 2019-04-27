package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.enums.Permission;
import com.middlemountain.model.Employee;
import com.middlemountain.service.MagicService;
import com.middlemountain.service.Service;
import com.middlemountain.service.TestService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class addWorkerController {
    private Service service;

    @FXML
    private TextField idAddWorker;

    @FXML
    private TextField nameAddWorker;

    @FXML
    private TextField salaryAddWorker;

    @FXML
    private Label infoWorker;

    @FXML
    private ChoiceBox<Permission> choiceBoxWorker;

    @FXML
    private Button createAddWorker;

    @FXML
    private Button cancelAddForm;

    @FXML
    private Button OKInfoWorker;

    @FXML
    void initialize() throws Exception {
        service = new MagicService();
        Employee employee = new Employee();

        ObservableList<Permission> availableChoices = FXCollections.observableArrayList(Permission.EMPLOYEE, Permission.MANAGER);
        choiceBoxWorker.setItems(availableChoices);
        choiceBoxWorker.setOnAction(event -> choiceBoxWorker.getValue());

        if (managerController.update == true ) {
            idAddWorker.setText(employee.getId().toString());
            nameAddWorker.setText(employee.getName());
            salaryAddWorker.setText(employee.getSalary().toString());
            choiceBoxWorker.setValue(employee.getPermission());
            managerController.update = false;
        }

        cancelAddForm.setOnAction(event -> {
            Controller controller = new Controller();
            controller.cancelButton(cancelAddForm);
        });

        createAddWorker.setOnAction(event -> {
            try {
                String nameWorker = nameAddWorker.getText();
                String username = nameWorker.replaceAll("\\s+", "");
                Float salary = Float.parseFloat(salaryAddWorker.getText().trim());
                Permission permission = choiceBoxWorker.getValue();
                employee.setName(nameWorker);
                employee.setSalary(salary);
                employee.setPermission(permission);
                service.createEmployee(employee, username);
            } catch (Exception e) {
                e.printStackTrace();
            }
            infoWorker.setText("New employee's id - " + employee.getId());
        });

        OKInfoWorker.setOnAction(event -> {
            Controller controller = new Controller();
            controller.cancelButton(OKInfoWorker);
        });
    }

}

