/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 11/12/13
 * Time: 10:01
 */

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.yammer.dropwizard.testing.ResourceTest;
import constants.Constants;
import core.ChatMessage;
import core.ChatResponse;
import core.ChatState;
import core.IChatState;
import org.junit.Test;
import resources.ChatKataResource;

import java.util.ArrayList;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class TestChatKataResources extends ResourceTest {
    private ChatKataResource chatKataResource;
    private IChatState iChatStateMock;

    @Override
    protected void setUpResources() {
        chatKataResource = new ChatKataResource();
        iChatStateMock = mock(ChatState.class);
        chatKataResource.setIChatState(iChatStateMock);
        addResource(chatKataResource);
    }

    @Test
    public void postGoodMessageTest() throws Exception {
        ChatMessage chatMessage = new ChatMessage("userName", "User message");
        WebResource webResource = client().resource(Constants.API_CHAT_KATA);
        ClientResponse response = webResource.type("application/json")
                .post(ClientResponse.class, chatMessage);

        assertThat(response.getStatus()).isEqualTo(200);
        verify(iChatStateMock).storeNewMessage(chatMessage);
    }

    @Test
    public void postNullMessageTest() throws Exception {
        String nullJSON = "null";
        WebResource webResource = client().resource(Constants.API_CHAT_KATA);
        ClientResponse response = webResource.type("application/json").post(ClientResponse.class, nullJSON);
        assertThat(response.getStatus()).isEqualTo(422);
    }

    @Test
    public void postBadMessageTest() throws Exception {
        String badJSON = "{\"nic\":\"user\",\"message\": \"Hola Mundo\"}";
        WebResource webResource = client().resource(Constants.API_CHAT_KATA);
        ClientResponse response = webResource.type("application/json").post(ClientResponse.class, badJSON);
        assertThat(response.getStatus()).isEqualTo(422);
    }

    @Test
    public void getEmptyMessageTest() throws Exception {
        ChatResponse expectedChatResponse = new ChatResponse(new ArrayList<ChatMessage>(), 0);
        when(iChatStateMock.retrieveChatResponse(0)).thenReturn(expectedChatResponse);
        WebResource webResource = client().resource(Constants.API_CHAT_KATA);
        ChatResponse chatResponseRetrieve = webResource.get(ChatResponse.class);
        assertThat(chatResponseRetrieve).isEqualTo(expectedChatResponse);
    }

    @Test
    public void getMessageTest() throws Exception {
        ArrayList<ChatMessage> messages = new ArrayList<ChatMessage>();
        messages.add(new ChatMessage("nick", "message"));
        ChatResponse expectedChatResponse = new ChatResponse(messages, 1);
        when(iChatStateMock.retrieveChatResponse(0)).thenReturn(expectedChatResponse);
        WebResource webResource = client().resource(Constants.API_CHAT_KATA);
        ChatResponse chatResponseRetrieve = webResource.get(ChatResponse.class);
        assertThat(chatResponseRetrieve).isEqualTo(expectedChatResponse);
    }

    @Test
    public void getMessagesFromNextSeqTest() throws Exception {
        ArrayList<ChatMessage> messages = new ArrayList<ChatMessage>();
        messages.add(new ChatMessage("nick", "message"));
        ChatResponse expectedChatResponse = new ChatResponse(messages, 1);
        when(iChatStateMock.retrieveChatResponse(2)).thenReturn(expectedChatResponse);
        WebResource webResource = client().resource(Constants.API_CHAT_KATA + "?next_seq=2");
        ChatResponse chatResponseRetrieve = webResource.get(ChatResponse.class);
        assertThat(chatResponseRetrieve).isEqualTo(expectedChatResponse);
    }


}