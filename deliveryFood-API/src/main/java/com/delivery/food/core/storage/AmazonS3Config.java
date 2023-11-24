package com.delivery.food.core.storage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonS3Config {
	
	private StorageProperties storageProperties;
	
	
	@Bean
	public AmazonS3 amazonS3() {
		
		var credencials = new BasicAWSCredentials(storageProperties.getS3().getIdChaveAcesso(),
				storageProperties.getS3().getChaveAcessoSecreta());
		
		return AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credencials))
				.withRegion(storageProperties.getS3().getRegiao())
				.build();
	}

}
