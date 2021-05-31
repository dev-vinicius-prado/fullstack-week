package com.fullstackweek.myinvest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "investimentos")
@Getter
@Setter
public class Investimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigoDoAtivo;
    private BigDecimal valorCota;
    private Integer quantidadeCotas;
    private LocalDate dataCompra;
}
