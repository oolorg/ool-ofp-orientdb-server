/**
 * @author OOL 1131080355959
 * @date 2014/02/17
 * @TODO 
 */
package ool.com.orientdb.service;

import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ool.com.orientdb.business.OFPatchBusiness;
import ool.com.orientdb.business.OFPatchBusinessImpl;
import ool.com.orientdb.json.ConnectPatchJsonIn;
import ool.com.orientdb.json.ConnectPatchJsonOut;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * @author 1131080355959
 *
 */
public class OFPatchServiceImpl implements OFPatchService {
	
	private static final Logger logger = Logger.getLogger(OFPatchServiceImpl.class);
	
    @Inject
    OFPatchBusiness ofpb;

    Injector injector;
    
    Gson gson = new Gson();

	/* (non-Javadoc)
	 * @see ool.com.orientdb.service.OFPatchService#createPatchWiring(java.lang.String)
	 */
	@Override
	public Response connectPatchWiring(@RequestBody String params) {
    	if (logger.isDebugEnabled()) {
    		logger.debug(String.format("connectPatchWiring(params=%s) - start ", params));
    	}
    	
        this.injector = Guice.createInjector(new AbstractModule() {
            @Override 
            protected void configure() {
            	bind(OFPatchBusiness.class).to(OFPatchBusinessImpl.class);
            }
        });
        
        Type type = new TypeToken<ConnectPatchJsonIn>(){}.getType();
        ConnectPatchJsonIn inPara = gson.fromJson(params, type);
        
        OFPatchServiceImpl main = injector.getInstance(OFPatchServiceImpl.class);
        ConnectPatchJsonOut ret = main.ofpb.connectPatch(inPara);
    	
        type = new TypeToken<ConnectPatchJsonOut>(){}.getType();
        String outPara = gson.toJson(ret, type);
        
        if (logger.isDebugEnabled()) {
    		logger.debug(String.format("connectPatchWiring(outPara=%s) - end ", outPara));
    	}
		return Response.ok(outPara).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	/* (non-Javadoc)
	 * @see ool.com.orientdb.service.OFPatchService#disConnectPatchWiring(java.lang.String)
	 */
	@Override
	public Response disConnectPatchWiring(@RequestBody String params) {
    	if (logger.isDebugEnabled()) {
    		logger.debug(String.format("disConnectPatchWiring(params=%s) - start ", params));
    	}
    	
        this.injector = Guice.createInjector(new AbstractModule() {
            @Override 
            protected void configure() {
            	bind(OFPatchBusiness.class).to(OFPatchBusinessImpl.class);
            }
        });
        
        Type type = new TypeToken<ConnectPatchJsonIn>(){}.getType();
        ConnectPatchJsonIn inPara = gson.fromJson(params, type);
        
        OFPatchServiceImpl main = injector.getInstance(OFPatchServiceImpl.class);
        ConnectPatchJsonOut ret = main.ofpb.disConnectPatch(inPara);
    	
        type = new TypeToken<ConnectPatchJsonOut>(){}.getType();
        String outPara = gson.toJson(ret, type);
        
        if (logger.isDebugEnabled()) {
    		logger.debug(String.format("disConnectPatchWiring(outPara=%s) - end ", outPara));
    	}
		return Response.ok(outPara).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

}
