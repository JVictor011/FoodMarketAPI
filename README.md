# FoodMarketAPI

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) 
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) 
![MongoDB](https://img.shields.io/badge/mongodb-%2347A248.svg?style=for-the-badge&logo=mongodb&logoColor=white) 
![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazonaws&logoColor=white)

API de gerenciamento de categorias e produtos de um mercado, desenvolvida com **Java**, **Spring Boot**, **MongoDB** e **AWS SNS** para envio de notificações.

## Funcionalidades

- **Cadastro de Categorias e Produtos**: Permite cadastrar, atualizar, excluir e listar categorias e produtos.
- **Integração com AWS SNS**: Envio de mensagens para tópicos SNS quando novos produtos são cadastrados ou atualizados.
- **Persistência com MongoDB**: Dados armazenados de forma eficiente usando o MongoDB.
- **Endpoints RESTful** para interação com as entidades.

## Tecnologias Utilizadas

- **Java 17**: Linguagem de programação.
- **Spring Boot**: Framework utilizado para construção da API.
- **MongoDB**: Banco de dados NoSQL para persistência de dados.
- **AWS SNS**: Serviço da AWS para gerenciamento de notificações e eventos.
- **JUnit**: Para execução de testes automatizados.

## Endpoints

### /categories

- **GET /categories**: Retorna todas as categorias.
- **POST /categories**: Cria uma nova categoria.
- **GET /categories/{id}**: Retorna a categoria pelo ID.
- **PUT /categories/{id}**: Atualiza uma categoria.
- **DELETE /categories/{id}**: Exclui uma categoria.

### /products

- **GET /products**: Retorna todos os produtos.
- **POST /products**: Cria um novo produto.
- **GET /products/{id}**: Retorna o produto pelo ID.
- **PUT /products/{id}**: Atualiza um produto.
- **DELETE /products/{id}**: Exclui um produto.

## Configuração

1. Clone o repositório:
   ```bash
   git clone https://github.com/jvictor011/FoodMarketAPI.git
   ```
2. Certifique-se de ter o Java 17 instalado.
3. Configure o arquivo **application.properties** com suas credenciais da AWS.
   ```bash
   spring.application.name=FoodMarketAPI

   aws.accessKeyId=${ACCESS_KEY_ID}
   aws.secretKey=${SECRET_KEY}
   aws.region=${AWS_REGION}
   aws.sns.topic.catalog.arn=${SNS_CATALOG_ARN}
   ```
 4. Execute a aplicação com o comando Maven:
    ```bash
    mvn spring-boot:run
    ```
 ## Exemplos de Requisições
 ### Criar Categoria
 #### POST /categories
 ```bash
 {
   "title": "Bebidas",
   "description": "Categoria para bebidas variadas."
 }
 ```
### Criar Produto
#### POST /products
```bash
 {
   "title": "Coca-Cola",
   "description": "Refrigerante de cola",
   "price": 5.99,
   "categoryId": "id_da_categoria"
 }
 ```

## Licença

Este projeto está licenciado sob a **Licença MIT** - veja o arquivo [LICENSE](https://github.com/JVictor011/FoodMarketAPI/blob/main/LICENSE) para mais detalhes.

### Resumo da Licença MIT

A Licença MIT permite que qualquer pessoa faça o que quiser com o código, incluindo usar, copiar, modificar, mesclar, publicar, distribuir, sublicenciar e/ou vender cópias do Software, desde que a seguinte mensagem de copyright e a licença sejam incluídas em todas as cópias ou partes substanciais do Software.

O software é fornecido "no estado em que se encontra", sem qualquer garantia de qualquer tipo, expressa ou implícita, incluindo, mas não se limitando a garantias de comercialização, adequação a um propósito específico e não infração. Em nenhum caso os autores ou detentores do copyright serão responsáveis por qualquer reclamação, dano ou outra responsabilidade, seja em uma ação de contrato, ato ilícito ou de outra forma, decorrente de, ou em conexão com, o software ou o uso ou outros negócios no software.
