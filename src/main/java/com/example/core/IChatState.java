package com.example.core;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 11/12/13
 * Time: 11:37
 */
public interface IChatState {
    public void storeNewMessage(ChatMessage chatMessage);

    public List<ChatMessage> retrieveMessages();

    public ChatResponse retrieveChatResponse(int fromSeq);
}
