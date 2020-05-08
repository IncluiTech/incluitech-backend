package com.ages.incuitech.backend.solucaodeproblemasservice.business.tag;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.tag.TagRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.tag.TagResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class TagMapper {

    public static Tag mapToModel(TagRequest tagRequest) {
        return Tag
                .builder()
                .id(tagRequest.getId())
                .nome(tagRequest.getNome())
               
                .dataCriacao(LocalDateTime.now())
                .build();
    }

    public static TagResponse mapToResponse(Tag tag) {
        return TagResponse.builder()
                .id(tag.getId())
                .nome(tag.getNome())
                
                .dataCriacao(LocalDateTime.now())
                .build();
    }
}
