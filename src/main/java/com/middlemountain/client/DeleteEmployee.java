package com.middlemountain.client;

import java.util.Scanner;

public class DeleteEmployee {
    public void deleteEmployee() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the employee's id or username: ");
        String delete = in.nextLine();
        String name;
        try {
            Integer.parseInt(delete);
            int id = Integer.parseInt(delete);
            MainApp.service.deleteEmployee(MainApp.service.getEmployee(id));
            name = MainApp.service.getEmployee(id).getName();
            System.out.println("The " + name + " was fired.");
        } catch (Exception e) {
            MainApp.service.deleteEmployee(MainApp.service.getEmployee(delete));
            name = MainApp.service.getEmployee(delete).getName();
            System.out.println("The " + name + " was fired.");
        }
    }
}
