package com.cd2.sigabem.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cotacao {
  
  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private double peso;

  @Column(nullable = false)
  private String cepOrigem;

  @Column(nullable = false)
  private String cepDestino;

  @Column(nullable = false)
  private String nomeDestinatario;

  private double vlTotalFrete;

  private LocalDate dataPrevistaEntrega;

  private LocalDate dataConsulta;
}
