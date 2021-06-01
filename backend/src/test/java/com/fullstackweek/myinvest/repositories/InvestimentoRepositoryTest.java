package com.fullstackweek.myinvest.repositories;

import com.fullstackweek.myinvest.domain.Investimento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class InvestimentoRepositoryTest {

    public static final String CODIGO_AAA_000 = "AAA000";
    public static final String CODIGO_BBB_111 = "BBB111";
    @Autowired
    private InvestimentoRepository investimentoRepository;

    @Test
    void deve_alterar_codigo_do_ativo() {
        var investimentoComCodigoAAA000 = new Investimento();
        investimentoComCodigoAAA000.setCodigoDoAtivo(CODIGO_AAA_000);
        final var investimentoSalvoCodigoAAA000 = investimentoRepository.save(investimentoComCodigoAAA000);

        investimentoSalvoCodigoAAA000.setCodigoDoAtivo(CODIGO_BBB_111);
        final var investimentoSalvoCodigoBBB111 = investimentoRepository.save(investimentoSalvoCodigoAAA000);
        Assertions.assertEquals(CODIGO_BBB_111, investimentoSalvoCodigoBBB111.getCodigoDoAtivo());
    }

    @Test
    void deve_buscar_um_investimento_por_id() {
        var investimentoComId = new Investimento();
        investimentoRepository.save(investimentoComId);

        final var idConsultado = investimentoComId.getId();
        final var investimento = investimentoRepository.findById(idConsultado).orElse(null);
        Assertions.assertNotNull(investimento);
        final var idEsperado = investimento.getId();

        Assertions.assertEquals(idConsultado, idEsperado);
    }

    @Test
    void deve_deletar_um_investimento() {
        var investimento = new Investimento();
        final var investimentoParaDeletar = investimentoRepository.save(investimento);
        final var idADeletar = investimentoParaDeletar.getId();
        investimentoRepository.delete(investimentoParaDeletar);
        Assertions.assertFalse(investimentoRepository.existsById(idADeletar));
    }

    @Test
    void deve_recuperar_lista_de_investimentos() {
        var investimentos = investimentoRepository.findAll();
        Assertions.assertNotNull(investimentos);
    }

    @Test
    void deve_salvar_um_investimento() {
        var investimentoParaSalvar = new Investimento();
        final var investimento = investimentoRepository.save(investimentoParaSalvar);
        Assertions.assertNotNull(investimento.getId());
    }

    @BeforeEach
    void setUp() {

    }
}
