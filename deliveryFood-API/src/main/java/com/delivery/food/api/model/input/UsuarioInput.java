package com.delivery.food.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UsuarioInput {
	
	@ApiModelProperty(example = "Roberto", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "roberto@exemplo.com", required = true)
	@NotBlank
	@Email
	private String email;
	

}
