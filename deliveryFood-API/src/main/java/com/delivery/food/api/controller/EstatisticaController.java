package com.delivery.food.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.food.api.openapi.controller.EstatisticasControllerOpenApi;
import com.delivery.food.domain.filter.VendaDiariaFilter;
import com.delivery.food.domain.model.dto.VendaDiaria;
import com.delivery.food.domain.service.VendaQueryService;
import com.delivery.food.domain.service.VendaReportService;

@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticaController implements EstatisticasControllerOpenApi {
	
	@Autowired
	private VendaQueryService vendaQueryService;
	
	@Autowired
	private VendaReportService vendaReportService;
	
	@GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro,
		 @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet){
		return vendaQueryService.consultarVendasDiarias(filtro, timeOffSet);
	}
	
	@GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro,
		 @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet){
		
		byte[] bytesPdf = vendaReportService.emitirVendasDiarias(filtro, timeOffSet);
		
		var headers = new HttpHeaders();
		
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");
		
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.headers(headers)
				.body(bytesPdf);
	}
		
}
