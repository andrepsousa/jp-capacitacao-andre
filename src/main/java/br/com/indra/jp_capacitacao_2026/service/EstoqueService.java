package br.com.indra.jp_capacitacao_2026.service;

import br.com.indra.jp_capacitacao_2026.controller.dto.EstoqueRequest;
import br.com.indra.jp_capacitacao_2026.model.Produto;
import br.com.indra.jp_capacitacao_2026.model.TransacaoEstoque;
import br.com.indra.jp_capacitacao_2026.repository.ProdutosRepository;
import br.com.indra.jp_capacitacao_2026.repository.TransacaoEstoqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final ProdutosRepository produtosRepository;
    private final TransacaoEstoqueRepository transacaoEstoqueRepository;

    @Transactional
    public void ajustarEstoque(Long produtoId, EstoqueRequest request, String tipo) {
        Produto produto = produtosRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        if (request.quantidade() <= 0) {
            throw new RuntimeException("A quantidade deve ser maior que zero!");
        }

        if (tipo.equals("SAIDA") && produto.getQuantidadeEstoque() < request.quantidade()) {
            throw new RuntimeException("Estoque insuficiente para esta venda!");
        }

        if (tipo.equals("ENTRADA")) {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + request.quantidade());
        } else {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - request.quantidade());
        }
        produtosRepository.save(produto);

        TransacaoEstoque transacao = new TransacaoEstoque();
        transacao.setProduto(produto);
        transacao.setQuantidadeAlterada(request.quantidade());
        transacao.setTipo(tipo);
        transacao.setMotivo(request.motivo());
        transacaoEstoqueRepository.save(transacao);
    }
}