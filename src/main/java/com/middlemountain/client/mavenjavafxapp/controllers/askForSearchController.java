package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.model.Order;
import com.middlemountain.service.MagicService;
import com.middlemountain.service.Service;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class askForSearchController {
    private Service service;
    public static Order currentOrder;
    public static int exist = 0;

    @FXML
    private Button enterSearchAction;

    @FXML
    private Button cancelSearchAction;

    @FXML
    private TextField enterNameSearch;

    @FXML
    private TextField enterIdSearch;

    @FXML
    void initialize() throws Exception {
        service = new MagicService();
        String name = enterNameSearch.getText();
        String id = enterIdSearch.getText();

        cancelSearchAction.setOnAction(event -> {
            Controller controller = new Controller();
            controller.cancelButton(cancelSearchAction);
        });

        enterSearchAction.setOnAction(event -> {
            Stage oldStage = (Stage)enterSearchAction.getScene().getWindow();
            oldStage.close();
            if(!name.equals("-")) {
                try {
                    currentOrder = service.getOrder(name);
                    exist = 1;
                    openForm(event);
                } catch (Exception e) {
                    warningOrderForm(event);
                    exist = 0;
                }
            } else {
                try {
                    currentOrder = service.getOrder(Integer.parseInt(id));
                    exist = 1;
                    loginController.oldestWorkerStage.close();
                    openForm(event);
                } catch (Exception e) {
                    warningOrderForm(event);
                    exist = 0;
                }
            }
        });
    }


    public void openForm(Event event) {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("formForOrder.fxml"));
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

    public void warningOrderForm(Event event) {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("warningNotExist.fxml"));
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

}
