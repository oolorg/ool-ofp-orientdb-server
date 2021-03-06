/**
 * @author OOL 1131080355959
 * @date 2014/02/05
 * @TODO TODO
 */
package ool.com.orientdb.business;

/**
 * @author 1131080355959
 *
 */
public interface DeviceManagerBusiness {
	
	String getDeviceInfo(String deviceName, String deviceType, String ofpFlag);

	String createDeviceInfo(String params);

	String createPortInfo(String params);

	String updateDeviceInfo(String params);

	String updatePortInfo(String params);

	String deleteDeviceInfo(String params);

	String deletePortInfo(String portName, String deviceName);
	
	String getConnectedPortInfo(String deviceName);
}
