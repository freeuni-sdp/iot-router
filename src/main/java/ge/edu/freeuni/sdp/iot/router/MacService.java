package ge.edu.freeuni.sdp.iot.router;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * Created by Nikoloz on 06/24/16.
 */
@Path("/houses/{house_id}")
public class MacService {

    @GET
    @Path("/available")
    public Response get(@PathParam("house_id") String houseId) {
        return Response.ok().entity("{\n" +
                "    \"atHome\": true\n" +
                "  }").build();
    }

    @POST
    @Path("/connect")
    public Response create(@PathParam("house_id") String houseId, MacObject macObject) {
        final String uniqueId = UUID.randomUUID().toString();
        return Response.ok().entity(macObject.toString()).build();
    }

    @DELETE
    @Path("/connect/{mac_id}")
    public Response create(@PathParam("house_id") String houseId, @PathParam("mac_id") String macId) {
        return Response.ok().build();
    }

    @GET
    @Path("/addresses")
    public Response getAddresses(@PathParam("house_id") String houseId) {
        return Response.ok().entity("[\n" +
                "            {\n" +
                "                \"deviceName\": \"USER-PC\",\n" +
                "                \"deviceMacAddress\": \"00:0a:95:9d:68:16\",\n" +
                "                \"mac_id\": qengimdsmf,\n" +
                "            },\n" +
                "            \n" +
                "            {\n" +
                "                \"deviceName\": \"USER2-PC\",\n" +
                "                \"deviceMacAddress\": \"01:0b:22:fd:18:1w\",\n" +
                "                \"mac_id\": asniasdanmsd\n" +
                "            }\n" +
                "        ]").build();
    }
}
