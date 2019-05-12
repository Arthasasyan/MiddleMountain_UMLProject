package com.middlemountain.client;

import java.util.Scanner;

public class DeleteGood {
    public void deleteGood() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the good's id or name: ");
        String delete = in.nextLine();
        try {
            Integer.parseInt(delete);
            int id = Integer.parseInt(delete);
            MainApp.service.deleteGood(MainApp.service.getGood(id));
            String name = MainApp.service.getGood(id).getName();
            System.out.println("The " + name + " was deleted.");
        } catch (Exception e) {
            MainApp.service.deleteGood(MainApp.service.getGood(delete));
            String name = MainApp.service.getGood(delete).getName();
            System.out.println("The " + name + " was deleted.");
        }
    }
}
