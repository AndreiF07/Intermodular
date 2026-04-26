Este proyecto está hecho con un restaurante inventado en mente, llamado Masa & Brasa.

Debido a que es un local relativamente popular dentro de su localidad, se han visto necesitados de una digitalización de reservas de mesas, o funciones internas, como registrar empleados.

En este proyecto, se ha usado MySQL como motor de bases de datos. Para la página web, se ha empleado HTML, CSS y JavaScript vanilla, sin un backend, ya que solamente será informativa. La aplicación, de uso tanto interno como externo, está escrita en Java. 

Para la ejecución de este proyecto en cualquier equipo, se necesita una base de datos con motor sql, cuya direccion sea localhost (XAMPP en este caso), en la cual habrá que ejecutar las queries SQL que están en en módulo de bases de datos, (./Bases_de_datos/sql), para la creación de las bases de datos y la inserción de los primeros datos, para garantizar el uso adecuado de la app. Para ejecutar la aplicación en sí es necesario instalar Java Runtime Environment, que proporciona la máquina virtual de Java y las librerías necesarias para su correcto funcionamiento. 

Este repositorio está dividido en las siguientes partes / módulos:
- Bases de datos (./Bases_de_datos). Dentro de directorio están todos los archivos relacionados con el almacenaje y manejo de datos de la aplicacíon. Esta, a su vez, está dividida en tres partes, el readme correspondiente al módulo, un directorio con los diagramas de la base de datos (./Bases_de_datos/diagramas), y otro con las queries para la creacion de las bases de datos, la insercion de datos principales, y algunos SELECT´s para familiarizarse con la base de datos (./Bases_de_datos/sql).

- Lenguaje de marcas (./Lenguaje_de_marcas). Aquí se encuentran todos los archivos relacionados con la página web informativa del restaurante, tanto el html, css y ej, como el readme dedicado a ese módulo. 

- Programación (./Programacion). Todo el proyecto de Intellij de la aplicación de escritorio, donde se puede registrar, iniciar sesión, crear / manejar reservas, etc... Estructura definida en MPO.

- MPO. La arquitectura del proyecto incluye, dentro de la carpeta src/main, la clase main y sesión. Todos los controladores están en la carpeta "controller". "dao" contiene todas las consultas contra la base de datos. En "database"se aloja la conexión a la base de datos. "model" contiene todas las clases modelo usadas en el proyecto. Por último, los FXML´s están en resources/org/example/masaybrasa, que son las interfaces gráficas.

La mejora de este módulo con respecto al proyecto base de programación pensado, es la asignación automática de los camareros. En las versiones previas, se los tenía que asignar a mano un Admin.

- Sistemas informáticos (./Sistemas). Este módulo incluye un archivo markdown donde se explica en detalle los requisitos mínimos del equipo para poder ejecutar y utilizar la app de manera satisfactoria, junto con los procesos de instalación y mantenimiento. También se adjuntan capturas de su funcionamiento



