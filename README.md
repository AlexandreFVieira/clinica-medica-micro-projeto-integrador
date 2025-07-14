
![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1.0-brightgreen) ![Maven](https://img.shields.io/badge/Maven-4.0.0-red) ![Docker](https://img.shields.io/badge/Docker-blue) ![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)


# 🏥 Clínica Médica - Sistema de Gestão Integrado

Seja bem-vindo(a) ao Sistema de Gestão Integrado para Clínicas Médicas, um projeto robusto e escalável construído com foco em modularidade e arquitetura de microsserviços, usando o melhor do ecossistema Java com Spring Boot.




## Demonstração

[Link para o video de Demonstração](https://drive.google.com/file/d/18PP49sszLr_AwRx8bkbkXHApo61Q33nQ/view)


## Documentação

[Link para a Documentação do projeto MultMed!](https://drive.google.com/file/d/1L0HWkiBx9xSqnx_dPLGNVWAMYxWSdCi7/view)


## ⚙️ Funcionalidades

#### O sistema é dividido em módulos independentes, cada um responsável por um aspecto específico do negócio:

#### Módulo Administrativo (clinica-medica-administrativo)
- Cadastro e gerenciamento de Funcionários
- Gestão de Convênios
- Cadastro de Especialidades Médicas
- Controle de Perfis de Acesso e permissões

#### Módulo de Agendamento (clinica-medica-agendamento)
- Gerenciamento de Prontuários Eletrônicos

#### Módulo de Atendimento (clinica-medica-atendimento)
- Cadastro e gestão de Pacientes
- Registro e acompanhamento de Consultas

#### Módulo Comum (clinica-medica-comum)
- Entidades, DTOs, repositórios e serviços compartilhados
- Atua como biblioteca de domínio para os outros microsserviços



## 📂 Estrutura de Pastas

- clinica-medica-micro-projeto-integrador/
- ├── clinica-medica-administrativo/
- ├── clinica-medica-atendimento/
- ├── clinica-medica-agendamento/
- ├── clinica-medica-comum/
- ├── containers/
- │   └── docker-compose.yml
- └── README.md

## 🚀 Rodando o Projeto Localmente

Para configurar e executar o ambiente de desenvolvimento na sua máquina, siga estes passos.

#### Pré-requisitos
Antes de começar, garanta que você tenha as seguintes ferramentas instaladas:

- Git: Para clonar o repositório.
- JDK 17: O projeto utiliza Java 17.
- Apache Maven: Para gerenciar as dependências e o build do projeto.
- Docker e Docker Compose: Para rodar o banco de dados de forma isolada e sem complicações.

## Passo a Passo da Instalação
#### Clone o Repositório
Primeiro, clone o projeto para a sua máquina local usando o Git.

```bash
git clone https://github.com/alexandrefvieira/clinica-medica-micro-projeto-integrador.git
cd clinica-medica-micro-projeto-integrador
```
#### Construa o Projeto com Maven
Este comando é crucial. Ele vai baixar todas as dependências e construir os módulos, principalmente o clinica-medica-comum que é necessário para os outros rodarem.

```bash
mvn clean install
```

#### Inicie o Banco de Dados com Docker
Com o Docker em execução na sua máquina, navegue até a pasta containers e suba o serviço do MySQL.

```bash
cd containers
docker-compose up -d
```
O -d faz com que ele rode em background. O banco de dados estará disponível em localhost:4040.

#### Execute os Microsserviços
Agora, vamos iniciar cada uma das aplicações. Importante: cada comando deve ser executado em um novo terminal.

- Terminal 1: Módulo Administrativo
```bash
# Na raiz do projeto
cd clinica-medica-administrativo
mvn spring-boot:run
```

- Terminal 2: Módulo de Atendimento
```bash
# Na raiz do projeto
cd clinica-medica-atendimento
mvn spring-boot:run
```

- Terminal 3: Módulo de Agendamento
```bash
# Na raiz do projeto
cd clinica-medica-agendamento
mvn spring-boot:run
```
#### Ao final desses passos, todo o ambiente estará configurado e os microsserviços estarão em execução, prontos para receber requisições.
    
## Licença
Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.
[MIT](https://choosealicense.com/licenses/mit/)


## ✉️ Contato

#### Se tiver dúvidas ou sugestões, entre em contato:
- E-mail: eliascoelhogf@gmail.com
- E-mail: alexandre.vieira@aluno.imepac.edu.br
- LinkedIn: https://www.linkedin.com/in/elias-fernandes-580093246/
- GitHub: https://github.com/eliasfernandess
- GitHub: https://github.com/AlexandreFVieira


## Autores

- [@eliasfernandess](https://github.com/eliasfernandess)
- [@AlexandreFVieira](https://github.com/AlexandreFVieira)

