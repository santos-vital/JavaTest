package com.cd2.sigabem.repository;

import java.util.List;

import com.cd2.sigabem.model.Cotacao;

public interface CotacaoRepository {
  
  List<Cotacao> listar();
  Cotacao salvar(Cotacao cotacao);
}
