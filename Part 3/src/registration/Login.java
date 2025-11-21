package registration;

public class Login {
    private String enteredUsername;
    private String enteredPassword;

    // ===== Check Username =====
    public static boolean checkUserName(String userName) {
        return userName != null && userName.contains("_") && userName.length() <= 5;
    }

    // ===== Check Password =====
    public static boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) return false;
        boolean hasNum = false, hasCap = false, hasSchar = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasCap = true;
            if (Character.isDigit(c)) hasNum = true;
            if (!Character.isLetterOrDigit(c)) hasSchar = true;
        }
        return hasNum && hasCap && hasSchar;
    }

    // ===== Check Cell Phone =====
    public static boolean checkCellPhoneNumber(String num) {
        return num != null && num.matches("^\\+27[0-9]{9}$");
    }

    // ===== Register User =====
    public String registerUser(String username, String password, String phoneNumber) {
        boolean usernameValid = checkUserName(username);
        boolean passwordValid = checkPasswordComplexity(password);
        boolean phoneValid = checkCellPhoneNumber(phoneNumber);

        if (!usernameValid) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        } else if (!passwordValid) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        } else if (!phoneValid) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        } else {
            return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.\nUser registered successfully.";
        }
    }

    // ===== Login Check =====
    public boolean loginUser() {
        return enteredUsername != null && enteredPassword != null &&
                enteredUsername.equals(Registration.getUserName()) &&
                enteredPassword.equals(Registration.getPassword());
    }

    // ===== Login Status =====
    public String returnLoginStatus() {
        if (loginUser()) {
            return "Welcome " + Registration.getFirstName() + ", " +
                   Registration.getLastName() + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // ===== Setters for login attempt =====
    public void setEnteredUsername(String username) { this.enteredUsername = username; }
    public void setEnteredPassword(String password) { this.enteredPassword = password; }
}
