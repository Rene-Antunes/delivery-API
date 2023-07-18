package com.delivery.food.api.model;

import com.delivery.food.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaModel {
	@JsonView(RestauranteView.Resumo.class)
	private Long id;
	@JsonView(RestauranteView.Resumo.class)
	private String nome;
}
