# DeliveryFood-API
> :construction: It's a Delivery Food work in progress API project made according on REST API :construction:

## Steps to Setup

**1. Clone the application**

  Personally i give preference to clone with SSH, considered more practical:
  git@github.com:Rene-Antunes/DeliveryFood-API.git

**2. Change local host, MySQL username and password as per your installation**
- Open src/test/resources/application-test.properties
- change spring.datasource.url, spring.datasource.username and spring.datasource.password as per your MySQL installation

**3. Create MySQL database**
- when you run the application the database will be created automatically for now 

**4. Build and run the application**
- Start the app with Boot Dashboard using Spring-tool-suite

**The app will start running at http://localhost:8080**

## WARNING
- If you not have lombok maybe the project will appear with errors,
to avoid this, make plugin download  on https://projectlombok.org/download
and instal using Spring> Add Starters clicking right button on your project

## Some app defines following CRUD APIs.

GET /restaurantes

GET /cozinhas

GET /estados

GET /cidades

-------------------------------------

POST /restaurantes

POST /cozinhas

POST /estados

POST /cidades

--------------------------------------

GET /restaurantes/{restauranteId}

GET /cozinhas/{cozinhaId}

GET /estados/{estadoId}

GET /cidades/{cidadeId}

-------------------------------------

PUT /restaurantes/{restauranteId}

PUT /cozinhas/{cozinhaId}

PUT /estados/{estadoId}

PUT /cidades/{cidadeId}

-------------------------------------

DELETE /restaurantes/{restauranteId}

DELETE /cozinhas/{cozinhaId}

DELETE /estados/{estadoId}

DELETE /cidades/{cidadeId}

