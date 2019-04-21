package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.client.mavenjavafxapp.MainApp;
import com.middlemountain.enums.Permission;
import com.middlemountain.model.Employee;
import com.middlemountain.service.Service;
import com.middlemountain.service.TestService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;;

public class loginController {
    private Service service;

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
        service = new TestService();
        enterAuthAction.setOnAction(event -> {
            try {
                Employee employee =  null;
                Stage stage = new Stage();
                Stage oldStage = (Stage)enterAuthAction.getScene().getWindow();
                oldStage.hide();
                MainApp.oldestStage.close();
                String loginText = loginInputAction.getText().trim();
                String passwordText = passwordInputAction.getText().trim();
                if(!loginText.equals("") && !passwordText.equals("")) {
                    try  {
                        employee = service.login(loginText, passwordText);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        throw e;
                    }
                    if( service.login(loginText, passwordText).getPermission().equals(Permission.EMPLOYEE)) {
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("worker.fxml"));
                        stage.setTitle("Worker's window");
                        stage.setResizable(false);
                        stage.setScene(new Scene(root));
                        stage.show();
                    } else if ( service.login(loginText, passwordText).getPermission().equals(Permission.MANAGER)) {
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("manager.fxml"));
                        stage.setTitle("Manager's window");
                        stage.setResizable(false);
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                } else System.out.println("Error");
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

