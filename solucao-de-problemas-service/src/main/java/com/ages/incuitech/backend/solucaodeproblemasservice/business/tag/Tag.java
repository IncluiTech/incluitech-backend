package com.ages.incuitech.backend.solucaodeproblemasservice.business.tag;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Builder
public class Tag {
	@Id
	private Long id;
	private String nome;
	private LocalDateTime dataCriacao;
	

}
