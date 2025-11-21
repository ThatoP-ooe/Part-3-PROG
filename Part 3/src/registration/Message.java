package registration;

import javax.swing.JOptionPane;

public class Message {

    private int id;
    private String recipient;
    private String text;
    private int actionFlag;  // 1 = Sent, 2 = Ignored, 3 = Saved

    public Message(int id, String recipient, String text) {
        this.id = id;
        this.recipient = recipient;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getText() {
        return text;
    }

    public int getFlag() {
        return actionFlag;
    }

    public static boolean checkRecipientCell(String num) {
        return num.startsWith("+27") && num.length() >= 12;
    }

    public String checkMessageLength() {
        if (text.length() <= 250) {
            return "Message ready to send.";
        }
        return "Message too long.";
    }

    public String sendMessage(int action) {
        this.actionFlag = action;

        switch(action) {
            case 1:
                return "Message sent.";
            case 2:
                return "Message disregarded.";
            case 3:
                return "Message stored.";
            default:
                return "Invalid option.";
        }
    }

    public String getHash() {
        return Integer.toHexString(text.hashCode());
    }

    public String toString() {
        return "Message ID: " + id +
                "\nRecipient: " + recipient +
                "\nText: " + text +
                "\nHash: " + getHash();
    }
}