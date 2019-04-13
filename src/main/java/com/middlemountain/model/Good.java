package com.middlemountain.model;

import com.middlemountain.enums.MagicType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Good {
  private Integer id;
  private MagicType magicType;
  private String name;
  private String description;
  private Float price;
}
