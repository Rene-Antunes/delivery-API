package com.delivery.food.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeResumoModel {
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Mesquita")
	private String nome;
	
	@ApiModelProperty(example = "Rio de Janeiro")
	private String Estado;
	
}
