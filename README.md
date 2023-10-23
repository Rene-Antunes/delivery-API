<h1>DeliveryFood-API</h1> 
> :construction: API Rest feito em Java com Spring de Delivery de comida em construção :construction:

<h2 id="sobre" >Descrição do Projeto </h2>
Um sistema de delivery de comida algumas features são: permite cadastrar restaurantes e gerenciar pedidos para delivery. É permitido cadastro de usuário, produtos, imagens, cadastrar responsáveis para gerenciamento de conta emissão de pedidos, envios de confirmação, buscas simples e complexas e gerir relatórios.



<h2 id="features">✅ Features</h2>

- [x] CRUD de usuário
- [x] CRUD  de produtos
- [x] CRUD  de fotos
- [x] CRUD  de Cidades
- [x] CRUD  de estados
- [x] CRUD  de grupos
- [x] CRUD  de endereço
- [x] CRUD  de permissões
- [x] CRUD  de formas de pagamento
- [x] Emissão de pedidos
- [x] Associação  e desassociação de permissões a grupos
- [x] Mudança de  status de pedido ex: criado, enviado, entregue.


h2 id="comoUsar">⚙️ Como Usar</h2>

<h2 id="requisitos">✅ Pré-requisitos e tecnologias </h2>

- [Java](https://www.java.com/pt-BR/download/manual.jsp)
- [Spring Boot](https://spring.io/projects/spring-boot)
- Maven
- IDE [Intellij](https://www.jetbrains.com/idea/download/?section=windows) ou [Eclipse](https://www.eclipse.org/downloads/)
- [MySQL](https://www.mysql.com/downloads/)
- [Postman](https://www.postman.com)
- [lombok](https://projectlombok.org/download)


<h2 id="rodandoApi">🎲 Rodando a Api</h2>

<h3> Clone este repositório</h3>

```
git clone <https://github.com/Rene-Antunes/delivery-API/tree/main>
```


<h3>Configurando banco de dados</h3>
É necessário ter MySQL instalado em sua máquina, após instalação crie um banco de dados com nome <strong>deliveryfood</strong>. Pode atualizar o usuário e senha no arquivo <strong>application.properties</strong> para os dados do seu banco de dados.

<h3>Iniciar aplicação</h3>
Iniciar por meio da IDE iniciando a classe DeliveyFoodApiAplication.java.

Pelo terminal:
```
java -jar target/deliveyfood-api.jar
```
```
spring.datasource.username=root
spring.datasource.password=12345678
```
ou utilizando o maven

```
mvn spring-boot:run
```

**A aplicação vai iniciar na porta http://localhost:8080**

**Documentação com swagger/SpringFox3.0**

```
  http://localhost:8080/swagger-ui/index.html
```
![image](https://github.com/Rene-Antunes/delivery-API/assets/93138911/dcdb6ca6-5f1b-4b0f-bdeb-0daacbf0bc8c)


  

