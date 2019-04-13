package com.middlemountain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Item {
  private final Integer id;
  private String description;
}
