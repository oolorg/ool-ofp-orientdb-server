/**
 * @author OOL 1131080355959
 * @date 2014/02/05
 * @TODO TODO
 */
package ool.com.orientdb.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.orientechnologies.orient.core.record.impl.ODocument;

import ool.com.orientdb.client.ConnectionUtils;
import ool.com.orientdb.client.ConnectionUtilsImpl;
import ool.com.orientdb.client.Dao;
import ool.com.orientdb.client.DaoImpl;
import ool.com.orientdb.json.LinkDevice;
import ool.com.orientdb.json.Node;
import ool.com.orientdb.json.LogicalTopologyJsonGetOut;
import ool.com.orientdb.utils.Definition;


/**
 * @author 1131080355959
 *
 */
public class TopologyBusinessImpl implements TopologyBusiness {
	
	private static final Logger logger = Logger.getLogger(TopologyBusinessImpl.class);

	/* (non-Javadoc)
	 * @see ool.com.ofpm.business.TopologyBusiness#createHello(ool.com.ofpm.json.TopologyJsonGetIn)
	 */
	@Override
	public LogicalTopologyJsonGetOut getTopology(String deviceNames) {
		if (logger.isDebugEnabled()) {
    		logger.debug(String.format("getTopology(deviceNames=%s) - start ", deviceNames));
    	}
		
    	LogicalTopologyJsonGetOut ret = new LogicalTopologyJsonGetOut();
    	LogicalTopologyJsonGetOut.ResultData resultData = ret.new ResultData();
    	Dao dao = null;
    	
    	try {
        	ConnectionUtils utils = new ConnectionUtilsImpl();
        	dao = new DaoImpl(utils);

    		List<String> deviceNameList = Arrays.asList(deviceNames.split(Definition.QUERY_SPLIT_WORD_COMMA));
			ODocument document = null;
			List<List<String>> connectNodeList = new ArrayList<List<String>>();
			String rid = "#0";
			
			// json
			List<Node> nodeList = new ArrayList<Node>();
			List<LinkDevice> linkList = new ArrayList<LinkDevice>();
			Node node = null;
			LinkDevice link = null;
			// connected patch port rid list
			//List<String> patchPortList = new ArrayList<String>();
			// key:patch port　rid value:connected deviceName
			//Map<String,String> mapPatchPortDevice = new HashMap<String,String>();
			
			
			for (String deviceName : deviceNameList) {
				
				document = dao.getDeviceInfo(deviceName);
				node = new Node();
				node.setDeviceName(document.field("name").toString());
				node.setDeviceType(document.field("type").toString());
				nodeList.add(node);
				/*
				rid = document.getIdentity().toString();

				// get connected patch port list to connected device 
				List<String> patchPortRidList = dao.getPatchPortRidList(rid);
				for (String portRid : patchPortRidList) {
					patchPortList.add(portRid);
					mapPatchPortDevice.put(portRid, deviceName);
				}*/
			}
			List<List<String>> connectedDeviceNameList = dao.getPatchConnectedDevice();
			for(List<String> connectNode : connectedDeviceNameList) {
				if (isOverlap(connectNodeList, connectNode)) {
					continue;
				}
				connectNodeList.add(connectNode);
			}
/*
			List<List<String>> patchPortPairList = getPatchPortPairList(patchPortList);
				
			// check patch wiring
			for (List<String> patchPortPair : patchPortPairList) {
				if (dao.isContainsPatchWiring(patchPortPair)) {
					connectNode = new ArrayList<String>();
					connectNode.add(mapPatchPortDevice.get(patchPortPair.get(0)));
					connectNode.add(mapPatchPortDevice.get(patchPortPair.get(1)));
					if (isOverlap(connectNodeList, connectNode)) {
						continue;
					}
					connectNodeList.add(connectNode);
				}
			}
			*/
			
			// create response data
			resultData.setNode(nodeList);
			for (List<String> cn : connectNodeList) {
				link = new LinkDevice();
				link.setDeviceName(cn);
				linkList.add(link);
			}
			resultData.setLink(linkList);
			ret.setResultData(resultData);
			ret.setStatusCode(Definition.HTTP_STATUS_CODE_OK);			
    	} catch (SQLException e) {
    		logger.error(e.getMessage());
    		ret.setStatusCode(Definition.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR);
    		ret.setMessage(e.getMessage());
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			ret.setStatusCode(Definition.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR);
    		ret.setMessage(re.getMessage());
		}
    	finally {
			try {
				dao.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				ret.setStatusCode(Definition.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR);
	    		ret.setMessage(e.getMessage());
			}
		}
		if (logger.isDebugEnabled()) {
    		logger.debug(String.format("getTopology(ret=%s) - end ", ret));
    	}
		return ret;
	}
	
	private boolean isOverlap(List<List<String>> dataList, List<String> data) {
		if (logger.isDebugEnabled()) {
    		logger.debug(String.format("isOverlap(dataList=%s, data=%s) - start ", dataList, data));
    	}
		boolean oneWordOverlapFlg = false;
		for (List<String> dataSet : dataList) {
			oneWordOverlapFlg = false;
			for ( String str : data) {
				if (!dataSet.contains(str)) {
					break;
				} else {
					if (oneWordOverlapFlg) {
						if (logger.isDebugEnabled()) {
				    		logger.debug(String.format("isOverlap(ret=%s) - end ", true));
				    	}
						return true;
					} else {
						oneWordOverlapFlg = true;
					}
				}
			}
		}
		if (logger.isDebugEnabled()) {
    		logger.debug(String.format("isOverlap(ret=%s) - end ", false));
    	}
		return false;
	}
	
	private List<List<String>> getPatchPortPairList(List<String> patchPortList) {
		if (logger.isDebugEnabled()) {
    		logger.debug(String.format("getPatchPortPairList(patchPortList=%s) - start ", patchPortList));
    	}
		List<List<String>> patchPortPairList = new ArrayList<List<String>>();
		
		for(int i = 0; i < patchPortList.size() - 1; i++){
			for(int j = i + 1; j < patchPortList.size(); j++){
				List<String> patchPortPair = new ArrayList<String>();
				patchPortPair.add(patchPortList.get(i));
				patchPortPair.add(patchPortList.get(j));
				patchPortPairList.add(patchPortPair);
			}
		}
		if (logger.isDebugEnabled()) {
    		logger.debug(String.format("getPatchPortPairList(ret=%s) - end ", patchPortPairList));
    	}
		return patchPortPairList;
	}
	
}
