package ool.com.orientdb.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/topology")
public interface TopologyService {
	
    @GET
    @Path("/logicalTopology")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    Response getLogicalTopology(@QueryParam("deviceNames") String deviceNames);
}
