package com.middlemountain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class Address {
  private final String country;
  private final String city;
  private final String address;
}
