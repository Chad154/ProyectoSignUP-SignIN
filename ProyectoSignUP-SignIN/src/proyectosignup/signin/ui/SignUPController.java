package proyectosignup.signin.ui;

import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyectosignup.signin.logic.CustomerRESTClient;
import proyectosignup.signin.model.Customer;

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
    private PasswordField pwPassword;
    @FXML
    private PasswordField pfPasswordValidation;
    @FXML
    private Button btSignUp;
    @FXML
    private Button btExit;

    private static final Logger LOGGER = Logger.getLogger("proyectosignup.signin.ui");

    public void init(Stage stage) {
        try {
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

            pwPassword.textProperty().addListener(this::handleftPasswordTextChange);
            pwPassword.focusedProperty().addListener(this::handletfPasswordFocusChange);
        } catch (Exception e) {
            String errorMSG = "Error abriendo la ventana:\n" + e.getMessage();
        }
    }

    // botones -------------------
    private void handlebExitMethod(ActionEvent event) {
        Platform.exit();
    }

    private void handlebLogInMethod(ActionEvent event) {
        // acción del botón SignUp
        try {
            //Se realizan las validaciones de todos los campos para que no esten vacios
            if (this.tfName.getText().trim().equals("")
                    || this.tfLastname.getText().trim().equals("")
                    || this.tfMidleeInitial.getText().trim().equals("")
                    || this.tfStreet.getText().trim().equals("")
                    || this.tfCity.getText().trim().equals("")
                    || this.tfState.getText().trim().equals("")
                    || this.tfZip.getText().trim().equals("")
                    || this.tfPhone.getText().trim().equals("")
                    || this.tfEmail.getText().trim().equals("")
                    || this.pwPassword.getText().trim().equals("")
                    || this.pfPasswordValidation.getText().trim().equals("")) {
                Alert alert = new Alert(AlertType.ERROR, "Los campos no pueden estar vacios");
            } else {
                //Creamos el objeto RestClient
                CustomerRESTClient client = new CustomerRESTClient();
                //Creaamos la variable ultimo ID inicializado en 0
                int lastId = 0;
                //Creamos un booleano "Encontrado" en true
                boolean found = true;
                //Realizamos un bucle utilizando el found para encontrar el ultimo ID existente
                //El bucle pide el Id mediante el find_XML sumandole 1 hasta que el propio metodo nos de un 404 donde ahi ha encontrado el ultimo ID, el cual cogemos.
                while (found) {
                    try {
                        client.find_XML(Customer.class, String.valueOf(lastId + 1));
                        lastId++;
                    } catch (Exception e) {
                        found = false;
                    }

                }
                //Al encontrar el ultimo ID 
                Long newId = Long.valueOf(lastId + 1);

                Customer customer = new Customer();
                customer.setId(newId);
                customer.setFirstName(tfName.getText().trim());
                customer.setLastName(tfLastname.getText().trim());
                customer.setMiddleInitial(tfMidleeInitial.getText().trim());
                customer.setStreet(tfStreet.getText().trim());
                customer.setCity(tfCity.getText().trim());
                customer.setState(tfState.getText().trim());
                customer.setZip(Integer.parseInt(tfZip.getText().trim()));
                customer.setPhone(Long.parseLong(tfPhone.getText().trim()));
                customer.setEmail(tfEmail.getText().trim());
                customer.setPassword(pwPassword.getText().trim());

                client.create_XML(customer);
                client.close();

            }
        } catch (Exception e) {

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
