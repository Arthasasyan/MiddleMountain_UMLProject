package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.client.mavenjavafxapp.MainApp;
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

public class AskForSearchController {
    public static Order currentOrder;

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

        cancelSearchAction.setOnAction(event -> {
            Controller controller = new Controller();
            controller.cancelButton(cancelSearchAction);
        });

        enterSearchAction.setOnAction(event -> {
            String clientName = enterNameSearch.getText().trim();
            String orderId = enterIdSearch.getText().trim();
            Stage oldStage = (Stage)enterSearchAction.getScene().getWindow();
            oldStage.close();
            if(!clientName.equals("-")) {
                try {
                    currentOrder = MainApp.service.getOrder(clientName);
                    openForm(event);
                } catch (Exception e) {
                    warningOrderForm(event);
                }
            } else {
                try {
                    currentOrder = MainApp.service.getOrder(Integer.parseInt(orderId));
                    openForm(event);
                } catch (Exception e) {
                    warningOrderForm(event);
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
