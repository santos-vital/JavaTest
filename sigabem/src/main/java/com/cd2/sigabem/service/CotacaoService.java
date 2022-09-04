package com.cd2.sigabem.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cd2.sigabem.exception.EntidadeNaoEncontradaException;
import com.cd2.sigabem.model.Cotacao;
import com.cd2.sigabem.repository.CotacaoRepository;

@Service
public class CotacaoService {
  
  @Autowired
  private CotacaoRepository cotacaoRepository;

  public Cotacao salvar(Cotacao cotacao) {

    LocalDate dataConsulta = LocalDate.now();    

    cotacao.setDataConsulta(dataConsulta);
    cotacao.setDataPrevistaEntrega(dataConsulta.plusDays(1));

    if(cotacao.getCepOrigem().equals(cotacao.getCepDestino())) {
      double valorFrete = cotacao.getPeso() - (cotacao.getPeso() * 0.5);

      cotacao.setVlTotalFrete(valorFrete);
    }

    return cotacaoRepository.salvar(cotacao);
  }

  public void excluir(Long id) {
    try {
      cotacaoRepository.remover(id);
    } catch (EmptyResultDataAccessException e) {
      throw new EntidadeNaoEncontradaException(String.format("Não existe uma cotação com código %d", id));
    }
  }
}
