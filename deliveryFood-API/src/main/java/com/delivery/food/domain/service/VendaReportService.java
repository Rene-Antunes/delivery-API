package com.delivery.food.domain.service;

import com.delivery.food.domain.filter.VendaDiariaFilter;

public interface VendaReportService {
	
	byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
