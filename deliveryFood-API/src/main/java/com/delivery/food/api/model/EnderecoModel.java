package com.delivery.food.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoModel {
	
	@ApiModelProperty(example = "26583-067")
	private String cep;
	@ApiModelProperty(example = "Rua Dr.godoy ")
	private String logradouro;
	@ApiModelProperty(example = "762")
	private String numero;
	@ApiModelProperty(example = "Edson Passos")
	private String bairro;
	@ApiModelProperty(example = "casa 2")
	private String complemento;
	@ApiModelProperty(example = "Mesquita")
	private CidadeResumoModel cidade;
}
