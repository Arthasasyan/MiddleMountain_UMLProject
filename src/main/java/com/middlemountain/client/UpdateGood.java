package com.middlemountain.client;

import com.middlemountain.enums.MagicType;
import com.middlemountain.model.Good;

import java.util.Scanner;

public class UpdateGood {
    private Good good;
    private CreateGood createGood = new CreateGood();
    public void updateGood() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the good's id or username: ");
        String update = in.nextLine();
        try {
            Integer.parseInt(update);
            int id = Integer.parseInt(update);
            good = MainApp.service.getGood(id);
        } catch (Exception e) {
            good = MainApp.service.getGood(update);
        }
        System.out.print("Change the desired items:\nGood's name(" + good.getName() + ") - ");
        String name = in.nextLine();
        System.out.print("Good's description(" + good.getDescription() + ") - ");
        String description = in.nextLine();
        System.out.print("Choose good's magic type(" + good.getMagicType().toString() + ") - ");
        String letter = in.nextLine();
        MagicType magicType = createGood.typeOfMagic(letter);
        System.out.print("Good's price(" + good.getPrice() + ") - ");
        Float price = in.nextFloat();
        System.out.print("Good's amount(" + good.getAmount() + ") - ");
        int amount = in.nextInt();
        System.out.print("Is good deleted? - now " + good.getDeleted() + " - ");
        int deleted = in.nextInt();
        good.setName(name);
        good.setDescription(description);
        good.setMagicType(magicType);
        good.setPrice(price);
        good.setAmount(amount);
        good.setDeleted(deleted);
        try {
            MainApp.service.updateGood(good);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
