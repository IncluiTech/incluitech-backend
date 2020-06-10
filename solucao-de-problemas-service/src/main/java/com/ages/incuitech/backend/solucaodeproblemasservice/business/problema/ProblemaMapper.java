package com.ages.incuitech.backend.solucaodeproblemasservice.business.problema;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.problema.ProblemaRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.problema.ProblemaResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.problema.Problema;

public class ProblemaMapper {
    private ProblemaMapper() {
    }

    public static Problema fromRequestToModel(ProblemaRequest request) {
        return Problema.builder()
                .descricao(request.getDescricao())
                .idCliente(request.getIdCliente())
                .build();
    }

    public static ProblemaResponse fromModelToResponse(Problema model) {
        return ProblemaResponse.builder()
                .descricao(model.getDescricao())
                .id(model.getId())
                .idCliente(model.getIdCliente())
                .build();
    }
}
