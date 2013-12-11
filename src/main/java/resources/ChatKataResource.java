package resources; /**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 11/12/13
 * Time: 9:50
 */

import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;
import constants.Constants;
import core.ChatMessage;
import core.ChatResponse;
import core.IChatState;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.atomic.AtomicLong;

@Path(Constants.API_CHAT_KATA)
@Produces(MediaType.APPLICATION_JSON)
public class ChatKataResource {
    private final AtomicLong counter;
    private IChatState iChatState;

    public ChatKataResource() {
        this.counter = new AtomicLong();
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
            return Response.status(422).build(); //422 Unprocessable
        } else {
            iChatState.storeNewMessage(message);
            return Response.ok().build();
        }
    }

    public void setIChatState(IChatState iChatState) {
        this.iChatState = iChatState;
    }

}