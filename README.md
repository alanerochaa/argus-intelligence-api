# 🛰️ ARGUS Intelligence API

API Java desenvolvida para o projeto **ARGUS**, uma solução de inteligência ambiental voltada ao monitoramento de focos de calor, análise de risco e geração de alertas operacionais com apoio de dados satelitais.

Projeto acadêmico desenvolvido para a **Global Solution 2026/1 — FIAP**.

---

## 👩‍💻 Grupo

**CodeGirls**

| Integrante                    | RM       |
| ----------------------------- | -------- |
| Alane Rocha da Silva          | RM561052 |
| Anna Beatriz de Araujo Bonfim | RM559561 |
| Maria Eduarda Araujo Penas    | RM560944 |

---

## 🎯 Objetivo da API

A **ARGUS Intelligence API** atua como o microserviço responsável pelo contexto de inteligência ambiental do projeto.

Ela centraliza:

* Cadastro de biomas;
* Cadastro de regiões monitoradas;
* Registro de focos de calor;
* Geração e controle de alertas;
* Consulta de risco ambiental;
* Integração com dados satelitais da NASA FIRMS;
* Integração com API de IA;
* Integração com API operacional em C#.

---

## 🧱 Arquitetura Geral

```text
                         ┌────────────────────┐
                         │     NASA FIRMS     │
                         │ Dados Satelitais   │
                         └─────────┬──────────┘
                                   │
                                   ▼
                    ┌──────────────────────────┐
                    │        INGESTÃO          │
                    │ /sync/24h  /sync/5dias   │
                    └────────────┬─────────────┘
                                 │
                                 ▼

══════════════════════════════════════════════════════════════
                    ARGUS INTELLIGENCE API
══════════════════════════════════════════════════════════════

      ┌────────────┐
      │   BIOMA    │
      └─────┬──────┘
            │
            ▼
      ┌────────────┐
      │   REGIÃO   │
      └─────┬──────┘
            │
            ▼
      ┌────────────┐
      │ FOCO CALOR │
      └─────┬──────┘
            │
            ▼
      ┌────────────┐
      │   ALERTA   │
      └─────┬──────┘
            │
            ▼
      ┌────────────┐
      │   RISCO    │
      └────────────┘


══════════════════════════════════════════════════════════════
                      INTEGRAÇÕES
══════════════════════════════════════════════════════════════

┌────────────────────┐       ┌────────────────────┐
│      API C#        │       │       API IA        │
│ Operações de Campo │       │ Relatórios e análise│
└─────────┬──────────┘       └─────────┬──────────┘
          │                            │
          └────────────┬───────────────┘
                       ▼
              ┌─────────────────┐
              │    ARGUS API    │
              │  Orquestração   │
              └─────────────────┘


══════════════════════════════════════════════════════════════
                      PERSISTÊNCIA
══════════════════════════════════════════════════════════════

              ┌──────────────────┐
              │ Oracle Database  │
              │ BIOMA            │
              │ REGIAO           │
              │ FOCO_CALOR       │
              │ ALERTA           │
              └──────────────────┘
```

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia        | Uso                                |
| ----------------- | ---------------------------------- |
| Java 17           | Linguagem principal                |
| Spring Boot       | Framework backend                  |
| Spring Web        | Criação dos endpoints REST         |
| Spring Data JPA   | Persistência com Oracle            |
| Oracle Database   | Banco de dados relacional          |
| OpenFeign         | Comunicação com APIs externas      |
| Swagger / OpenAPI | Documentação dos endpoints         |
| Bean Validation   | Validação de DTOs                  |
| Lombok            | Redução de boilerplate             |
| HikariCP          | Pool de conexões                   |
| NASA FIRMS API    | Dados satelitais de focos de calor |
| Weather API       | Apoio ao cálculo de risco          |
| RabbitMQ          | Base para mensageria               |
| Spring Cache      | Cache simples em memória           |

---

## 📦 Estrutura do Projeto

```text
src/main/java/br/com/fiap/argus
│
├── client
│   ├── ClienteNASAFirms.java
│   ├── ClienteIA.java
│   ├── ClienteOcorrenciaCSharp.java
│   └── ClienteWeather.java
│
├── config
│   ├── CacheConfig.java
│   ├── CorsConfig.java
│   ├── OpenApiConfig.java
│   └── SecurityConfig.java
│
├── controller
│   ├── AlertaController.java
│   ├── BiomaController.java
│   ├── FocoCalorController.java
│   ├── IAController.java
│   ├── IngestaoController.java
│   ├── RegiaoController.java
│   └── RiscoController.java
│
├── domain
│   ├── Alerta.java
│   ├── Bioma.java
│   ├── FocoCalor.java
│   └── Regiao.java
│
├── dto
│   ├── request
│   └── response
│
├── mapper
│
├── repository
│
├── service
│
└── ArgusIntelligenceApiApplication.java
```

