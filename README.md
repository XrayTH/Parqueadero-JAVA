## Autores
- Juan Pablo Valverde
- Santiago Vasquez

## Instrucciones

- Al ejecutar el programa, lo primero a hacer es crear una base de datos al abrir el formulario. Este formulario solicitará nombre, usuario y contraseña. La base de datos puede tener cualquier nombre. El usuario y la contraseña deben corresponder a un usuario SQL ya creado y con todos los permisos necesarios en el equipo.

- Si se realiza correctamente, debería crear una base de datos con el nombre elegido y cargar las tablas y datos del archivo "database.sql" que se encuentra dentro del proyecto. Además, se guardarán los datos de la base de datos en 3 archivos .txt también almacenados en la carpeta del proyecto. Si estos archivos estuvieran vacíos, no se permitirá ninguna acción que no sea la creación de la base de datos.

- Si todo ha salido bien hasta ahora, podrá usar el programa con normalidad. Primero deberá iniciar sesión, ya que de lo contrario no tendrá acceso a nada más. Si las tablas se cargaron correctamente, habrá 3 usuarios ya creados:
    + Tipo Admin:
        - Correo: admin@gmail.com
        - Contraseña: admin

    + Tipo Empleado:
        - Correo: empleado@gmail.com
        - Contraseña: empleado

    + Tipo Cliente:
        - Correo: cliente@gmail.com
        - Contraseña: cliente

    Dependiendo de cuál use, tendrá acceso o no a las demás partes del programa. Puede utilizar "Crear Usuario" para crear otros, cerrar su sesión actual e iniciar sesión con el usuario que creó y usarlo dependiendo del nivel de acceso que le haya otorgado.

- Si todo funciona como debería hasta ahora, y cierra el programa, no debería tener que crear otra base de datos a menos que la haya borrado. La información debería haberse guardado en sus respectivos archivos y solo tendría que iniciar sesión otra vez. Pero si lo desea, puede crear otra base de datos distinta sin borrar la anterior. El programa la creará y se conectará a ella, pasándole los datos y dejando la anterior tal y como la dejó.
