package com.delivery.food.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.delivery.food.api.model.FotoProdutoModel;
import com.delivery.food.api.model.UsuarioModel;
import com.delivery.food.domain.model.FotoProduto;
import com.delivery.food.domain.model.Usuario;

@Component
public class FotoProdutoModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public FotoProdutoModel toModel(FotoProduto fotoProduto) {
		
		return modelMapper.map(fotoProduto, FotoProdutoModel.class);
	}	
	
	
}
