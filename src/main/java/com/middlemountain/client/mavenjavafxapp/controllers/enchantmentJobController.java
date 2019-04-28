package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.enums.MagicType;
import com.middlemountain.service.MagicService;
import com.middlemountain.service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class enchantmentJobController {
    private Service service;

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
        service = new MagicService();
        OKEnchant.setOnAction(event -> {
            Controller controller = new Controller();
            controller.cancelButton(OKEnchant);
        });

        cancelEnchant.setOnAction(event -> {
            Controller controller = new Controller();
            controller.cancelButton(cancelEnchant);
        });
    }

}
