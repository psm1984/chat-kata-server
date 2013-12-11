package com.example.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 11/12/13
 * Time: 11:41
 */
public class ChatState implements IChatState {
    private List<ChatMessage> storedChatMessages;
    private int nextSeq;
    private ChatResponse storedChatResponse;

    public ChatState() {
        storedChatMessages = new ArrayList<ChatMessage>();
        nextSeq = 0;
    }

    @Override
    public void storeNewMessage(ChatMessage chatMessage) {
        storedChatMessages.add(chatMessage);
        nextSeq++;
    }

    @Override
    public List<ChatMessage> retrieveMessages() {
        return storedChatMessages;
    }

    @Override
    public ChatResponse retrieveChatResponse(int fromSeq) {
        if (fromSeq > nextSeq || fromSeq < 0) fromSeq = nextSeq;
        return new ChatResponse(storedChatMessages.subList(fromSeq, nextSeq), nextSeq);
    }

}
