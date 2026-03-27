package br.com.indra.jp_capacitacao_2026.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProdutoRequest(
        @NotBlank(message = "O nome é obrigatório")
        String nome,
        String descricao,

        @Positive(message = "O preço deve ser maior que zero")
        BigDecimal preco,
        String codigoBarras,

        @NotNull(message = "O ID da categoria é obrigatório")
        Long categoriaId
) {
}