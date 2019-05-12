package com.middlemountain.client;

import com.middlemountain.enums.OrderStatus;
import com.middlemountain.model.Order;

import java.util.Scanner;

public class UpdateOrder {
    private Order order;
    private OrderStatus orderStatus;
    public void updateOrder() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a client's name or order's id: ");
        String update = in.nextLine();
        try {
            Integer.parseInt(update);
            int id = Integer.parseInt(update);
            order = MainApp.service.getOrder(id);
        } catch (Exception e) {
            order = MainApp.service.getOrder(update);
        }
        System.out.println("The client's name - " + order.getClientName());
        System.out.println("Client's address - " + order.getShippingAddress().getCountry() + ", "
                + order.getShippingAddress().getCity() + ", " + order.getShippingAddress().getAddress());
        System.out.println("The employee's name - " + order.getAssignedEmployee().getName());
        System.out.print("List of enchantment - ");
        for ( int i = 0; i < order.getEnchantmentJobs().size(); i++ ) {
            System.out.print(order.getEnchantmentJobs().get(i).getDescription());
            if ( i != order.getEnchantmentJobs().size() - 1 ) {
                System.out.print(", ");
            } else System.out.print("\n");
        }
        System.out.print("List of goods - ");
        for ( int i = 0; i < order.getGoods().size(); i++ ) {
            System.out.print(order.getGoods().get(i).getName());
            if ( i != order.getGoods().size() - 1 ) {
                System.out.print(", ");
            } else System.out.print("\n");
        }
        System.out.print("Change order status from " + order.getStatus().toString() + "to(N - New, I - In Progress," +
                "S - Shipping, R - Ready, C - Closed, D - Denied) - ");
        String status = in.nextLine();
        orderStatus = getStatus(status);
        order.setStatus(orderStatus);
        try {
            MainApp.service.updateOrder(order);
            System.out.println(order.getClientName() + "'s order was updated.");
        } catch (Exception e) {
            System.out.println("Order with " + order.getId() +" id failed to update.");
        }
    }

    public OrderStatus getStatus(String status)  {
        if ( status.equals("N")) {
            orderStatus = OrderStatus.NEW;
        } else if ( status.equals("I")) {
            orderStatus = OrderStatus.IN_PROGRESS;
        } else if ( status.equals("S")) {
            orderStatus = OrderStatus.SHIPPING;
        } else if ( status.equals("R")) {
            orderStatus = OrderStatus.READY;
        } else if ( status.equals("C")) {
            orderStatus = OrderStatus.CLOSED;
        } else if ( status.equals("D")) {
            orderStatus = OrderStatus.DENIED;
        }
        return orderStatus;
    }
}
