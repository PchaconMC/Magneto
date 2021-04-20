# Magneto Reclutamiento de Mutantes

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men. Este proyecto detecta si un
humano es mutante basándose en su secuencia de ADN
___________________________________________________________________

# Pre-requisitos

- VPS con sistema operativo Ubuntu 20.04
- Instalar **[*Docker*](https://docs.docker.com/get-docker/)**
- Instalar **[*PostgreSql - Ubuntu 20.04*](https://www.digitalocean.com/community/tutorials/how-to-install-and-use-postgresql-on-ubuntu-20-04-es)**

Se deben habilitar los puertos (8090) para el Api Gateway y (8761) Para visializar las instancias de los microservicios activas, este ultimo es opcional.

# Instalación

Se deben generar las imagenes a partir de los docker file de cada proyecto, existen tres proyectos de los cuales debemos generar las imagenes.
    -**Magneto/magneto-api-gateway/Dockerfile**
    -**Magneto/magneto-eureka/Dockerfile**
    -**Magneto/magneto-mutant/Dockerfile**

A continuación se detalla el procedimiento:
-1. ----------------Magneto Network --------------------
--Creamos la red
docker network create magnetonetwork
2. ----------------------------  Magneto Eureka----------------------------
--Nos ubicamos en el raiz del proyecto
CD E:\Test-MercadoLibre\back-end\magneto-eureka
--Creamos la imagen docker
docker build -f Dockerfile -t magneto-eureka:v1 .
--Levantamos el contenedor
docker run -p 8761:8761 -i -t --name magneto-eureka --network magnetonetwork magneto-eureka:v1
docker save -o E:\Test-MercadoLibre\images\magneto-eureka.tar magneto-eureka:v1
2. ----------------------------  Magneto API Gateway ----------------------------
--Nos ubicamos en el raiz del proyecto
CD E:\Test-MercadoLibre\back-end\magneto-api-gateway
--Creamos la imagen docker
docker build -f Dockerfile -t magneto-api-gateway:v1 .
--Levantamos el contenedor
docker run -p 8090:8090 -i -t --name magneto-api-gateway --network magnetonetwork magneto-api-gateway:v1
3. ---------------------------(Microservicio mutant)-----------------------------
--Nos ubicamos en el raiz del proyecto
CD E:\Test-MercadoLibre\back-end\magneto-mutant
--Creamos la imagen docker
docker build -f Dockerfile -t magneto-mutant:v1 .
--Levantamos el contenedor
docker run -P -i -t --name magneto-mutant-02 --network magnetonetwork magneto-mutant:v1
## 
# Arquitectura

Para el diseño de la arquitectura se realizó la apuesta por diseñar una de tipo **Clean Architecture**, siendo el componente **core** el que posee la lógica de negocio, dicho componente tiene entradas y saldas a traves de interfaces, la inyección de dependencias que se usaron fueron las proporcionadas por Spring Boot

![Clean Architecture](ops/readme/ca.png?raw=true)

# C4Model

Para el diseño utilizamos diagramación por c4model

### Diagrama de contexto

Pretendemos representar la imagen global de lo que se pretende solucionas y la interacción a muy alto nivel

![Diagrama de contexto](ops/readme/dcontext.png?raw=true)

### Diagrama de Contenedores

A nivel de contenedores tenemos únicamente tres, uno hace referencia a la **UI** generada por **SWAGGER** el cual usamos para documentar el API, otro a la aplicación al servicio REST hecho en **Java 11** con **Spring Boot** y por ultimo el contenedor de base de datos, para este caso usamos una base de datos **POSTGRESQL**, la ultima versión disponible hasta el momento.

![Diagrama de contenedores](ops/readme/dcontenedor.png?raw=true)
### Diagrama de Componentes

Se definieron 4 componentes principales:

- Endpoint
    - Controladores
    - Configuración de los codigos de estado de respuestas HTTP
- Dataprovider
    - contiene el manejo dado a la persistencia de base de datos, en este caso la creación de una tabla y el Mapeo contra los DTO del Core
- Configuration
    - Contiene la configuración de los componentes de Endpoint  y Dataprovider
        - Base de datos, para esta ultima se utilizó una estrategia de flyaway para porder ir generando versions de la persistenciab
        - Configuracion Swagger
        - CORS
        - Persistencia con flyaway para el versionamiento de base de datos
- Core
    - Contiene la lógica de negocio
    - Interfaces de entrada y salidas
    - DTOs
    - Pruebas unitarias
        - Cobertura de código:
            -   magneto-rool: 85.7%
            -   magneto-mutant: 97.6%

![Diagrama de componentes](img/code-coverage_1.JPG?raw=true)

### Diagrama de Clases de  Alto Nivel (CORE)

Es un diagrama de clases de alto nivel, los métodos que se ven en el diagrama **no son los implementados en las clases**, lo que se pretende con este diagrama es ver la interacción entre las distintas clases del **Core**

![Diagrama de core](ops/readme/core.png?raw=true)
# Despliegue

- El despliegue fue realizado en un Servidor Cloud VPS con un sitema operativo Ubuntu 20.04 con las siguientes características:
    - CPU:4 vCore
    - RAM:8 GB
    - SSD:160 GB
    - Políticas de firewall: Linux + Plesk

![Diagrama de despliegue](ops/readme/despliegue.png?raw=true)

# Instrucciones de Usuo

- Para usar la **App Magneto** se debe acceder a la siguiente url  [**Swagger Magneto**](http://82.223.99.25:8090/magneto/swagger-ui.html#/human-controller), esta consta de una pequeña interface de usuario realizada a través de **swagger** para poder facilitar los usos de los servicios
- Los test funcionales a los endpoints fueron realizados con Postman, se encuentran en la colección [**Magneto.postman_collection.json**](doc/Magneto.postman_collection.json)

# Notas

- La aplicación se construyó utilizando **Eclipse IDE** para como editor de codigo fuente.
- **DBeaver** para como manager de base de datos
- **Postman** para testear los microservicios
- **SourceTree** como cliente git para la administración del repositorio de código.
- **FileZilla** para subir administrar imagenes de los contenedores Docker al servidor
- **Putty** como cliente SSH para la configuración y administración del servidor VPS
