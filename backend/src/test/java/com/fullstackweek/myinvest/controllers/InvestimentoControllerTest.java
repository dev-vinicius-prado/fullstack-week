package com.fullstackweek.myinvest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstackweek.myinvest.domain.Investimento;
import com.fullstackweek.myinvest.services.InvestimentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class InvestimentoControllerTest {

    public static final String APP_BASE_URL = "/investimentos";
    public static final long ID = 1L;
    @MockBean
    private InvestimentoService investimentoService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void deve_retornar_status_Created_quando_registrar_investimento() throws Exception {
        //dado que
        final var investimento = new Investimento();
        investimento.setId(ID);
        investimento.setCodigoDoAtivo("AAA000");
        investimento.setQuantidadeCotas(1);

        final var investimentoToPost = new Investimento();
        investimento.setCodigoDoAtivo("AAA000");
        investimento.setQuantidadeCotas(1);

        //quando
        doReturn(investimento).when(investimentoService).registrarInvestimento(any());
        //entao
        mockMvc.perform(
                post(APP_BASE_URL).contentType(APPLICATION_JSON)
                        .content(asJsonString(investimentoToPost))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.codigoDoAtivo", is("AAA000")))
                .andExpect(jsonPath("$.quantidadeCotas", is(1)));
    }

    @Test
    void deve_retornar_status_No_Content_quando_carregar_lista_investimentos_vazia() throws Exception {
        //dado que
        //quando
        doReturn(Collections.emptyList()).when(investimentoService).carregarListaInvestimentos();
        //entao
        mockMvc.perform(
                get(APP_BASE_URL).contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deve_retornar_status_Not_Found_quando_buscar_por_id_que_nao_existe() throws Exception {
        //dado que
        final var idInexistente = -ID;
        //quando
        doReturn(true).when(investimentoService).naoExisteInvestimentoComId(idInexistente);
        //entao
        mockMvc.perform(
                get(APP_BASE_URL + "/{id}", idInexistente).contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deve_retornar_status_Not_Found_quando_nao_encontrar_investimento_para_atualizar() throws Exception {
        //dado que
        final var paraAtualizar = new Investimento();
        final var idQueNaoExiste = -ID;
        paraAtualizar.setId(idQueNaoExiste);
        //quando
        doReturn(true).when(investimentoService).naoExisteInvestimentoComId(idQueNaoExiste);
        //entao
        mockMvc.perform(put(APP_BASE_URL + "/{id}", idQueNaoExiste)
                .content(asJsonString(paraAtualizar))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deve_retornar_status_Not_Found_quando_nao_encontrar_investimento_para_deletar() throws Exception {
        //dado que
        final var paraDeletar = new Investimento();
        final var idQueNaoExiste = -ID;
        paraDeletar.setId(idQueNaoExiste);
        //quando
        doReturn(true).when(investimentoService).naoExisteInvestimentoComId(idQueNaoExiste);
        //entao
        mockMvc.perform(delete(APP_BASE_URL + "/{id}", idQueNaoExiste)
                .content(asJsonString(paraDeletar))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deve_retornar_status_OK_quando_carregar_lista_investimentos() throws Exception {
        //dado que
        final var investimento = new Investimento();
        final var investimentoA = new Investimento();
        final var investimentos = Arrays.asList(investimento, investimentoA);

        //quando
        doReturn(investimentos).when(investimentoService).carregarListaInvestimentos();

        //entao
        mockMvc.perform(get(APP_BASE_URL)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void deve_retornar_status_Ok_quando_atualizar_investimento() throws Exception {
        //dado que
        final var paraAtualizar = new Investimento();
        paraAtualizar.setId(ID);
        paraAtualizar.setCodigoDoAtivo("AAA001");

        final var retornoDaBuscaPorId = new Investimento();
        retornoDaBuscaPorId.setId(ID);
        retornoDaBuscaPorId.setCodigoDoAtivo("AAA000");

        final var retornoAtualizado = new Investimento();
        retornoAtualizado.setId(ID);
        retornoAtualizado.setCodigoDoAtivo("AAA001");

        //quando
        doReturn(false).when(investimentoService).naoExisteInvestimentoComId(paraAtualizar.getId());
        doReturn(retornoAtualizado).when(investimentoService).registrarInvestimento(any());
        //entao
        mockMvc.perform(put(APP_BASE_URL + "/{id}", ID)
                .content(asJsonString(retornoDaBuscaPorId))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.codigoDoAtivo", is("AAA001")));
    }

    @Test
    void deve_retornar_status_Ok_quando_deletar_investimento() throws Exception {
        //dado que
        final var paraDeletar = new Investimento();
        paraDeletar.setId(ID);
        //quando
        doReturn(false).when(investimentoService).naoExisteInvestimentoComId(paraDeletar.getId());
        doNothing().when(investimentoService).deletarInvestimento(paraDeletar.getId());
        //entao
        mockMvc.perform(delete(APP_BASE_URL + "/{id}", paraDeletar.getId())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}