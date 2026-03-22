package br.com.indra.jp_capacitacao_2026.controller.dto;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        String codigoBarras,
        String nomeCategoria
) {
}
