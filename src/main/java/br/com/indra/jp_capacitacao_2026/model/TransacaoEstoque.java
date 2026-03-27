package br.com.indra.jp_capacitacao_2026.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transacao_estoque")
public class TransacaoEstoque {

    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidadeAlterada;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String motivo;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dataTransacao;
}