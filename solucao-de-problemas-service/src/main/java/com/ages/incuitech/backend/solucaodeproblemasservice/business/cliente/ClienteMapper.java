package com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente.ClienteRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente.ClienteResponse;

public class ClienteMapper {

    public static Cliente mapToModel(ClienteRequest clienteRequest){
        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequest.getNome());
        return cliente;
    }

    public static ClienteResponse mapToResponse(Cliente cliente){
        return new ClienteResponse(cliente.getId(), cliente.getNome());
    }
}
