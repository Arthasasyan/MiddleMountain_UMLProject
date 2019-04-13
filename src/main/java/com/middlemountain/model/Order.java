package com.middlemountain.model;

import com.middlemountain.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  private Integer id;
  private List<EnchantmentJob> enchantmentJobs;
  private List<Good> goods;
  private String clientName;
  private OrderStatus status;
  private Employee assignedEmployee;
  private Address shippingAddress;
}
