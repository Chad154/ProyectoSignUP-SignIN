# ProyectoSignUP-SignIN
Esto es un proyecto de inicio de sesion sencillo donde se trabaja con javaFX para el uso de interfaces.

# Funciones
Sign In: El usuario ingresa su correo electronico y contraseña para iniciar sesion.
Sign Up: Se crea un nuevo usuario introduciendo todos los datos necesarios.
Change Password: El usuario usando su contraseña antigua, la actualiza a una nueva.

# Como usar
1.Se configura el lado servidor para que se disponga de las tablas con las que se trabaja en la base de datos.
2.En la carpeta "dist" que dispone de todas las librerias necesarias, se ejecuta el archivo ".jar" del programa.
3.Una vez realizado estos pasos, ya se puede usar el programa.

# Estructura del proyecto
ProyectoSignUp-SignIn
  -ProyectoSignUp.SignIn
    -ProyectoSignUp.java
    -ProyectoSignIn.java
    -ProyectoChangePassword.java
  -ProyectoSignUp.SignIn.logic
    -CustomerRESTClient.java
  -ProyectoSignUp.SignIn.model
    -Customer.java
  -ProyectoSignUp.SignIn.ui
    -CambioContraseña.fxml
    -ChangeController.java
    -SignInController.java
    -SignUp.fxml
    -SignUpController.java
    -SignIn.fxml
  TextPackage
    -ChangeControllerTest.java
    -SignInControllerTest.java
    -SignUPControllerTest.java
    
# URL
https://github.com/Chad154/ProyectoSignUP-SignIN
