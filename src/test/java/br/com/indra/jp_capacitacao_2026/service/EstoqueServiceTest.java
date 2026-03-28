package br.com.indra.jp_capacitacao_2026.service;

import br.com.indra.jp_capacitacao_2026.controller.dto.EstoqueRequest;
import br.com.indra.jp_capacitacao_2026.model.Produto;
import br.com.indra.jp_capacitacao_2026.repository.ProdutosRepository;
import br.com.indra.jp_capacitacao_2026.repository.TransacaoEstoqueRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstoqueServiceTest {

    @Mock
    private ProdutosRepository produtosRepository;

    @Mock
    private TransacaoEstoqueRepository transacaoEstoqueRepository;

    @InjectMocks
    private EstoqueService estoqueService;

    @Test
    void deveAdicionarEstoqueComSucesso() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setQuantidadeEstoque(10);

        EstoqueRequest request = new EstoqueRequest(5, "Compra Fornecedor");

        when(produtosRepository.findById(1L)).thenReturn(Optional.of(produto));

        estoqueService.ajustarEstoque(1L, request, "ENTRADA");

        assertEquals(15, produto.getQuantidadeEstoque());
        verify(produtosRepository, times(1)).save(produto);
        verify(transacaoEstoqueRepository, times(1)).save(any());
    }

    @Test
    void deveLancarErroQuandoVenderSemEstoque() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setQuantidadeEstoque(2);

        EstoqueRequest request = new EstoqueRequest(5, "Venda Cliente");

        when(produtosRepository.findById(1L)).thenReturn(Optional.of(produto));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            estoqueService.ajustarEstoque(1L, request, "SAIDA");
        });

        assertEquals("Estoque insuficiente para esta venda!", exception.getMessage());

        verify(produtosRepository, never()).save(any());
    }
}