# Desafio Backend – Controle de Estoque de Relógios

API REST para controle de estoque de relógios, construída com Spring Boot e PostgreSQL.  
Permite cadastrar relógios, listar com filtros avançados (busca, resistência à água, tipo de movimento, material, vidro, preço, diâmetro, marca) e ordenar por diferentes critérios.

---

## Tecnologias

- **Linguagem**: Java 21  
- **Framework**: Spring Boot 3  
- **Módulos Spring**:
  - Spring Web
  - Spring Data JPA
  - Bean Validation
- **Banco de dados**: PostgreSQL  
- **ORM**: Hibernate / JPA  
- **Build**: Maven  
- **Outros**:
  - Lombok
  - Specifications do Spring Data para filtros dinâmicos

---

## Arquitetura e organização

O projeto segue uma separação em camadas:

- `controller` – endpoints REST (`RelogioController`)
- `service` – regras de negócio e orquestração (`RelogioService`, estratégias de ordenação)
- `repository` – acesso a dados (`RelogioRepository`, `RelogioSpecification`)
- `entity` – entidades JPA (`Relogio`, enums relacionados)
- `dto` – objetos de entrada (ex.: `RelogioDto`)
- `mapper` – conversão entre DTOs e entidades (`RelogioMapper`)
- `exception` – tratamento global de erros (`GlobalExceptionHandler`, exceções de domínio)
- `config` – configurações da aplicação e seed de dados (`Seed`, `JacksonConfig`)

---

## Funcionalidades

- **Cadastro de relógios**
  - Validação de campos obrigatórios via Bean Validation (`@Valid`, `@NotNull`, `@NotBlank`, etc.).
  - Cálculo de:
    - **Etiqueta de resistência à água** a partir de `resistenciaAguaM`.
    - **Pontuação de colecionador** com base em movimento, vidro, material da caixa, diâmetro, etc.

- **Listagem de relógios com filtros e paginação**
  - Paginação (`pageNo`, `pageSize`).
  - Busca textual por marca, modelo ou referência (`busca`).
  - Filtros:
    - Resistência à água mínima/máxima.
    - Tipo de movimento (enum).
    - Material da caixa (enum).
    - Tipo de vidro (enum).
    - Preço mínimo/máximo.
    - Diâmetro mínimo/máximo.
    - Marca.
  - Ordenação configurável via parâmetro (`ordenar`), usando estratégias de ordenação.

- **Consulta por ID**
  - Retorna um relógio específico.
  - Resposta customizada em caso de não encontrado.

- **Seed de dados (ambiente de desenvolvimento)**
  - Classe `Seed` que, ao subir a aplicação, verifica se já existem relógios na base.
  - Se não existir nenhum, cria alguns registros de exemplo (Casio, Seiko, Tissot).

- **Tratamento de erros**
  - Handler global (`GlobalExceptionHandler`) padronizando respostas de erro.
  - Mensagens claras para:
    - Erros de validação de campos.
    - ID de relógio não encontrado.
    - Tipos inválidos em parâmetros (ex.: enum incorreto).

---

## Modelagem de dados (entidade `Relogio`)

Campos principais da entidade:

- `id` (`UUID`)
- `marca`, `modelo`, `referencia`
- `tipoMovimento` (`enum TipoMovimento`)
- `materialCaixa` (`enum MaterialCaixa`)
- `tipoVidro` (`enum TipoVidro`)
- `resistenciaAguaM`
- `diametroMm`, `lugToLugMm`, `espessuraMm`, `larguraLugMm`
- `precoEmCentavos`
- `urlImagem`
- `etiquetaResistenciaAgua`
- `pontuacaoColecionador`

Enums definem valores válidos para tipo de movimento, material da caixa e tipo de vidro.

---

## Como rodar o projeto localmente

### Pré-requisitos

- Java 21 instalado
- Maven instalado (ou usar o `mvnw` do próprio projeto)
- PostgreSQL em execução

### Configuração do banco

Crie um banco no PostgreSQL (por exemplo, `relogios_db`) e configure as variáveis de ambiente esperadas pelo `application.yaml`, como:

- `POSTGRES_URL` – ex.: `jdbc:postgresql://localhost:5433/relogios_db`
- `POSTGRES_USERNAME` – ex.: `postgres`
- `POSTGRES_PASSWORD` – ex.: `sua_senha`

### Executando

Via Maven:

mvn spring-boot:run
