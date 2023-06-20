package com.reneantunes.reneFood.domain.service;

import com.reneantunes.reneFood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {
	
	byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
