package com.middlemountain.client;

import com.middlemountain.enums.MagicType;
import com.middlemountain.model.Good;

import java.util.Scanner;

public class CreateGood {
    private Good good = new Good();
    private MagicType magicType;

    public void createGood() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a good's name: ");
        String name = in.nextLine();
        System.out.print("Write a good's description: ");
        String description = in.nextLine();
        System.out.print("Choose a magic type - F(fire), W(water), A(air), G(ground), M(mental), AR(arcane), H(household) - ");
        String letter = in.nextLine();
        magicType = typeOfMagic(letter);
        System.out.print("Enter a good's price: ");
        Float price = in.nextFloat();
        System.out.print("Enter an amount of good: ");
        int amount = in.nextInt();
        System.out.print("Is good deleted? - ");
        int deleted = in.nextInt();
        good.setName(name);
        good.setDescription(description);
        good.setPrice(price);
        good.setMagicType(magicType);
        good.setAmount(amount);
        good.setDeleted(deleted);
        try {
            MainApp.service.createGood(good);
            System.out.println("A new good " + name + " has got " + good.getId() + " id");
        } catch (Exception e) {
            System.out.println("A new good " + name + " failed to create.");
        }
    }

    public MagicType typeOfMagic(String letter) {
        if ( letter.equals("F")) {
            magicType = MagicType.FIRE;
        } else if ( letter.equals("W")) {
            magicType = MagicType.WATER;
        } else if ( letter.equals("A")) {
            magicType = MagicType.AIR;
        } else if ( letter.equals("G")) {
            magicType = MagicType.GROUND;
        } else if (letter.equals("M")) {
            magicType = MagicType.MENTAL;
        } else if ( letter.equals("AR") ) {
            magicType = MagicType.ARCANE;
        } else if ( letter.equals("H")) {
            magicType = MagicType.HOUSEHOLD;
        }
        return magicType;
    }
}
