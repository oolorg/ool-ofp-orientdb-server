/**
 * @author OOL 1131080355959
 * @date 2014/02/05
 * @TODO TODO
 */
package ool.com.orientdb.business;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ool.com.orientdb.client.ConnectionUtils;
import ool.com.orientdb.client.ConnectionUtilsImpl;
import ool.com.orientdb.client.Dao;
import ool.com.orientdb.client.DaoImpl;
import ool.com.orientdb.json.LinkDevice;
import ool.com.orientdb.json.LogicalTopologyJsonGetOut;
import ool.com.orientdb.json.Node;
import ool.com.orientdb.json.Port;
import ool.com.orientdb.json.TopologyCreateWiringJsonIn;
import ool.com.orientdb.json.TopologyCreateWiringJsonOut;
import ool.com.orientdb.json.TopologyDeleteWiringJsonIn;
import ool.com.orientdb.json.TopologyDeleteWiringJsonOut;
import ool.com.orientdb.utils.Definition;
import ool.com.orientdb.utils.ErrorMessage;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orientechnologies.orient.core.record.impl.ODocument;


/**
 * @author 1131080355959
 *
 */
public class TopologyBusinessImpl implements TopologyBusiness {

	private static final Logger logger = Logger.getLogger(TopologyBusinessImpl.class);

	Gson gson = new Gson();

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

			// json
			List<Node> nodeList = new ArrayList<Node>();
			List<LinkDevice> linkList = new ArrayList<LinkDevice>();
			Node node = null;
			LinkDevice link = null;

