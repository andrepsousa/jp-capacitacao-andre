package br.com.indra.jp_capacitacao_2026.service;

import br.com.indra.jp_capacitacao_2026.controller.dto.CategoriaRequest;
import br.com.indra.jp_capacitacao_2026.controller.dto.CategoriaResponse;
import br.com.indra.jp_capacitacao_2026.model.Categoria;
import br.com.indra.jp_capacitacao_2026.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaResponse criar(CategoriaRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNome(request.nome());

        if (request.paiId() != null) {
            Categoria pai = categoriaRepository.findById(request.paiId())
                    .orElseThrow(() -> new RuntimeException("Pai não encontrado"));
            categoria.setCategoriaPai(pai);
        }

        Categoria salva = categoriaRepository.save(categoria);

        return new CategoriaResponse(
                salva.getId(),
                salva.getNome(),
                salva.getCategoriaPai() != null ? salva.getCategoriaPai().getNome() : "Sem Pai"
        );
    }
}