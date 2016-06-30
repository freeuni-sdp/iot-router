package ge.edu.freeuni.sdp.iot.router;

import java.util.HashMap;

/**
 * Created by Nikoloz on 06/24/16.
 */
public class Houses {
    private static Houses ourInstance;

    public static Houses getInstance() {
        if (ourInstance != null)
            return ourInstance;
        else {
            ourInstance = new Houses();
            return ourInstance;
        }
    }

    private HashMap<String, House> houses;

    private Houses() {
        houses = new HashMap<>();
    }

    public void addHouse(House house) {
        houses.put(house.getHouseId(), house);
    }

    public House getHouse(String houseId) {
        return houses.get(houseId);
    }

    public boolean deleteHouse(int houseId) {
        if (houses.containsKey(houseId)) {
            houses.remove(houseId);
            return true;
        }
        return false;
    }
}
