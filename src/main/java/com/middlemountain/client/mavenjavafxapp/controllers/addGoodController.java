package com.middlemountain.client.mavenjavafxapp.controllers;

import com.middlemountain.enums.MagicType;
import com.middlemountain.model.Good;
import com.middlemountain.service.MagicService;
import com.middlemountain.service.Service;
import com.middlemountain.service.TestService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class addGoodController {
    private Service service;

    @FXML
    private TextField nameAddGood;

    @FXML
    private TextField idAddGood;

    @FXML
    private TextField priceAddGood;

    @FXML
    private Label infoGood;

    @FXML
    private ChoiceBox<MagicType> magicTypeGood;

    @FXML
    private Button createAddGood;

    @FXML
    private Button cancelAddGood;

    @FXML
    private TextArea descriptionAddGood;

    @FXML
    void initialize() throws Exception {
        Good good = new Good();
        service = new MagicService();

        ObservableList<MagicType> availableChoices = FXCollections.observableArrayList(MagicType.FIRE,
                MagicType.AIR, MagicType.ARCANE, MagicType.GROUND, MagicType.HOUSEHOLD,
                MagicType.MENTAL, MagicType.WATER);
        magicTypeGood.setItems(availableChoices);
        magicTypeGood.setOnAction(event -> magicTypeGood.getValue());

        if( managerController.update == true ) {
            idAddGood.setText(good.getId().toString());
            nameAddGood.setText(good.getName());
            priceAddGood.setText(good.getPrice().toString());
            magicTypeGood.setValue(good.getMagicType());
            descriptionAddGood.setText(good.getDescription());
            managerController.update = false;
        }

        cancelAddGood.setOnAction(event -> {
            Controller controller = new Controller();
            controller.cancelButton(cancelAddGood);
        });

        createAddGood.setOnAction(event -> {
            try {
                String name = nameAddGood.getText();
                float price = Float.parseFloat(priceAddGood.getText().trim());
                String description = descriptionAddGood.getText();
                MagicType magicType = magicTypeGood.getValue();
                good.setDescription(description);
                good.setName(name);
                good.setPrice(price);
                good.setMagicType(magicType);
                service.createGood(good);
            } catch (Exception e) {
                e.printStackTrace();
            }
            infoGood.setText("New good's id - " + good.getId());
        });
    }

}
