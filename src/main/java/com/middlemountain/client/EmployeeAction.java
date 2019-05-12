package com.middlemountain.client;

import com.middlemountain.model.Employee;

import java.util.Scanner;

public class EmployeeAction {
    private ManagerAction managerAction = new ManagerAction();
    public void employeeAction(Employee employee) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("Available options:\nCreate order - 0\nUpdate order - 1\nCreate creation job - 2\n" +
                "Update creation job - 3\nStop to work - 4\n");
        while (true) {
            System.out.print("Choose what you want - ");
            int func = in.nextInt();
            if (func == 0) {
                managerAction.listOfOrders(employee);
                CreateOrder createOrder = new CreateOrder();
                createOrder.createOrder();
            } else if (func == 1) {
                managerAction.listOfOrders(employee);
                UpdateOrder updateOrder = new UpdateOrder();
                updateOrder.updateOrder();
            } else if (func == 2) {
                managerAction.listOfCreations(employee);
                CreateCreationJob createCreationJob = new CreateCreationJob();
                createCreationJob.createCreationJob();
            } else if (func == 3) {
                managerAction.listOfCreations(employee);
                UpdateCreationJob updateCreationJob = new UpdateCreationJob();
                updateCreationJob.updateCreationJob();
            } else if (func == 4) {
                break;
            }
        }
    }
}
