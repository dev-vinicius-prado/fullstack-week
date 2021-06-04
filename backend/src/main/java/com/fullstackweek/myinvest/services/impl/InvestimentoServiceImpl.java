package com.fullstackweek.myinvest.services.impl;

import com.fullstackweek.myinvest.domain.Investimento;
import com.fullstackweek.myinvest.exceptions.ResourceNotFoundException;
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
        return investimentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Investimento não encontrado."));
    }

    @Override
    public Collection<Investimento> carregarListaInvestimentos() {
        return investimentoRepository.findAll();
    }

    @Override
    public void deletarInvestimento(Long idParaDeletar) {
        final var paraDeletar = this.buscarPorId(idParaDeletar);
        investimentoRepository.delete(paraDeletar);
    }

    @Override
    public Investimento registrarInvestimento(Investimento investimento) {
        return investimentoRepository.save(investimento);
    }

    @Override
    public boolean naoExisteInvestimentoComId(Long id) {
        return !investimentoRepository.existsById(id);
    }
}
