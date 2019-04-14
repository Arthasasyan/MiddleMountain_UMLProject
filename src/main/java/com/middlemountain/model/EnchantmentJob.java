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
public class EnchantmentJob {
  private Integer id;
  private Item item;
  private MagicType magicType;
  private String description;
  private Boolean completed;
}
