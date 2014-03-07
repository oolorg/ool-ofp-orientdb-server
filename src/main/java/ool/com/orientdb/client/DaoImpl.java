/**
 * @author OOL 1131080355959
 * @date 2014/02/17
 * @TODO 
 */
package ool.com.orientdb.client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ool.com.orientdb.utils.Definition;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;

/**
 * @author 1131080355959
 *
 */
public class DaoImpl implements Dao {
	
	private static final Logger logger = Logger.getLogger(DaoImpl.class);
	
	protected ConnectionUtils utils = null;
	protected ODatabaseDocumentTx database = null;
	protected List<ODocument> documents = null;

	public DaoImpl(ConnectionUtils utils) throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug(String.format("DaoImpl(utils=%s) - start", utils));
		}
		this.utils = utils;
		init();
		if (logger.isDebugEnabled()){
			logger.debug("DaoImpl() - end");
		}
	}
	
	synchronized private void init() throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug("init() - start");
		}
		database = utils.getDatabase();
		if (logger.isDebugEnabled()){
			logger.debug("init() - end");
		}
	}
	
	synchronized public void close() throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug("close() - start");
		}
		if (database != null && !database.isClosed()) {
			utils.close(database);
		}
		if (logger.isDebugEnabled()){
			logger.debug("close() - end");
		}
	}
	
	/* (non-Javadoc)
	 * @see ool.com.orientdb.client.Dao#getDeviceRid(java.lang.String)
	 */
	@Override
	public String getDeviceRid(String deviceName) throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug(String.format("getDeviceRid(deviceName=%s) - start", deviceName));
		}
		try {
			String query = String.format(Definition.SQL_GET_DEVICE, deviceName);
			if (logger.isInfoEnabled()){
				logger.info(String.format("query=%s", query));
			}
			documents = utils.query(database, query);
			if (logger.isDebugEnabled()){
				logger.debug(String.format("getDeviceRid(ret=%s) - end", documents.get(0).getIdentity().toString()));
			}
			if (documents.size() > 0) {
				return documents.get(0).getIdentity().toString();
			} else {
				return null;
			}
		} catch (Exception e){
			throw new SQLException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.client.Dao#getDeviceInfo(java.lang.String)
	 */
	@Override
	public ODocument getDeviceInfo(String deviceName) throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug(String.format("getDeviceInfo(deviceName=%s) - start", deviceName));
		}
		try {
			String query = String.format(Definition.SQL_GET_DEVICE, deviceName);
			if (logger.isInfoEnabled()){
				logger.info(String.format("query=%s", query));
			}
			documents = utils.query(database, query);
			if (logger.isDebugEnabled()){
				logger.debug(String.format("getDeviceInfo(ret=%s) - end", documents.get(0)));
			}
			return documents.get(0);			
		} catch (Exception e){
			throw new SQLException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.client.Dao#getConnectedDevice(java.lang.String)
	 */
	@Override
	public List<ODocument> getConnectedDevice(String deviceRid) throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug(String.format("getConnectedDevice(deviceRid=%s) - start", deviceRid));
		}
		try {
			String query = String.format(Definition.SQL_GET_CONNECTED_NODE, deviceRid);
			if (logger.isInfoEnabled()){
				logger.info(String.format("query=%s", query));
			}
			documents = utils.query(database, query);
			if (logger.isDebugEnabled()){
				logger.debug(String.format("getConnectedDevice(ret=%s) - end", documents));
			}
			return documents;			
		} catch (Exception e){
			throw new SQLException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.client.Dao#getPatchPortRidList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<String> getPatchPortRidList(String deviceRid) throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug(String.format("getPatchPortRidList(deviceRid=%s) - start", deviceRid));
		}
		try {
			String query = String.format(Definition.SQL_GET_PATCHPORT_RID, deviceRid);
			if (logger.isInfoEnabled()){
				logger.info(String.format("query=%s", query));
			}
			documents = utils.query(database, query);
			List<String> patchPortList = new ArrayList<String>();
			for (ODocument document : documents) {
				patchPortList.add(document.getIdentity().toString());
			}
			if (logger.isDebugEnabled()){
				logger.debug(String.format("getPatchPortRidList(ret=%s) - end", patchPortList));
			}
			return patchPortList;
		} catch (Exception e){
			throw new SQLException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.client.Dao#isContainsPatchWiring(java.util.List)
	 */
	@Override
	public boolean isContainsPatchWiring(List<String> patchPortPair) throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug(String.format("isContainsPatchWiring(patchPortPair=%s) - start", patchPortPair));
		}
		try {
			String query = String.format(Definition.SQL_GET_PATCH_WIRING, patchPortPair.get(0), patchPortPair.get(1));
			if (logger.isInfoEnabled()){
				logger.info(String.format("query=%s", query));
			}
			documents = utils.query(database, query);
			boolean ret = (documents.size() > 0) ? true : false;
			if (logger.isDebugEnabled()){
				logger.debug(String.format("isContainsPatchWiring(ret=%s) - end", ret));
			}
			return ret;	
		} catch (Exception e){
			throw new SQLException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.client.Dao#getShortestPath(java.util.List)
	 */
	@Override
	public ODocument getShortestPath(List<String> deviceRidList) throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug(String.format("getShortestPath(deviceRidList=%s) - start", deviceRidList));
		}
		try {
			String query = String.format(Definition.SQL_GET_DIJKSTRA_PATH, deviceRidList.get(0), deviceRidList.get(1));
			if (logger.isInfoEnabled()){
				logger.info(String.format("query=%s", query));
			}
			documents = utils.query(database, query);
			if (logger.isDebugEnabled()){
				logger.debug(String.format("getShortestPath(ret=%s) - end", documents.get(0)));
			}
			return documents.get(0);
		} catch (Exception e){
			throw new SQLException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.client.Dao#insertPatchWiring(java.util.List)
	 */
	@Override
	synchronized public void insertPatchWiring(List<String> portRidList, 
			String parentRid, List<String> deviceNameList) throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug(String.format("insertPatchWiring(portRidList=%s, parentRid=%s, deviceNameList=%s) - start", portRidList, parentRid, deviceNameList));
		}
		try {
			if (getPortRidPatchWiring(deviceNameList).size() > 0) {
				return; //duplicate error
			}
			String out = portRidList.get(0);
			String in = portRidList.get(1);
			String outDevName = deviceNameList.get(0);
			String inDevName = deviceNameList.get(1);
			String query = String.format(Definition.SQL_INSERT_PATCH_WIRING, out, in, parentRid, outDevName, inDevName);
			if (logger.isInfoEnabled()){
				logger.info(String.format("query=%s", query));
			}
			database.command(new OCommandSQL(query)).execute();
			out = portRidList.get(1);
			in = portRidList.get(0);
			outDevName = deviceNameList.get(1);
			inDevName = deviceNameList.get(0);
			query = String.format(Definition.SQL_INSERT_PATCH_WIRING, out, in, parentRid, outDevName, inDevName);
			if (logger.isInfoEnabled()){
				logger.info(String.format("query=%s", query));
			}
			database.command(new OCommandSQL(query)).execute();
			if (logger.isDebugEnabled()){
				logger.debug("insertPatchWiring() - end");
			}
			return;
		} catch (Exception e){
			throw new SQLException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.client.Dao#getPortRidPatchWiring(java.util.List)
	 */
	@Override
	public List<Map<String, String>> getPortRidPatchWiring(List<String> deviceNameList) throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug(String.format("getPortRidPatchWiring(deviceNameList=%s) - start", deviceNameList));
		}
		try {
			List<Map<String, String>> portRidPairList = new ArrayList<Map<String, String>>();
			Map<String, String> portRidPair = new HashMap<String, String>();
			String query = String.format(Definition.SQL_GET_PATCH_WIRING2, deviceNameList.get(0), deviceNameList.get(1));
			if (logger.isInfoEnabled()){
				logger.info(String.format("query=%s", query));
			}
			documents = utils.query(database, query);
			if (documents.size() > 0) {
				for (ODocument document : documents) {
					String out = document.field("out").toString();
					String in = document.field("in").toString();
					String parent = document.field("parent").toString();
					portRidPair.put("out", out.split("\\{")[0].substring("port".length()));
					portRidPair.put("in", in.split("\\{")[0].substring("port".length()));
					portRidPair.put("parent", parent.split("\\{")[0].substring("node".length()));
					portRidPairList.add(portRidPair);
				}
			}
			if (logger.isDebugEnabled()){
				logger.debug(String.format("getPortRidPatchWiring(ret=%s) - end", portRidPairList));
			}
			return portRidPairList;
		} catch (Exception e){
			throw new SQLException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.client.Dao#deleteRecordPathcWirng(java.util.List)
	 */
	@Override
	synchronized public void deleteRecordPatchWiring(List<String> deviceNameList) throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug(String.format("deleteRecordPatchWiring(deviceNameList=%s) - start", deviceNameList));
		}
		try {
			String query = String.format(Definition.SQL_DELETE_PATCH_WIRING, deviceNameList.get(0), deviceNameList.get(1));
			if (logger.isInfoEnabled()){
				logger.info(String.format("query=%s", query));
			}
			database.command(new OCommandSQL(query)).execute();
			query = String.format(Definition.SQL_DELETE_PATCH_WIRING, deviceNameList.get(1), deviceNameList.get(0));
			database.command(new OCommandSQL(query)).execute();
			if (logger.isDebugEnabled()){
				logger.debug("deleteRecordPatchWiring() - end");
			}
			return;
		} catch (Exception e){
			throw new SQLException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.client.Dao#getPortInfo(java.lang.String)
	 */
	@Override
	public ODocument getPortInfo(String rid) throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug(String.format("getPortInfo(rid=%s) - start", rid));
		}
		try {
			String query = String.format(Definition.SQL_GET_PORT, rid);
			if (logger.isInfoEnabled()){
				logger.info(String.format("query=%s", query));
			}
			documents = utils.query(database, query);
			if (logger.isDebugEnabled()){
				logger.debug(String.format("getPortInfo(ret=%s) - end", documents.get(0)));
			}
			return documents.get(0);			
		} catch (Exception e){
			throw new SQLException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see ool.com.orientdb.client.Dao#getPortInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public ODocument getPortInfo(String name, String deviceName) throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug(String.format("getPortInfo(name=%s, deviceName=%s) - start", name, deviceName));
		}
		try {
			String query = String.format(Definition.SQL_GET_PORT_INFO, name, deviceName);
			if (logger.isInfoEnabled()){
				logger.info(String.format("query=%s", query));
			}
			documents = utils.query(database, query);
			if (logger.isDebugEnabled()){
				logger.debug(String.format("getPortInfo(ret=%s) - end", documents.get(0)));
			}
			return documents.get(0);			
		} catch (Exception e){
			throw new SQLException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see ool.com.orientdb.client.Dao#getPortInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public ODocument getPortInfo(int number, String deviceName) throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug(String.format("getPortInfo(number=%s, deviceName=%s) - start", number, deviceName));
		}
		try {
			String query = String.format(Definition.SQL_GET_PORT_INFO2, number, deviceName);
			if (logger.isInfoEnabled()){
				logger.info(String.format("query=%s", query));
			}
			documents = utils.query(database, query);
			if (logger.isDebugEnabled()){
				logger.debug(String.format("getPortInfo(ret=%s) - end", documents.get(0)));
			}
			return documents.get(0);			
		} catch (Exception e){
			throw new SQLException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.client.Dao#updateLinkWeight(int, java.lang.String, java.lang.String)
	 */
	@Override
	synchronized public void updateLinkWeight(int weight, String portRid, String patchRid) throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug(String.format("updateLinkWeight(weight=%s, portRid=%s, patchRid=%s) - start", weight, portRid, patchRid));
		}
		try {
			String query = String.format(Definition.SQL_UPDATE_WEIGHT_TO_LINK, weight, portRid, patchRid);
			if (logger.isInfoEnabled()){
				logger.info(String.format("query=%s", query));
			}
			database.command(new OCommandSQL(query)).execute();
			query = String.format(Definition.SQL_UPDATE_WEIGHT_TO_LINK, weight, patchRid, portRid);
			if (logger.isInfoEnabled()){
				logger.info(String.format("query=%s", query));
			}
			database.command(new OCommandSQL(query)).execute();
			if (logger.isDebugEnabled()){
				logger.debug("updateLinkWeight() - end");
			}
			return;
		} catch (Exception e){
			throw new SQLException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.client.Dao#getPatchConnectedDevice(java.lang.String)
	 */
	@Override
	public List<List<String>> getPatchConnectedDevice() throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug("getPatchConnectedDevice() - start");
		}
		try {
			List<List<String>> deviceNameList = new ArrayList<List<String>>(); 
			String query = Definition.SQL_GET_PATCH_CONNECTED_NODE;
			if (logger.isInfoEnabled()){
				logger.info(String.format("query=%s", query));
			}
			documents = utils.query(database, query);
			for (ODocument document: documents) {
				List<String> connectNode = new ArrayList<String>();
				connectNode.add(document.field("outDeviceName").toString());
				connectNode.add(document.field("inDeviceName").toString());
				deviceNameList.add(connectNode);
			}
			if (logger.isDebugEnabled()){
				logger.debug(String.format("getPatchConnectedDevice(ret=%s) - end", deviceNameList));
			}
			return deviceNameList;			
		} catch (Exception e){
			throw new SQLException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.client.Dao#createNodeInfo(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public int createNodeInfo(String name, String type, boolean ofpFlag) throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug(String.format("createNodeInfo(name=%s, type=%s, ofpFlag=%s) - start", name, type, ofpFlag));
		}
		try {
			try {
				ODocument document = getDeviceInfo(name);
				return Definition.DB_RESPONSE_STATUS_EXIST; //duplicate error
			}
			catch(SQLException se){
			}
			String query = String.format(Definition.SQL_INSERT_NODE, name, type, ofpFlag);
			if (logger.isInfoEnabled()){
				logger.info(String.format("query=%s", query));
			}
			database.command(new OCommandSQL(query)).execute();
			if (logger.isDebugEnabled()){
				logger.debug("createNodeInfo() - end");
			}
			return Definition.DB_RESPONSE_STATUS_OK;
		} catch (Exception e){
			throw new SQLException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.client.Dao#createPortInfo(java.lang.String, int, java.lang.String, java.lang.String)
	 */
	@Override
	public int createPortInfo(String portName, int portNumber, String type, String deviceName) throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug(String.format("createPortInfo(portName=%s, portNumber=%s, type=%s, deviceName=%s) - start", 
					portName, portNumber, deviceName, type));
		}
		try {
			String nodeRid = "";
			try {
				ODocument document = getDeviceInfo(deviceName);
				nodeRid = document.getIdentity().toString();
			} catch(SQLException se) {
				return Definition.DB_RESPONSE_STATUS_NOT_FOUND;
			}
			try {
				ODocument document = getPortInfo(portName, deviceName);
				return Definition.DB_RESPONSE_STATUS_EXIST; //duplicate error
			} catch(SQLException se){
				try {
					ODocument document = getPortInfo(portNumber, deviceName);
					return Definition.DB_RESPONSE_STATUS_EXIST; //duplicate error
				} catch(SQLException se2){
				}
			}
			String query = String.format(Definition.SQL_INSERT_PORT, portName, portNumber, type, deviceName);
			if (logger.isInfoEnabled()){
				logger.info(String.format("query=%s", query));
			}
			database.command(new OCommandSQL(query)).execute();
			
			// get rid
			ODocument document = getPortInfo(portName, deviceName);
			String portRid = document.getIdentity().toString();
			createLinkInfo(nodeRid, portRid);
			createLinkInfo(portRid, nodeRid);
			if (logger.isDebugEnabled()){
				logger.debug("createPortInfo() - end");
			}
			return Definition.DB_RESPONSE_STATUS_OK;
		} catch (Exception e){
			throw new SQLException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.client.Dao#getLinkInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public ODocument getLinkInfo(String outRid, String inRid) throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug(String.format("getLinkInfo(inRid=%s, outRid=%s) - start", inRid, outRid));
		}
		try {
			String query = String.format(Definition.SQL_GET_LINK, outRid, inRid);
			if (logger.isInfoEnabled()){
				logger.info(String.format("query=%s", query));
			}
			documents = utils.query(database, query);
			if (logger.isDebugEnabled()){
				logger.debug(String.format("getLinkInfo(ret=%s) - end", documents.get(0)));
			}
			return documents.get(0);			
		} catch (Exception e){
			throw new SQLException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.client.Dao#createLinkInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public int createLinkInfo(String outRid, String inRid) throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug(String.format("createLinkInfo(inRid=%s, outRid=%s) - start", inRid, outRid));
		}
		try {
			try {
				ODocument document = getLinkInfo(outRid, inRid);
				return Definition.DB_RESPONSE_STATUS_EXIST; //duplicate error
			}
			catch(SQLException se){
			}
			String query = String.format(Definition.SQL_INSERT_LINK, outRid, inRid);
			if (logger.isInfoEnabled()){
				logger.info(String.format("query=%s", query));
			}
			database.command(new OCommandSQL(query)).execute();
			if (logger.isDebugEnabled()){
				logger.debug("createLinkInfo() - end");
			}
			return Definition.DB_RESPONSE_STATUS_OK;
		} catch (Exception e){
			throw new SQLException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.client.Dao#deleteLinkInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public int deleteLinkInfo(String outRid, String inRid) throws SQLException {
		if (logger.isDebugEnabled()){
			logger.debug(String.format("deleteLinkInfo(inRid=%s, outRid=%s) - start", inRid, outRid));
		}
		try {
			try {
				ODocument document = getLinkInfo(outRid, inRid);
			}
			catch(SQLException se){
				return Definition.DB_RESPONSE_STATUS_NOT_FOUND; //duplicate error
			}
			String query = String.format(Definition.SQL_DELETE_LINK, outRid, inRid);
			if (logger.isInfoEnabled()){
				logger.info(String.format("query=%s", query));
			}
			database.command(new OCommandSQL(query)).execute();
			if (logger.isDebugEnabled()){
				logger.debug("deleteLinkInfo() - end");
			}
			return Definition.DB_RESPONSE_STATUS_OK;
		} catch (Exception e){
			throw new SQLException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.client.Dao#updateNodeInfo(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public int updateNodeInfo(String key, String name, boolean ofpFlag)
			throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
