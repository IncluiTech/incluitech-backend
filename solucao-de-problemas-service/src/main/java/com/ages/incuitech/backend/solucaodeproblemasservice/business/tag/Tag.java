package com.ages.incuitech.backend.solucaodeproblemasservice.business.tag;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class Tag {
	@Id
	private Long id;
	private String nome;
	private LocalDateTime dataCriacao;
	private StatusCadastro statusCadastro;

}
