/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 11/12/13
 * Time: 9:43
 */

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import resources.HelloWorldResource;

public class HelloWorldService extends Service<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldService().run(args);

    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {
        final String template = configuration.getTemplate();
        final String defaultName = configuration.getDefaultName();
        environment.addResource(new HelloWorldResource(template, defaultName));
    }


    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.setName("hello-world");
    }


}