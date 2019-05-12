package com.middlemountain.client;

import com.middlemountain.enums.Permission;
import com.middlemountain.model.Employee;

import java.util.Scanner;

public class Login {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private Employee employee;
    public void login() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println(ANSI_WHITE + "Please, enter your login: ");
        String logIn = in.nextLine();
        System.out.println("Enter your password: ");
        String password = in.nextLine();
        if(!logIn.equals("") && !password.equals("")) {
            try {
                employee = MainApp.service.login(logIn, password);
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Warning!\nlogin or password incorrect! Try again." );
                Login log = new Login();
                log.login();
            }
            if (MainApp.service.login(logIn, password).getPermission().equals(Permission.MANAGER)) {
                System.out.println("Welcome, manager " + employee.getName() + "!");
                ManagerAction managerAction = new ManagerAction();
                managerAction.managerAction(employee);
            }
            else if (MainApp.service.login(logIn, password).getPermission().equals(Permission.EMPLOYEE)) {
                System.out.println("Welcome, employee " + employee.getName() + "!");
                EmployeeAction employeeAction = new EmployeeAction();
                employeeAction.employeeAction(employee);
            }
        } else {
            System.out.println(ANSI_RED + "Warning!\n login or password is empty! Try again.");
            Login log = new Login();
            log.login();
        }
    }


}
