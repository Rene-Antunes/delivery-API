package com.reneantunes.reneFood.core.email;

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
	
	private Implemetacao impl = Implemetacao.FAKE;
	
	
	public enum Implemetacao{
		SMTP, FAKE
	}
}
