# 🛒 E-Commerce API - Capacitação INDRA/MINSAIT

Bem-vindo ao repositório do projeto de E-commerce, desenvolvido como parte do programa de capacitação técnica da **INDRA/MINSAIT**. 

Esta API RESTful foi construída com foco em **arquitetura limpa**, **regras de negócio robustas** e **boas práticas de mercado**, simulando o backend de um catálogo de produtos com controle de inventário e auditoria.

---

## 🚀 Funcionalidades e Regras de Negócio

O sistema não é apenas um CRUD básico. Ele implementa lógicas reais de um e-commerce:

* **Gestão de Categorias:** Suporte a hierarquia infinita (Categorias e Subcategorias).
* **Catálogo de Produtos:** Produtos estritamente vinculados a categorias.
* **Auditoria Invisível (Histórico de Preços):** Sempre que o preço de um produto é atualizado, o sistema utiliza eventos do Hibernate e `@Transactional` para gravar automaticamente o valor antigo e o novo no banco, garantindo rastreabilidade.
* **Controle de Estoque Inteligente:** * Registro detalhado de transações (Entradas e Saídas com seus devidos motivos).
  * **Validação de Segurança:** O sistema bloqueia automaticamente tentativas de saída/venda caso o produto não possua saldo suficiente no estoque.
* **Tratamento Global de Exceções:** Utilização de `@RestControllerAdvice` para capturar erros e devolver respostas HTTP consistentes (Status 400), sem expor a *stack trace* do servidor ao cliente.

---

## 🛠️ Tecnologias e Boas Práticas Utilizadas

* **Java 21**
* **Spring Boot 3** (Web, Data JPA, Validation)
* **Banco de Dados Relacional** com mapeamento via **Hibernate** (Geração automática de UUIDs e Timestamps).
* **Padrão DTO (Data Transfer Object):** Isolamento total das entidades de banco de dados. A API só recebe e devolve DTOs.
* **Arquitetura em Camadas (MVC Limpo):** Separação estrita de responsabilidades entre `Controllers`, `Services` e `Repositories`.
* **Swagger / OpenAPI:** Documentação interativa e testes de endpoints direto no navegador.
* **Lombok:** Redução de código boilerplate.
* **Testes Automatizados:** Cobertura de testes unitários na camada de serviço utilizando **JUnit 5** e **Mockito**, garantindo a confiabilidade das regras de estoque.

---

## ⚙️ Como Executar o Projeto

1. Certifique-se de ter o **Java 21** e o **Maven** instalados na sua máquina.
2. Clone este repositório:
   ```bash
   git clone https://github.com/andrepsousa/jp-capacitacao-andre.git
   ```
3. Acesse a pasta do projeto:
   ```bash
   cd jp-capacitacao-andre
   ```
4. Execute a aplicação via Maven:
   ```bash
   ./mvnw spring-boot:run
   ```
5. A API estará disponível na porta `9090`.

---

## 📚 Documentação da API (Swagger)

Com a aplicação rodando, você pode explorar, testar e interagir com todos os endpoints diretamente pela interface do Swagger UI.

Acesse no seu navegador:
👉 **http://localhost:9090/swagger-ui.html**

---

## 🧪 Como Rodar os Testes Unitários

Para garantir a integridade das regras de negócio (como o bloqueio de vendas sem estoque), execute os testes automatizados com o comando:

```bash
./mvnw test
```

---
