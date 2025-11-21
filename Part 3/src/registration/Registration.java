package registration;

import javax.swing.JOptionPane;

public class Registration {
    private static String firstName;
    private static String lastName;
    private static String password;
    private static String username;
    private static String phoneNumber;

    // Getters and Setters
    public static void setFirstName(String name) { firstName = name; }
    public static String getFirstName() { return firstName; }

    public static void setLastName(String surname) { lastName = surname; }
    public static String getLastName() { return lastName; }

    public static void setUserName(String userName) { username = userName; }
    public static String getUserName() { return username; }

    public static void setPassword(String pass) { password = pass; }
    public static String getPassword() { return password; }

    public static void setPhoneNumber(String phone) { phoneNumber = phone; }
    public static String getPhoneNumber() { return phoneNumber; }

    // ---------------- MAIN APPLICATION ----------------
    public static void main(String[] args) {
        // Registration process
        setFirstName(JOptionPane.showInputDialog(null, "Please enter your first name:"));
        setLastName(JOptionPane.showInputDialog(null, "Please enter your last name:"));

        // Username
        String usernameInput;
        do {
            usernameInput = JOptionPane.showInputDialog(null, "Enter username (≤5 chars and must contain '_'):");
            if (!Login.checkUserName(usernameInput)) {
                JOptionPane.showMessageDialog(null,
                        "Username is incorrectly formatted. Must contain '_' and ≤5 chars.");
            }
        } while (!Login.checkUserName(usernameInput));
        setUserName(usernameInput);

        // Password
        String passwordInput;
        do {
            passwordInput = JOptionPane.showInputDialog(null,
                    "Enter password (≥8 chars, 1 capital, 1 number, 1 special char):");
            if (!Login.checkPasswordComplexity(passwordInput)) {
                JOptionPane.showMessageDialog(null,
                        "Password is incorrectly formatted. Ensure it has at least 8 chars, a capital, a number, and a special char.");
            }
        } while (!Login.checkPasswordComplexity(passwordInput));
        setPassword(passwordInput);

        // Phone Number
        String phoneInput;
        do {
            phoneInput = JOptionPane.showInputDialog(null,
                    "Enter phone number (+27 followed by 9 digits):");
            if (!Login.checkCellPhoneNumber(phoneInput)) {
                JOptionPane.showMessageDialog(null,
                        "Phone number incorrectly formatted. Must start with +27 followed by 9 digits.");
            }
        } while (!Login.checkCellPhoneNumber(phoneInput));
        setPhoneNumber(phoneInput);

        // Register user
        Login login = new Login();
        String registrationResult = login.registerUser(getUserName(), getPassword(), getPhoneNumber());
        JOptionPane.showMessageDialog(null, registrationResult);

        // ---------------- LOGIN ----------------
        boolean loginSuccessful = false;
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;

        while (!loginSuccessful && attempts < MAX_ATTEMPTS) {
            String enteredUsername = JOptionPane.showInputDialog("Enter username to login:");
            String enteredPassword = JOptionPane.showInputDialog("Enter password to login:");

            login.setEnteredUsername(enteredUsername);
            login.setEnteredPassword(enteredPassword);

            if (login.loginUser()) {
                JOptionPane.showMessageDialog(null, login.returnLoginStatus());
                loginSuccessful = true;

                // Main menu loop
                boolean running = true;
                while (running) {
                    String option = JOptionPane.showInputDialog(
                            "Welcome to QuickChat.\n1) Send Messages\n2) Show recently sent messages\n3) Reports & Data Tools\n4) Quit");

                    switch (option) {
                        case "1":
                            sendMessages();
                            break;

                        case "2":
                            JOptionPane.showMessageDialog(null, "Coming Soon."); // placeholder
                            break;

                        case "3":
                            showReportsMenu();
                            break;

                        case "4":
                            running = false;
                            JOptionPane.showMessageDialog(null, "Logging out...");
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Invalid option.");
                    }
                }

            } else {
                attempts++;
                JOptionPane.showMessageDialog(null, "Login failed. Attempt " + attempts + " of " + MAX_ATTEMPTS);
            }
        }
    }

    // ---------------- SEND MESSAGES ----------------
    private static void sendMessages() {
        int numMessages = Integer.parseInt(
                JOptionPane.showInputDialog("How many messages do you want to send?"));
        for (int i = 1; i <= numMessages; i++) {
            String recipient = JOptionPane.showInputDialog("Enter recipient (+27...):");
            if (!Message.checkRecipientCell(recipient)) {
                JOptionPane.showMessageDialog(null, "Invalid number.");
                continue;
            }

            String text = JOptionPane.showInputDialog("Enter message (max 250 chars):");
            Message msg = new Message(i, recipient, text);

            String lengthCheck = msg.checkMessageLength();
            if (!lengthCheck.equals("Message ready to send.")) {
                JOptionPane.showMessageDialog(null, lengthCheck);
                continue;
            }

            String action = JOptionPane.showInputDialog("Choose:\n1) Send\n2) Disregard\n3) Store");
            msg.sendMessage(Integer.parseInt(action));
            MessageManager.addMessage(msg);

            JOptionPane.showMessageDialog(null, msg.toString());
        }
    }

    // ---------------- REPORTS MENU ----------------
    public static void showReportsMenu() {
        boolean active = true;

        while (active) {
            String opt = JOptionPane.showInputDialog(
                    "Reports & Data Tools\n" +
                            "1) Display sender & recipient\n" +
                            "2) Display longest message\n" +
                            "3) Search by Message ID\n" +
                            "4) Search by recipient\n" +
                            "5) Delete by hash\n" +
                            "6) Full report\n" +
                            "7) Back");

            switch (opt) {
                case "1":
                    JOptionPane.showMessageDialog(null, MessageManager.displaySendersRecipients());
                    break;

                case "2":
                    JOptionPane.showMessageDialog(null, MessageManager.longestMessage());
                    break;

                case "3":
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Enter message ID:"));
                    JOptionPane.showMessageDialog(null, MessageManager.searchByID(id));
                    break;

                case "4":
                    String rec = JOptionPane.showInputDialog("Enter recipient number:");
                    JOptionPane.showMessageDialog(null, MessageManager.searchByRecipient(rec));
                    break;

                case "5":
                    String hash = JOptionPane.showInputDialog("Enter message hash:");
                    JOptionPane.showMessageDialog(null, MessageManager.deleteByHash(hash));
                    break;

                case "6":
                    JOptionPane.showMessageDialog(null, MessageManager.fullReport());
                    break;

                case "7":
                    active = false;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice.");
            }
        }
    }
}