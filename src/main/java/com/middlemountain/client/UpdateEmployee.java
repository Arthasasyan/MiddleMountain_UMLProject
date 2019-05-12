package com.middlemountain.client;

import com.middlemountain.enums.Permission;
import com.middlemountain.model.Employee;

import java.util.Scanner;

public class UpdateEmployee {
    private Employee employee;
    public void UpdateEmployee() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the employee's id or username: ");
        String update = in.nextLine();
        try {
            Integer.parseInt(update);
            int id = Integer.parseInt(update);
            employee = MainApp.service.getEmployee(id);
        } catch (Exception e) {
            employee = MainApp.service.getEmployee(update);
        }
        System.out.print("Change the desired items:\nEmployee's name(" + employee.getName() + ") - ");
        String name = in.nextLine();
        System.out.print("Employee's permission(" + employee.getPermission().toString() + ") - ");
        String letter = in.nextLine();
        Permission permission;
        if ( letter.equals("E") ) {
            permission = Permission.EMPLOYEE;
        } else permission = Permission.MANAGER;
        System.out.print("Employee's salary(" + employee.getSalary() + ") - ");
        Float salary = in.nextFloat();
        System.out.print("Is Employee on vacation? - now " + employee.getOnVacation() + " - ");
        int onVacation = in.nextInt();
        System.out.print("Is Employee fired& - now " + employee.getFired() + " - ");
        int fired = in.nextInt();
        employee.setName(name);
        employee.setPermission(permission);
        employee.setSalary(salary);
        employee.setOnVacation(onVacation);
        employee.setFired(fired);
        try {
            MainApp.service.updateEmployee(employee);
        } catch (Exception e ){
            e.printStackTrace();
        }
    }
}
