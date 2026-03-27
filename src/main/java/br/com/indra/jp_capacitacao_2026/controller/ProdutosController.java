package br.com.indra.jp_capacitacao_2026.controller;

import br.com.indra.jp_capacitacao_2026.controller.dto.ProdutoRequest;
import br.com.indra.jp_capacitacao_2026.controller.dto.ProdutoResponse;
import br.com.indra.jp_capacitacao_2026.model.Produto;
import br.com.indra.jp_capacitacao_2026.service.ProdutosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Produtos", description = "Endpoints para gerenciamento de produtos")
@RequestMapping("/produtos")
public class ProdutosController {

    private final ProdutosService produtosService;

    //C

    /**
     * Recomendação de desenvolvimento, ampliar responses(responseEntity)
     * possíveis além do ok.
     */
    @Operation(description = "Endpoint para criar um novo produto",
            summary = "Criação de produto")
    @PostMapping // Removemos o "/cria". No padrão REST, fazer um POST na rota "/produtos" já significa criar.
    public ResponseEntity<ProdutoResponse> criarProduto(@RequestBody @Valid ProdutoRequest request){

        // Passamos o DTO validado para o Service, que nos devolve o DTO de resposta com o ID
        ProdutoResponse response = produtosService.createdProduto(request);

        // O professor pediu para usar mais status além do 200 (OK).
        // O padrão HTTP correto para quando algo é criado é o 201 (CREATED).
        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(response);
    }

    /**
     * GET
     * localhost:9090/produtos
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Produto>> getAll(){
        return ResponseEntity.ok(produtosService.getAll());
    }

    /**
     * GET
     * localhost:9090/produtos/1
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id){
        return ResponseEntity.ok(produtosService.getById(id));
    }

    @PutMapping("/atualiza")
    public ResponseEntity<Produto> atualizarProduto(@RequestParam Long id,
                                                    @RequestBody Produto produto){
        return ResponseEntity.ok(produtosService.atualiza(produto));
    }

    //Mudar para delete lógico
    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtosService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/atualiza-preco/{id}")
    public ResponseEntity<ProdutoResponse> atualizarPreco(@PathVariable Long id, @RequestBody BigDecimal novoPreco) {
        ProdutoResponse response = produtosService.updateProduto(id, novoPreco);
        return ResponseEntity.ok(response);
    }
}
