package com.middlemountain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreationJob {
  private Integer id;
  private Good good;
  private Employee employee;
  private Integer amountRemaining;
}
