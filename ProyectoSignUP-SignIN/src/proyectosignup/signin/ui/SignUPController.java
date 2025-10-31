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
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.InternalServerErrorException;
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

    private Stage stage;

    public void init(Stage stage) {
        try {
            this.stage = stage;
            stage.setTitle("Sign Up");
            stage.setResizable(false);
            LOGGER.info("Initialize Sign Up Window");

            // estado inicial
            btSignUp.setDisable(true);
            btExit.setDisable(false);

            // foco inicial
            tfName.requestFocus();

            // manejadores de botones
            btExit.setOnAction(this::handlebExitMethod);
            btSignUp.setOnAction(this::handlebSignUpMethod);

            // habilitar el botón solo cuando todos los campos estén rellenos
            tfName.textProperty().addListener(this::validateForm);
            tfLastname.textProperty().addListener(this::validateForm);
            tfMidleeInitial.textProperty().addListener(this::validateForm);
            tfStreet.textProperty().addListener(this::validateForm);
            tfCity.textProperty().addListener(this::validateForm);
            tfState.textProperty().addListener(this::validateForm);
            tfZip.textProperty().addListener(this::validateForm);
            tfPhone.textProperty().addListener(this::validateForm);
            tfEmail.textProperty().addListener(this::validateForm);
            pwPassword.textProperty().addListener(this::validateForm);
            pfPasswordValidation.textProperty().addListener(this::validateForm);

        } catch (Exception e) {
            LOGGER.severe("Error inicializando la ventana: " + e.getMessage());
        }
    }

    // ---------------------- BOTONES ----------------------

    private void handlebExitMethod(ActionEvent event) {
        Platform.exit();
    }

    private void handlebSignUpMethod(ActionEvent event) {
        CustomerRESTClient client = null;
        try {
            //Validacion de el tamaño del campo
            if(tfName.getText().trim().length() > 255){
                new Alert(AlertType.INFORMATION,"El nombre no puede contener mas de 255 caracteres").showAndWait();  
            }else if(tfLastname.getText().trim().length() > 255){
                new Alert(AlertType.INFORMATION,"El apellido no puede contener mas de 255 caracteres").showAndWait(); 
            }else if(tfMidleeInitial.getText().trim().length() > 255){
                new Alert(AlertType.INFORMATION,"El apartado MidleeInitial no puede contener mas de 255 caracteres").showAndWait(); 
            }else if(tfStreet.getText().trim().length() > 255){
                new Alert(AlertType.INFORMATION,"La calle no puede contener mas de 255 caracteres").showAndWait(); 
            }else if(tfCity.getText().trim().length() > 255){
                new Alert(AlertType.INFORMATION,"La ciudad no puede contener mas de 255 caracteres").showAndWait(); 
            }else if(tfState.getText().trim().length() > 255){
                new Alert(AlertType.INFORMATION,"El estado no puede contener mas de 255 caracteres").showAndWait(); 
            }else if(tfZip.getText().trim().length() > 19){
                new Alert(AlertType.INFORMATION,"El ZIP no puede contener mas de 19 caracteres").showAndWait(); 
            }else if(tfPhone.getText().trim().length() > 19){
                new Alert(AlertType.INFORMATION,"El telefono no puede contener mas de 19 caracteres").showAndWait(); 
            }
            // Validar contraseñas iguales
            else if (!pwPassword.getText().equals(pfPasswordValidation.getText())) {
                new Alert(AlertType.ERROR, "Las contraseñas no coinciden").showAndWait();
            }
            // Crear nuevo cliente
            Customer customer = new Customer();
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
            
            client = new CustomerRESTClient();
            client.create_XML(customer);
            client.close();

            new Alert(AlertType.INFORMATION, "Usuario creado correctamente").showAndWait();
            clearForm();
        }catch (InternalServerErrorException e){
            LOGGER.warning(e.getLocalizedMessage());
            new Alert(AlertType.INFORMATION, "Error con el servidor").showAndWait();
        }catch(ForbiddenException e) {
            LOGGER.warning(e.getLocalizedMessage());
            new Alert(AlertType.INFORMATION, "Error el email ya existe").showAndWait();
        }
        catch (Exception e) {
            new Alert(AlertType.ERROR, "Error al crear usuario:\n" + e.getMessage()).showAndWait();
            LOGGER.severe("Error en SignUp: " + e.getMessage());
        }
    }

    /** Limpia todos los campos */
    private void clearForm() {
        tfName.clear();
        tfLastname.clear();
        tfMidleeInitial.clear();
        tfStreet.clear();
        tfCity.clear();
        tfState.clear();
        tfZip.clear();
        tfPhone.clear();
        tfEmail.clear();
        pwPassword.clear();
        pfPasswordValidation.clear();
        btSignUp.setDisable(true);
        tfName.requestFocus();
    }

    /** Valida si todos los campos están rellenados para habilitar el botón Sign Up */
    private void validateForm(ObservableValue<? extends String> obs, String oldVal, String newVal) {
        boolean allFilled =
                !tfName.getText().trim().isEmpty() &&
                !tfLastname.getText().trim().isEmpty() &&
                !tfMidleeInitial.getText().trim().isEmpty() &&
                !tfStreet.getText().trim().isEmpty() &&
                !tfCity.getText().trim().isEmpty() &&
                !tfState.getText().trim().isEmpty() &&
                !tfZip.getText().trim().isEmpty() &&
                !tfPhone.getText().trim().isEmpty() &&
                !tfEmail.getText().trim().isEmpty() &&
                !pwPassword.getText().trim().isEmpty() &&
                !pfPasswordValidation.getText().trim().isEmpty();

        btSignUp.setDisable(!allFilled);
    }
}
