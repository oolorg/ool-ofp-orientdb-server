/**
 * @author OOL 1131080355959
 * @date 2014/03/04
 * @TODO 
 */
package ool.com.orientdb.json;

/**
 * @author 1131080355959
 *
 */
public class DeviceManagerUpdateDeviceInfoJsonIn {

	private String deviceName;
	private Node params;
	/**
	 * @return the name
	 */
	public final String getDeviceName() {
		return deviceName;
	}
	/**
	 * @param name the name to set
	 */
	public final void setDeviceName(final String deviceName) {
		this.deviceName = deviceName;
	}
	/**
	 * @return the params
	 */
	public final Node getParams() {
		return params;
	}
	/**S
	 * @param params the params to set
	 */
	public final void setParams(final Node params) {
		this.params = params;
	}
}