			for (String deviceName : deviceNameList) {

				document = dao.getDeviceInfo(deviceName);
				node = new Node();
				node.setDeviceName(document.field("name").toString());
				node.setDeviceType(document.field("type").toString());
				nodeList.add(node);
			}
//			List<List<String>> connectedDeviceNameList = dao.getPatchConnectedDevice();
			List<List<String>> connectedDeviceNameList = new ArrayList<List<String>>();
			for (String deviceName : deviceNameList) {
				connectedDeviceNameList.addAll(dao.getPatchConnectedDevice(deviceName));
			}
			for(List<String> connectNode : connectedDeviceNameList) {
				if (isOverlap(connectNodeList, connectNode)) {
					continue;
				}
				connectNodeList.add(connectNode);
			}

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
			ret.setMessage(e.getMessage());
    		if (e.getCause() != null) {
    			ret.setStatusCode(Definition.HTTP_STATUS_CODE_NOT_FOUND);
    		} else {
    			ret.setStatusCode(Definition.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR);
    		}
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			ret.setStatusCode(Definition.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR);
    		ret.setMessage(re.getMessage());
		}
    	finally {
			try {
				if(dao != null) {
					dao.close();
				}
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

	/* (non-Javadoc)
	 * @see ool.com.orientdb.business.TopologyBusiness#createWiring(java.lang.String)
	 */
	@Override
	public String createWiring(String params) {
    	if (logger.isDebugEnabled()) {
    		logger.debug(String.format("createWiring(params=%s) - start ", params));
    	}

		String ret = "";
		Dao dao = null;
		TopologyCreateWiringJsonOut outPara = new TopologyCreateWiringJsonOut();

		try {
	        Type type = new TypeToken<TopologyCreateWiringJsonIn>(){}.getType();
	        TopologyCreateWiringJsonIn inPara = gson.fromJson(params, type);

			ConnectionUtils utils = new ConnectionUtilsImpl();
			dao = new DaoImpl(utils);

			List<Port> portList = inPara.getLink();
			String rid1 = "";
			String rid2 = "";

			// portList 2つ以上アウト

			// get port rid
			ODocument doc = dao.getPortInfo(portList.get(0).getPortName(), portList.get(0).getDeviceName());
			rid1 = doc.getIdentity().toString();
			doc = dao.getPortInfo(portList.get(1).getPortName(), portList.get(1).getDeviceName());
			rid2 = doc.getIdentity().toString();

			if (rid1.isEmpty()) {
				outPara.setMessage(String.format(ErrorMessage.NOT_FOUND, portList.get(0).getPortName() + "," + portList.get(0).getDeviceName()));
				outPara.setStatusCode(Definition.HTTP_STATUS_CODE_NOT_FOUND);
				return ret;
			} else if (rid2.isEmpty()) {
				outPara.setMessage(String.format(ErrorMessage.NOT_FOUND, portList.get(1).getPortName() + "," + portList.get(1).getDeviceName()));
				outPara.setStatusCode(Definition.HTTP_STATUS_CODE_NOT_FOUND);
				return ret;
			}

			if (dao.createLinkInfo(rid1, rid2) == Definition.DB_RESPONSE_STATUS_EXIST) {
				outPara.setStatusCode(Definition.HTTP_STATUS_CODE_CONFLICT);
				outPara.setMessage(String.format(ErrorMessage.ALREADY_EXIST, portList.get(0).getPortName() + "," + portList.get(1).getPortName()));
				return ret;
			}
			if (dao.createLinkInfo(rid2, rid1) == Definition.DB_RESPONSE_STATUS_EXIST) {
				outPara.setStatusCode(Definition.HTTP_STATUS_CODE_CONFLICT);
				outPara.setMessage(String.format(ErrorMessage.ALREADY_EXIST, portList.get(0).getPortName() + "," + portList.get(1).getPortName()));
				return ret;
			}
			outPara.setStatusCode(Definition.HTTP_STATUS_CODE_CREATED);
    	} catch (SQLException e) {
    		logger.error(e.getMessage());
    		outPara.setMessage(e.getMessage());
    		if (e.getCause() == null) {
	    		outPara.setStatusCode(Definition.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR);
    		} else {
    			outPara.setStatusCode(Definition.HTTP_STATUS_CODE_NOT_FOUND);
    		}
		}  catch (RuntimeException re) {
			logger.error(re.getMessage());
			outPara.setStatusCode(Definition.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR);
			outPara.setMessage(re.getMessage());
		} finally {
			try {
				if(dao != null) {
					dao.close();
				}
			} catch (final SQLException e) {
				logger.error(e.getMessage());
				outPara.setStatusCode(Definition.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR);
				outPara.setMessage(e.getMessage());
			}
			Type type = new TypeToken<TopologyCreateWiringJsonOut>(){}.getType();
	        ret = gson.toJson(outPara, type);
			if (logger.isDebugEnabled()) {
	    		logger.debug(String.format("createWiring(ret=%s) - end ", ret));
	    	}
			return ret;
		}
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.business.TopologyBusiness#deleteWiring(java.lang.String)
	 */
	@Override
	public String deleteWiring(String params) {
    	if (logger.isDebugEnabled()) {
    		logger.debug(String.format("deleteWiring(params=%s) - start ", params));
    	}

		String ret = "";
		Dao dao = null;
		TopologyDeleteWiringJsonOut outPara = new TopologyDeleteWiringJsonOut();

		try {
	        Type type = new TypeToken<TopologyDeleteWiringJsonIn>(){}.getType();
	        TopologyDeleteWiringJsonIn inPara = gson.fromJson(params, type);

			ConnectionUtils utils = new ConnectionUtilsImpl();
			dao = new DaoImpl(utils);

			List<Port> portList = inPara.getLink();
			String rid1 = "";
			String rid2 = "";

			// portList 2つ以上アウト

			// get port rid
			ODocument doc = dao.getPortInfo(portList.get(0).getPortName(), portList.get(0).getDeviceName());
			rid1 = doc.getIdentity().toString();
			doc = dao.getPortInfo(portList.get(1).getPortName(), portList.get(1).getDeviceName());
			rid2 = doc.getIdentity().toString();

			if (rid1.isEmpty()) {
				outPara.setMessage(String.format(ErrorMessage.NOT_FOUND, portList.get(0).getPortName() + "," + portList.get(0).getDeviceName()));
				outPara.setStatusCode(Definition.HTTP_STATUS_CODE_NOT_FOUND);
				return ret;
			} else if (rid2.isEmpty()) {
				outPara.setMessage(String.format(ErrorMessage.NOT_FOUND, portList.get(1).getPortName() + "," + portList.get(1).getDeviceName()));
				outPara.setStatusCode(Definition.HTTP_STATUS_CODE_NOT_FOUND);
				return ret;
			}

			if (dao.deleteLinkInfo(rid1, rid2) == Definition.DB_RESPONSE_STATUS_NOT_FOUND) {
				outPara.setStatusCode(Definition.HTTP_STATUS_CODE_NOT_FOUND);
				outPara.setMessage(String.format(ErrorMessage.NOT_FOUND, portList.get(0).getPortName() + "," + portList.get(1).getPortName()));
			} else {
				if (dao.deleteLinkInfo(rid2, rid1) == Definition.DB_RESPONSE_STATUS_NOT_FOUND) {
					outPara.setStatusCode(Definition.HTTP_STATUS_CODE_NOT_FOUND);
					outPara.setMessage(String.format(ErrorMessage.NOT_FOUND, portList.get(0).getPortName() + "," + portList.get(1).getPortName()));
				} else {
					outPara.setStatusCode(Definition.HTTP_STATUS_CODE_OK);
				}
			}
    	} catch (SQLException e) {
    		logger.error(e.getMessage());
    		outPara.setMessage(e.getMessage());
    		if (e.getCause() == null) {
	    		outPara.setStatusCode(Definition.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR);
    		} else {
    			outPara.setStatusCode(Definition.HTTP_STATUS_CODE_NOT_FOUND);
    		}
		}  catch (RuntimeException re) {
			logger.error(re.getMessage());
			outPara.setStatusCode(Definition.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR);
			outPara.setMessage(re.getMessage());
		} finally {
			try {
				if(dao != null) {
					dao.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage());
				outPara.setStatusCode(Definition.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR);
				outPara.setMessage(e.getMessage());
			}
			Type type = new TypeToken<TopologyDeleteWiringJsonOut>(){}.getType();
	        ret = gson.toJson(outPara, type);
			if (logger.isDebugEnabled()) {
	    		logger.debug(String.format("deleteWiring(ret=%s) - end ", ret));
	    	}
			return ret;
		}
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
