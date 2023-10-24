package com.delivery.food.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenhaInput {
	@ApiModelProperty(example = "123", required = true)
	private String senhaAtual;
	@ApiModelProperty(example = "3456", required = true)
	private String novaSenha;
	
}
