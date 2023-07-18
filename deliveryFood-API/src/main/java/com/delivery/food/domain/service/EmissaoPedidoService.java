package com.delivery.food.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.food.domain.exception.NegocioException;
import com.delivery.food.domain.exception.PedidoNaoEncontradoException;
import com.delivery.food.domain.model.Cidade;
import com.delivery.food.domain.model.FormaPagamento;
import com.delivery.food.domain.model.Pedido;
import com.delivery.food.domain.model.Produto;
import com.delivery.food.domain.model.Restaurante;
import com.delivery.food.domain.model.Usuario;
import com.delivery.food.domain.repository.PedidoRepository;

@Service
public class EmissaoPedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;

	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	private CadastroProdutoService cadastroProdutoService;

	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamentoService;
	

	public Pedido buscarOuFalhar(String codigoPedido) {
		return pedidoRepository.findByCodigo(codigoPedido)
				.orElseThrow(() -> new PedidoNaoEncontradoException(codigoPedido));
	}
	
	
	@Transactional
	public Pedido emitir(Pedido pedido) {
		validarPedido(pedido);
		validarItens(pedido);
		
		pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
		pedido.calcularValorTotal();
		
		return pedidoRepository.save(pedido);
	}

	private void validarPedido(Pedido pedido) {
		Cidade cidade = cadastroCidadeService.buscarOuFalhar(pedido.getEnderecoEntrega().getCidade().getId());
		Usuario cliente = cadastroUsuarioService.buscarOuFalhar(pedido.getCliente().getId());
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(pedido.getRestaurante().getId());
		FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(pedido.getFormaPagamento().getId());
		
		pedido.getEnderecoEntrega().setCidade(cidade);
		pedido.setCliente(cliente);
		pedido.setRestaurante(restaurante);
		pedido.setFormaPagamento(formaPagamento);
		
		if(restaurante.naoAceitarFormaPagamento(formaPagamento)) {
			
			throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.", formaPagamento.getDescricao()));
		}
		
		
	}
	
	private void validarItens(Pedido pedido) {
		
		pedido.getItens().forEach(item -> {
			Produto produto = cadastroProdutoService.buscarOuFalhar(pedido.getRestaurante().getId(), item.getProduto().getId());
			
			item.setPedido(pedido);
			item.setProduto(produto);
			item.setPrecoUnitario(produto.getPreco());
		});
		
	}

}
