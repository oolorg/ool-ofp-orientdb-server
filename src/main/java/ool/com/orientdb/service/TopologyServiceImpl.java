/**
 * 
 */
package ool.com.orientdb.service;

import java.lang.reflect.Type;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ool.com.orientdb.business.TopologyBusiness;
import ool.com.orientdb.business.TopologyBusinessImpl;
import ool.com.orientdb.json.LogicalTopologyJsonGetOut;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

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
@Component
public class TopologyServiceImpl implements TopologyService {
	
	private static final Logger logger = Logger.getLogger(TopologyServiceImpl.class);

    @Inject
    TopologyBusiness tb;

    Injector injector;
    
    Gson gson = new Gson();
	
    @Override
    public Response getLogicalTopology(String deviceNames) {
    	if (logger.isDebugEnabled()) {
    		logger.debug(String.format("getLogicalTopology(deviceNames=%s) - start ", deviceNames));
    	}
    	
        this.injector = Guice.createInjector(new AbstractModule() {
            @Override 
            protected void configure() {
            	bind(TopologyBusiness.class).to(TopologyBusinessImpl.class);
            }
        });
    	TopologyServiceImpl main = injector.getInstance(TopologyServiceImpl.class);
    	LogicalTopologyJsonGetOut ret = main.tb.getTopology(deviceNames);
        
    	Type type = new TypeToken<LogicalTopologyJsonGetOut>(){}.getType();
        String outPara = gson.toJson(ret, type);
        
        if (logger.isDebugEnabled()) {
    		logger.debug(String.format("getLogicalTopology(outPara=%s) - end ", outPara));
    	}
		return Response.ok(outPara).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
