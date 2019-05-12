package com.middlemountain.client;

import com.middlemountain.enums.OrderStatus;
import com.middlemountain.model.*;

import java.util.Scanner;

public class CreateOrder {
    private Order order = new Order();
    private Good good = new Good();
    private EnchantmentJob enchantmentJob;
    private OrderStatus orderStatus;
    private Employee employee;
    Scanner in = new Scanner(System.in);
    public void CreateOrder() throws Exception {
        System.out.println("Order creation process");
        System.out.print("Enter your username: ");
        String username = in.nextLine();
        System.out.print("Enter a client name: ");
        String clientName = in.nextLine();
        System.out.print("Client's address. ");
        Address address = EnterAddress();
        System.out.print("Do you want to enchant item?\nYes(0) or No(1) - ");
        int action = in.nextInt();
        while (action == 0) {
            CreateEnchantmentJob createEnchantmentJob = new CreateEnchantmentJob();
            enchantmentJob = createEnchantmentJob.CreateEnchantmentJob();
            enchantmentJob.setCompleted(true);
            order.addEnchantmentJob(enchantmentJob);
            System.out.print("Do you want to enchant some more? Yes(0) or No(1) - ");
            action = in.nextInt();
        }
        in.nextLine();
        System.out.print("You have to add a good");
        AddGood();
        System.out.print("Do you want to add something else? Yes(0) or No(1) - ");
        int addGood = in.nextInt();
        while ( addGood == 0 ) {
            AddGood();
            addGood = in.nextInt();
        }
        orderStatus = OrderStatus.NEW;
        employee = MainApp.service.getEmployee(username);
        order.setStatus(orderStatus);
        order.setClientName(clientName);
        order.setShippingAddress(address);
        order.setAssignedEmployee(employee);
        try {
            MainApp.service.createOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddGood() throws Exception {
        System.out.print("Enter a good's id or name: ");
        String add = in.nextLine();
        try {
            Integer.parseInt(add);
            int id = Integer.parseInt(add);
            order.addGood(MainApp.service.getGood(id));
        } catch (Exception e) {
            order.addGood(MainApp.service.getGood(add));
        }
    }

    public Address EnterAddress() {
        System.out.print("Enter a country: ");
        String countryShip = in.nextLine();
        System.out.print("Enter a city: ");
        String cityShip = in.nextLine();
        System.out.print("Enter an address: ");
        String addressShip = in.nextLine();
        Address address = new Address(countryShip, cityShip, addressShip);
        return address;
    }
}
