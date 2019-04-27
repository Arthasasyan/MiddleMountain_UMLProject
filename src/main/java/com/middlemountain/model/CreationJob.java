package com.middlemountain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CreationJob {
  private Integer id = 0;
  private Good good;
  private Employee employee;
  private Integer amountRemaining;
}
