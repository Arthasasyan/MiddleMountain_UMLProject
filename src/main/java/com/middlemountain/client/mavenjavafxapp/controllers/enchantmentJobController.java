package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.enums.MagicType;
import com.middlemountain.enums.OrderStatus;
import com.middlemountain.model.EnchantmentJob;
import com.middlemountain.model.Item;
import com.middlemountain.model.Order;
import com.middlemountain.service.MagicService;
import com.middlemountain.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class enchantmentJobController {
    private Service service;
    private Order order = new Order();

    @FXML
    private TextField itemDescription;

    @FXML
    private ChoiceBox<MagicType> magicTypeEnchant;

    @FXML
    private TextField descriptionEnchant;

    @FXML
    private Label statusEnchant;

    @FXML
    private Button createEnchant;

    @FXML
    private Button OKEnchant;

    @FXML
    private Button cancelEnchant;

    @FXML
    void initialize() throws Exception {
        Item item = new Item();
        EnchantmentJob enchantmentJob = new EnchantmentJob();
        service = new MagicService();

        ObservableList<MagicType> availableChoices = FXCollections.observableArrayList(MagicType.FIRE,
                MagicType.AIR, MagicType.ARCANE, MagicType.GROUND, MagicType.HOUSEHOLD,
                MagicType.MENTAL, MagicType.WATER);
        magicTypeEnchant.setItems(availableChoices);
        magicTypeEnchant.setOnAction(event -> magicTypeEnchant.getValue());

        OKEnchant.setOnAction(event -> {
            Controller controller = new Controller();
            controller.cancelButton(OKEnchant);
        });

        cancelEnchant.setOnAction(event -> {
            Controller controller = new Controller();
            controller.cancelButton(cancelEnchant);
        });

        createEnchant.setOnAction(event -> {
            enchantmentJob.setCompleted(true);
            if(enchantmentJob.getCompleted()) {
                statusEnchant.setText("Completed");
            }
            enchantmentJob.setDescription(descriptionEnchant.getText());
            enchantmentJob.setMagicType(magicTypeEnchant.getValue());
            enchantmentJob.setItem(item.setDescription(itemDescription.getText()));
            order.addEnchantmentJob(enchantmentJob);
        });

    }

    public List<EnchantmentJob> getOrderEnchant() {
        return order.getEnchantmentJobs();
    }

}
