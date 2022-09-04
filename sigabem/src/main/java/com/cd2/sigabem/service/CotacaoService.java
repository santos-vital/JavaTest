package com.cd2.sigabem.service;

import java.time.LocalDate;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.cd2.sigabem.controller.dto.CepDTO;
import com.cd2.sigabem.exception.CepIncorretoException;
import com.cd2.sigabem.exception.CepInexistenteException;
import com.cd2.sigabem.exception.EntidadeNaoEncontradaException;
import com.cd2.sigabem.model.Cotacao;
import com.cd2.sigabem.repository.CotacaoRepository;

@Service
public class CotacaoService {

  CepDTO cepDTO = new CepDTO();
  
  @Autowired
  private CotacaoRepository cotacaoRepository;

  public CepDTO consultaCep(String cep) {
    return new RestTemplate().getForEntity("https://viacep.com.br/ws/" + cep + "/json/", CepDTO.class).getBody();
  }

  public Cotacao salvar(Cotacao cotacao) {
    try {
      LocalDate dataConsulta = LocalDate.now();

      CepDTO cepOrigem = consultaCep(cotacao.getCepOrigem());
      CepDTO cepDestino = consultaCep(cotacao.getCepDestino());

      cotacao.setDataConsulta(dataConsulta);

      if(cotacao.getId() == null || cotacao.getId() <= 0) {
        cotacao.setCepOrigem(cepOrigem.getCep());
        cotacao.setCepDestino(cepDestino.getCep());
      }

      if(cepOrigem.getDdd().equals(cepDestino.getDdd())) {
        double valorFrete = cotacao.getPeso() - (cotacao.getPeso() * 0.5);
        cotacao.setDataPrevistaEntrega(dataConsulta.plusDays(1));
    
        cotacao.setVlTotalFrete(valorFrete);
      } else if(cepOrigem.getLocalidade().equals(cepDestino.getLocalidade())) {
        double valorFrete = cotacao.getPeso() - (cotacao.getPeso() * 0.75);
        cotacao.setDataPrevistaEntrega(dataConsulta.plusDays(3));
    
        cotacao.setVlTotalFrete(valorFrete);
      } else if(!(cepOrigem.getLocalidade().equals(cepDestino.getLocalidade()))) {
        double valorFrete = cotacao.getPeso();
        cotacao.setDataPrevistaEntrega(dataConsulta.plusDays(10));
    
        cotacao.setVlTotalFrete(valorFrete);
      }

      return cotacaoRepository.salvar(cotacao);
    } catch (HttpClientErrorException e) {
      throw new CepIncorretoException("Um ou mais CEPs foram informados de forma incorreta!");
    } catch (PersistenceException e) {
      throw new CepInexistenteException("Um ou mais CEPs informados são inexistentes!");
    }
  }

  public void excluir(Long id) {
    try {
      cotacaoRepository.remover(id);
    } catch (EmptyResultDataAccessException e) {
      throw new EntidadeNaoEncontradaException(String.format("Não existe uma cotação com código %d", id));
    }
  }
}
