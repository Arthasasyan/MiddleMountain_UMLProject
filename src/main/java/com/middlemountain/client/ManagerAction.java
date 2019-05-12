package com.middlemountain.client;

import com.middlemountain.enums.MagicType;
import com.middlemountain.model.Employee;
import com.middlemountain.service.EmployeeSystem;
import com.middlemountain.service.MagicService;

import java.util.Scanner;

public class ManagerAction {
    public void managerAction(Employee employee) throws Exception {
        System.out.println("Employees of our company:");
        Scanner in = new Scanner(System.in);
        System.out.print("Available options:\nCreate employee - 0\nCreate good - 1\nCreate order - 2\n" +
                "Update employee - 3\n" + "Update good - 4\nUpdate order - 5\nDelete employee - 6\nDelete good - 7\n" +
                "Create creation job - 8\nUpdate creation job - 9\nStop to work - 10\n");
        while (true) {
            System.out.print("Choose what you want - ");
            int func = in.nextInt();
            if (func == 0) {
                listOfEmployees();
                CreateEmployee createEmployee = new CreateEmployee();
                createEmployee.createEmployee();
            } else if (func == 1) {
                CreateGood createGood = new CreateGood();
                createGood.createGood();
            } else if (func == 2) {
                listOfOrders(employee);
                CreateOrder createOrder = new CreateOrder();
                createOrder.createOrder();
            } else if (func == 3) {
                listOfEmployees();
                UpdateEmployee updateEmployee = new UpdateEmployee();
                updateEmployee.updateEmployee();
            } else if (func == 4) {
                UpdateGood updateGood = new UpdateGood();
                updateGood.updateGood();
            } else if (func == 5) {
                listOfOrders(employee);
                UpdateOrder updateOrder = new UpdateOrder();
                updateOrder.updateOrder();
            } else if (func == 6) {
                listOfEmployees();
                DeleteEmployee deleteEmployee = new DeleteEmployee();
                deleteEmployee.deleteEmployee();
            } else if (func == 7) {
                DeleteGood deleteGood = new DeleteGood();
                deleteGood.deleteGood();
            } else if (func == 8) {
                listOfCreations(employee);
                CreateCreationJob createCreationJob = new CreateCreationJob();
                createCreationJob.createCreationJob();
            } else if (func == 9) {
                listOfCreations(employee);
                UpdateCreationJob updateCreationJob = new UpdateCreationJob();
                updateCreationJob.updateCreationJob();
            }
            else if ( func == 10 ) {
                break;
            }
        }
    }

    public void listOfCreations(Employee employee) throws Exception {
        if (MainApp.service.getCreationJobs(employee).size() != 0 ) {
            System.out.println("Existing creation jobs: ");
            for (int i = 0; i < MainApp.service.getCreationJobs(employee).size(); i++) {
                System.out.println(MainApp.service.getCreationJobs(employee).get(i).toString());
            }
        } else return;
    }

    public void listOfOrders(Employee employee) throws Exception {
        if( MainApp.service.getOrders(employee).size() != 0 ) {
            System.out.println("Existing orders: ");
            for ( int i = 0; i < MainApp.service.getOrders(employee).size(); i++ ) {
                System.out.println(MainApp.service.getOrders(employee).get(i).toString());
            }
        } else return;
    }

    public void listOfEmployees() throws Exception {
        if ( MainApp.service.getEmployees().size() != 0 ) {
            System.out.println("Employees of our company: ");
            for (int i = 0; i < MainApp.service.getEmployees().size(); i++) {
                System.out.println("\t" + MainApp.service.getEmployees().get(i).getName() + "(id "
                        + MainApp.service.getEmployees().get(i).getId() + ") - "
                        + MainApp.service.getEmployees().get(i).getPermission().toString());
            }
        } else return;
    }
}
