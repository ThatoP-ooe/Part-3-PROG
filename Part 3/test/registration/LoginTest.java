package registration;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    // ===== checkUserName() Tests =====
    @Test
    public void testValidUserName() {
        assertTrue(Login.checkUserName("kyl_1"));
    }

    @Test
    public void testInvalidUserName() {
        assertFalse(Login.checkUserName("kyle!!!!!!!"));
    }

    // ===== checkPasswordComplexity() Tests =====
    @Test
    public void testValidPassword() {
        assertTrue(Login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testInvalidPassword() {
        assertFalse(Login.checkPasswordComplexity("password"));
    }

    // ===== checkCellPhoneNumber() Tests =====
    @Test
    public void testValidPhoneNumber() {
        assertTrue(Login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testInvalidPhoneNumber() {
        assertFalse(Login.checkCellPhoneNumber("08966553"));
    }

    // ===== registerUser() Tests =====
    @Test
    public void testRegisterUserSuccess() {
        Login login = new Login();
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.\nUser registered successfully.", result);
    }

    @Test
    public void testRegisterUserInvalidUsername() {
        Login login = new Login();
        String result = login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.", result);
    }

    @Test
    public void testRegisterUserInvalidPassword() {
        Login login = new Login();
        String result = login.registerUser("kyl_1", "password", "+27838968976");
        assertEquals("Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", result);
    }

    @Test
    public void testRegisterUserInvalidPhoneNumber() {
        Login login = new Login();
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code.", result);
    }

    // ===== loginUser() Tests =====
    @Test
    public void testLoginSuccess() {
        Registration.setUserName("kyl_1");
        Registration.setPassword("Ch&&sec@ke99!");
        Login login = new Login();
        login.setEnteredUsername("kyl_1");
        login.setEnteredPassword("Ch&&sec@ke99!");
        assertTrue(login.loginUser());
    }

    @Test
    public void testLoginFailure() {
        Registration.setUserName("kyl_1");
        Registration.setPassword("Ch&&sec@ke99!");
        Login login = new Login();
        login.setEnteredUsername("wrong");
        login.setEnteredPassword("wrong");
        assertFalse(login.loginUser());
    }
}
