package com.ages.incuitech.backend.chatbotservice.infrastructure;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class SolucaoDeProblemasProperties {
    @Value("${url.solucao-de-problemas-service}")
    private String url;

    @Value("${uri.salvar-solucionador}")
    private String uri;

    @Value("${uri.salvar-cliente}")
    private String uriCliente;
}
