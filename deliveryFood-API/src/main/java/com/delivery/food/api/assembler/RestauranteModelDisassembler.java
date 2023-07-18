package com.delivery.food.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.delivery.food.api.model.input.RestauranteInput;
import com.delivery.food.domain.model.Cidade;
import com.delivery.food.domain.model.Cozinha;
import com.delivery.food.domain.model.Endereco;
import com.delivery.food.domain.model.Restaurante;

@Component
public class RestauranteModelDisassembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurante toDomainObject(RestauranteInput restauranteInput ) {
		
		return modelMapper.map(restauranteInput,Restaurante.class);
		
	}
	
	
	public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
		// To avoid error: org.hibernate.HibernateException: identifier of an instance of
		//com.delivery.food.domain.model.Cozinha was altered from 1 to 2
		
		if (restaurante.getEndereco() != null) {
			
			restaurante.getEndereco().setCidade(new Cidade());
		}
		
		
		
		restaurante.setCozinha(new Cozinha());
		
		modelMapper.map(restauranteInput, restaurante);
		
	}
	

}
