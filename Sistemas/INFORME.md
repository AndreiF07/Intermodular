# Informe técnico del entorno de ejecución

Aplicación: Masa y Brasa (gestión de reservas de restaurante).

## Tipo de sistema donde se ejecuta

La aplicación está pensada para ejecutarse en un PC de usuario normal, el del
encargado o el camarero del restaurante. No hace falta un servidor dedicado
ni una máquina virtual, porque es una aplicación de escritorio que se conecta
a una base de datos MySQL local instalada en el mismo equipo (XAMPP).

## Requisitos de hardware

CPU:

- Mínima: cualquier procesador de doble núcleo a 2 GHz.
- Recomendada: procesador de cuatro núcleos a 2,5 GHz o superior.

RAM:

- Mínima: 4 GB.
- Recomendada: 8 GB.

Almacenamiento:

- 1 GB libre para Java, MySQL y la aplicación.
- Recomendado disco SSD para que la base de datos vaya más rápida.

Periféricos:

- Teclado y ratón.
- Pantalla de al menos 1280x720.
- No necesita impresora, lector de tarjetas ni nada especial.

## Sistema operativo recomendado

- Principal: Windows 10 o Windows 11 (64 bits).
- También funciona en Linux y macOS, ya que Java es multiplataforma.
- Se recomienda Windows porque es el sistema más común en pequeños
  negocios y porque XAMPP se instala con un par de clics.

## Instalación del entorno

Pasos a seguir, en este orden:

- Instalar Java 23 (Amazon Corretto u otra distribución).
- Instalar XAMPP y arrancar el servicio MySQL desde su panel de control.
- Instalar IntelliJ IDEA Community (gratuito) para abrir el proyecto.
- Abrir phpMyAdmin desde XAMPP e y ejecutar los scripts .sql,
  que crean la base de datos masaybrasa con sus tablas y datos de ejemplo.
- Abrir el proyecto en IntelliJ y dejar que descargue las dependencias
  (JavaFX, Lombok y el conector de MySQL) automáticamente con Maven.

Configuración por defecto de la conexión a la base de datos:

- Host: localhost
- Puerto: 3306
- Usuario: root
- Contraseña: vacía

Si MySQL usa otra contraseña, se cambia en el archivo
src/main/java/org/example/masaybrasa/database/DatabaseConnection.java.

## Cómo se ejecuta

- Arrancar MySQL desde el panel de XAMPP.
- Abrir el proyecto en IntelliJ y ejecutar la clase Main.
- Se abrirá la ventana de login de la aplicación.

## Usuarios, permisos y estructura

Usuarios de la aplicación:

- Administrador: puede ver todas las reservas, mesas y empleados.
- Cliente: puede registrarse, hacer reservas y cancelar las suyas.

Usuarios del sistema:

- El usuario de Windows que arranque la aplicación.
- El usuario root de MySQL (el que usa XAMPP por defecto).

Estructura de carpetas del proyecto:

- src/main/java: código Java (modelos, DAO, controladores).
- src/main/resources: vistas FXML de JavaFX.
- target: archivos generados al compilar (no se toca a mano).

Dónde se guardan los datos:

- Toda la información (usuarios, reservas, mesas, empleados) se guarda en la
  base de datos MySQL de XAMPP, en la carpeta xampp/mysql/data.

Copias de seguridad:

- Se recomienda exportar la base de datos desde phpMyAdmin (opción Exportar)
  y guardar el archivo .sql en una carpeta aparte, por ejemplo en un disco
  externo o en la nube.

## Mantenimiento básico

- Actualizar Java cuando salga una nueva versión LTS.
- Actualizar XAMPP una o dos veces al año.
- Hacer una copia de seguridad de la base de datos al menos una vez por
  semana.
- Revisar de vez en cuando que MySQL arranca correctamente desde XAMPP.
- Si la aplicación falla al iniciar, comprobar primero que MySQL está
  encendido y que la base de datos masaybrasa existe.

## Protección mínima

- Cambiar la contraseña del usuario root de MySQL en cuanto se instale XAMPP
  (no dejarla vacía si el equipo se usa en red).
- Mantener Windows y el antivirus actualizados.
- No exponer el puerto 3306 de MySQL fuera del equipo (solo uso local).
- Guardar las copias de seguridad en un sitio distinto del propio equipo.
- Bloquear el equipo con contraseña de usuario para que no entre cualquiera.

## Evidencias

- La aplicación arranca al ejecutar la clase Main desde IntelliJ.
- Se conecta correctamente a la base de datos masaybrasa de XAMPP.
- Permite registrar usuarios, iniciar sesión y crear reservas que quedan
  guardadas en la base de datos y se ven al volver a entrar.
- Se han hecho pruebas con el usuario administrador y con clientes registrados desde la propia aplicación.
