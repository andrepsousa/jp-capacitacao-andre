package br.com.indra.jp_capacitacao_2026.controller;

import br.com.indra.jp_capacitacao_2026.controller.dto.CategoriaRequest;
import br.com.indra.jp_capacitacao_2026.controller.dto.CategoriaResponse;
import br.com.indra.jp_capacitacao_2026.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponse> criar(@RequestBody @Valid CategoriaRequest request) {
        return ResponseEntity.status(201).body(categoriaService.criar(request));
    }
}