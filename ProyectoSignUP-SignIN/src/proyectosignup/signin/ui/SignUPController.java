package proyectosignup.signin.ui;

import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUPController {

    @FXML
    private TextField tfName;
    @FXML
    private TextField tfLastname;
    @FXML
    private TextField tfMidleeInitial;
    @FXML
    private TextField tfStreet;
    @FXML
    private TextField tfCity;
    @FXML
    private TextField tfState;
    @FXML
    private TextField tfZip;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField pwPaswword;
    @FXML
    private PasswordField pfPasswordValidation;
    @FXML
    private Button btSignUp;
    @FXML
    private Button btExit;

    private static final Logger LOGGER = Logger.getLogger("proyectosignup.signin.ui");

    public void init(Stage stage) {
        try{
        /*
        Establecer titulo de la ventana
        La ventana mostrará todos los campos necesarios para el registro de un nuevo usuario.
        El foco se establecerá en el campo Name.
        El botón Sign Up estará deshabilitado hasta que todos los campos requeridos estén informados.
        El botón Exit estará habilitado.
        El título “Sign Up” se mostrará centrado en la parte superior
         */
        LOGGER.info("Initialize Window");
        stage.setTitle("Sign UP");
        stage.setResizable(false);

        btSignUp.setDisable(true);
        btExit.setDisable(false);
        

        // Los campos de username y password tendrán un texto de fondo.
        // Se enfoca inicialmente en el campo username.
        // asociar eventos a manejadores
        btExit.setOnAction(this::handlebExitMethod);
        btSignUp.setOnAction(this::handlebLogInMethod);

        // asociacion de manejadores a properties
        tfName.textProperty().addListener(this::handletfUsernameTextChange);
        tfName.focusedProperty().addListener(this::handletfUsernameFocusChange);

        pwPaswword.textProperty().addListener(this::handleftPasswordTextChange);
        pwPaswword.focusedProperty().addListener(this::handletfPasswordFocusChange);
        }catch (Exception e){
            String errorMSG="Error abriendo la ventana:\n" + e.getMessage();
        }
    }

    // botones -------------------
    private void handlebExitMethod(ActionEvent event) {
        Platform.exit();
    }

    private void handlebLogInMethod(ActionEvent event) {
        // acción del botón SignUp
        try{
            if(tfName!=null)
                throw new Exception ("El nombre no puede estar vacio");
            if(tfLastname!=null)
                throw new Exception ("El apellido no puede estar vacio");
            if(tfMidleeInitial!=null)
                throw new Exception ("No puede estar vacio");
            if(tfStreet!=null)
                throw new Exception ("La calle no puede estar vacio");
            if(tfState!=null)
                throw new Exception ("El estado no puede estar vacio");
             if(tfZip!=null)
                throw new Exception ("El Zip no puede estar vacio");
        }catch(Exception e){
            
        }
    }

    // campos --------------------
    private void handletfUsernameTextChange(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        // aquí manejar cambio de texto
    }

    private void handletfUsernameFocusChange(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            // si se pierde el foco
        }
    }

    private void handleftPasswordTextChange(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        // cambio en contraseña
    }

    private void handletfPasswordFocusChange(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            // si se pierde el foco
        }
    }
}
