package com.delivery.food.api.model.input;

import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.delivery.food.core.validation.FileContentType;
import com.delivery.food.core.validation.FileSize;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class FotoProdutoInput {
	
	@ApiModelProperty(hidden = true)
	@NotNull
	@FileSize(max = "500KB")
	@FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	private MultipartFile arquivo;
	
	@ApiModelProperty(value = "Descrição da foto do produto", required = true)
	@NotNull
	private String descricao;
}
