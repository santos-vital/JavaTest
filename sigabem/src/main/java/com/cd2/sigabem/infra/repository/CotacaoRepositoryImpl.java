package com.cd2.sigabem.infra.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cd2.sigabem.model.Cotacao;
import com.cd2.sigabem.repository.CotacaoRepository;

@Component
public class CotacaoRepositoryImpl implements CotacaoRepository{
  
  @PersistenceContext
  private EntityManager manager;

  @Override
  public List<Cotacao> listar() {
    return manager.createQuery("from Cotacao", Cotacao.class).getResultList();
  }

  @Transactional
  @Override
  public Cotacao salvar(Cotacao cotacao) {

    return manager.merge(cotacao);
  }
}
