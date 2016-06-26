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

    private static final String LOCAL = "http://localhost:8080";
    private static final String HEROKU = "http://iot-webui.herokuapp.com";
    private Set<String> originSet = new HashSet<>(Arrays.asList(LOCAL, HEROKU));

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
    public Response get(@PathParam("house_id") int houseId, @HeaderParam("Origin") String origin) {
        Houses houses = Houses.getInstance();
        House house = getHouseById(houses, houseId);
        return addHeaders(Response.ok().entity("{\"atHome\":" + house.isAnynoneAtHome() + "}"), origin);
    }

    @POST
    @Path("/connect")
    public Response create(@PathParam("house_id") int houseId, MacObject macObject, @HeaderParam("Origin") String origin) {
        Houses houses = Houses.getInstance();
        House house = getHouseById(houses, houseId);
        final String uniqueId = UUID.randomUUID().toString();
        macObject.setId(uniqueId);
        house.addMac(macObject);
        return addHeaders(Response.ok().entity(macObject.toString()), origin);
    }

    @DELETE
    @Path("/connect/{mac_id}")
    public Response delete(@PathParam("house_id") int houseId, @PathParam("mac_id") String macId, @HeaderParam("Origin") String origin) {
        Houses houses = Houses.getInstance();
        House house = getHouseById(houses, houseId);
        boolean succ = house.removeMac(macId);
        if (succ)
            return addHeaders(Response.ok(), origin);
        else
            return addHeaders(Response.status(404), origin);
    }

    @GET
    @Path("/addresses")
    public Response getAddresses(@PathParam("house_id") int houseId, @HeaderParam("Origin") String origin) {
        Houses houses = Houses.getInstance();
        House house = getHouseById(houses, houseId);
        return addHeaders(Response.ok().entity(house.toString()), origin);
    }

    private Response addHeaders(Response.ResponseBuilder rpBuilder, String origin){
        if (originSet.contains(origin)) {
            rpBuilder.header("Access-Control-Allow-Origin", origin);
            rpBuilder.header("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
            rpBuilder.header("Access-Control-Allow-Headers", "Content-Type");
        }
        return rpBuilder.build();
    }
}
