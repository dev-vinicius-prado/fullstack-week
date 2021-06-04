package com.fullstackweek.myinvest.services;


import com.fullstackweek.myinvest.domain.Investimento;
import com.fullstackweek.myinvest.repositories.InvestimentoRepository;
import com.fullstackweek.myinvest.services.impl.InvestimentoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InvestimentosServiceTest {

    @Mock
    private InvestimentoRepository investimentoRepository;
    private InvestimentoService underTest;

    @Test
    void deve_chamar_o_buscar_por_codigo_do_ativo() {
        //dado que
        final var codigoDoAtivo = "AAA000";
        //quando
        underTest.buscarPorCodigoDoAtivo(codigoDoAtivo);
        //entao
        verify(investimentoRepository).findByCodigoDoAtivo(codigoDoAtivo);
    }

    @Test
    void deve_chamar_o_buscar_por_id() {
        //dado que
        final var idInvestimento = 1L;
        final var investimento = new Investimento();
        investimento.setId(idInvestimento);
        //quando
        doReturn(Optional.of(investimento)).when(investimentoRepository).findById(idInvestimento);
        underTest.buscarPorId(idInvestimento);
        //entao
        verify(investimentoRepository).findById(idInvestimento);
    }

    @Test
    void deve_chamar_o_carregar_lista_de_investimentos() {
        //quando
        underTest.carregarListaInvestimentos();
        //entao
        verify(investimentoRepository).findAll();
    }

    @Test
    void deve_chamar_o_deletar_investimento() {
        //dado que
        final var idParaDeletar = 1L;
        final var investimento = new Investimento();
        investimento.setId(idParaDeletar);
        //quando
        doReturn(Optional.of(investimento)).when(investimentoRepository).findById(idParaDeletar);
        underTest.buscarPorId(idParaDeletar);
        underTest.deletarInvestimento(idParaDeletar);
        //entao
        verify(investimentoRepository).delete(any());
    }

    @Test
    void deve_chamar_o_registrar_investimento() {
        //dado que
        final var investimento = new Investimento();
        //quando
        underTest.registrarInvestimento(investimento);
        //entao
        verify(investimentoRepository).save(investimento);
    }

    @BeforeEach
    void setUp() {
        underTest = new InvestimentoServiceImpl(investimentoRepository);
    }
}
