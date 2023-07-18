package com.delivery.food.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.delivery.food.api.model.FormaPagamentoModel;
import com.delivery.food.domain.model.FormaPagamento;

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
