package com.example.resources;

/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 11/12/13
 * Time: 9:50
 */

import com.example.constants.Constants;
import com.example.core.ChatMessage;
import com.example.core.ChatResponse;
import com.example.core.IChatState;
import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(Constants.API_CHAT_KATA)
@Produces(MediaType.APPLICATION_JSON)
public class ChatKataResource {
    private IChatState iChatState;

    public ChatKataResource() {
    }

    @GET
    @Timed
    public ChatResponse getMessages(@QueryParam("next_seq") Optional<String> nextSeq) {
        int fromSeq;
        try {
            fromSeq = Integer.parseInt(nextSeq.get());
        } catch (Exception e) {
            fromSeq = 0;
        }
        return iChatState.retrieveChatResponse(fromSeq);
    }

    @POST
    @Timed
    public Response postMessage(ChatMessage message) {
        if (message == null ||
                message.getMessage() == null ||
                message.getNick() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            iChatState.storeNewMessage(message);
            return Response.ok().build();
        }
    }

    public void setIChatState(IChatState iChatState) {
        this.iChatState = iChatState;
    }

}