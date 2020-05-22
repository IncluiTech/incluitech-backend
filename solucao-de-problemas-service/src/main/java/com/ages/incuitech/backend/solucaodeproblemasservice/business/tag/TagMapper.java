package com.ages.incuitech.backend.solucaodeproblemasservice.business.tag;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagsolucionador.TagSolucionador;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class TagMapper {

    public static List<TagSolucionador> buildTagSolucionador(List<Tag> tags, Long solucionadorId) {
        return tags.stream()
                .map(tag ->
                        TagSolucionador.builder()
                                .idTag(tag.getId())
                                .dataCriacao(LocalDateTime.now())
                                .idSolucionador(solucionadorId)
                                .build()
                ).collect(toList());
    }

    public static List<String> mapToTagName(List<Tag> tags) {
        return tags.stream()
                .map(Tag::getNome)
                .collect(toList());
    }
}
