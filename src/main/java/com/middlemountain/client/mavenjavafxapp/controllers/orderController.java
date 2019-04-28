package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.enums.OrderStatus;
import com.middlemountain.model.EnchantmentJob;
import com.middlemountain.model.Good;
import com.middlemountain.model.Order;
import com.middlemountain.service.MagicService;
import com.middlemountain.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class orderController {
    private Service service;

    @FXML
    private TextField clientNameOrder;

    @FXML
    private ChoiceBox<OrderStatus> statusOrder;

    @FXML
    private TextField assignedEmployeeOrder;

    @FXML
    private TextField shippingAddressOrder;

    @FXML
    private Button createOrder;

    @FXML
    private Button OKOrder;

    @FXML
    private Button cancelOrder;

    @FXML
    private ListView<EnchantmentJob> enchantJobOrder;

    @FXML
    private ListView<Good> goodsOrder;

    @FXML
    void initialize() throws Exception {
        Order order = new Order();
        service = new MagicService();
        Stage enchantStage = enchantWindow();

        cancelOrder.setOnAction(event -> {
            Stage oldStage = (Stage)cancelOrder.getScene().getWindow();
            oldStage.close();
            enchantStage.close();
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
            Controller controller = new Controller();
            controller.cancelButton(OKOrder);
        });

        createOrder.setOnAction(event -> {
            String nameClient = clientNameOrder.getText();
            assignedEmployeeOrder.setText(loginController.nowEmployee.getName());
            shippingAddressOrder.setText(order.getShippingAddress().getAddress());
        });

        /*ObservableList<EnchantmentJob> jobs = FXCollections.observableArrayList(order.getEnchantmentJobs());
        enchantJobOrder.setItems(jobs);
        enchantJobOrder.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ObservableList<Good> goods = FXCollections.observableArrayList(order.getGoods());
        goodsOrder.setItems(goods);
        goodsOrder.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ObservableList<OrderStatus> orderStatus = FXCollections.observableArrayList(OrderStatus.CLOSED, OrderStatus.DENIED,
                OrderStatus.IN_PROGRESS, OrderStatus.NEW, OrderStatus.READY, OrderStatus.SHIPPING);
        statusOrder.setItems(orderStatus);
        statusOrder.setOnAction(event -> statusOrder.getValue());*/
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

}
