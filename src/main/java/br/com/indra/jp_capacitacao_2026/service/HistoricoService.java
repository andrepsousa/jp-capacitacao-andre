package br.com.indra.jp_capacitacao_2026.service;

import br.com.indra.jp_capacitacao_2026.model.HistoricoPreco;
import br.com.indra.jp_capacitacao_2026.repository.HistoricoPrecoRepository;
import br.com.indra.jp_capacitacao_2026.service.dto.HistoricoProdutoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HistoricoService {

    private final HistoricoPrecoRepository historicoPrecoRepository;

    public List<HistoricoProdutoDTO> getHistoricoByProdutoId(Long produtoId) {

        Set<HistoricoPreco> historicoPrecos = historicoPrecoRepository.findByProdutosId(produtoId);

        return historicoPrecos.stream()
                .map(historico -> new HistoricoProdutoDTO(
                        historico.getId(),
                        historico.getProdutos().getNome(),
                        historico.getPrecoAntigo(),
                        historico.getPrecoNovo(),
                        historico.getDataAlteracao()
                ))
                .toList();
    }
}