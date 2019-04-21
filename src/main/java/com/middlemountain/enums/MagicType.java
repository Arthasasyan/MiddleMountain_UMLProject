package com.middlemountain.enums;

public enum MagicType {
  FIRE,
  WATER,
  AIR,
  GROUND,
  MENTAL,
  ARCANE,
  HOUSEHOLD;

  public static MagicType fromInteger(String number) {
    return MagicType.values()[Integer.parseInt(number) - 1];
  }
  public static Integer toInteger(MagicType magicType){
    return (magicType.ordinal() + 1);
  }
}
