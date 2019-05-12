package com.middlemountain.client;

import com.middlemountain.model.CreationJob;

import java.util.Scanner;

public class UpdateCreationJob {
    private CreationJob creationJob;
    public void updateCreationJob() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter creation job id - ");
        int id = in.nextInt();
        creationJob = MainApp.service.getCreationJob(id);
        System.out.println("Employee's name - " + creationJob.getEmployee().getName());
        System.out.println("Good to create - " + creationJob.getGood().getName());
        System.out.print("Change amount from " + creationJob.getAmountRemaining() + " to ");
        int amount = in.nextInt();
        creationJob.setAmountRemaining(amount);
        try {
            MainApp.service.updateCreationJob(creationJob);
        } catch (Exception e) {
            System.out.println("Creation job with " + creationJob.getId() + " id failed to update.");
        }
    }
}
