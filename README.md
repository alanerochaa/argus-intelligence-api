## 📘 ARGUS Intelligence API

ARGUS Intelligence API é um microserviço desenvolvido com o objetivo de monitorar dados ambientais e fornecer recursos analíticos utilizando tecnologias modernas como Java, Spring Boot e Inteligência Artificial. Este projeto viabiliza a centralização e integração de dados de ambientes, alertas e operações com conectividade em tempo real.

---

### 🌟 **Funcionalidades Principais**

1. **Ingestão de Dados Ambientais**:
    - Conexão com fontes externas como NASA FIRMS para coleta de dados ambientais e geográficos.
    - Persistência em banco de dados Oracle.

2. **Análise de Risco e Geração de Alertas**:
    - Boosted por IA, o serviço permite a análise de eventos para identificar padrões críticos.

3. **Mensageria e Eventos Assíncronos**:
    - RabbitMQ para fila de mensagens e CloudAMQP suporte no cloud.

4. **Integrações**:
    - Operação conectada ao sistema C#/.NET e um aplicativo mobile de brigadistas.

---

### 🛠 **Tecnologias Utilizadas**

- **Backend**:
    - Java 17, Spring Boot
    - JPA com suporte ao Oracle

- **Mensageria**:
    - RabbitMQ + CloudAMQP

- **Inteligência e Analytics**:
    - IA customizada para análise ambiental

- **Frontend**:
    - Templates Thymeleaf para views HTML5
    - CSS para estilização (diretório `static/css/`)

- **Ambiente e Integração**:
    - NASA FIRMS, OpenAPI
    - Microsoft Azure para conectividade

---

### 📂 **Estrutura do Projeto**

1. **Pacote `br.com.fiap.argus.service`**:
    - Contém os serviços fundamentais do sistema, como:
        - IAService, SpringAiService para integração com IA
        - IngestaoService para ingestão de dados.

2. **Recursos Estáticos**:
    - Arquivos CSS e assets como imagens e logos hospedados no diretório `src/main/resources/static`.

3. **Frontend / Templates**:
    - Visão gerada usando Thymeleaf (`src/main/resources/templates/home.html`).

---

### 🎯 **Como Executar**

1. Clone o repositório:
   ```bash
   git clone <URL-DO-REPOSITÓRIO>
   cd argus-intelligence-api
   ```

2. Configure as propriedades:
    - Certifique-se de editar o arquivo de configurações (`application.properties` ou `application.yaml`), inserindo valores corretos para o banco de dados Oracle, mensageria RabbitMQ e APIs externas.

3. Execute o projeto:
   ```bash
   mvn spring-boot:run
   ```

4. # 🌍 **ARGUS Intelligence API**

<p align="center">
  <img src="src/main/resources/static/images/argus-logo.png" alt="ARGUS Logo" width="300">
</p>

**ARGUS Intelligence API** é um microserviço robusto projetado para monitoramento ambiental e análise avançada de risco em incêndios florestais. Através de tecnologias modernas como **Spring Boot**, **RabbitMQ**, **Oracle DB** e **IA preditiva**, ele entrega inteligência em tempo real e suporte estratégico para brigadistas.

> 💡 **Missão**: Aproveitar Inteligência Artificial e tecnologias escaláveis para prever e combater incêndios florestais enquanto protege ecossistemas e comunidades ao redor do mundo.

---

## 🎨 **Visualização Rápida**

