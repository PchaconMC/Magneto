# Magneto Reclutamiento de Mutantes, para todos

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men. Este proyecto detecta si un
humano es mutante basándose en su secuencia de ADN
___________________________________________________________________

# Pre-requisitos, para merge

- VPS con sistema operativo Ubuntu 20.04.
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

### Diagrama de Contenedores

A nivel de contenedores tenemos tres, uno hace referencia a la **magneto-api-gateway:** quien cuemple la función de enrutamiento para los microservicios de Magneto. **magneto-eureka:** Registrar y localiza microservicios existentes de Magneto, informa de su localización, su estado y datos relevantes. Atiende el balanceo de carga y tolerancia a fallos, **magneto-mutant:** Microservicio que permite dar solución a los requerimientos del cliente, detecta si un humano es mutante basándose en su secuencia de su ADN, de este ultimo se levantaron dos instancias en el servidor para dar solicitud al requerimiento no funciónal y que permita recibir fluctuaciones agresivas de tráfico.


Podemos ver el servidor  [**Magneto Eureka**](http://82.223.99.25:8761/) las instancias desplegadas.

### Pruebas Unitarias y de Integración
Se puede navegar por el reporte de pruebas [**Unitarias Magneto**](https://pinkbook.veoweb.site/) de generado por el framewor JaCoCo.
Para el proyecto se logró la  cobertura de código de la siguiente manera:
    - Proyecto magneto-tool: 85.7%
    - Proyecto magneto-mutant: 97.6%

![Pruebas Unitarias](img/code-coverage_1.JPG?raw=true)

# Despliegue

- El despliegue fue realizado en un Servidor Cloud VPS con un sitema operativo Ubuntu 20.04 con las siguientes características:
    - CPU:4 vCore
    - RAM:8 GB
    - SSD:160 GB
    - Políticas de firewall: Linux + Plesk

# Instrucciones de Usuo

- Para usar la **App Magneto** se debe acceder a la siguiente url  [**Swagger Magneto**](http://82.223.99.25:8090/magneto/swagger-ui.html#/human-controller), esta consta de una pequeña interface de usuario realizada a través de **swagger** para poder facilitar los usos de los servicios
- Los test funcionales a los endpoints fueron realizados con Postman, se encuentran en la colección [**Magneto.postman_collection.json**](doc/Magneto.postman_collection.json)

Para detectar si un humano es mutante enviando la secuencia de ADN mediante, utilice el siguiente endpoint
```
**HTTP POST** http://82.223.99.25:8090/magneto/mutant/

Json envía:
    {
    “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
    }
    
Json respuesta:
    {
        "mensaje": "Para este Humano, se ha validado su secuencia de ADN!!",
        "mutant": true
    }
```

Consulta las estadísticas de las verificaciones de ADN:
```
**HTTP GET** http://82.223.99.25:8090/magneto/stats

Json Respuesta:
{
    "count_mutant_dna": 3.0,
    "count_human_dna": 4.0,
    "ratio": 0.75
}
```


# Notas

- La aplicación se construyó utilizando **Eclipse IDE** para como editor de codigo fuente.
- **DBeaver** para como manager de base de datos
- **Postman** para testear los microservicios
- **SourceTree** como cliente git para la administración del repositorio de código.
- **FileZilla** para subir administrar imagenes de los contenedores Docker al servidor
- **Putty** como cliente SSH para la configuración y administración del servidor VPS
