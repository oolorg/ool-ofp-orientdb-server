/**
 * @author OOL 1131080355959
 * @date 2014/02/17
 * @TODO 
 */
package ool.com.orientdb.client;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.orientechnologies.orient.core.record.impl.ODocument;

/**
 * @author 1131080355959
 *
 */
public interface Dao {
	
	void close() throws SQLException;
	
	/**
	 * get rid of device from deviceName
	 * @param deviceName
	 * @return rid(device)
	 */
	String getDeviceRid(String deviceName) throws SQLException;
	
	/**
	 * get device info from deviceName
	 * @param deviceName
	 * @return ODocument
	 */
	ODocument getDeviceInfo(String deviceName) throws SQLException;
	
	/**
	 * get Connected device to device of rid
	 * @param deviceRid
	 * @return List<ODocument>
	 */
	List<ODocument> getConnectedDevice(String deviceRid) throws SQLException;
	
	/**
	 * get rid of port list
	 * @param deviceRid
	 * @param switchName
	 * @return rid of port list
	 */
	List<String> getPatchPortRidList(String deviceRid) throws SQLException;
	
	/**
	 * 
	 * @param patchPortPair
	 * @return true:contain false:not contain
	 */
	boolean isContainsPatchWiring(List<String> patchPortPair) throws SQLException;
	
	/**
	 * 
	 * @param deviceRidList
	 * @return
	 */
	ODocument getShortestPath(List<String> deviceRidList) throws SQLException;
	
	/**
	 * 
	 * @param portRidList
	 */
	void insertPatchWiring(List<String> portRidList, String parentRid, List<String> deviceNameList) throws SQLException;
	
	/**
	 * 
	 * @param deviceNameList two
	 * @return portRidList two
	 */
	List<Map<String, String>> getPortRidPatchWiring(List<String> deviceNameList) throws SQLException;
	
	/**
	 * 
	 * @param deviceNameList
	 */
	void deleteRecordPatchWiring(List<String> deviceNameList) throws SQLException;
	
	/**
	 * 
	 * @param rid
	 * @return portInfo
	 */
	ODocument getPortInfo(String rid) throws SQLException;
	
	/**
	 * 
	 * @param weight
	 * @param portRid
	 * @param patchRid
	 */
	void updateLinkWeight(int weight, String portRid, String patchRid) throws SQLException;
	
	/**
	 * get patch Connected device to deviceName
	 * @param deviceName
	 * @return List<String>
	 */
	List<List<String>> getPatchConnectedDevice() throws SQLException;

}