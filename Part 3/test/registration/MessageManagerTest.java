package registration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.junit.Assert.*;
import org.junit.Test;

public class MessageManagerTest {

    @Test
    public void testSentMessagesPopulated() {

        Message m1 = new Message(1, "+27834557896", "Did you get the cake?");
        m1.sendMessage(1);
        MessageManager.addMessage(m1);

        Message m4 = new Message(4, "0838884567", "It is dinner time!");
        m4.sendMessage(1);
        MessageManager.addMessage(m4);

        assertEquals(2, MessageManager.sentMessages.size());
    }

    @Test
    public void testLongestMessage() {
        Message m = new Message(2, "+27838884567",
                "Where are you? You are late! I have asked you to be on time.");
        m.sendMessage(1);
        MessageManager.addMessage(m);

        assertEquals("Where are you? You are late! I have asked you to be on time.",
                MessageManager.longestMessage());
    }

    @Test
    public void testSearchByID() {
        Message m = new Message(4, "0838884567", "It is dinner time!");
        m.sendMessage(1);
        MessageManager.addMessage(m);

        String result = MessageManager.searchByID(4);
        assertTrue(result.contains("It is dinner time!"));
    }
}
