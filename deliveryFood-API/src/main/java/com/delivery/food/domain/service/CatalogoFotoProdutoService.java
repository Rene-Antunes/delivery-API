package com.delivery.food.domain.service;

import java.io.InputStream;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery.food.domain.exception.FotoProdutoNaoEncontradaException;
import com.delivery.food.domain.model.FotoProduto;
import com.delivery.food.domain.repository.ProdutoRepository;
import com.delivery.food.domain.service.FotoStorageService.NovaFoto;

@Service
public class CatalogoFotoProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private FotoStorageService fotoStorageService;
	
	@Transactional
	public FotoProduto salvar(FotoProduto fotoProduto, InputStream inputStream) {
		Long restauranteId = fotoProduto.getRestaunranteId();
		Long produtoId = fotoProduto.getProduto().getId();
		String nomeNovoArquivo = fotoStorageService.gerarNomeArquivo(fotoProduto.getNomeArquivo());
		String nomeArquivoExistente = null;
		
		Optional<FotoProduto> fotoExistente = produtoRepository
				.findFotoById(restauranteId, produtoId);
		
		if(fotoExistente.isPresent()) {
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			produtoRepository.delete(fotoExistente.get());
		}
		
		fotoProduto.setNomeArquivo(nomeNovoArquivo);
		fotoProduto = produtoRepository.save(fotoProduto);
		produtoRepository.flush();
		
		NovaFoto novaFoto = NovaFoto.builder()
				.nomeArquivo(fotoProduto.getNomeArquivo())
				.inputStream(inputStream)
				.build();
		
		fotoStorageService.substituir(nomeArquivoExistente, novaFoto);
		
		return fotoProduto;
	}
	
	@Transactional
	public void excluir(Long restauranteId, Long produtoId) {
		FotoProduto foto = buscarOuFalhar(restauranteId, produtoId);
		
		produtoRepository.delete(foto);
		produtoRepository.flush();
		
		fotoStorageService.remover(foto.getNomeArquivo());
	}
	
	
	
	public FotoProduto buscarOuFalhar(Long restauranteId, Long produtoId) {
	    return produtoRepository.findFotoById(restauranteId, produtoId)
	            .orElseThrow(() -> new FotoProdutoNaoEncontradaException(restauranteId, produtoId));
	}
	
}
