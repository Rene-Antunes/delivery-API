package com.delivery.food.api.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoModel {
	
	@ApiModelProperty(example = "Frango assado")
	private String nome;
	
	@ApiModelProperty(example = "Bem passado, por favor")
	private String descricao;
	
	@ApiModelProperty(example = "39.90")
	private BigDecimal preco;
	
	@ApiModelProperty(example = "true")
	private boolean ativo;
	

}