---

## 🗄️ Modelo de Dados

```text
BIOMA
  ↓
REGIAO
  ↓
FOCO_CALOR
  ↓
ALERTA
```

### Principais entidades

| Entidade   | Responsabilidade                                           |
| ---------- | ---------------------------------------------------------- |
| BIOMA      | Representa os biomas monitorados                           |
| REGIAO     | Representa regiões vinculadas a um bioma                   |
| FOCO_CALOR | Representa eventos de calor detectados                     |
| ALERTA     | Representa alertas operacionais gerados a partir dos focos |

---

## 🌐 Endpoints da API

### 🌿 Biomas

| Método | Endpoint           | Origem | Descrição           |
| ------ | ------------------ | ------ | ------------------- |
| GET    | `/api/biomas`      | Oracle | Listar biomas       |
| GET    | `/api/biomas/{id}` | Oracle | Buscar bioma por ID |
| POST   | `/api/biomas`      | Oracle | Cadastrar bioma     |
| PUT    | `/api/biomas/{id}` | Oracle | Atualizar bioma     |
| DELETE | `/api/biomas/{id}` | Oracle | Remover bioma       |

---

### 📍 Regiões

| Método | Endpoint       | Origem | Descrição        |
| ------ | -------------- | ------ | ---------------- |
| GET    | `/api/regioes` | Oracle | Listar regiões   |
| POST   | `/api/regioes` | Oracle | Cadastrar região |

---

### 🔥 Focos de Calor

| Método | Endpoint     | Origem | Descrição               |
| ------ | ------------ | ------ | ----------------------- |
| GET    | `/api/focos` | Oracle | Listar focos de calor   |
| POST   | `/api/focos` | Oracle | Registrar foco de calor |

---

### 🚨 Alertas

| Método | Endpoint            | Origem | Descrição            |
| ------ | ------------------- | ------ | -------------------- |
| GET    | `/api/alertas`      | Oracle | Listar alertas       |
| GET    | `/api/alertas/{id}` | Oracle | Buscar alerta por ID |
| POST   | `/api/alertas`      | Oracle | Criar alerta         |
| PUT    | `/api/alertas/{id}` | Oracle | Atualizar alerta     |
| DELETE | `/api/alertas/{id}` | Oracle | Remover alerta       |

---

### 🛰️ Ingestão NASA FIRMS

| Método | Endpoint                   | Origem     | Descrição                       |
| ------ | -------------------------- | ---------- | ------------------------------- |
| POST   | `/api/ingestao/sync/24h`   | NASA FIRMS | Buscar focos das últimas 24h    |
| POST   | `/api/ingestao/sync/5dias` | NASA FIRMS | Buscar focos dos últimos 5 dias |

---

### ⚠️ Risco

| Método | Endpoint                   | Origem               | Descrição                              |
| ------ | -------------------------- | -------------------- | -------------------------------------- |
| GET    | `/api/riscos/regioes/{id}` | Oracle + Weather API | Calcular risco ambiental de uma região |

---

### 🤖 Inteligência Artificial

| Método | Endpoint                  | Origem | Descrição                   |
| ------ | ------------------------- | ------ | --------------------------- |
| POST   | `/api/ia/gerar-relatorio` | API IA | Gerar relatório inteligente |
| POST   | `/api/ia/consultar`       | API IA | Consultar análise da IA     |

---

### 🧭 Operações C#

| Método | Endpoint                          | Origem | Descrição                 |
| ------ | --------------------------------- | ------ | ------------------------- |
| GET    | `/api/operacoes/ocorrencias`      | API C# | Listar ocorrências        |
| GET    | `/api/operacoes/ocorrencias/{id}` | API C# | Buscar ocorrência por ID  |
| GET    | `/api/operacoes/registros-campo`  | API C# | Listar registros de campo |
| GET    | `/api/operacoes/brigadistas/{id}` | API C# | Buscar brigadista         |

---

## 🔄 Fluxo Operacional

```text
NASA FIRMS
   ↓
Ingestão de focos
   ↓
Registro de FOCO_CALOR
   ↓
Cálculo de RISCO
   ↓
Geração de ALERTA
   ↓
Apoio da IA
   ↓
Suporte operacional
```

---