[![Java](https://img.shields.io/badge/Java-17-red)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-green)](https://spring.io/)
[![RabbitMQ](https://img.shields.io/badge/RabbitMQ-CloudAMQP-orange)](https://www.rabbitmq.com/)
[![Oracle](https://img.shields.io/badge/Oracle-Database-blue)](https://www.oracle.com/database/)
[![Swagger](https://img.shields.io/badge/Swagger-UI-brightgreen)](https://swagger.io/)
[![Azure Deployment](https://img.shields.io/badge/Azure-Deployment-blue)](https://azure.microsoft.com/)

---

## 📜 **Sumário**

1. [✨ Resumo Executivo](#-resumo-executivo)
2. [🎓 Problema e Solução](#-problema-e-solução)
3. [🎯 Objetivos e Benefícios](#-objetivos-e-benefícios)
4. [🏗 Arquitetura](#-arquitetura)
    - [📂 Diagrama Geral](#-diagrama-geral)
    - [🔁 Comunicação entre Serviços](#-comunicação-entre-serviços)
5. [🛠 Tecnologias](#-tecnologias)
6. [⚙ Funcionalidades](#-funcionalidades)
7. [🌐 Endpoints da API](#-endpoints-da-api)
8. [🛡 Segurança](#-segurança)
9. [🚀 Execução Local](#-execução-local)
10. [☁ Deploy (Azure)](#-deploy-azure)
11. [📈 Prints e Resultados](#-prints-e-resultados)
12. [🤝 Equipe](#-equipe)

---

## ✨ **Resumo Executivo**

**ARGUS Intelligence API** é parte de um **ecossistema baseado em microserviços**, projetado para monitorar grandes volumes de dados ambientais e prever eventos catastróficos como queimadas florestais. Ele utiliza algoritmos de IA para análise de riscos, **mensageria com RabbitMQ** para comunicação confiável entre diferentes partes do sistema e persistência robusta com **Oracle Database**.

---

## 🎓 **Problema e Solução**

### 🌍 **Problema**
🔥 Incêndios florestais têm se tornado uma ameaça crescente devido às mudanças climáticas, demandando **tecnologia precisa e rápida** para monitoramento e decisão.

### 💡 **Solução**
Construir um sistema **automatizado, inteligente e integrado** para monitoramento em tempo real, análise de padrões de risco e suporte estratégico a brigadistas.

---

## 🎯 **Objetivos e Benefícios**

#### ⚡ **Objetivos**
- Monitorar regiões sensíveis e prever incêndios florestais.
- Criar inteligência analítica acessível para as equipes no campo.
- Facilitar a comunicação e integração entre serviços e plataformas móveis.

#### ✅ **Principais Benefícios**
✔️ **Decisão ágil:** Alertas preventivos e automatizados.  
✔️ **Escalabilidade:** Baseado em microserviços.  
✔️ **Segurança:** Dados protegidos com autenticação JWT.

---

## 🏗 **Arquitetura**

### 📂 Diagrama Geral
```mermaid
graph TD
    A[📡 APIs Externas (ex: NASA, IA)] -->|Coleta| B[[🌍 ARGUS Intelligence API]]
    B -->|Mensageria| C[[📡 RabbitMQ CloudAMQP]]
    C -->|Envio de Alertas| D(📲 Aplicativo Mobile)
    B -->|Persistência| E((💾 Banco Oracle))
    D -->|Consulta Via API REST| B
```

### 🔁 Comunicação entre Serviços
A arquitetura de comunicação é baseada em **mensageria** (RabbitMQ), garantindo integração em tempo real, decoupling entre serviços e escalabilidade.

---

## 🛠 **Tecnologias**

| **Ferramenta**       | **Descrição**                          | **Onde É Usada**                  |
|-----------------------|----------------------------------------|------------------------------------|
| 💻 **Java 17**        | Linguagem Backend                     | Camada principal do sistema       |
| 🎨 **Spring Boot 3**  | Framework MVC para APIs REST          | Backend robusto e moderno         |
| 📨 **RabbitMQ**       | Agente Message Broker                 | Comunicação assíncrona            |
| 💾 **Oracle DB**      | Banco de Dados Relacional             | Persistência crítica              |
| 📖 **Swagger UI**     | Documentação de API Interativa        | Interface de teste de APIs REST   |
| ☁ **Azure**          | Plataforma em Nuvem                   | Deploy seguro e escalável         |

---

## ⚙ **Funcionalidades**

1. **Coleta de Dados Geográficos 🛰**:
    - Integração com APIs externas como **NASA FIRMS**.
    - Dados ambientais registrados em tempo real.

2. **Análise de Riscos com IA 🤖**:
    - Detecção de padrões críticos relevantes (fogo/imediato).

3. **Geração de Alertas 🔔**:
    - Alertas automáticos baseados em zonas críticas.

4. **Mensageria Assíncrona 📬**:
    - RabbitMQ para garantir a entrega de eventos.

5. **Relatórios e Visualização 📊**:
    - Painéis de consulta e relatórios para visualização dinâmica.

---

## 🌐 **Endpoints da API**

| **Método** | **Endpoint**              | **Descrição**                           |
|------------|---------------------------|-----------------------------------------|
| `GET`      | `/api/v1/regions`         | Retorna dados das regiões monitoradas   |
| `POST`     | `/api/v1/risks`           | Inicia análise de risco                 |
| `GET`      | `/api/v1/alerts`          | Lista alertas gerados                   |
| `POST`     | `/api/v1/ingest`          | Ingestão de novos dados ambientais      |

---

## 🛡 **Segurança**

- **Autenticação JWT**: Conteúdos protegidos por tokens de sessão temporária.
- **Configurações Sensíveis**: Variáveis em propriedades externas como:
   ```properties
   spring.datasource.url=${DB_URL}
   spring.security.jwt.secret=${JWT_SECRET}
   rabbitmq.host=${RABBIT_HOST}
   ```

---

## 🚀 **Execução Local**

1. Clone o repositório:
   ```bash
   git clone https://github.com/alanerochaa/argus-intelligence-api
   cd argus-intelligence-api
   ```

2. Configure o Banco de Dados e Mensageria no arquivo **application.properties**.

3. Inicie o servidor:
   ```bash
   mvn spring-boot:run
   ```

4. Acesse: `http://localhost:8080`

---

## ☁ **Deploy (Azure)**

1. Configure no Azure:
   ```bash
   az webapp up --name argus-intelligence
   ```

2. Atualize variáveis no painel com segurança.

---

## 📈 **Prints e Resultados**

> **Imagens disponíveis aqui**: Painel, log de alertas e Swagger no browser.

---

## 🤝 **Equipe**

| Nome            | Função                    |
|-----------------|--------------------------|
| **Alan Rocha**  | Backend Java             |
| **Duda Araújo** | Inteligência Artificial  |
| **Anna Bonfim** | Backend (.NET)           |

---

**Licença:** Uso acadêmico.# 🌍 **ARGUS Intelligence API**

<p align="center">
  <img src="src/main/resources/static/images/argus-logo.png" alt="ARGUS Logo" width="300">
</p>

**ARGUS Intelligence API** é um microserviço robusto projetado para monitoramento ambiental e análise avançada de risco em incêndios florestais. Através de tecnologias modernas como **Spring Boot**, **RabbitMQ**, **Oracle DB** e **IA preditiva**, ele entrega inteligência em tempo real e suporte estratégico para brigadistas.

> 💡 **Missão**: Aproveitar Inteligência Artificial e tecnologias escaláveis para prever e combater incêndios florestais enquanto protege ecossistemas e comunidades ao redor do mundo.

---

## 🎨 **Visualização Rápida**

[![Java](https://img.shields.io/badge/Java-17-red)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-green)](https://spring.io/)
[![RabbitMQ](https://img.shields.io/badge/RabbitMQ-CloudAMQP-orange)](https://www.rabbitmq.com/)
[![Oracle](https://img.shields.io/badge/Oracle-Database-blue)](https://www.oracle.com/database/)
[![Swagger](https://img.shields.io/badge/Swagger-UI-brightgreen)](https://swagger.io/)
[![Azure Deployment](https://img.shields.io/badge/Azure-Deployment-blue)](https://azure.microsoft.com/)

---

## 📜 **Sumário**

1. [✨ Resumo Executivo](#-resumo-executivo)
2. [🎓 Problema e Solução](#-problema-e-solução)
3. [🎯 Objetivos e Benefícios](#-objetivos-e-benefícios)
4. [🏗 Arquitetura](#-arquitetura)
    - [📂 Diagrama Geral](#-diagrama-geral)
    - [🔁 Comunicação entre Serviços](#-comunicação-entre-serviços)
5. [🛠 Tecnologias](#-tecnologias)
6. [⚙ Funcionalidades](#-funcionalidades)
7. [🌐 Endpoints da API](#-endpoints-da-api)
8. [🛡 Segurança](#-segurança)
9. [🚀 Execução Local](#-execução-local)
10. [☁ Deploy (Azure)](#-deploy-azure)
11. [📈 Prints e Resultados](#-prints-e-resultados)
12. [🤝 Equipe](#-equipe)

---

## ✨ **Resumo Executivo**

**ARGUS Intelligence API** é parte de um **ecossistema baseado em microserviços**, projetado para monitorar grandes volumes de dados ambientais e prever eventos catastróficos como queimadas florestais. Ele utiliza algoritmos de IA para análise de riscos, **mensageria com RabbitMQ** para comunicação confiável entre diferentes partes do sistema e persistência robusta com **Oracle Database**.

---

## 🎓 **Problema e Solução**

### 🌍 **Problema**
🔥 Incêndios florestais têm se tornado uma ameaça crescente devido às mudanças climáticas, demandando **tecnologia precisa e rápida** para monitoramento e decisão.

### 💡 **Solução**
Construir um sistema **automatizado, inteligente e integrado** para monitoramento em tempo real, análise de padrões de risco e suporte estratégico a brigadistas.

---

## 🎯 **Objetivos e Benefícios**

#### ⚡ **Objetivos**
- Monitorar regiões sensíveis e prever incêndios florestais.
- Criar inteligência analítica acessível para as equipes no campo.
- Facilitar a comunicação e integração entre serviços e plataformas móveis.

#### ✅ **Principais Benefícios**
✔️ **Decisão ágil:** Alertas preventivos e automatizados.  
✔️ **Escalabilidade:** Baseado em microserviços.  
✔️ **Segurança:** Dados protegidos com autenticação JWT.

---

## 🏗 **Arquitetura**

### 📂 Diagrama Geral
```mermaid
graph TD
    A[📡 APIs Externas (ex: NASA, IA)] -->|Coleta| B[[🌍 ARGUS Intelligence API]]
    B -->|Mensageria| C[[📡 RabbitMQ CloudAMQP]]
    C -->|Envio de Alertas| D(📲 Aplicativo Mobile)
    B -->|Persistência| E((💾 Banco Oracle))
    D -->|Consulta Via API REST| B
```

### 🔁 Comunicação entre Serviços
A arquitetura de comunicação é baseada em **mensageria** (RabbitMQ), garantindo integração em tempo real, decoupling entre serviços e escalabilidade.

---

## 🛠 **Tecnologias**

| **Ferramenta**       | **Descrição**                          | **Onde É Usada**                  |
|-----------------------|----------------------------------------|------------------------------------|
| 💻 **Java 17**        | Linguagem Backend                     | Camada principal do sistema       |
| 🎨 **Spring Boot 3**  | Framework MVC para APIs REST          | Backend robusto e moderno         |
| 📨 **RabbitMQ**       | Agente Message Broker                 | Comunicação assíncrona            |
| 💾 **Oracle DB**      | Banco de Dados Relacional             | Persistência crítica              |
| 📖 **Swagger UI**     | Documentação de API Interativa        | Interface de teste de APIs REST   |
| ☁ **Azure**          | Plataforma em Nuvem                   | Deploy seguro e escalável         |

---

## ⚙ **Funcionalidades**

1. **Coleta de Dados Geográficos 🛰**:
    - Integração com APIs externas como **NASA FIRMS**.
    - Dados ambientais registrados em tempo real.

2. **Análise de Riscos com IA 🤖**:
    - Detecção de padrões críticos relevantes (fogo/imediato).

3. **Geração de Alertas 🔔**:
    - Alertas automáticos baseados em zonas críticas.

4. **Mensageria Assíncrona 📬**:
    - RabbitMQ para garantir a entrega de eventos.

5. **Relatórios e Visualização 📊**:
    - Painéis de consulta e relatórios para visualização dinâmica.

---

## 🌐 **Endpoints da API**

| **Método** | **Endpoint**              | **Descrição**                           |
|------------|---------------------------|-----------------------------------------|
| `GET`      | `/api/v1/regions`         | Retorna dados das regiões monitoradas   |
| `POST`     | `/api/v1/risks`           | Inicia análise de risco                 |
| `GET`      | `/api/v1/alerts`          | Lista alertas gerados                   |
| `POST`     | `/api/v1/ingest`          | Ingestão de novos dados ambientais      |

---

## 🛡 **Segurança**

- **Autenticação JWT**: Conteúdos protegidos por tokens de sessão temporária.
- **Configurações Sensíveis**: Variáveis em propriedades externas como:
   ```properties
   spring.datasource.url=${DB_URL}
   spring.security.jwt.secret=${JWT_SECRET}
   rabbitmq.host=${RABBIT_HOST}
   ```

---

## 🚀 **Execução Local**

1. Clone o repositório:
   ```bash
   git clone https://github.com/alanerochaa/argus-intelligence-api
   cd argus-intelligence-api
   ```

2. Configure o Banco de Dados e Mensageria no arquivo **application.properties**.

3. Inicie o servidor:
   ```bash
   mvn spring-boot:run
   ```

4. Acesse: `http://localhost:8080`

---

## ☁ **Deploy (Azure)**

1. Configure no Azure:
   ```bash
   az webapp up --name argus-intelligence
   ```

2. Atualize variáveis no painel com segurança.

---

## 📈 **Prints e Resultados**

> **Imagens disponíveis aqui**: Painel, log de alertas e Swagger no browser.

---

## 🤝 **Equipe**

| Nome            | Função                    |
|-----------------|--------------------------|
| **Alan Rocha**  | Backend Java             |
| **Duda Araújo** | Inteligência Artificial  |
| **Anna Bonfim** | Backend (.NET)           |

---

**Licença:** Uso acadêmico.Acesse no navegador: