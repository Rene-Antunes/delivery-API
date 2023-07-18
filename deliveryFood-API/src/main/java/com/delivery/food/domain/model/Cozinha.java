package com.delivery.food.domain.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.delivery.food.core.validation.Groups;

import lombok.Data;
import lombok.EqualsAndHashCode;

//@JsonRootName("cozinha")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {
	
	@NotNull(groups = Groups.CozinhaId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@JsonIgnore
//	@JsonProperty("titulo")
	
	@Column(nullable = false)
	private String nome;
	
//	@Column(name = "observacao")
//	private String descricao;
	
	
	
		
}
