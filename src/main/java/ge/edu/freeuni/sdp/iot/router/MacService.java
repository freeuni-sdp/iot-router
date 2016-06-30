package ge.edu.freeuni.sdp.iot.router;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Nikoloz on 06/24/16.
 */


@Path("/houses/{house_id}")
public class MacService {


    private House getHouseById(Houses houses, String houseId) {
        House house = houses.getHouse(houseId);
        if (house == null) {
            house = new House(houseId);
            houses.addHouse(house);
        }
        return house;
    }

    @GET
    @Path("/available")
    public Response get(@PathParam("house_id") String houseId) {
        Houses houses = Houses.getInstance();
        House house = getHouseById(houses, houseId);
        return addHeaders(Response.ok().entity("{\"atHome\":" + house.isAnynoneAtHome() + "}"));
    }

    @POST
    @Path("/connect")
    public Response create(@PathParam("house_id") String houseId, MacObject macObject) {
        Houses houses = Houses.getInstance();
        House house = getHouseById(houses, houseId);
        final String uniqueId = UUID.randomUUID().toString();
        macObject.setId(uniqueId);
        house.addMac(macObject);
        return addHeaders(Response.ok().entity(macObject.toString()));
    }

    @DELETE
    @Path("/connect/{mac_id}")
    public Response delete(@PathParam("house_id") String houseId, @PathParam("mac_id") String macId) {
        Houses houses = Houses.getInstance();
        House house = getHouseById(houses, houseId);
        boolean succ = house.removeMac(macId);
        if (succ)
            return addHeaders(Response.ok());
        else
            return addHeaders(Response.status(404));
    }

    @GET
    @Path("/addresses")
    public Response getAddresses(@PathParam("house_id") String houseId) {
        Houses houses = Houses.getInstance();
        House house = getHouseById(houses, houseId);
        return addHeaders(Response.ok().entity(house.toString()));
    }

    private Response addHeaders(Response.ResponseBuilder rpBuilder){
            rpBuilder.header("Access-Control-Allow-Origin", "*");
            rpBuilder.header("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
            rpBuilder.header("Access-Control-Allow-Headers", "Content-Type");
        return rpBuilder.build();
    }
}
