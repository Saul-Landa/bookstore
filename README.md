# Bookstore REST API

Consideraciones:
- Tener instalado java 17.

# Instalación
1. Clonar repositorio con el siguiente comando: 
```sh
$ git clone https://github.com/Saul-Landa/bookstore.git
```
2. Abrir el proyecto con algún IDE (se recomienda IntelliJ IDEA).
3. Ejecutar el proyecto, se creará el archivo con los libros automáticamente.
4. Las credenciales para la autenticación básica se encuentra en la clase "GlobalVariables".

# End Points
| Método Http | End Point | Descripción |
| ------ | ----- | ------ |
| GET | /api/book | Consulta los libros por el nombre, autor o año de publicación, dependiendo de cual parámetro se envíe en la URL (bookName, author, year).
| GET | /api/book/search/{key} | Consulta los libros por el nombre, autor o año de publicación, pero solo toma el valor enviado en la url.
| POST | /api/book | Registra un libro con la información del cuerpo de la petición.
| PUT | /api/book | Actualiza los libros con la información del cuerpo de la petición, buscando los libros por el parámetro que se envíe en la URL (bookName, author).
| DELETE | /api/book | Elimina los libros que encuentre, buscando por el parámetro que se envíe en la URL (bookName, author, year).

# Nota
* La collección de postman para probar los end points se encuentre dentro de la carpeta "postman".