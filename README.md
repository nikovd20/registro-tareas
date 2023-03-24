# Aplicacion de Registro de Tareas WEB!

En el interior de este repositorio, podras encontrar 2 carpetas que contienen el codigo de esta aplicacion.

## Rest

En la carpeta rest contiene, todo el codigo del backend o RestfulAPI que se utilizo para generar la base de datos del registro de tareas.

Esta contiene todas las especificaciones necesarias y solicitada por parte de la evaluacion:

- Framework Spring Boot 
- Expuesto mediante API REST
- Utiliza Swagger para documentar la API --> Puedes ingresar en el link http://localhost:8080/swagger-ui/index.html
- Se Validaron mediante @Validation y @Notblank los campos necesario al agregar y editar contenido de la base
- Se utilizo para persistencia JPA 
- En la carpeta /rest/src/test/java/com/nikovd/app/rest se encuentran las pruebas unitarias realizadas a los Servicios y Controladores utilizados.

- Se utilizo Base de datos MySQL
- Posee una estructura de columnas ID(numero y autocreado), title o nombre(cadena), descripcion (cadena), fechadecreacion(Timestapm), estado(cadena) *se utilizo cadena finalmente en estado ya que se necesitaban 3 opciones (pendiente, en proceso, terminado).

- Abrir Spring Boot tools, IntelliJ u otro IDE de preferencias, * Correr backend primero que front para mantener las direcciones.

## Tareas Web

Esta carpeta contiene todo el codigo del frontend donde se implemento una pagina web que permitiera agregar las tareas con las codiciones expuestas en el backend

Esta contiene todas las especificaciones necesarias y solicitada por parte de la evaluacion:

- Framework VueJs 3
- Se utilizaron Axios para conectar el front con la API creada
- Dentro de la app es posible, Agregar, editar,quitar tareas, listar por ID, por fecha y por estado

- Para correr frontend utilizar comando npm run serve en la carpeta tareas-web/ 