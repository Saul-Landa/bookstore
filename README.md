# Bookstore REST API

Consideraciones:
- Tener instalado git.
- Tener instalado java 17.
- Tener instalado Docker (opcional, solo en caso de correr el proyecto con Docker)

# Ejecución

Se proponen dos maneras de ejecutar el proyecto:
1. De manera manual con la ayuda un IDE.
2. Mediante el uso de Docker.

# 1. Ejecución con un IDE
1. Clonar repositorio con el siguiente comando: 
```sh
$ git clone https://github.com/Saul-Landa/bookstore.git
```
2. Abrir el proyecto con algún IDE (se recomienda IntelliJ IDEA).
3. En caso de utilizar IntelliJ, se recomienda contruir el proyecto, en las opciones del menú se elije Build > Build Project
4. Ejecutar el proyecto, se creará el archivo con los libros automáticamente.
5. Las credenciales para la autenticación básica se encuentra en la clase "GlobalVariables".

# 2. Ejecución con Docker
1. Clonar repositorio con el siguiente comando: 
```sh
$ git clone https://github.com/Saul-Landa/bookstore.git
```
2. Generar el archivo .jar, para lo cual se debe ingresar a la raíz del proyecto desde el símbolo del sistema(cmd) o el PowerShell y ejecutar el siguiente comando:
```sh
$ .\mvnw clean package
```
3. Sin cambiar de directorio en la consola, se debe generar la imagen del proyecto con la ayuda del archivo Dockerfile, ejecutar el siguiente comando:
```sh
$ docker build -t bookstore-service:1.0.0 .
```
4. Ingresamos a la carpeta "docker-compose" con el siguiente comando:
```sh
$ cd docker-compose
```
5. Ejecutar el siguiente comando para tener la API en un contenedor de Docker y posterirormente poder consumir los end points desde Postman.
```sh
$ docker compose up -d
```

# End Points
| Método Http | End Point | Descripción |
| ------ | ----- | ------ |
| GET | /api/book | Consulta los libros por el nombre, autor o año de publicación, dependiendo de cual parámetro se envíe en la URL (bookName, author, year).
| GET | /api/book/search/{key} | Consulta los libros por el nombre, autor o año de publicación, pero solo toma el valor enviado en la url.
| POST | /api/book | Registra un libro con la información del cuerpo de la petición.
| PUT | /api/book | Actualiza los libros con la información del cuerpo de la petición, buscando los libros por el parámetro que se envíe en la URL (bookName, author).
| DELETE | /api/book | Elimina los libros que encuentre, buscando por el parámetro que se envíe en la URL (bookName, author, year).

# Nota
* La collección de Postman para probar los end points se encuentre dentro de la carpeta "postman".