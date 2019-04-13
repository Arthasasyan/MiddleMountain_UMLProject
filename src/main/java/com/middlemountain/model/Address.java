package com.middlemountain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Address {
  private final String country;
  private final String city;
  private final String address;
}
