/**
 * @author OOL 1131080355959
 * @date 2014/02/13
 * @TODO 
 */
package ool.com.orientdb.json;

/**
 * @author 1131080355959
 *
 */
public class Port {
	
	private String portName;
	private int portNumber;
	private String deviceName;
	private String type;
	/**
	 * @return the portName
	 */
	public String getPortName() {
		return portName;
	}
	/**
	 * @param portName the portName to set
	 */
	public void setPortName(final String portName) {
		this.portName = portName;
	}
	/**
	 * @return the portNumber
	 */
	public int getPortNumber() {
		return portNumber;
	}
	/**
	 * @param portNumber the portNumber to set
	 */
	public void setPortNumber(final int portNumber) {
		this.portNumber = portNumber;
	}
	/**
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}
	/**
	 * @param deviceName the deviceName to set
	 */
	public void setDeviceName(final String deviceName) {
		this.deviceName = deviceName;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(final String type) {
		this.type = type;
	}
}
