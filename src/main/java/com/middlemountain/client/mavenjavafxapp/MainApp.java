package com.middlemountain.client.mavenjavafxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static Stage oldestStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
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
