/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosignup.signin.ui;

import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isFocused;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
import proyectosignup.signin.ProyectoSignUPSignIN;

/**
 *
 * @author imad
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignInControllerTest extends ApplicationTest {
    @Override
    public void start(Stage stage)throws Exception{
        new ProyectoSignUPSignIN().start(stage);
    }
    
    
    @Test
    public void test1_SignInFocus() {
        verifyThat("#tfUsername", isFocused());
    }
    @Test
    public void test2_SignInInitialState(){
        verifyThat("#tfUsername",hasText(""));
        verifyThat("#pfPassword",hasText(""));
    }
    @Test
    public void test3_SignInLogIn(){//verificar q se abre la pestaña de change password(Importante, esta ventana es modal por lo que habria que darle al boton de cancelar para testear el siguiente metodo)
        clickOn("#tfUsername");
        write("a");
        clickOn("#pfPassword");
        write("a");
        clickOn("#bLogIn");
        clickOn("Aceptar");
    }
    @Test
    public void test4_SignInHipervinculo(){//verificar q se abre la ventana de signUp
        clickOn("Don't you have an account? Register");//para que clique en el texto del hipervinvulo q es el unico que tiene puesto esto
    }
    
}
