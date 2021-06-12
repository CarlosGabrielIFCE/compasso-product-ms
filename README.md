

# Compasso Product List

Esse projeto tem por finalidade criar um backend em que seja possível criar, buscar, editar, excluir e listar
produtos utilizando o framework Spring Boot Rest API.

## Instalação
  - Baixe o arquivo ZIP do repositório ou faça um clone deste em sua máquina.
  - Descompacte o arquivo em sua máquina e abra-o em sua IDE de preferência(para desenvolvimento, utilizei o Eclipse)
  - Crie um banco de dados de nome products em sua máquina (Postgres)

### Detalhes

A Aplicação foi montada utilizando o Spring Boot, com o Padrão MVC de organização de código.
Foi criada uma Entidade:
  - Produto - Entidade relacionada à um produto, com nome, descrição, e preço.
  
Foi criado o Repository para flexibilizar a criação dos métodos do CRUD da entidade.
Foi criada também uma interface de serviço, e um Service Implementation para lidar com as lógicas de negócio.
Por último, foi criado o Controller, com as Anotações necessárias para que seja possível realizar requisições REST.

Seguem abaixo as Dependências que foram utilizadas:

  - ``` Spring Web ```
  - ``` Spring Data JPA ``` 
  - ``` Spring Dev Tools ```
  - ``` Swagger ```
  - ``` Spring Security OAuth2 (Branch develop) ```  
  - ``` PostgreSQL ```

### Modo de Utilização

Para utilização do aplicativo, você pode fazer o download do repositório e rodar em sua máquina.
Foi feito também um deploy da aplicação para o Heroku, para que não seja necessário baixar o projeto,
dessa forma, é apenas necessário fazer as requsições utilizando o seu REST Service de preferência.

URL de requisição Local: ```http://localhost:9999/products```

URL de requisição de Produção: ```https://compass-products-backend.herokuapp.com/products```

Endpoints importantes da aplicação:
```
 - Products
 
	- GET ALL- /products
 
 	- GET BY ID - /products/{id}
	
	- POST - /products
			{
				"name": "string",
				"description": "string",
        			"price": "number"
			}
	
	- PUT - /products/{id}
			{
				"name": "string",
				"description": "string",
        			"price": "number"
			}
	
	- DELETE BY ID - /products/{id}
	
	- SEARCH BY FIELD  - /products/search?q=string&min_value=number&max_value=number
	
```

### Organização do Projeto

Para a organização da demanda, foi criado um quadro no Trello para melhor organizar o que deveria ser feito
no projeto.

Para acessar o quadro, você pode ir por esse [link](https://trello.com/b/80pJgPwG/product-ms)

O repositório contém as branchs relacionadas à cada card do Trello. Existem duas branchs principais:
  - ```master``` - Nessa branch, está presente todo o CRUD, juntamente com a Documentação do Swagger e Testes Unitários
  - ```develop``` - Nessa branch, foi integrado o Spring Security OAuth2 para garantir a segurança de requisições fazendo o uso
                    de token nas requisições.

### Demonstração

Foi integrado um módulo frontend para fazer a demonstração do funcionamento do backend, para utilizá-lo,
basta clicar nesse [link](https://compasso-products-frontend.herokuapp.com/)

Repositório do frontend: https://github.com/CarlosGabrielIFCE/compasso-product-ms-frontend
