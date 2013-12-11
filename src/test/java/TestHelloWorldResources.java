/**
 * Created with IntelliJ IDEA.
 * User: psm1984
 * Date: 11/12/13
 * Time: 10:01
 */

import com.sun.jersey.api.client.WebResource;
import com.yammer.dropwizard.testing.ResourceTest;
import core.Saying;
import org.junit.Test;
import resources.HelloWorldResource;

import static org.fest.assertions.api.Assertions.assertThat;


public class TestHelloWorldResources extends ResourceTest {
    private final Saying saying = new Saying(1, "Hello, Stranger!");

    @Override
    protected void setUpResources() {
        addResource(new HelloWorldResource("Hello, %s!", "Stranger"));
    }

    @Test
    public void simpleGetResourceTest() throws Exception {
        WebResource webResource = client().resource("/hello-world");
        Saying sayingRetrieve = webResource.get(Saying.class);
        assertThat(sayingRetrieve).isEqualTo(saying);
    }

    @Test
    public void simplePostResourceTest() throws Exception {
        WebResource webResource = client().resource("/hello-world");
        Saying response = webResource.accept("application/json")
                .type("application/json").post(Saying.class, saying);
        assertThat(response).isEqualTo(saying);
    }
}