package ool.com.orientdb.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;

@Path("/topology")
public interface TopologyService {
	
    @GET
    @Path("/logicalTopology")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    Response getLogicalTopology(@QueryParam("deviceNames") String deviceNames);
    
    @POST
    @Path("/physicalTopology/connect")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    Response createWiring(@RequestBody String params);
    
    @POST
    @Path("/physicalTopology/disconnect")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    Response deleteWiring(@RequestBody String params);
}
