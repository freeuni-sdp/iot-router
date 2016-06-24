package ge.edu.freeuni.sdp.iot.router;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * Created by Nikoloz on 06/24/16.
 */
@Path("/houses/{house_id}")
public class MacService {

    private House getHouseById(Houses houses, int houseId) {
        House house = houses.getHouse(houseId);
        if (house == null) {
            house = new House(houseId);
            houses.addHouse(house);
        }
        return house;
    }

    @GET
    @Path("/available")
    public Response get(@PathParam("house_id") int houseId) {
        return Response.ok().entity("{\n" +
                "    \"atHome\": true\n" +
                "  }").build();
    }

    @POST
    @Path("/connect")
    public Response create(@PathParam("house_id") int houseId, MacObject macObject) {
        Houses houses = Houses.getInstance();
        House house = getHouseById(houses, houseId);
        final String uniqueId = UUID.randomUUID().toString();
        macObject.setId(uniqueId);
        house.addMac(macObject);
        return Response.ok().entity(macObject.toString()).build();
    }

    @DELETE
    @Path("/connect/{mac_id}")
    public Response create(@PathParam("house_id") int houseId, @PathParam("mac_id") String macId) {
        return Response.ok().build();
    }

    @GET
    @Path("/addresses")
    public Response getAddresses(@PathParam("house_id") int houseId) {
        Houses houses = Houses.getInstance();
        House house = getHouseById(houses, houseId);
        return Response.ok().entity(houses.toString()).build();
    }
}
