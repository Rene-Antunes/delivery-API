package com.delivery.food.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteResumoModel {
	@ApiModelProperty(example = "1")
	 private Long id;
	@ApiModelProperty(example = "BrasucaFood")
	 private String nome;
	
	
}
