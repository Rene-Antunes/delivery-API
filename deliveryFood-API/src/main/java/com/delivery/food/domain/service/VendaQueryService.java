package com.delivery.food.domain.service;

import java.util.List;

import com.delivery.food.domain.filter.VendaDiariaFilter;
import com.delivery.food.domain.model.dto.VendaDiaria;

public interface VendaQueryService {
	
	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffSet);
}
