package com.middlemountain.enums;

public enum OrderStatus {
  NEW,
  IN_PROGRESS,
  SHIPPING,
  READY,
  CLOSED,
  DENIED;

  public static OrderStatus fromInteger(String number) {
    return OrderStatus.values()[Integer.parseInt(number) - 1];
  }
  public static Integer toInteger(OrderStatus orderStatus){
    return (orderStatus.ordinal() + 1);
  }
}
