package com.middlemountain.enums;


public enum Permission {
  EMPLOYEE,
  MANAGER;

  public static Permission fromInteger(String number) {
    return Permission.values()[Integer.parseInt(number) - 1];
  }
  public static Integer toInteger(Permission permission){
    return (permission.ordinal() + 1);
  }
}
