# GlUserProject
Aplicación Api Restful de Creación de Usuarios
La Clase primaria para inicializar la aplicación es:
GlProjectH2UsersApplication.java
Nota: Al momento de subir este proyecto aun no estaban funcionando las pruebas unitarias de java, el desarrollo fue probado con la aplicación Postman.
Con los siguientes pasos:
1)	Autenticar:
Parametros de postman: 
Post  http://localhost:8080/authenticate
En la pestaña body (seleccionado raw y JSON)
Poner los siguientes datos de prueba:

{
"username" : "boo",
"password" : "boo"
}
Presionar enviar, en la parte de abajo se obtiene el token de autenticación, como se muestra en la figura:

Este token se maneja internamente en la aplicación, pero para efectos de las pruebas con postman, se deberá seleccionar los caracteres del token devuelto
que se encuentran entre comillas dobles del lado derecho.
Para poder probar la creación de usuarios, deberá dirigirse a la pestaña Headers, y agregar un parámetro Authorization y en value poner el token de la 
autenticación de la siguiente forma:
 Bearer [token de autenticacion] (con 1 espacio en blanco entre Bearer y el token)

Ejemplo: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib28iLCJleHAiOjE2MDAzMTIzMjMsImlhdCI6MTYwMDI3NjMyM30.rww7O5WiuP4Z7BIWj44b07Y_Qv_Zzfi3zxIkBRfDdMk

2)	Luego probar la funcionalidad de crear usuarios en la Base de Datos H2, mediante el el método POST y la URL http://localhost:8080/create
En el body deben ponerse los datos de prueba, para esta prueba se ingresó el primer usuario con 1 telefono:

Datos de prueba:
{
    "name" : "Carolina Tapia",
    "email"     : "cartapia@gmail.com",
    "password"  : "myPassuu45",
    "phones"    :[
        {
            "number":"1234567",
            "citycode": "1",
            "countrycode": "57"
        }
    ]

}



Luego el segundo usuario con 2 teléfonos:

 

{
    "name"  : "Elena Reyes",
    "email"     : "ereyes@gmail.com",
    "password"  : "My23oofs",
    "phones"    :[
        {
            "number":"45673456",
            "citycode": "1",
            "countrycode": "56"
        },
        {
            "number":"4567878",
            "citycode": "1",
            "countrycode": "56"
        }
    ]

}


 

Luego el 3 usuario con 3 telefonos:

{
    "name"  : "Bianca Reyes",
    "email"     : "breyes@gmail.com",
    "password"  : "mYpass23",
    "phones"    :[
        {
            "number":"45678890",
            "citycode": "1",
            "countrycode": "56"
        },
        {
            "number":"35456777",
            "citycode": "1",
            "countrycode": "56"
        },
        {
            "number":"454676575",
            "citycode": "1",
            "countrycode": "56"
        }
    ]

}

En todas las pruebas, devolviendo correctamente objeto json con los datos de id, name, created, modified, last_login, password, token, isactive, y los teléfonos asociados (tal como se solicita en 
el enunciado de la evaluación)


3)	Para ver la base de datos en forma grafica, sin la autenticación hecha en un principio, en browser poner la dirección:
http://localhost:8080/h2-console

Con la autenticación del principio, no se puede accesar a esta funcionalidad gráfica para ver la BD ya que retorna un mensaje de prohibido, por el hecho de la encriptación, 
no está dentro del objetivo de la evaluación ver como solucionar este problema, pero con más tiempo, podría averiguar cómo hacer esto.

para ver los datos que se llenan en la Base de datos, con la encriptación de datos, lo podemos hacer con el método get/all

4)	Para listar la creación de usuarios realizada en la BD se debe utilizar el método Get con la siguiente URL en postman:
http://localhost:8080/all
La cual retorna todos los usuarios ya creados.
5)	Una característica de la aplicación es que valida si el email esta bien formado, si el password esta bien formado (de acuerdo a las restricciones del enunciado) 
para lo cual retorna mensaje con los errores encontrados en caso de tenerlos.

6)	Además chequea que el email no haya sido ingresado con anterioridad, ya que de ser así, retorna el mensaje : email ya ingresado.
7)	Adicionalmente los métodos del{id} y get{id} también están habilitados, para borrar con el primero o listar con el segundo dado un id específico.
8)	El método Put esta presente, pero no funciona todavía bien (no está dentro del scope de la evaluación).
9)	Diagrama de componentes ,	Diagrama de tablas y Diagrama de Secuencia se adjuntara al repositorio.

Para la construcción del servicio se utilizó Java, Springboot, Hibernate, Base de datos en memoria H2, Gradle como herramienta de repositorio.

