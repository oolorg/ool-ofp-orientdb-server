package ool.com.orientdb.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;

@Path("/deviceManager")
public interface DeviceManagerService {
	
    @POST
    @Path("/nodeCreate")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    Response createDeviceInfo(@RequestBody String params);
    
    @PUT
    @Path("/nodeCUpdate")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    Response updateDeviceInfo(@RequestBody String params);
    
    @POST
    @Path("/portCreate")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    Response createPortInfo(@RequestBody String params);
}
