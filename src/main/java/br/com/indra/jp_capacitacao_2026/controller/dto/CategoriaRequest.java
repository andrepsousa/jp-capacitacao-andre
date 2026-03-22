package br.com.indra.jp_capacitacao_2026.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRequest(
        @NotBlank(message = "O nome da categoria é obrigatório")
        String nome,
        Long paiId
) {}