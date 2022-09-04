package com.cd2.sigabem.exception;

public class CepInexistenteException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;

  public CepInexistenteException(String mensagem) {
    super(mensagem);
  }
}
