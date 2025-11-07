/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosignup.signin.ui;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isFocused;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
import proyectosignup.signin.ProyectoChangePassword;

/**
 *
 * @author Daniel López López
 */

//TODOS LOS METODOS TEST TIENEN QUE TENER UN VERIFY!
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChangeControllerTest extends ApplicationTest{
    
    @Override
    public void start(Stage stage)throws Exception{
        new ProyectoChangePassword().start(stage);
    }
    @Test
    public void test1_ChangePasswordFocus() {
        verifyThat("#CurrentPassword", isFocused());
    }
    @Test
    public void test2_ChangePasswordInitialState(){
        verifyThat("#CurrentPassword", hasText(""));
        verifyThat("#NewPassword", hasText(""));
        verifyThat("#ConfirmPassword", hasText(""));
    }
            
            
}