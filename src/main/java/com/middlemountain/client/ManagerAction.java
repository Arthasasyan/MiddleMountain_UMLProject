package com.middlemountain.client;



import com.middlemountain.model.CreationJob;

import java.util.Scanner;

public class ManagerAction {
    ManagerAction() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("Available options:\nCreate employee - 0\nCreate good - 1\nCreate order - 2\n" +
                "Update employee - 3\n" + "Update good - 4\nUpdate order - 5\nDelete employee - 6\nDelete good - 7\n" +
                "Create creation job - 8\nUpdate creation job - 9\n");
        System.out.print("Choose what you want - ");
        int func = in.nextInt();
        if ( func == 0 ) {
            CreateEmployee createEmployee = new CreateEmployee();
            createEmployee.CreateEmployee();
        } else if ( func == 1 ) {
            CreateGood createGood = new CreateGood();
            createGood.CreateGood();
        } else if ( func == 2 ) {
            CreateOrder createOrder = new CreateOrder();
            createOrder.CreateOrder();
        } else if ( func == 3 ) {
            UpdateEmployee updateEmployee = new UpdateEmployee();
            updateEmployee.UpdateEmployee();
        } else if ( func == 4 ) {
            UpdateGood updateGood = new UpdateGood();
            updateGood.UpdateGood();
        } else if ( func == 6 ) {
            DeleteEmployee deleteEmployee = new DeleteEmployee();
            deleteEmployee.DeleteEmployee();
        } else if ( func == 7 ) {
            DeleteGood deleteGood = new DeleteGood();
            deleteGood.DeleteGood();
        } else if ( func == 8 ) {
            CreateCreationJob createCreationJob = new CreateCreationJob();
            createCreationJob.CreateCreationJob();
        }

    }
}
