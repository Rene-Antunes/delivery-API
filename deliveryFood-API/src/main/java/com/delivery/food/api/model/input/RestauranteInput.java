package com.delivery.food.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.delivery.food.core.validation.TaxaFrete;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteInput {
	
	@ApiModelProperty(example = "BrasucaFood", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "12.00", required = true)
	@PositiveOrZero
	@TaxaFrete
	private BigDecimal taxaFrete;
	@Valid
	@NotNull
	private CozinhaIdInput cozinha;	
	@Valid
	@NotNull
	private EnderecoInput endereco;
	
	

}
