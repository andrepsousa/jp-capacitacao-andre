package br.com.indra.jp_capacitacao_2026.controller;

import br.com.indra.jp_capacitacao_2026.controller.dto.EstoqueRequest;
import br.com.indra.jp_capacitacao_2026.service.EstoqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
@RequiredArgsConstructor
public class EstoqueController {

    private final EstoqueService estoqueService;

    @PostMapping("/{produtoId}/adicionar")
    public ResponseEntity<String> adicionar(@PathVariable Long produtoId, @RequestBody EstoqueRequest request) {
        estoqueService.ajustarEstoque(produtoId, request, "ENTRADA");
        return ResponseEntity.ok("Estoque adicionado com sucesso!");
    }

    @PostMapping("/{produtoId}/remover")
    public ResponseEntity<String> remover(@PathVariable Long produtoId, @RequestBody EstoqueRequest request) {
        estoqueService.ajustarEstoque(produtoId, request, "SAIDA");
        return ResponseEntity.ok("Estoque removido com sucesso!");
    }
}