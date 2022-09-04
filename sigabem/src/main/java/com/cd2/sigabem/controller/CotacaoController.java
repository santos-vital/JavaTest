package com.cd2.sigabem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cd2.sigabem.controller.dto.CotacaoDTO;
import com.cd2.sigabem.model.Cotacao;
import com.cd2.sigabem.repository.CotacaoRepository;
import com.cd2.sigabem.service.CotacaoService;

@RestController
@RequestMapping("/cotacoes")
public class CotacaoController {
  
  @Autowired
  private CotacaoRepository cotacaoRepository;

  @Autowired
  private CotacaoService cotacaoService;

  @GetMapping
  public List<CotacaoDTO> listar() {

    List<Cotacao> cotacoes = cotacaoRepository.listar();

    return CotacaoDTO.converter(cotacoes);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Cotacao adicionar(@RequestBody Cotacao cotacao) {
    
    return cotacaoService.salvar(cotacao);
  }
}
