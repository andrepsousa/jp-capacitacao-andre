package br.com.indra.jp_capacitacao_2026.service;

import br.com.indra.jp_capacitacao_2026.controller.dto.ProdutoRequest;
import br.com.indra.jp_capacitacao_2026.controller.dto.ProdutoResponse;
import br.com.indra.jp_capacitacao_2026.model.Categoria;
import br.com.indra.jp_capacitacao_2026.model.HistoricoPreco;
import br.com.indra.jp_capacitacao_2026.model.Produto;
import br.com.indra.jp_capacitacao_2026.repository.CategoriaRepository;
import br.com.indra.jp_capacitacao_2026.repository.HistoricoPrecoRepository;
import br.com.indra.jp_capacitacao_2026.repository.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutosService {

    private final ProdutosRepository produtosRepository;
    private final HistoricoPrecoRepository historicoPrecoRepository;
    private final CategoriaRepository categoriaRepository;

    public List<Produto> getAll() {
        return produtosRepository.findAll();
    }

    public ProdutoResponse createdProduto(ProdutoRequest request) {

        Categoria categoria = categoriaRepository.findById(request.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));

        Produto produto = new Produto();
        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        produto.setCodigoBarras(request.codigoBarras());

        produto.setCategoria(categoria);

        Produto produtoSalvo = produtosRepository.save(produto);

        return new ProdutoResponse(
                produtoSalvo.getId(),
                produtoSalvo.getNome(),
                produtoSalvo.getDescricao(),
                produtoSalvo.getPreco(),
                produtoSalvo.getCodigoBarras(),
                produtoSalvo.getCategoria().getNome() // Pega o nome da categoria que acabou de ser salva
        );
    }

    public Produto atualiza(Produto produto) {
        return produtosRepository.save(produto);
    }

    public void deletarProduto(Long id) {
        produtosRepository.deleteById(id);
    }

    public Produto getById(Long id) {
        return produtosRepository.findById(id).get();
    }

    public Produto atualizaPreco(Long id, BigDecimal preco) {
//        Produtos produto = produtosRepository.findById(id).get();
        final var produto = produtosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produto.setPreco(preco);
        /***
         * Rastreabilidade
         * 1 - Criar um log
         * 2 - Adicionar em tabela historico de preços valores old e new
         * para cada produto atualizado
         * 3 - Antes de atualizar a tabela de produto, pegar o valor atual da tabela e adiconar
         * na tabela historico
         * 4 - Pegar novo valor da tabela e adicionar na tabela historico
         * 5 - Sempre na tabela, adicionar novo registro após atualizar tabela de produto
         * Estrutura da tabela historico de preços
         * id
         * id_produto
         * preco_antigo
         * preco_novo
         * data_alteracao
         */
        final var historico = new HistoricoPreco();
        historico.setPrecoAntigo(produto.getPreco());
        historico.setProdutos(produto);
        historico.setPrecoNovo(preco);
        //Código abaixo pode ser substituido por @CreationTimestamp
//        historico.setDataAlteracao(LocalDateTime.now());
        historicoPrecoRepository.save(historico);
        return produtosRepository.saveAndFlush(produto);

        //Exemplo de não se fazer por gerar retrabalho
//        final var historicoNovo = historicoPrecoRepository.findById(historico.getId()).get();
//        historicoNovo.setPrecoNovo(preco);
//        historicoPrecoRepository.save(historicoNovo);
        /**
         * get na tabela produtos para novo preço
         */
//        return produto;
    }
}
