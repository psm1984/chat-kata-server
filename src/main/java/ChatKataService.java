/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 11/12/13
 * Time: 9:43
 */

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import core.ChatState;
import resources.ChatKataResource;


public class ChatKataService extends Service<ChatKataConfiguration> {
    public static void main(String[] args) throws Exception {
        new ChatKataService().run(args);
    }

    @Override
    public void initialize(Bootstrap bootstrap) {
        bootstrap.setName("chat-kata");
    }

    @Override
    public void run(ChatKataConfiguration chatKataConfiguration, Environment environment) throws Exception {
        ChatKataResource chatKataResource = new ChatKataResource();
        chatKataResource.setIChatState(new ChatState());
        environment.addResource(chatKataResource);
    }

}