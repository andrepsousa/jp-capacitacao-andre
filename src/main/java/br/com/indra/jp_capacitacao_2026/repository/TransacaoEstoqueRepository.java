package br.com.indra.jp_capacitacao_2026.repository;

import br.com.indra.jp_capacitacao_2026.model.TransacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TransacaoEstoqueRepository extends JpaRepository<TransacaoEstoque, UUID> {
}