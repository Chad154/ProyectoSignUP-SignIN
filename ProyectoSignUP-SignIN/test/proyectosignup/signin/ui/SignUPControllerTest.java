/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosignup.signin.ui;

import javafx.stage.Stage;
import org.testfx.framework.junit.ApplicationTest;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isFocused;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.matcher.control.ListViewMatchers.isEmpty;
import proyectosignup.signin.ProyectoSignUPApplication;
/**
 *
 * @author chad
 */
public class SignUPControllerTest extends ApplicationTest{
    
    @Override
    public void start(Stage stage) throws Exception{
        new ProyectoSignUPApplication().start(stage);
    } 
    
    /**
     * Test of init method, of class SignUPController.
     */
    
  
    @Test
    public void testInit() {
        verifyThat("#btSignUp", isDisabled());
        verifyThat("#tfName", isVisible());
        verifyThat("#tfName", isFocused());
    }
     
   
   @Test
   public void testSignUpButtonEnabled(){
       clickOn("#tfName");
       write("Robertico");
       clickOn("#tfLastname");
       write("De Souza");
       clickOn("#tfMidleeInitial");
       write("A");
       clickOn("#tfStreet");
       write("Kilometro 25");
       clickOn("#tfCity");
       write("Madrid");
       clickOn("#tfState");
       write("Madrid");
       clickOn("#tfZip");
       write("28223");
       clickOn("#tfPhone");
       write("911");
       clickOn("#tfEmail");
       write("Robertico@gmail.com");
       clickOn("#pwPassword");
       write("12345678");
       clickOn("#pfPasswordValidation");
       write("12345678");
       
       verifyThat("#btSignUp", isEnabled());
       
   }
   
   
   @Test
   public void testEmailExistente(){
        clickOn("#tfName");
       write("Chad");
       clickOn("#tfLastname");
       write("Arzaga");
       clickOn("#tfMidleeInitial");
       write("A");
       clickOn("#tfStreet");
       write("Kilometro 25");
       clickOn("#tfCity");
       write("Madrid");
       clickOn("#tfState");
       write("Madrid");
       clickOn("#tfZip");
       write("28223");
       clickOn("#tfPhone");
       write("123456789");
       clickOn("#tfEmail");
       write("Chad@chad.com");
       clickOn("#pwPassword");
       write("12345678");
       clickOn("#pfPasswordValidation");
       write("12345678");
       
       clickOn("Sign Up");
       clickOn("Aceptar");
       
       

   }
   
}
