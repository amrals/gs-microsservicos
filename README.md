# ZeloUrbano API - Global Solution (3SIR)

API RESTful desenvolvida em **Java 21** com **Spring Boot 3.2.5** para zeladoria colaborativa, acessibilidade urbana e acionamento automático do poder público. O sistema permite que moradores registrem problemas de infraestrutura urbana em seus bairros e, ao atingirem a meta de apoio comunitário (80% da população ativa da região no aplicativo), gera automaticamente uma solicitação formal para a subprefeitura responsável.

## Links Oficiais da Entrega

* **API em Produção (Azure App Service - Swagger UI):** https://zelourbano-api-3sir-exc5e2dtcug7cnet.brazilsouth-01.azurewebsites.net
* **Especificação OpenAPI (JSON):** https://zelourbano-api-3sir-exc5e2dtcug7cnet.brazilsouth-01.azurewebsites.net/api-docs
* **Imagem no Docker Hub:** https://hub.docker.com/r/amrals/zelourbano-api
* **Repositório GitHub:** https://github.com/amrals/gs-microsservicos

## Tecnologias e Arquitetura

* **Linguagem e Framework:** Java 21 LTS + Spring Boot 3.2.5
* **Persistência de Dados:** Spring Data JPA / Hibernate + Microsoft SQL Server (Azure SQL Database em produção e contêiner SQL Server 2022 localmente)
* **Validação e Padronização:** Jakarta Bean Validation (`@Valid`) + Tratamento global de exceções (`@RestControllerAdvice`)
* **Documentação Interativa:** Springdoc OpenAPI 2.5.0 (Swagger UI acessível diretamente na rota raiz `/`)
* **Containerização:** Docker (Multi-stage build com Maven + Eclipse Temurin JRE 21) e Docker Compose
* **CI/CD (Continuous Delivery):** GitHub Actions automatizando compilação, build da imagem e publicação no Docker Hub a cada push na branch `main`
* **Nuvem:** Microsoft Azure (Azure App Service for Containers Linux + Azure SQL Database)

## Regra de Negócio e Domínio (Gatilho de 80%)

O modelo de domínio é composto por 4 entidades relacionais mapeadas no Microsoft SQL Server:

1. **Região (`regioes`):** Representa o bairro monitorado, a subprefeitura responsável e a quantidade de moradores ativos no aplicativo (`populacaoAtivaApp`).
2. **Usuário (`usuarios`):** Morador cadastrado vinculado a uma região, incluindo seu perfil de mobilidade (`PEDESTRE`, `CADEIRANTE`, `IDOSO`, etc.).
3. **Ocorrência (`ocorrencias`):** Problema urbano reportado por um usuário em uma região (ex.: `ACESSIBILIDADE`, `ILUMINACAO`, `CALCADAS`), contabilizando o número de apoios (`quantidadeCurtidas`).
4. **Solicitação à Prefeitura (`solicitacoes_prefeitura`):** Ofício formal com protocolo único enviado ao órgão público.

**Funcionamento do Gatilho Automático:**
Sempre que uma ocorrência recebe apoios (via criação ou pelo endpoint `PATCH /api/v1/ocorrencias/{id}/curtir`) e o total de curtidas atinge ou supera **80% da população ativa da região**, a API altera automaticamente o status da ocorrência para `SOLICITACAO_GERADA` e persiste um novo registro em `solicitacoes_prefeitura` com protocolo oficial e status `ENVIADA_AUTOMATICAMENTE`.

## Endpoints da API

A rota raiz `/` redireciona automaticamente para a interface do Swagger UI (`/swagger-ui.html`).

### 1. Regiões (`/api/v1/regioes`)

* `POST /api/v1/regioes` - Cadastra uma nova região (`201 Created`)
* `GET /api/v1/regioes` - Lista todas as regiões (`200 OK`)
* `GET /api/v1/regioes/{id}` - Consulta uma região por ID (`200 OK` / `404 Not Found`)
* `PUT /api/v1/regioes/{id}` - Atualiza os dados de uma região (`200 OK`)
* `DELETE /api/v1/regioes/{id}` - Remove uma região (`204 No Content`)

