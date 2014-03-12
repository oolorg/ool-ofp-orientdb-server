/**
 * @author OOL 1134380013430
 * @date 2014/03/04
 * @TODO
 */
package ool.com.orientdb.json;

/**
 * @author 1134380013430
 *
 */
public class DeviceManagerUpdatePortInfoJsonIn {

	private String portName;
	private String deviceName;
	private Port params;
	/**
	 * @return the name
	 */
	public String getPortName() {
		return portName;
	}
	/**
	 * @param name the name to set
	 */
	public void setPortName(
			String portName) {
		this.portName = portName;
	}
	/**
	 * @return the name
	 */
	public String getDeviceName() {
		return deviceName;
	}
	/**
	 * @param name the name to set
	 */
	public void setDeviceName(
			String deviceName) {
		this.deviceName = deviceName;
	}
	/**
	 * @return the params
	 */
	public Port getParams() {
		return params;
	}
	/**
	 * @param params the params to set
	 */
	public void setParams(Port params) {
		this.params = params;
	}
}
