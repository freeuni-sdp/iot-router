/**
 * Created by Koko on 12.06.2016.
 */
import ge.edu.freeuni.sdp.iot.router.PingService;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import static org.junit.Assert.assertEquals;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class PingTest extends JerseyTest {

        @Override
        protected Application configure() {
            return new ResourceConfig(PingService.class);
        }

        @Test
        public void testGetPing() {
            Response basilResponse = target("ping").request().get();
            assertEquals(basilResponse.getStatus(), Response.Status.OK.getStatusCode());
        }

}
