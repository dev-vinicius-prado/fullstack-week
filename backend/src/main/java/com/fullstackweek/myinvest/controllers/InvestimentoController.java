package com.fullstackweek.myinvest.controllers;

import com.fullstackweek.myinvest.domain.Investimento;
import com.fullstackweek.myinvest.services.InvestimentoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/investimentos")
@AllArgsConstructor
public class InvestimentoController {

    private InvestimentoService investimentoService;

    @PutMapping("/{id}")
    public ResponseEntity<Investimento> atualizar(@PathVariable Long id, @RequestBody Investimento paraAtualizar) {
        if (investimentoService.naoExisteInvestimentoComId(id)) {
            return ResponseEntity.notFound().build();
        }
        paraAtualizar.setId(id);
        final var investimentoAtualizado = investimentoService.registrarInvestimento(paraAtualizar);
        return ResponseEntity.ok(investimentoAtualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Investimento> buscarPorId(@PathVariable Long id) {
        if (investimentoService.naoExisteInvestimentoComId(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(investimentoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<Collection<Investimento>> carregarListaDeInvestimentos() {
        final Collection<Investimento> investimentos = investimentoService.carregarListaInvestimentos();
        return investimentos.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(investimentos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (investimentoService.naoExisteInvestimentoComId(id)) {
            return ResponseEntity.notFound().build();
        }

        investimentoService.deletarInvestimento(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Investimento> registrarInvestimento(@RequestBody Investimento investimento) {
        final var investimentoRegistrado = investimentoService.registrarInvestimento(investimento);
        return ResponseEntity.ok(investimentoRegistrado);
    }
}
