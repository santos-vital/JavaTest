package com.cd2.sigabem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cd2.sigabem.controller.dto.CotacaoDTO;
import com.cd2.sigabem.exception.CepIncorretoException;
import com.cd2.sigabem.exception.CepInexistenteException;
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
  public ResponseEntity<?> adicionar(@RequestBody Cotacao cotacao) {
    try {
      cotacao = cotacaoService.salvar(cotacao);

      return ResponseEntity.status(HttpStatus.CREATED).body(cotacao);
    } catch (CepIncorretoException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    } catch (CepInexistenteException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
