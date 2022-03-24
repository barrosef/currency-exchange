# Challenge
## Índice
1. [Propósito](#proposito)
2. [Features](#features)
3. [Motivação](#motivação)
4. [Como utilizar](#como-utilizar)
5. [Arquitetura](#arquitetura)
6. [Pendências](#pendencias)

##Propósito
1. Participar do desafio de construção de uma api para conversão entre moedas;
2. Me divertir com as tecnologias e seu ecosistema;
3. Gerar um legado de conhecimento para utilizarno curto e médio prao;

## Features
Ao utilizar esta API, você consegue converter valores de moedas e visualizar as suas conversões juntamente com a taxa de conversão utilizada pela plataforma.
A visualização das conversões está restrita ao seu usuário, sendo possível somente ao usuário root realizar visualizar conversões de outros usuários, ao tempo em que este usuário (root) é vedado em realizar conversões.

## Motivação
Desafiar minha capacidade de aprendizado com tecnologias que ainda não tenho vivência;
Aprender num curto espaço de tempo algo que normalmente levaria semanas;
Networking

## Como utilizar
### Build a imagem docker
    docker build -f src/main/docker/Dockerfile.jvm -t quarkus/currency-exchange-jvm

### Execute o container
    docker run -i --rm -p 8080:8080 quarkus/currency-exchange-jvm

### Acesse a documentação da API
Clique [aqui](http://localhost:8080/swagger-ui) ou cole este link no browser de sua preferência http://localhost:8080/swagger-ui

### Acesso aos endpoints
É necessário informar no header o parâmetro *user_login* e considerar que a api, inicialmente, realiza a carga de dois usuários:
1. root - usuário pode realizar leitura sem restrições porém sem permissão para realização de conversões de moedas.
2. rate - restrito a leitura somente dos seus próprios dados e com acesso às operações de conversão.

## Arquitetura
A base da arquitetura é o framerwork quarkus, com uso de camadas Controler, Services e repository.
Para a lógica de negócio utilizamos o padrão builder visando facilitar a compreensão das regras, sua 
implementação e os testes.

## Pendências
1. CI/CD estou superando um poblema que ocorre no heroku ao buildar com gradle
2. Produzir endpoint para cadastro de usuário e geração de um api-token substituindo assim o acesso atual via login
3. Aumentar a cobertura de testes
4. Javadoc
5. Corrigir anchorlinks deste documento