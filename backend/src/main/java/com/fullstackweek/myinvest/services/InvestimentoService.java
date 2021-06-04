package com.fullstackweek.myinvest.services;

import com.fullstackweek.myinvest.domain.Investimento;

import java.util.Collection;

public interface InvestimentoService {

    Investimento buscarPorCodigoDoAtivo(String codigoDoAtivo);

    Investimento buscarPorId(Long id);

    Collection<Investimento> carregarListaInvestimentos();

    void deletarInvestimento(Long paraDeletar);

    Investimento registrarInvestimento(Investimento investimento);

    boolean naoExisteInvestimentoComId(Long id);
}
