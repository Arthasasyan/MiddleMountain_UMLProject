package com.middlemountain.client;

import com.middlemountain.enums.MagicType;
import com.middlemountain.model.EnchantmentJob;
import com.middlemountain.model.Item;

import java.util.Scanner;

public class CreateEnchantmentJob {
    Scanner in = new Scanner(System.in);
    private EnchantmentJob enchantmentJob = new EnchantmentJob();
    private Item item = new Item();
    private MagicType magicType;
    private CreateGood createGood = new CreateGood();
    public EnchantmentJob CreateEnchantmentJob() {
        System.out.println("You have to add an item to enchant");
        CreateItem();
        System.out.print("Choose a magic type - F(fire), W(water), A(air), G(ground), M(mental), AR(arcane), H(household) - ");
        String letter = in.nextLine();
        magicType = createGood.typeOfMagic(letter);
        System.out.print("Enter a description: ");
        String description = in.nextLine();
        enchantmentJob.setMagicType(magicType);
        enchantmentJob.setDescription(description);
        enchantmentJob.setCompleted(false);
        return enchantmentJob;
    }

    public void CreateItem() {
        System.out.print("Enter item's description: ");
        String description = in.nextLine();
        item.setDescription(description);
        enchantmentJob.setItem(item);
    }
}
