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

Se deben generar las imagenes a partir de los docker file de cada proyecto, existen tres proyectos de los cuales debemos generar las imagenes, a continuación se detalla el procedimiento:
```
1. ----------------Magneto Network --------------------
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
3. ----------------------------  Magneto API Gateway ----------------------------
--Nos ubicamos en el raiz del proyecto
CD E:\Test-MercadoLibre\back-end\magneto-api-gateway
--Creamos la imagen docker
docker build -f Dockerfile -t magneto-api-gateway:v1 .
--Levantamos el contenedor
docker run -p 8090:8090 -i -t --name magneto-api-gateway --network magnetonetwork magneto-api-gateway:v1
4. ---------------------------(Microservicio mutant)-----------------------------
--Nos ubicamos en el raiz del proyecto
CD E:\Test-MercadoLibre\back-end\magneto-mutant
--Creamos la imagen docker
docker build -f Dockerfile -t magneto-mutant:v1 .
--Levantamos el contenedor
docker run -P -i -t --name magneto-mutant-02 --network magnetonetwork magneto-mutant:v1
```

## 
# Arquitectura

Para el diseño de la arquitectura se realizó la apuesta por diseñar una de tipo **Clean Architecture**, siendo el componente **core** el que posee la lógica de negocio, dicho componente tiene entradas y saldas a traves de interfaces, la inyección de dependencias que se usaron fueron las proporcionadas por Spring Boot

### Diagrama de contexto

Pretendemos representar la imagen global de lo que se pretende solucionas y la interacción a muy alto nivel

### Diagrama de Contenedores

A nivel de contenedores tenemos tres, uno hace referencia a la **magneto-api-gateway:** quien cuemple la función de enrutamiento para los microservicios de Magneto. **magneto-eureka:** Registrar y localiza microservicios existentes de Magneto, informa de su localización, su estado y datos relevantes. Atiende el balanceo de carga y tolerancia a fallos, **magneto-mutant:** Microservicio que permite dar solución a los requerimientos del cliente, detecta si un humano es mutante basándose en su secuencia de su ADN, de este ultimo se levantaron dos instancias en el servidor para dar solicitud al requerimiento no funciónal y que permita recibir fluctuaciones agresivas de tráfico.


Podemos ver el servidor  [**Magneto Eureka**](http://82.223.99.25:8761/) las instancias desplegadas.

### Pruebas Unitarias y de Integración

Par el proyecto se logró la  cobertura de código de la siguiente manear:
    - Proyecto magneto-rool: 85.7%
    - Proyecto magneto-mutant: 97.6%

![Pruebas Unitarias](img/code-coverage_1.JPG?raw=true)

### Diagrama de Clases de  Alto Nivel (CORE)

Es un diagrama de clases de alto nivel, los métodos que se ven en el diagrama **no son los implementados en las clases**, lo que se pretende con este diagrama es ver la interacción entre las distintas clases del **Core**

# Despliegue

- El despliegue fue realizado en un Servidor Cloud VPS con un sitema operativo Ubuntu 20.04 con las siguientes características:
    - CPU:4 vCore
    - RAM:8 GB
    - SSD:160 GB
    - Políticas de firewall: Linux + Plesk

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
