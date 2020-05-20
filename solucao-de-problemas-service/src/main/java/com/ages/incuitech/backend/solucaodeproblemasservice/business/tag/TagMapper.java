package com.ages.incuitech.backend.solucaodeproblemasservice.business.tag;

import java.util.List;
import java.util.stream.Collectors;

public class TagMapper {

    public static List<String> mapToTagName(List<Tag> tags) {
        return tags.stream()
                .map(Tag::getNome)
                .collect(Collectors.toList());
    }
}
