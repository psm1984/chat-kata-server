package core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 11/12/13
 * Time: 13:21
 */
public class ChatResponse {
    @JsonProperty
    private List<ChatMessage> messages;
    @JsonProperty
    private int nextSeq;

    public ChatResponse(List<ChatMessage> messages, int nextSeq) {
        this.messages = messages;
        this.nextSeq = nextSeq;
    }

    private ChatResponse() {
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public int getNextSeq() {
        return nextSeq;
    }

    public void setNextSeq(int nextSeq) {
        this.nextSeq = nextSeq;
    }

    public boolean equals(Object obj) {
        ChatResponse objComp = (ChatResponse) obj;
        return (this.getNextSeq() == objComp.getNextSeq() &&
                this.getMessages().equals(objComp.getMessages()));
    }

}
