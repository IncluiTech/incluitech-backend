package com.ages.incuitech.backend.solucaodeproblemasservice.api.exception;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String mensagem) {
    super(mensagem);
  }
}
