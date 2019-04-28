package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.client.mavenjavafxapp.MainApp;
import com.middlemountain.enums.Permission;
import com.middlemountain.model.Employee;
import com.middlemountain.service.MagicService;
import com.middlemountain.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AddWorkerController {

    @FXML
    private Text idAndUsername;

    @FXML
    private TextField usernameAddWorker;

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
    private Label passwordWorker;

    @FXML
    void initialize() throws Exception {
        Employee employee = new Employee();

        ObservableList<Permission> availableChoices = FXCollections.observableArrayList(Permission.EMPLOYEE, Permission.MANAGER);
        choiceBoxWorker.setItems(availableChoices);
        choiceBoxWorker.setOnAction(event -> choiceBoxWorker.getValue());

        if (ManagerController.update == true ) {
            idAndUsername.setText("id");
            usernameAddWorker.setText(AskForChangeController.currentEmployee.getId().toString());
            nameAddWorker.setText(AskForChangeController.currentEmployee.getName());
            salaryAddWorker.setText(AskForChangeController.currentEmployee.getSalary().toString());
            choiceBoxWorker.setValue(AskForChangeController.currentEmployee.getPermission());
        }

        cancelAddForm.setOnAction(event -> {
            Controller controller = new Controller();
            controller.cancelButton(cancelAddForm);
        });

        createAddWorker.setOnAction(event -> {
            idAndUsername.setText("Username");
            try {
                String nameWorker = nameAddWorker.getText();
                String username = usernameAddWorker.getText();
                Float salary = Float.parseFloat(salaryAddWorker.getText().trim());
                Permission permission = choiceBoxWorker.getValue();
                employee.setName(nameWorker);
                employee.setSalary(salary);
                employee.setPermission(permission);
                if( ManagerController.update == true ) {
                    employee.setId(AskForChangeController.currentEmployee.getId());
                    MainApp.service.updateEmployee(employee);
                    System.out.println("Update has done");
                } else passwordWorker.setText(MainApp.service.createEmployee(employee, username));
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

