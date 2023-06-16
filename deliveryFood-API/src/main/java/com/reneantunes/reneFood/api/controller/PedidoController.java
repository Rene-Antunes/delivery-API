package com.reneantunes.reneFood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.reneantunes.reneFood.api.assembler.PedidoInputDisassembler;
import com.reneantunes.reneFood.api.assembler.PedidoModelAssembler;
import com.reneantunes.reneFood.api.assembler.PedidoResumoModelAssembler;
import com.reneantunes.reneFood.api.model.PedidoModel;
import com.reneantunes.reneFood.api.model.PedidoResumoModel;
import com.reneantunes.reneFood.api.model.input.PedidoInput;
import com.reneantunes.reneFood.core.data.PageableTranslator;
import com.reneantunes.reneFood.domain.exception.EntidadeNaoEncontrataException;
import com.reneantunes.reneFood.domain.exception.NegocioException;
import com.reneantunes.reneFood.domain.model.Pedido;
import com.reneantunes.reneFood.domain.model.Usuario;
import com.reneantunes.reneFood.domain.repository.PedidoRepository;
import com.reneantunes.reneFood.domain.repository.filter.PedidoFilter;
import com.reneantunes.reneFood.domain.service.EmissaoPedidoService;
import com.reneantunes.reneFood.infrainstructure.repository.spec.PedidoSpecs;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {
	
	@Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private EmissaoPedidoService emissaoPedidoService;
    
    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;
    
    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;
    
    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;
    
    
    @GetMapping
    public Page<PedidoResumoModel> pesquisar(PedidoFilter filtro,
    	@PageableDefault(size=10) Pageable pageable){
    	
    	pageable = traduzirPageable(pageable);
    	
    	Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageable);
    	
    	
    	List<PedidoResumoModel> pedidosresumoModel =  pedidoResumoModelAssembler.toCollectionModel(pedidosPage.getContent());
    
    	Page<PedidoResumoModel> pedidosResumoModelPage = new PageImpl<>(
    			pedidosresumoModel, pageable, pedidosPage.getTotalElements());
    	
    	return pedidosResumoModelPage;
    }
    
    @GetMapping("/{codigoPedido}")
    public PedidoModel buscar(@PathVariable String codigoPedido) {
    	Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
    	
    	return pedidoModelAssembler.toModel(pedido);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
    	try {
			Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
			//TODO pegar usuário autenticado
			
			novoPedido.setCliente(new Usuario());
			novoPedido.getCliente().setId(1L);
			
			novoPedido = emissaoPedidoService.emitir(novoPedido);
			
			return pedidoModelAssembler.toModel(novoPedido);
		} catch (EntidadeNaoEncontrataException e) {
			throw new NegocioException(e.getMessage(), e);
		}
    }
    
    private Pageable traduzirPageable(Pageable apiPageable) {
    	var mapeamento = ImmutableMap.of(
    			"codigo", "codigo",
    			"restaurante.nome", "restaurante.nome", 
    			"nomeCliente", "cliente.nome",
    			"valorTotal", "valorTotal"
    		);
    	
    	return PageableTranslator.translate(apiPageable, mapeamento);
    }
}
