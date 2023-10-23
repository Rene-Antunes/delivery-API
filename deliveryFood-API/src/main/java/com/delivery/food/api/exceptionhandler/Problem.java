package com.delivery.food.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problema")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {
	@ApiModelProperty(example = "400")
	private Integer status;
	@ApiModelProperty(example = "http://renefood.com.br/mensagem-incompreensível")
	private String type;
	@ApiModelProperty(example = "Mensagem incompreensível")
	private String title;
	@ApiModelProperty(example = "O corpo da requisição está inválido. Verifique erro de sintaxe")
	private String detail;
	
	@ApiModelProperty(example = "2023-10-19T13:36:54.1100073Z")
	private OffsetDateTime timestamp;
	@ApiModelProperty(example = "O corpo da requisição está inválido. Verifique erro de sintaxe")
	private String userMessage;
	@ApiModelProperty("Objetos ou campos que geraram o erro")
	private List<Object> objects;
	
	@ApiModel("ObjetoProblema")
	@Getter
	@Builder
	public static class Object{
		@ApiModelProperty(example = "preco")
		private String name;
		@ApiModelProperty(example = "O preço é obrigatório")
		private String userMessage;
	}
	
	
}
