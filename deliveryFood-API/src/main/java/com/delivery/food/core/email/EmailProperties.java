package com.delivery.food.core.email;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("renefood.email")
public class EmailProperties {
	
	@NotNull
	private String remetente;
	private SandBox sandbox = new SandBox();
	
	private Implemetacao impl = Implemetacao.FAKE;
	
	
	
	
	@Getter
	@Setter
	public class SandBox {
		private String destinatario;
	}
	
	
	public enum Implemetacao{
		SMTP, FAKE, SANDBOX
	}
}
