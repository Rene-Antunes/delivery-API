package com.delivery.food.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoInput {
	@ApiModelProperty(example = "38400-000", required = true)
	@NotBlank
	private String cep;
	@ApiModelProperty(example = "Rua Dr.godoy", required = true)
	@NotBlank
	private String logradouro;
	@ApiModelProperty(example = "726", required = true)
	@NotBlank
	private String numero;
	@ApiModelProperty(example = "Edson Passos", required = true)
	@NotBlank
	private String bairro;
	@ApiModelProperty(example = "casa 2", required = true)
	private String complemento;
	
	@ApiModelProperty(example = "Mesquita", required = true)
	@Valid
	@NotNull
	private CidadeIdInput cidade;

}
