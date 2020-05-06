package com.ages.incuitech.backend.solucaodeproblemasservice.api.tag;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagRequest {
	private Long id;
	private String nome;
}
