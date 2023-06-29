package com.reneantunes.reneFood.infrainstructure.service.storage;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.flywaydb.core.internal.util.FileCopyUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.reneantunes.reneFood.domain.service.FotoStorageService;

@Service
public class LocalFotoStorageService implements FotoStorageService {
	
	@Value("${reneFood.storage.local.diretorio-fotos}")
	private Path diretorioFotos;
	
	@Override
	public void armazenar(NovaFoto novaFato) {
		
		try {
			Path arquivoPath = getArquivoPath(novaFato.getNomeArquivo());
			FileCopyUtils.copy(novaFato.getInputStream(),
					Files.newOutputStream(arquivoPath));
		} catch (Exception e) {
			throw new StorageException("Não foi possível armazenar arquivo.", e);
		}
	}
	
	@Override
	public InputStream recuperar(String nomeArquivo) {
		try {
			Path arquivoPath = getArquivoPath(nomeArquivo);
			return Files.newInputStream(arquivoPath);
		} catch (Exception e) {
			throw new StorageException("Não foi possível recuperar arquivo.", e);
		}
	}
	
	@Override
	public void remover(String nomeArquivo) {
		Path arquivoPath = getArquivoPath(nomeArquivo);
		
		try {
			Files.deleteIfExists(arquivoPath);
		} catch (Exception e) {
			throw new StorageException("Não foi possível excluir arquivo.", e);
		}
	
	}
	
	private Path getArquivoPath(String nomeArquivo) {
		return diretorioFotos.resolve(Path.of(nomeArquivo));
	}






}