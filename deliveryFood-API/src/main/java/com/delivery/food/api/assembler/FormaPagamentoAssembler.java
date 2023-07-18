package com.reneantunes.reneFood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reneantunes.reneFood.api.model.FormaPagamentoModel;
import com.reneantunes.reneFood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
		return modelMapper.map(formaPagamento, FormaPagamentoModel.class);
	}
	
	public List<FormaPagamentoModel> toCollectionModel(Collection<FormaPagamento> formasPagamentos){
		return formasPagamentos.stream()
				.map(formaPagamento -> toModel(formaPagamento))
				.collect(Collectors.toList());
	}
}
