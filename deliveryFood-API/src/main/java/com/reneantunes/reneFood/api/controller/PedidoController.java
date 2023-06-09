package com.reneantunes.reneFood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reneantunes.reneFood.api.assembler.PedidoInputDisassembler;
import com.reneantunes.reneFood.api.assembler.PedidoModelAssembler;
import com.reneantunes.reneFood.api.assembler.PedidoResumoModelAssembler;
import com.reneantunes.reneFood.api.model.PedidoModel;
import com.reneantunes.reneFood.api.model.PedidoResumoModel;
import com.reneantunes.reneFood.api.model.input.PedidoInput;
import com.reneantunes.reneFood.domain.exception.EntidadeNaoEncontrataException;
import com.reneantunes.reneFood.domain.exception.NegocioException;
import com.reneantunes.reneFood.domain.model.Pedido;
import com.reneantunes.reneFood.domain.model.Usuario;
import com.reneantunes.reneFood.domain.repository.PedidoRepository;
import com.reneantunes.reneFood.domain.service.EmissaoPedidoService;

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
    public List<PedidoResumoModel> listar(){
    	List<Pedido> todosPedidos = pedidoRepository.findAll();
    	
    	return pedidoResumoModelAssembler.toCollectionModel(todosPedidos);
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

}
