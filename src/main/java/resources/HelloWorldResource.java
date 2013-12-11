package resources; /**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 11/12/13
 * Time: 9:50
 */

import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;
import core.Saying;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }


    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        return new Saying(counter.incrementAndGet(),
                String.format(template, name.or(defaultName)));
    }

    @POST
    @Timed
    public Saying postHello(@Valid Saying saying) {
        return new Saying(saying.getId(),
                saying.getContent());
    }

}