### 2. Usuários (`/api/v1/usuarios`)

* `POST /api/v1/usuarios` - Cadastra um novo usuário vinculado a uma região (`201 Created`)
* `GET /api/v1/usuarios` - Lista todos os usuários (`200 OK`)
* `GET /api/v1/usuarios/{id}` - Consulta um usuário por ID (`200 OK` / `404 Not Found`)
* `PUT /api/v1/usuarios/{id}` - Atualiza os dados e perfil de mobilidade do usuário (`200 OK`)
* `DELETE /api/v1/usuarios/{id}` - Remove um usuário (`204 No Content`)

### 3. Ocorrências (`/api/v1/ocorrencias`)

* `POST /api/v1/ocorrencias` - Registra uma nova ocorrência urbana (`201 Created`)
* `GET /api/v1/ocorrencias` - Lista todas as ocorrências registradas (`200 OK`)
* `GET /api/v1/ocorrencias/{id}` - Consulta uma ocorrência por ID (`200 OK` / `404 Not Found`)
* `PUT /api/v1/ocorrencias/{id}` - Atualiza uma ocorrência existente (`200 OK`)
* `PATCH /api/v1/ocorrencias/{id}/curtir` - Incrementa uma curtida e verifica o gatilho de 80% da região (`200 OK`)
* `DELETE /api/v1/ocorrencias/{id}` - Remove uma ocorrência (`204 No Content`)

### 4. Solicitações à Prefeitura (`/api/v1/solicitacoes-prefeitura`)

* `POST /api/v1/solicitacoes-prefeitura` - Registra manualmente uma solicitação oficial (`201 Created`)
* `GET /api/v1/solicitacoes-prefeitura` - Lista todas as solicitações geradas automática ou manualmente (`200 OK`)
* `GET /api/v1/solicitacoes-prefeitura/{id}` - Consulta uma solicitação por ID (`200 OK` / `404 Not Found`)
* `PUT /api/v1/solicitacoes-prefeitura/{id}` - Atualiza o status ou mensagem da solicitação (`200 OK`)
* `DELETE /api/v1/solicitacoes-prefeitura/{id}` - Remove uma solicitação (`204 No Content`)

## Como Executar Localmente com Docker Compose

1. Clone este repositório:

~~~bash
git clone https://github.com/amrals/gs-microsservicos.git
cd gs-microsservicos
~~~

2. Suba o banco de dados Microsoft SQL Server 2022 e a API Spring Boot com um único comando:

~~~bash
docker compose up --build
~~~

3. Acesse a documentação interativa em `http://localhost:8080/`.

## Variáveis de Ambiente (Configuração no Azure)

Nenhuma credencial sensível de produção está exposta no código-fonte. A conexão no Azure App Service utiliza variáveis de ambiente injetadas em tempo de execução:

* `WEBSITES_PORT`: `8080`
* `DB_URL`: `jdbc:sqlserver://<servidor>.database.windows.net:1433;databaseName=<banco>;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;`
* `DB_USERNAME`: Usuário administrador do Azure SQL Server
* `DB_PASSWORD`: Senha do Azure SQL Server

## Pipeline de Continuous Delivery (GitHub Actions)

O workflow `.github/workflows/docker-publish.yml` é acionado automaticamente a cada `push` na branch `main` e executa as seguintes etapas:

1. Checkout do código-fonte e configuração do JDK 21 (Eclipse Temurin).
2. Compilação e empacotamento via Maven (`mvn clean package -DskipTests`).
3. Autenticação segura no Docker Hub utilizando GitHub Secrets (`DOCKERHUB_USERNAME` e `DOCKERHUB_TOKEN`).
4. Build multi-stage da imagem Docker e publicação automática no Docker Hub com as tags `:latest` e `:<commit-sha>`.
