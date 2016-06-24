package ge.edu.freeuni.sdp.iot.router;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Nikoloz on 06/24/16.
 */
public class House {

    private HashMap<String, MacObject> macAddresses;
    private int houseId;

    public House(int houseId) {
        this.houseId = houseId;
        macAddresses = new HashMap<>();
    }

    public void addMac(MacObject macObject) {
        macAddresses.put(macObject.getId(), macObject);
    }

    public boolean removeMac(String id) {
        if (macAddresses.containsKey(id)) {
            macAddresses.remove(id);
            return true;
        }
        return false;
    }

    public int getHouseId() {
        return houseId;
    }

    @Override
    public String toString() {
        String res = "[";
        for (Object o : macAddresses.entrySet()) {
            res += " " + ((Map.Entry) o).getValue();
            res += ",";
        }
        res += "]";
        return res;
    }
}
