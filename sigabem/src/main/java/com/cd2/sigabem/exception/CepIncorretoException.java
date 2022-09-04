package com.cd2.sigabem.exception;

public class CepIncorretoException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;

  public CepIncorretoException(String mensagem) {
    super(mensagem);
  }
}
