package com.delivery.food.infrainstructure.service.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.delivery.food.domain.filter.VendaDiariaFilter;
import com.delivery.food.domain.model.Pedido;
import com.delivery.food.domain.model.dto.VendaDiaria;
import com.delivery.food.domain.service.VendaQueryService;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffSet) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(VendaDiaria.class);
		var root = query.from(Pedido.class);
		var predicates = new ArrayList<Predicate>();
		
		var functionConvertTzDataCriacao = builder.function
				("convert_tz", Date.class, root.get("dataCriacao"),
						builder.literal("+00:00"), builder.literal(timeOffSet));
		
		var functionDataCriacao = builder.function
				("date", Date.class, functionConvertTzDataCriacao);
		
		var selection = builder.construct(VendaDiaria.class, 
				functionDataCriacao, builder.count(root.get("id")),
				builder.sum(root.get("valorTotal")));
		
		if(filtro.getRestauranteId() !=null) {
			predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
		}
		
		if (filtro.getDataCriacaoInicio() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), 
					filtro.getDataCriacaoInicio()));
		}
		
		if (filtro.getDataCriacaoFim() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), 
					filtro.getDataCriacaoFim()));
		}
		
		
		
		query.select(selection);
		query.where(predicates.toArray(new Predicate[0]));
		query.groupBy(functionDataCriacao);
		
		return manager.createQuery(query).getResultList();
	}

}
