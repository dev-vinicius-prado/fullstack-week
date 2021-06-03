package com.fullstackweek.myinvest.services.impl;

import com.fullstackweek.myinvest.domain.Investimento;
import com.fullstackweek.myinvest.repositories.InvestimentoRepository;
import com.fullstackweek.myinvest.services.InvestimentoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class InvestimentoServiceImpl implements InvestimentoService {

    private final InvestimentoRepository investimentoRepository;

    @Override
    public Investimento buscarPorCodigoDoAtivo(String codigoDoAtivo) {
        return investimentoRepository.findByCodigoDoAtivo(codigoDoAtivo).orElse(null);
    }

    @Override
    public Investimento buscarPorId(Long id) {
        return investimentoRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Investimento> carregarListaInvestimentos() {
        return investimentoRepository.findAll();
    }

    @Override
    public void deletarInvestimento(Investimento investimento) {
        investimentoRepository.delete(investimento);
    }

    @Override
    public Investimento registrarInvestimento(Investimento investimento) {
        return investimentoRepository.save(investimento);
    }
}
