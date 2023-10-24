<h1>DeliveryFood-API</h1> 
> :construction: API Rest feito em Java com Spring de Delivery de comida em construção :construction:

<h2 id="sobre" >Descrição do Projeto </h2>
Um sistema de delivery de comida, algumas features são: permite cadastrar restaurantes e gerenciar pedidos para delivery, permite cadastro de usuário, produtos, imagens, cadastrar responsáveis para gerenciamento de conta emissão de pedidos, envios de confirmação por e-mail, buscas simples e complexas e gerir relatórios de vendas.



<h2 id="features">✅ Features</h2>

- ☑️ Gerenciamento de usuários
- ☑️ Gerenciamento de produtos
- ☑️ Gerenciamento de fotos
- ☑️ Gerenciamento de Cidades
- ☑️ Gerenciamento de estados
- ☑️ Gerenciamento de grupos
- ☑️ Gerenciamento de endereço
- ☑️ Gerenciamento de permissões
- ☑️ Gerenciamento de formas de pagamento
- ☑️ Emissão de pedidos
- ☑️ Associação e desassociação de permissões a grupos de usuários
- ☑️ Mudança de status de pedido ex: criado, enviado, entregue.

<h3>🛑Veja mais na documentação completa do pejeto:</h3>

**Documentação com swagger/SpringFox3.0**


Assim que startar a aplicação pode-se acessar a documentação:

```
  http://localhost:8080/swagger-ui/index.html
```

<strong>Interface gerada pelo SpringFox:</strong>

![image](https://github.com/Rene-Antunes/delivery-API/assets/93138911/dcdb6ca6-5f1b-4b0f-bdeb-0daacbf0bc8c)


<h2 id="comoUsar">⚙️ Como Usar</h2>

<h2 id="requisitos">✅ Pré-requisitos e tecnologias </h2>

- [Java](https://www.java.com/pt-BR/download/manual.jsp)
- [Spring Boot](https://spring.io/projects/spring-boot)
- Maven
- IDE [Intellij](https://www.jetbrains.com/idea/download/?section=windows) ou [Eclipse](https://www.eclipse.org/downloads/) ⚠️(Recomendo usar Eclipse para fazer uso do SpringToolsSuit) [SpringToolsSuit](https://spring.io/tools)
- [MySQL](https://www.mysql.com/downloads/)
- [Postman](https://www.postman.com)
- [lombok](https://projectlombok.org/download) (Para instalar o Lombok basta apenas fazer o dowload abrir o arquivo e esperar ele escanear e selecionar a IDE que deseja usar.
  Não é necessário instalar a dependência no pom.xml pois já está no projeto
)

<h2 id="rodandoApi">🎲 Rodando a Api</h2>

<h3>✔️ Clone este repositório</h3>

```
git clone <https://github.com/Rene-Antunes/delivery-API/tree/main>
```


<h3>⚙️Configurando banco de dados</h3>
É necessário ter MySQL instalado em sua máquina, após instalação crie um banco de dados com nome que desejar, caso não queira criar, o projeto está configurado para criar um banco automáticamente com nome de <strong>deliveryfood</strong>, pode-se alterar o nome do banco indo em application.properties e alterando a propriedade:

``` 
  spring.datasource.url=jdbc:mysql://localhost:3306/NomeDoBando?createDatabaseIfNotExist=true&serverTimezone=UTC
```
Pode atualizar o usuário e senha no mesmo arquivo nas propriedades para os dados do seu banco:
``` 
spring.datasource.password=senha
spring.datasource.username=nomeRoot
```


<h3>Iniciar aplicação 🚀</h3>
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




  

