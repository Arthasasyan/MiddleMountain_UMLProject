package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.model.Good;
import com.middlemountain.model.Order;
import com.middlemountain.service.MagicService;
import com.middlemountain.service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class goodToOrderController {
    private Service service;
    private Order order = new Order();

    @FXML
    private Button addGoodToOrder;

    @FXML
    private Button cancelAddGood;

    @FXML
    private TextField enterNameGood;

    @FXML
    private TextField enterIdGood;

    @FXML
    void initialize() throws Exception {
        service = new MagicService();
        String name = enterNameGood.getText();
        String id = enterIdGood.getText();

        cancelAddGood.setOnAction(event -> {
            Controller controller = new Controller();
            controller.cancelButton(cancelAddGood);
        });

        addGoodToOrder.setOnAction(event -> {
            if(!name.equals("-")) {
                try {
                    order.addGood(service.getGood(name));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (!id.equals("-")) {
                try {
                    order.addGood(service.getGood(Integer.parseInt(id)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public List<Good> getOrderGood() {
        return order.getGoods();
    }
}
