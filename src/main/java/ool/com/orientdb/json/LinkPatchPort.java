/**
 * @author OOL 1131080355959
 * @date 2014/02/13
 * @TODO 
 */
package ool.com.orientdb.json;

import java.util.List;

/**
 * @author 1131080355959
 *
 */
public class LinkPatchPort {
	private String deviceName;
	private List<String> portName;

	public List<String> getPortName() {
		return portName;
	}

	public void setPortName(final List<String> portName) {
		this.portName = portName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(final String deviceName) {
		this.deviceName = deviceName;
	}

}
