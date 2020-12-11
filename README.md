# brasil-prev
SPRING - RESTful API

* Carregamento sobre demanda / lazy loading na listagem de recursos da API;
* Validação da API com o padrão DTO e o Bean Validation
* Testes unitários da camada de acesso a dados com o JUnit
* Configuração excepções e handlers personalizados para diferentes problemas na API;
* Configuração de segurança com o Spring Security e JSON Web Token (JWT) 

Requerimentos:
- Maven
- JRE 8 ou superior

Instalação:
- Executar o comando Maven "mvn clean install" na raíz do projeto

Executar:
- Executar o comando Maven "mvn spring-boot:run" na raíz do projeto

Acesso a API da aplicação através da url
-  http://localhost:8080/


REST (Representational State Transfer)
Modelo arquitetural que define um conjunto de regras e propriedades baseadas no protocolo HTTP.

Regras / Princípios REST

    1. Identificação dos recursos: cada um dos recursos da aplicação deve possuir uma identificação única utilizando o conceito de URI:
        ◦ http://localhost:8080/users;
        ◦ http://localhost:8080/users/1;
        
Exemplo:

Método             URI                             Descrição
GET                http://localhost:8080/users     Buscar todos os usuários.
POST               http://localhost:8080/users     Criar um novo usuário.
GET                http://localhost:8080/users/1   Busca os dados de um usuário específico.
 
