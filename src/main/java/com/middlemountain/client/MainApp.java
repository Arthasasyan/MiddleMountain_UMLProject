package com.middlemountain.client;

import com.middlemountain.service.MagicService;
import com.middlemountain.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp {
    public static Service service;
    public static void main(String[] args) throws Exception {
        service = new MagicService();
        Login login = new Login();
        login.Login();
    }
}
