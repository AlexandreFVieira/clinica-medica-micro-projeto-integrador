🏥 Clínica Médica - Sistema de Gestão Integrado


Seja bem-vindo(a) ao Sistema de Gestão Integrado para Clínicas Médicas, um projeto robusto e escalável construído com foco em modularidade e arquitetura de microsserviços, usando o melhor do ecossistema Java com Spring Boot.

✨ Funcionalidades Principais
O sistema é dividido em módulos independentes, cada um responsável por um aspecto específico do negócio:

🧑‍💼 Módulo Administrativo (clinica-medica-administrativo)
Cadastro e gerenciamento de Funcionários

Gestão de Convênios

Cadastro de Especialidades Médicas

Controle de Perfis de Acesso e permissões

👩‍⚕️ Módulo de Atendimento (clinica-medica-atendimento)
Cadastro e gestão de Pacientes

Registro e acompanhamento de Consultas

📅 Módulo de Agendamento (clinica-medica-agendamento)
Gerenciamento de Prontuários Eletrônicos

📦 Módulo Comum (clinica-medica-comum)
Entidades, DTOs, repositórios e serviços compartilhados

Atua como biblioteca de domínio para os outros microsserviços

🏗️ Arquitetura do Projeto
Este projeto segue o padrão de Microsserviços, onde cada módulo é uma aplicação Spring Boot isolada:

Desenvolvimento e deploy independentes

Escalabilidade modular

Alta coesão e baixo acoplamento

O módulo comum garante consistência e reutilização de código

🛠️ Tecnologias Utilizadas
Categoria	Ferramentas
Backend	Java 17, Spring Boot 3.1.0, Spring Data JPA, Hibernate
Banco de Dados	MySQL 8.0 (via Docker)
Documentação	Springdoc OpenAPI + Swagger UI
Build	Apache Maven 4.0.0
Testes	JUnit 5, Mockito
Utilitários	Lombok, ModelMapper
Containerização	Docker, Docker Compose

🚀 Como Executar o Projeto
✅ Pré-requisitos
Java 17+
Apache Maven
Docker + Docker Compose
Git

🔧 Passo a Passo
Clone o repositório

bash
Copy
Edit
git clone https://github.com/alexandrefvieira/clinica-medica-micro-projeto-integrador.git
cd clinica-medica-micro-projeto-integrador
Build do projeto

bash
Copy
Edit
mvn clean install
Suba o MySQL com Docker

bash
Copy
Edit
cd containers
docker-compose up -d
Execute os Microsserviços
Em terminais separados, execute os módulos:

Administrativo:

bash
Copy
Edit
cd clinica-medica-administrativo
mvn spring-boot:run
Atendimento:

bash
Copy
Edit
cd ../clinica-medica-atendimento
mvn spring-boot:run
Agendamento:

bash
Copy
Edit
cd ../clinica-medica-agendamento
mvn spring-boot:run
📚 Documentação Interativa (Swagger)
Após rodar os serviços, acesse as interfaces do Swagger para testar as APIs:

Módulo	URL
Administrativo	localhost:8080/swagger-ui
Atendimento	localhost:8081/swagger-ui
Agendamento	localhost:8082/swagger-ui

📂 Estrutura de Pastas
Copy
Edit
clinica-medica-micro-projeto-integrador/
├── clinica-medica-administrativo/
├── clinica-medica-atendimento/
├── clinica-medica-agendamento/
├── clinica-medica-comum/
├── containers/
│   └── docker-compose.yml
└── README.md
🙌 Contribuição
Contribuições são muito bem-vindas! Caso queira colaborar:

Faça um fork.

Crie uma branch (feature/minha-nova-feature).

Commit suas mudanças.

Faça um push e abra um Pull Request.
