package com.middlemountain.client;

import java.util.Scanner;

public class EmployeeAction {
    public void employeeAction() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("Available options:\nCreate order - 0\nUpdate order - 1\nCreate creation job - 2\n" +
                "Update creation job - 3\nStop to work - 4\n");
        while (true) {
            System.out.print("Choose what you want - ");
            int func = in.nextInt();
            if (func == 0) {
                CreateOrder createOrder = new CreateOrder();
                createOrder.createOrder();
            } else if (func == 1) {
                UpdateOrder updateOrder = new UpdateOrder();
                updateOrder.updateOrder();
            } else if (func == 2) {
                CreateCreationJob createCreationJob = new CreateCreationJob();
                createCreationJob.createCreationJob();
            } else if (func == 3) {
                UpdateCreationJob updateCreationJob = new UpdateCreationJob();
                updateCreationJob.updateCreationJob();
            } else if (func == 4) {
                break;
            }
        }
    }
}
