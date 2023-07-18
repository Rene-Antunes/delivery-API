package com.reneantunes.reneFood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reneantunes.reneFood.api.model.FotoProdutoModel;
import com.reneantunes.reneFood.api.model.UsuarioModel;
import com.reneantunes.reneFood.domain.model.FotoProduto;
import com.reneantunes.reneFood.domain.model.Usuario;

@Component
public class FotoProdutoModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public FotoProdutoModel toModel(FotoProduto fotoProduto) {
		
		return modelMapper.map(fotoProduto, FotoProdutoModel.class);
	}	
	
	
}
