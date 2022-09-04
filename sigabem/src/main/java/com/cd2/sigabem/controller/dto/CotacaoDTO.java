package com.cd2.sigabem.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.cd2.sigabem.model.Cotacao;

public class CotacaoDTO {
  
  private double vlTotalFrete;
  private LocalDate dataPrevistaEntrega;
  private String cepOrigem;
  private String cepDestino;

  public CotacaoDTO(Cotacao cotacao) {
    this.vlTotalFrete = cotacao.getVlTotalFrete();
    this.dataPrevistaEntrega = cotacao.getDataPrevistaEntrega();
    this.cepOrigem = cotacao.getCepOrigem();
    this.cepDestino = cotacao.getCepDestino();
  }

  public double getVlTotalFrete() {
    return vlTotalFrete;
  }

  public LocalDate getDataPrevistaEntrega() {
    return dataPrevistaEntrega;
  }

  public String getCepOrigem() {
    return cepOrigem;
  }

  public String getCepDestino() {
    return cepDestino;
  }

  public static List<CotacaoDTO> converter(List<Cotacao> cotacoes) {

    return cotacoes.stream().map(CotacaoDTO::new).collect(Collectors.toList());
  }
}
