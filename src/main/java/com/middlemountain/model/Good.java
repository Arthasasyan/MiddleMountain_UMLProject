package com.middlemountain.model;

import com.middlemountain.enums.MagicType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Good {
  private Integer id = 0;
  private MagicType magicType;
  private String name;
  private String description;
  private Float price;
  private Integer amount;
  private Integer deleted;
}
