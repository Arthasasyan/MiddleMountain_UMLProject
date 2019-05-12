package com.middlemountain.client;

import com.middlemountain.enums.Permission;
import com.middlemountain.model.Employee;

import java.util.Scanner;

public class Login {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private Employee employee;
    public void Login () throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println(ANSI_WHITE + "Please, enter your login: ");
        String login = in.nextLine();
        System.out.println("Enter your password: ");
        String password = in.nextLine();
        if(!login.equals("") && !password.equals("")) {
            try {
                employee = MainApp.service.login(login, password);
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Warning!\nLogin or password incorrect! Try again." );
                new Login();
            }
            if (MainApp.service.login(login, password).getPermission().equals(Permission.MANAGER)) {
                System.out.println("Welcome, manager " + employee.getName() + "!");
                new ManagerAction();
            }
            else if (MainApp.service.login(login, password).getPermission().equals(Permission.EMPLOYEE)) {
                System.out.println("Welcome, employee " + employee.getName() + "!");
            }
        } else {
            System.out.println(ANSI_RED + "Warning!\n Login or password is empty! Try again.");
            new Login();
        }
    }


}
