package com.middlemountain.model;

import com.middlemountain.enums.MagicType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnchantmentJob {
  private Integer id;
  private Item item;
  private MagicType magicType;
  private String description;
  private Boolean completed;
}
