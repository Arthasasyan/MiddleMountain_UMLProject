package com.middlemountain.client;

import java.util.Scanner;

public class ManagerAction {
    public void managerAction() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("Available options:\nCreate employee - 0\nCreate good - 1\nCreate order - 2\n" +
                "Update employee - 3\n" + "Update good - 4\nUpdate order - 5\nDelete employee - 6\nDelete good - 7\n" +
                "Create creation job - 8\nUpdate creation job - 9\nStop to work - 10\n");
        while (true) {
            System.out.print("Choose what you want - ");
            int func = in.nextInt();
            if (func == 0) {
                CreateEmployee createEmployee = new CreateEmployee();
                createEmployee.createEmployee();
            } else if (func == 1) {
                CreateGood createGood = new CreateGood();
                createGood.createGood();
            } else if (func == 2) {
                CreateOrder createOrder = new CreateOrder();
                createOrder.createOrder();
            } else if (func == 3) {
                UpdateEmployee updateEmployee = new UpdateEmployee();
                updateEmployee.updateEmployee();
            } else if (func == 4) {
                UpdateGood updateGood = new UpdateGood();
                updateGood.updateGood();
            } else if (func == 5) {
                UpdateOrder updateOrder = new UpdateOrder();
                updateOrder.updateOrder();
            } else if (func == 6) {
                DeleteEmployee deleteEmployee = new DeleteEmployee();
                deleteEmployee.deleteEmployee();
            } else if (func == 7) {
                DeleteGood deleteGood = new DeleteGood();
                deleteGood.deleteGood();
            } else if (func == 8) {
                CreateCreationJob createCreationJob = new CreateCreationJob();
                createCreationJob.createCreationJob();
            } else if (func == 9) {
                UpdateCreationJob updateCreationJob = new UpdateCreationJob();
                updateCreationJob.updateCreationJob();
            }
            else if ( func == 10 ) {
                break;
            }
        }

    }
}
