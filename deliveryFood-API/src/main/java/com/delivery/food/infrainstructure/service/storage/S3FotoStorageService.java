package com.delivery.food.infrainstructure.service.storage;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.delivery.food.domain.service.FotoStorageService;
@Service
public class S3FotoStorageService implements FotoStorageService {

	private AmazonS3 amazonS3;
	
	@Override
	public void armazenar(NovaFoto novoFato) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(String nomeArquivo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InputStream recuperar(String nomeArquivo) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
