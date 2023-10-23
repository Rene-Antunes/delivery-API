package com.delivery.food.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Paulo Ricardo")
	private String nome;
	
	@ApiModelProperty(example = "paulo@exemplo.com")
	private String email;
	
}
