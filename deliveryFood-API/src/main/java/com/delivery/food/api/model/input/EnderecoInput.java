package com.reneantunes.reneFood.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoInput {
	
	@NotBlank
	private String cep;
	@NotBlank
	private String logradouro;
	@NotBlank
	private String numero;
	@NotBlank
	private String bairro;
	private String complemento;
	@Valid
	@NotNull
	private CidadeIdInput cidade;

}
