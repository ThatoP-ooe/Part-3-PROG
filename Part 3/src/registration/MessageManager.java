package registration;
import java.util.ArrayList;

public class MessageManager {

    public static ArrayList<Message> sentMessages = new ArrayList<>();
    public static ArrayList<Message> storedMessages = new ArrayList<>();
    public static ArrayList<Message> disregardedMessages = new ArrayList<>();

    public static ArrayList<String> messageHashes = new ArrayList<>();
    public static ArrayList<Integer> messageIDs = new ArrayList<>();

    // ----------------------------
    // Add message to correct list
    // ----------------------------
    public static void addMessage(Message msg) {

        switch (msg.getFlag()) {
            case 1:
                sentMessages.add(msg);
                break;
            case 2:
                disregardedMessages.add(msg);
                break;
            case 3:
                storedMessages.add(msg);
                break;
        }

        messageHashes.add(msg.getHash());
        messageIDs.add(msg.getId());
    }

    // ----------------------------
    // Display all sent messages
    // ----------------------------
    public static String displaySendersRecipients() {
        StringBuilder sb = new StringBuilder();

        for (Message m : sentMessages) {
            sb.append("Message ID ").append(m.getId())
                    .append(" → Recipient: ").append(m.getRecipient())
                    .append("\n");
        }

        return sb.toString();
    }

    // ----------------------------
    // Longest message
    // ----------------------------
    public static String longestMessage() {
        Message longest = null;

        for (Message m : sentMessages) {
            if (longest == null || m.getText().length() > longest.getText().length()) {
                longest = m;
            }
        }
        return longest.getText();
    }

    // ----------------------------
    // Search by Message ID
    // ----------------------------
    public static String searchByID(int id) {
        for (Message m : sentMessages) {
            if (m.getId() == id) {
                return "Recipient: " + m.getRecipient() +
                        "\nMessage: " + m.getText();
            }
        }
        return "Message not found.";
    }

    // ----------------------------
    // Search messages by recipient
    // ----------------------------
    public static String searchByRecipient(String number) {
        StringBuilder sb = new StringBuilder();

        for (Message m : sentMessages) {
            if (m.getRecipient().equals(number)) {
                sb.append(m.getText()).append("\n");
            }
        }
        for (Message m : storedMessages) {
            if (m.getRecipient().equals(number)) {
                sb.append(m.getText()).append("\n");
            }
        }

        return sb.toString();
    }

    // ----------------------------
    // Delete using hash
    // ----------------------------
    public static String deleteByHash(String hash) {
        for (Message m : sentMessages) {
            if (m.getHash().equals(hash)) {
                sentMessages.remove(m);
                return "Message \"" + m.getText() + "\" successfully deleted.";
            }
        }
        return "Message not found.";
    }

    // ----------------------------
    // Full Report
    // ----------------------------
    public static String fullReport() {
        StringBuilder sb = new StringBuilder("FULL REPORT:\n\n");

        for (Message m : sentMessages) {
            sb.append("ID: ").append(m.getId()).append("\n")
                    .append("Recipient: ").append(m.getRecipient()).append("\n")
                    .append("Message: ").append(m.getText()).append("\n")
                    .append("Hash: ").append(m.getHash()).append("\n\n");
        }

        return sb.toString();
    }
}
