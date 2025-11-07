/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosignup.signin.ui;

import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
import proyectosignup.signin.logic.CustomerRESTClient;
import proyectosignup.signin.model.Customer;

/**
 *
 * @author Imad
 */
public class SignInController {
    @FXML //si no pones esto te saldra el error de null pointer
    private TextField tfUsername;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Button bLogIn;
    @FXML
    private Hyperlink Hipervinculo;
    @FXML
    private Button bExit;
    
    private static final Logger LOGGER=Logger.getLogger("proyectosignup.signup.ui");

    public void init(Stage stage, Parent root) {
        LOGGER.info("Initializing window.");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //establecer titulo de la ventana
            stage.setTitle("Sign in");
        //La ventana no debe ser redimensionable.
            stage.setResizable(false);
        //desabilitar botones
            //boton.setDisable(true);
        //Los campos de username y password tendrán un texto de fondo.
        //Se enfoca inicialmente en el campo username.
        //asociar eventos a manejadores
        bExit.setOnAction(this::handlebExitMethod);
        bLogIn.setOnAction(this::handlebLogInMethod);
        //asociacion de manejadores a properties
        tfUsername.textProperty().addListener(this::handletfUsernameTextChange);
        tfUsername.focusedProperty().addListener(this::handletfUsernameFocusChange);
        
        pfPassword.textProperty().addListener(this::handleftPasswordTextChange);
        pfPassword.focusedProperty().addListener(this::handletfPasswordFocusChange);
        
        
        
        
        //Mostar la ventana
        stage.show();

    }
    //botones-------------------
    private void handlebExitMethod(ActionEvent event){
        Platform.exit();
    }
    private void handlebLogInMethod(ActionEvent event){
        try {
            //validar objetos ventana
        if (this.tfUsername.getText().trim().equals("") || this.pfPassword.getText().trim().equals("")) {
            // si los campos no están informados, dará error
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Los campos usuario y contraseña \n deben estar informados",
                    ButtonType.OK);
            alert.showAndWait();   
            //El borde so pondra en rojo en caso de no estar informados
            if (this.tfUsername.getText().trim().equals("")) {
                tfUsername.setStyle("-fx-border-color: red;");
            }
            if (this.pfPassword.getText().trim().equals("")) {
                pfPassword.setStyle("-fx-border-color: red;");
            }
        }
        //instnciar objeto customer de restful, y un objeto customer para pasar al change password
        Customer customer = new Customer();
        CustomerRESTClient resCustomer = new CustomerRESTClient();
        //recoger valor del usuario como email
        customer=resCustomer.findCustomerByEmailPassword_XML(Customer.class, tfUsername.getText().trim(), pfPassword.getText().trim());
        
        }catch (InternalServerErrorException e) {
            LOGGER.warning(e.getLocalizedMessage());
            new Alert(AlertType.INFORMATION,"ERROR: Problemas con el servidor, "+"\n"+"pruebe mas tarde.").showAndWait();//tipo 500
        }catch (NotAuthorizedException e) {
            LOGGER.warning(e.getLocalizedMessage());
            new Alert(AlertType.INFORMATION,"ERROR: Parametros invalidos").showAndWait();//tipo 400
        }catch (Exception e) {
            LOGGER.warning(e.getLocalizedMessage());
            new Alert(AlertType.INFORMATION,"ERROR").showAndWait();
        }

        
        

    }
    //campos--------------------
    /**
     * 
     * @param observable
     * @param oldValue
     * @param newValue 
     */
    
    //username
    private void handletfUsernameTextChange(ObservableValue observable,String oldValue,String newValue){
        try{
            
            //Quitar borde rojo si el usuario escribe algo
            if (!newValue.trim().isEmpty()) {
                tfUsername.setStyle(null);
            }
            
            if (newValue.length()>200) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                "Usuario demasiado largo",
                ButtonType.OK);
                alert.showAndWait();
                tfUsername.clear();
            }
        } catch (Exception e){
            LOGGER.warning(e.getLocalizedMessage());
            new Alert(AlertType.INFORMATION,"ERROR: Usuario demasiado largo").showAndWait();
        }
    }
    /**
     * 
     * @param observable
     * @param oldValue
     * @param newValue 
     */
    private void handletfUsernameFocusChange(ObservableValue observable,Boolean oldValue,Boolean newValue){
        if (newValue) {//si NewValue es true estas ganando el foco, y en else seria la perdida,y viceversa.  !oldValue, si lo niego, quiere decir q si era false,no estaba enfocado
            LOGGER.info("onFocus");
            tfUsername.requestFocus();
        }else if(oldValue){
            LOGGER.info("onBlur");
        }
    }
    /**
     * 
     * @param observable
     * @param oldValue
     * @param newValue 
     */
    //password
    private void handleftPasswordTextChange(ObservableValue observable,String oldValue,String newValue){
        try{
            
            //Quitar borde rojo si el usuario escribe algo
            if (!newValue.trim().isEmpty()) {
                pfPassword.setStyle(null);
            }
            
            if (newValue.length()>200) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                "Contraseña demasiada larga",
                ButtonType.OK);
                alert.showAndWait(); 
                pfPassword.clear();
            }
        } catch (Exception e){
            LOGGER.warning(e.getLocalizedMessage());
            new Alert(AlertType.INFORMATION,"ERROR: Contraseña demasiada larga").showAndWait();
        }
    }
    /**
     * 
     * @param observable
     * @param oldValue
     * @param newValue 
     */
    private void handletfPasswordFocusChange(ObservableValue observable,Boolean oldValue,Boolean newValue){
        if (newValue) {
            LOGGER.info("onFocus");
            pfPassword.requestFocus();
        }else if(oldValue){
            LOGGER.info("onBlur");
        }
    }
    
}
