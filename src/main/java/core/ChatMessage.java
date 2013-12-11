package core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 11/12/13
 * Time: 9:44
 */
public class ChatMessage {
    @JsonProperty
    private String nick;
    @JsonProperty
    private String message;

    private ChatMessage() {
    }

    public ChatMessage(String nick, String message) {
        this.nick = nick;
        this.message = message;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean equals(Object obj) {
        ChatMessage objComp = (ChatMessage) obj;
        return (this.getNick().equals(objComp.getNick()) &&
                this.getMessage().equals(objComp.getMessage()));
    }


}