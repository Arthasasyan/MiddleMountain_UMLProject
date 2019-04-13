package com.middlemountain.model;

import com.middlemountain.enums.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
  private Integer id;
  private String name;
  private Float salary;
  private Permission permission;
}
