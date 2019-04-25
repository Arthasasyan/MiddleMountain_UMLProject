package com.middlemountain.client.mavenjavafxapp.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class addWorkerController {

    @FXML
    private TextField idAddWorker;

    @FXML
    private TextField nameAddWorker;

    @FXML
    private TextField salaryAddWorker;

    @FXML
    private ChoiceBox<String> choiseBoxWorker;
    ObservableList<String> availableChoices = FXCollections.observableArrayList("Employee", "Manager");

    @FXML
    private Button createAddWorker;

    @FXML
    private Button canselAddForm;

}

