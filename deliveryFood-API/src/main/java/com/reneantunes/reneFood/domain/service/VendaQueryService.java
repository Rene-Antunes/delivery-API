package com.reneantunes.reneFood.domain.service;

import java.util.List;

import com.reneantunes.reneFood.domain.filter.VendaDiariaFilter;
import com.reneantunes.reneFood.domain.model.dto.VendaDiaria;

public interface VendaQueryService {
	
	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro);
}
