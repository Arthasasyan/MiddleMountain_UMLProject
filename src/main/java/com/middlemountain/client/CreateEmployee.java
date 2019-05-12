package com.middlemountain.client;

import com.middlemountain.enums.Permission;
import com.middlemountain.model.Employee;

import java.util.Scanner;

public class CreateEmployee {
    private Employee employee = new Employee();

    public void createEmployee() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter an employee's name: ");
        String name = in.nextLine();
        System.out.print("Choose username for worker: ");
        String username = in.nextLine();
        System.out.print("Choose a permission - E(employee) or M(manager): ");
        String letter = in.nextLine();
        Permission permission;
        if ( letter.equals("E") ) {
            permission = Permission.EMPLOYEE;
        } else permission = Permission.MANAGER;
        System.out.print("Enter salary: ");
        Float salary = in.nextFloat();
        System.out.print("Is employee on vacation? - ");
        int onVacation = in.nextInt();
        System.out.print("Is employee fired? - ");
        int fired = in.nextInt();
        employee.setName(name);
        employee.setSalary(salary);
        employee.setPermission(permission);
        employee.setOnVacation(onVacation);
        employee.setFired(fired);
        try {
            MainApp.service.createEmployee(employee, username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("A new employee " + name + " has got " + employee.getId() + " id");
    }
}
