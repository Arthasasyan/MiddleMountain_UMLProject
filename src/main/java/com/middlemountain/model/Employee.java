package com.middlemountain.model;

import com.middlemountain.enums.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Employee {
  private Integer id = 0;
  private String name;
  private Float salary;
  private Permission permission;
  private Integer onVacation;
  private Integer fired;
}
