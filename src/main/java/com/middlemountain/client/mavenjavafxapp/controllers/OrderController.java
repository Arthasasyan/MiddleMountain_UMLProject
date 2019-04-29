package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.client.mavenjavafxapp.MainApp;
import com.middlemountain.enums.OrderStatus;
import com.middlemountain.model.*;
import com.middlemountain.service.MagicService;
import com.middlemountain.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class OrderController {

    @FXML
    private TextField clientNameOrder;

    @FXML
    private ChoiceBox<OrderStatus> statusOrder;

    @FXML
    private TextField assignedEmployeeOrder;

    @FXML
    private Button createOrder;

    @FXML
    private Button OKOrder;

    @FXML
    private Button cancelOrder;

    @FXML
    private TextField addressShip;

    @FXML
    private TextField cityShip;

    @FXML
    private TextField countryShip;

    @FXML
    private ListView<Good> goodsOrder;


    @FXML
    void initialize() throws Exception {
        EnchantmentJobController jobController = new EnchantmentJobController();
        GoodToOrderController toOrderController = new GoodToOrderController();
        LoginController.oldestWorkerStage.close();
        Order order = new Order();
        Stage enchantStage = enchantWindow();
        Stage addStage = addWindow();

        /*ObservableList<Good> goods = FXCollections.observableArrayList(order.getGoods());
        goodsOrder.setItems(goods);
        goodsOrder.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);*/

        ObservableList<OrderStatus> orderStatus = FXCollections.observableArrayList(OrderStatus.CLOSED, OrderStatus.DENIED,
                OrderStatus.IN_PROGRESS, OrderStatus.NEW, OrderStatus.READY, OrderStatus.SHIPPING);
        statusOrder.setItems(orderStatus);
        statusOrder.setOnAction(event -> statusOrder.getValue());

        if( WorkerController.exist == 1 ) {
            clientNameOrder.setText(AskForSearchController.currentOrder.getClientName());
            statusOrder.setValue(AskForSearchController.currentOrder.getStatus());
            assignedEmployeeOrder.setText(LoginController.loginUsername);
            addressShip.setText(AskForSearchController.currentOrder.getShippingAddress().getAddress());
            cityShip.setText(AskForSearchController.currentOrder.getShippingAddress().getCity());
            countryShip.setText(AskForSearchController.currentOrder.getShippingAddress().getCountry());
        }

        createOrder.setOnAction(event -> {
            try {

                Address address = new Address(countryShip.getText(),cityShip.getText(), addressShip.getText());
                order.setAssignedEmployee(MainApp.service.getEmployee(LoginController.loginUsername));
                order.setClientName(clientNameOrder.getText());
                order.setShippingAddress(address);
                order.setStatus(statusOrder.getValue());
                order.setEnchantmentJobs(jobController.getOrderEnchant());
                order.setGoods(toOrderController.getOrderGood());
                if (WorkerController.exist == 1) {
                    order.setId(MainApp.service.getOrder(clientNameOrder.getText()).getId());
                    MainApp.service.updateOrder(order);
                } else MainApp.service.createOrder(order);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        cancelOrder.setOnAction(event -> {
            Stage oldStage = (Stage)cancelOrder.getScene().getWindow();
            oldStage.close();
            enchantStage.close();
            addStage.close();
            Stage stage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("worker.fxml"));
                stage.setTitle("Magic Shop");
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        OKOrder.setOnAction(event -> {
            Stage oldStage = (Stage)OKOrder.getScene().getWindow();
            oldStage.close();
            enchantStage.close();
            addStage.close();
            Stage stage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("worker.fxml"));
                stage.setTitle("Magic Shop");
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }

    public Stage enchantWindow() {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("enchantmentJob.fxml"));
            stage.setTitle("Enchant");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stage;
    }

    public Stage addWindow() {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("goodToOrder.fxml"));
            stage.setTitle("Goods");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stage;
    }

}
