/**
 * @author OOL 1131080355959
 * @date 2014/03/04
 * @TODO 
 */
package ool.com.orientdb.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ool.com.orientdb.business.DeviceManagerBusiness;
import ool.com.orientdb.business.DeviceManagerBusinessImpl;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * @author 1131080355959
 *
 */
public class DeviceManagerServiceImpl implements DeviceManagerService {
	
	private static final Logger logger = Logger.getLogger(DeviceManagerServiceImpl.class);
	
    @Inject
    DeviceManagerBusiness dmb;

    Injector injector;

	/* (non-Javadoc)
	 * @see ool.com.orientdb.service.DeviceManagerService#createDeviceInfo(java.lang.String)
	 */
	@Override
	public Response createDeviceInfo(@RequestBody String params) {
    	if (logger.isDebugEnabled()) {
    		logger.debug(String.format("createDeviceInfo(params=%s) - start ", params));
    	}
    	
        this.injector = Guice.createInjector(new AbstractModule() {
            @Override 
            protected void configure() {
            	bind(DeviceManagerBusiness.class).to(DeviceManagerBusinessImpl.class);
            }
        });

        DeviceManagerServiceImpl main = injector.getInstance(DeviceManagerServiceImpl.class);
        String ret = main.dmb.createDeviceInfo(params);
        
        if (logger.isDebugEnabled()) {
    		logger.debug(String.format("createDeviceInfo(ret=%s) - end ", ret));
    	}
		return Response.ok(ret).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.service.DeviceManagerService#createPortInfo(java.lang.String)
	 */
	@Override
	public Response createPortInfo(@RequestBody String params) {
    	if (logger.isDebugEnabled()) {
    		logger.debug(String.format("createPortInfo(params=%s) - start ", params));
    	}
    	
        this.injector = Guice.createInjector(new AbstractModule() {
            @Override 
            protected void configure() {
            	bind(DeviceManagerBusiness.class).to(DeviceManagerBusinessImpl.class);
            }
        });

        DeviceManagerServiceImpl main = injector.getInstance(DeviceManagerServiceImpl.class);
        String ret = main.dmb.createPortInfo(params);
        
        if (logger.isDebugEnabled()) {
    		logger.debug(String.format("createPortInfo(ret=%s) - end ", ret));
    	}
		return Response.ok(ret).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.service.DeviceManagerService#updateDeviceInfo(java.lang.String)
	 */
	@Override
	public Response updateDeviceInfo(@RequestBody String params) {
    	if (logger.isDebugEnabled()) {
    		logger.debug(String.format("updateDeviceInfo(params=%s) - start ", params));
    	}
    	
        this.injector = Guice.createInjector(new AbstractModule() {
            @Override 
            protected void configure() {
            	bind(DeviceManagerBusiness.class).to(DeviceManagerBusinessImpl.class);
            }
        });

        DeviceManagerServiceImpl main = injector.getInstance(DeviceManagerServiceImpl.class);
        String ret = main.dmb.updateDeviceInfo(params);
        
        if (logger.isDebugEnabled()) {
    		logger.debug(String.format("updateDeviceInfo(ret=%s) - end ", ret));
    	}
		return Response.ok(ret).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

}
