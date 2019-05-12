package com.middlemountain.client;

import com.middlemountain.service.MagicService;
import com.middlemountain.service.Service;

public class MainApp {
    public static Service service;
    public static void main(String[] args) throws Exception {
        service = new MagicService();
        Login login = new Login();
        login.login();
    }
}
