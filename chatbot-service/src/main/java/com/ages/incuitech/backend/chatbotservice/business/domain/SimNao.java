package com.ages.incuitech.backend.chatbotservice.business.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SimNao {
    SIM("S"),
    NAO("N");

    private String texto;
}
