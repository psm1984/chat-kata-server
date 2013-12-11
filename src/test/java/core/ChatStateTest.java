package core;

import org.junit.Test;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 11/12/13
 * Time: 12:44
 */
public class ChatStateTest {

    @Test
    public void testStoreOneMessage() throws Exception {
        IChatState chatState = new ChatState();
        ChatMessage message = new ChatMessage("Nick1", "Message1");
        chatState.storeNewMessage(message);
        List<ChatMessage> messages = chatState.retrieveMessages();
        assertThat(messages.size()).isEqualTo(1);
        assertThat(messages.get(0)).isEqualTo(message);
    }


    @Test
    public void testStoreMoreMessage() throws Exception {
        IChatState chatState = new ChatState();
        final int messagesToTest = 10;
        ChatMessage messages[] = new ChatMessage[messagesToTest];

        for (int i = 0; i < messagesToTest; i++) {
            messages[i] = new ChatMessage("Nick" + i, "Message" + i);
            chatState.storeNewMessage(messages[i]);
        }

        List<ChatMessage> retrieveMessages = chatState.retrieveMessages();
        assertThat(retrieveMessages.size()).isEqualTo(messagesToTest);

        for (int i = 0; i < messagesToTest; i++) {
            assertThat(retrieveMessages.get(i)).isEqualTo(messages[i]);
        }
    }

    @Test
    public void testStoreNoMessage() throws Exception {
        IChatState chatState = new ChatState();
        List<ChatMessage> retrieveMessages = chatState.retrieveMessages();
        assertThat(retrieveMessages.size()).isEqualTo(0);
    }


}
