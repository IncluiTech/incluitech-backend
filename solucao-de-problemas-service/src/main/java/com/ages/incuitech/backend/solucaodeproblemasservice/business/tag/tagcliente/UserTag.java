package com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagcliente;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UserTag {
    private String tagName;
    private Long userId;
}
