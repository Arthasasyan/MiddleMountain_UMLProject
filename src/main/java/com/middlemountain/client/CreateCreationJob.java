package com.middlemountain.client;

import com.middlemountain.model.CreationJob;
import com.middlemountain.model.Employee;
import com.middlemountain.model.Good;

import java.util.Scanner;

public class CreateCreationJob {
    private Good good;
    private Employee employee;
    private CreationJob creationJob = new CreationJob();
    public void CreateCreationJob() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("Do you want to create good? Yes(0) or No(1) - ");
        int action = in.nextInt();
        if ( action == 0 ) {
            System.out.print("Enter a good's id or name: ");
            in.nextLine();
            String create = in.nextLine();
            try {
                Integer.parseInt(create);
                int id = Integer.parseInt(create);
                good = MainApp.service.getGood(id);
            } catch (Exception e) {
                good = MainApp.service.getGood(create);
            }
            System.out.print("Enter your username: ");
            String username = in.nextLine();
            employee = MainApp.service.getEmployee(username);
            System.out.print("How many do you want to create: ");
            int amount = in.nextInt();
            creationJob.setGood(good);
            creationJob.setEmployee(employee);
            creationJob.setAmountRemaining(amount);
            try {
                MainApp.service.createCreationJob(creationJob);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else return;
    }
}
