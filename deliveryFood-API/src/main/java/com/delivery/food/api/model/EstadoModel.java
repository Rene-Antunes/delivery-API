package com.delivery.food.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstadoModel {
	@ApiModelProperty(example = "1")
	private Long Id;
	@ApiModelProperty(example = "Rio de Janeiro")
	private String nome;
}
