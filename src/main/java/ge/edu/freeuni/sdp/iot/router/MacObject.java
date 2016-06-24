package ge.edu.freeuni.sdp.iot.router;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Nikoloz on 06/24/16.
 */
@XmlRootElement
public class MacObject {

    @XmlElement
    private String deviceName;

    @XmlElement
    private String deviceMacAddress;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceMacAddress() {
        return deviceMacAddress;
    }

    public void setDeviceMacAddress(String deviceMacAddress) {
        this.deviceMacAddress = deviceMacAddress;
    }
}
