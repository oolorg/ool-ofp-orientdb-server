/**
 * @author OOL 1131080355959
 * @date 2014/03/04
 * @TODO 
 */
package ool.com.orientdb.business;

import java.lang.reflect.Type;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ool.com.orientdb.client.ConnectionUtils;
import ool.com.orientdb.client.ConnectionUtilsImpl;
import ool.com.orientdb.client.Dao;
import ool.com.orientdb.client.DaoImpl;
import ool.com.orientdb.json.DeviceManagerCreateDeviceInfoJsonIn;
import ool.com.orientdb.json.DeviceManagerCreateDeviceInfoJsonOut;
import ool.com.orientdb.json.DeviceManagerCreatePortInfoJsonIn;
import ool.com.orientdb.json.DeviceManagerCreatePortInfoJsonOut;
import ool.com.orientdb.utils.Definition;
import ool.com.orientdb.utils.ErrorMessage;

/**
 * @author 1131080355959
 *
 */
public class DeviceManagerBusinessImpl implements DeviceManagerBusiness {
	
	private static final Logger logger = Logger.getLogger(DeviceManagerBusinessImpl.class);
	
	Gson gson = new Gson();

	/* (non-Javadoc)
	 * @see ool.com.orientdb.business.DeviceManagerBusiness#createDeviceInfo(java.lang.String)
	 */
	@Override
	public String createDeviceInfo(String params) {
    	if (logger.isDebugEnabled()) {
    		logger.debug(String.format("createDeviceInfo(params=%s) - start ", params));
    	}
    	
		String ret = "";
		Dao dao = null;
		DeviceManagerCreateDeviceInfoJsonOut outPara = new DeviceManagerCreateDeviceInfoJsonOut();
				
		try {
	        Type type = new TypeToken<DeviceManagerCreateDeviceInfoJsonIn>(){}.getType();
	        DeviceManagerCreateDeviceInfoJsonIn inPara = gson.fromJson(params, type);
	        
			ConnectionUtils utils = new ConnectionUtilsImpl();
			dao = new DaoImpl(utils);
			
			if (dao.createNodeInfo(inPara.getDeviceName(), inPara.getDeviceType(), inPara.getOfpFlag()) == Definition.DB_RESPONSE_STATUS_EXIST) {
				outPara.setStatusCode(Definition.HTTP_STATUS_CODE_BAD_REQUEST);
				outPara.setMessage(String.format(ErrorMessage.ALREADY_EXIST, inPara.getDeviceName()));
			} else {
				outPara.setStatusCode(Definition.HTTP_STATUS_CODE_CREATED);
			}
    	} catch (SQLException e) {
    		logger.error(e.getMessage());
    		outPara.setStatusCode(Definition.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR);
    		outPara.setMessage(e.getMessage());
		}  catch (RuntimeException re) {
			logger.error(re.getMessage());
			outPara.setStatusCode(Definition.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR);
			outPara.setMessage(re.getMessage());
		} finally {
			try {
				dao.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				outPara.setStatusCode(Definition.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR);
				outPara.setMessage(e.getMessage());
			}
			Type type = new TypeToken<DeviceManagerCreateDeviceInfoJsonOut>(){}.getType();
	        ret = gson.toJson(outPara, type);
		}
		if (logger.isDebugEnabled()) {
    		logger.debug(String.format("createDeviceInfo(ret=%s) - end ", ret));
    	}
		return ret;
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.business.DeviceManagerBusiness#createPortInfo(java.lang.String)
	 */
	@Override
	public String createPortInfo(String params) {
    	if (logger.isDebugEnabled()) {
    		logger.debug(String.format("createPortInfo(params=%s) - start ", params));
    	}
    	
		String ret = "";
		Dao dao = null;
		DeviceManagerCreatePortInfoJsonOut outPara = new DeviceManagerCreatePortInfoJsonOut();
				
		try {
	        Type type = new TypeToken<DeviceManagerCreatePortInfoJsonIn>(){}.getType();
	        DeviceManagerCreatePortInfoJsonIn inPara = gson.fromJson(params, type);
	        
			ConnectionUtils utils = new ConnectionUtilsImpl();
			dao = new DaoImpl(utils);
			
			if (dao.createPortInfo(inPara.getPortName(), inPara.getPortNumber(), inPara.getType(), inPara.getDeviceName()) == Definition.DB_RESPONSE_STATUS_EXIST) {
				outPara.setStatusCode(Definition.HTTP_STATUS_CODE_BAD_REQUEST);
				outPara.setMessage(String.format(ErrorMessage.ALREADY_EXIST, inPara.getPortName()));
			} else {
				outPara.setStatusCode(Definition.HTTP_STATUS_CODE_CREATED);
			}
    	} catch (SQLException e) {
    		logger.error(e.getMessage());
    		outPara.setStatusCode(Definition.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR);
    		outPara.setMessage(e.getMessage());
		}  catch (RuntimeException re) {
			logger.error(re.getMessage());
			outPara.setStatusCode(Definition.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR);
			outPara.setMessage(re.getMessage());
		} finally {
			try {
				dao.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				outPara.setStatusCode(Definition.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR);
				outPara.setMessage(e.getMessage());
			}
			Type type = new TypeToken<DeviceManagerCreatePortInfoJsonOut>(){}.getType();
	        ret = gson.toJson(outPara, type);
		}
		if (logger.isDebugEnabled()) {
    		logger.debug(String.format("createPortInfo(ret=%s) - end ", ret));
    	}
		return ret;
	}

}