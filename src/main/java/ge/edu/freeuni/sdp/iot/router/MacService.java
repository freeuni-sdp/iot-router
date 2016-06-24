package ge.edu.freeuni.sdp.iot.router;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by Nikoloz on 06/24/16.
 */
@Path("/house/{house_id}/available")
public class MacService {

    @GET
    public Response get(@PathParam("house_id") String houseId) {
        return Response.ok().entity("{\n" +
                "    \"atHome\": true\n" +
                "  }").build();
    }
}
