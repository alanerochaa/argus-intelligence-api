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

4. Acesse no navegador: