# Operación Fuego de Quasar

___________________________________________________________________

# Pre-requisitos

- Instalar **[*Docker*](https://docs.docker.com/get-docker/)**
- Installar **[*Docker Compose*](https://docs.docker.com/get-docker/)**

# Instalación

- Ejecutar el **docker-compose.yml** que se encuentra en **app-trilateration/ops**
    - ```docker-compose up -d ``` para ejecutarlo en segundo plano o ```docker-compose up```  para ver el logging de la aplicación en ejecución

![Trilateration](ops/readme/iteration.png?raw=true)

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

A nivel de contenedores tenemos únicamente tres, uno hace referencia a la **UI** generada por **SWAGGER** el cual usamos para documentar el API, otro a la aplicación al servicio REST hecho en **Java 8** con **Spring Boot** y por ultimo el contenedor de base de datos, para este caso usamos una base de datos **POSTGRESQL**, la ultima versión disponible hasta el momento.

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
        - Se logró una cobertura de código al 94%

![Diagrama de componentes](ops/readme/dcomponentes.png?raw=true)

### Diagrama de Clases de  Alto Nivel (CORE)

Es un diagrama de clases de alto nivel, los métodos que se ven en el diagrama **no son los implementados en las clases**, lo que se pretende con este diagrama es ver la interacción entre las distintas clases del **Core**

![Diagrama de core](ops/readme/core.png?raw=true)
# Despliegue

- El despliegue fue realizado en AWS en una instancia Cent OS 7con las siguientes características:
    - tipo de instancia t2.small
    - 8 G de disco
    - Security Group puerto de enlace 80

![Diagrama de despliegue](ops/readme/despliegue.png?raw=true)

# Uso

- Para usar la **app-trilateration** se debe ingresar a la siguiente url  [http://3.140.185.20/swagger-ui.html](http://3.140.185.20/swagger-ui.html), esta consta de una pequeña interface de usuario realizada a través de **swagger** para poder facilitar los usos de los servicios
- Los endpoints desarrollados hacen referencia en  **satellites-service**   y  **top-secret-service**

# Notas

- La aplicación se construyó en un contendor **docker** y se encuentra alojada en docker hub [https://hub.docker.com/repository/docker/andresnator/trilateration](https://hub.docker.com/repository/docker/andresnator/trilateration)
- La cobertura del código fuente en el componente de core fue del 94
- En el directorio ops/ se encuentra la construcción del docker, diagramas en draw.io y el .jar de la solución 