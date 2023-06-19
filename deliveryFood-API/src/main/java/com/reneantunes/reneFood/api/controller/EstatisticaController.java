package com.reneantunes.reneFood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reneantunes.reneFood.domain.filter.VendaDiariaFilter;
import com.reneantunes.reneFood.domain.model.dto.VendaDiaria;
import com.reneantunes.reneFood.domain.service.VendaQueryService;

@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticaController {
	
	@Autowired
	private VendaQueryService vendaQueryService;
	
	@GetMapping("/vendas-diarias")
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro){
		return vendaQueryService.consultarVendasDiarias(filtro);
	}
}
