/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosignup.signin.ui;

import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author chad
 */
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

    }

}

