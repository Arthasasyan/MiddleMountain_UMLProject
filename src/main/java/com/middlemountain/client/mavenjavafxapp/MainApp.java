package com.middlemountain.client.mavenjavafxapp;

import com.middlemountain.service.MagicService;
import com.middlemountain.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static Stage oldestStage;
    public static Service service;

    @Override
    public void start(Stage primaryStage) throws Exception{
        service = new MagicService();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        oldestStage = primaryStage;
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
