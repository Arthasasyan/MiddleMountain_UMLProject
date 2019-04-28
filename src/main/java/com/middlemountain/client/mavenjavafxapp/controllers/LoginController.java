package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.client.mavenjavafxapp.MainApp;
import com.middlemountain.enums.Permission;
import com.middlemountain.model.Employee;
import com.middlemountain.service.MagicService;
import com.middlemountain.service.Service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;;

public class LoginController {
    public static String loginUsername;
    public static String accountName = "";
    public static Employee employee =  null;
    public static Employee nowEmployee;
    public static Stage oldestWorkerStage;

    @FXML
    private Button enterAuthAction;

    @FXML
    private Button cancelAuthAction;

    @FXML
    private TextField loginInputAction;

    @FXML
    private PasswordField passwordInputAction;

    @FXML
    void initialize() throws Exception {
        enterAuthAction.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Stage oldStage = (Stage)enterAuthAction.getScene().getWindow();
                oldStage.hide();
                MainApp.oldestStage.close();
                String loginText = loginInputAction.getText().trim();
                loginUsername = loginInputAction.getText();
                String passwordText = passwordInputAction.getText().trim();
                if(!loginText.equals("") && !passwordText.equals("")) {
                    try  {
                        employee = MainApp.service.login(loginText, passwordText);
                    } catch (Exception e) {
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("warningAuth.fxml"));
                        stage.setTitle("Warning");
                        stage.setResizable(false);
                        stage.setScene(new Scene(root));
                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
                        stage.show();
                    }
                    if( MainApp.service.login(loginText, passwordText).getPermission().equals(Permission.EMPLOYEE)) {
                        nowEmployee = MainApp.service.login(loginText, passwordText);
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("worker.fxml"));
                        stage.setTitle("Worker's window");
                        stage.setResizable(false);
                        stage.setScene(new Scene(root));
                        oldestWorkerStage = stage;
                        stage.show();
                    } else if ( MainApp.service.login(loginText, passwordText).getPermission().equals(Permission.MANAGER)) {
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("manager.fxml"));
                        stage.setTitle("Manager's window");
                        stage.setResizable(false);
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                    accountName = employee.getName();
                }
            } catch (Exception e) {
                System.out.println("Error");
            }
        });

        cancelAuthAction.setOnAction(event -> {
            Controller controller = new Controller();
            controller.cancelButton(cancelAuthAction);
        });

    }

}

