package com.middlemountain.model;

import com.middlemountain.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Order {
  private Integer id = 0;
  private List<EnchantmentJob> enchantmentJobs;
  private List<Good> goods;
  private String clientName;
  private OrderStatus status;
  private Employee assignedEmployee;
  private Address shippingAddress;

  public void addGood(Good good) {
    if(goods == null) {
      goods = new ArrayList<>();
    }
    goods.add(good);
  }

  public void addEnchantmentJob(EnchantmentJob enchantmentJob) {
    if(enchantmentJobs == null) {
      enchantmentJobs = new ArrayList<>();
    }
    enchantmentJobs.add(enchantmentJob);
  }
}
