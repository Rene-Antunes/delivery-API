package com.reneantunes.reneFood.core.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.reneantunes.reneFood.api.model.EnderecoModel;
import com.reneantunes.reneFood.api.model.input.ItemPedidoInput;
import com.reneantunes.reneFood.domain.model.Endereco;
import com.reneantunes.reneFood.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		
		var modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
			.addMappings(mapper -> mapper.skip(ItemPedido::setId));
		
		var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);
		
	enderecoToEnderecoModelTypeMap.<String>addMapping(
			enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(), (enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));
	
		
		return modelMapper;
	}
}