## 🔗 Integrações

| Integração  | Tipo                 | Responsabilidade                       |
| ----------- | -------------------- | -------------------------------------- |
| NASA FIRMS  | API externa          | Dados satelitais de focos de calor     |
| Weather API | API externa          | Dados climáticos para análise de risco |
| API IA      | Microserviço externo | Relatórios e análise inteligente       |
| API C#      | Microserviço externo | Operações de campo                     |
| Oracle      | Banco de dados       | Persistência principal                 |

---

## ⚙️ Configuração do Projeto

Arquivo principal:

```text
src/main/resources/application.properties
```

Exemplo de configuração:

```properties
spring.application.name=argus-intelligence-api
server.port=8080

spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521/ORCL
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.open-in-view=false

nasa.firms.map-key=SUA_CHAVE_NASA
nasa.firms.base-url=https://firms.modaps.eosdis.nasa.gov/api
nasa.firms.source=VIIRS_SNPP_NRT
nasa.firms.area=-85,-57,-32,14
nasa.firms.day-range=1

weather.api.url=https://api.open-meteo.com/v1/forecast

ia.api.url=http://localhost:8082
csharp.api.url=http://localhost:8081

spring.cache.type=simple

management.endpoints.web.exposure.include=health,info,metrics
management.endpoint.health.show-details=always
```

> Observação: credenciais reais e chaves de API não devem ser versionadas no GitHub.

---

## ▶️ Como Executar

### 1. Clonar o repositório

```bash
git clone https://github.com/alanerochaa/argus-intelligence-api.git
```

### 2. Acessar a pasta do projeto

```bash
cd argus-intelligence-api
```

### 3. Configurar o `application.properties`

Ajustar:

```properties
spring.datasource.username=
spring.datasource.password=
nasa.firms.map-key=
ia.api.url=
csharp.api.url=
```

### 4. Executar a aplicação

```bash
mvn spring-boot:run
```

Ou executar diretamente pela IDE.

---

## 📘 Swagger

Após subir a aplicação, acessar:

```text
http://localhost:8080/swagger-ui/index.html
```

Ou:

```text
http://localhost:8080/swagger-ui.html
```

---

## ❤️ Health Check

```text
http://localhost:8080/actuator/health
```

---

## 🧪 Exemplos de Teste

### Criar Bioma

```http
POST /api/biomas
Content-Type: application/json
```

```json
{
  "nome": "Amazônia",
  "descricao": "Maior bioma brasileiro",
  "areaKm2": 4200000,
  "nivelRiscoMedio": "ALTO",
  "statusMonitoramento": "ATIVO"
}
```

---

### Criar Região

```http
POST /api/regioes
Content-Type: application/json
```

```json
{
  "nome": "Região Norte de Monitoramento",
  "estado": "AM",
  "cidadeReferencia": "Manaus",
  "latitudeCentral": -3.1190,
  "longitudeCentral": -60.0217,
  "nivelRisco": "ALTO",
  "statusMonitoramento": "ATIVA",
  "biomaId": 1
}
```

---

### Ingestão NASA — Últimas 24h

```http
POST /api/ingestao/sync/24h
```

Retorno esperado:

```json
{
  "status": "SUCESSO",
  "fonte": "NASA FIRMS",
  "periodo": "24h",
  "totalRegistros": 10,
  "mensagem": "Consulta realizada com sucesso.",
  "amostraCsv": "latitude,longitude,..."
}
```

---

## 📊 Responsabilidade por Camada

| Camada     | Responsabilidade                   |
| ---------- | ---------------------------------- |
| Controller | Expor endpoints REST               |
| Service    | Regras de negócio                  |
| Repository | Comunicação com Oracle             |
| Mapper     | Conversão entre entidade e DTO     |
| DTO        | Contrato de entrada e saída        |
| Client     | Comunicação com APIs externas      |
| Config     | Configurações globais da aplicação |

---

## ✅ Status da API

| Recurso                | Status                |
| ---------------------- | --------------------- |
| CRUD Bioma             | Implementado          |
| CRUD Região            | Implementado          |
| CRUD Foco de Calor     | Implementado          |
| CRUD Alerta            | Implementado          |
| Integração NASA FIRMS  | Implementado          |
| Integração Weather API | Implementado          |
| Integração API IA      | Estruturado via Feign |
| Integração API C#      | Estruturado via Feign |
| Swagger                | Implementado          |
| Oracle                 | Implementado          |
| Actuator               | Implementado          |

---

## 📌 Repositório

```text
https://github.com/alanerochaa/argus-intelligence-api
```
