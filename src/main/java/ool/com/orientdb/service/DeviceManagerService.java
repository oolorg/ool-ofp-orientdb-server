package ool.com.orientdb.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    @Path("/nodeUpdate")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    Response updateDeviceInfo(@RequestBody String params);

    @DELETE
    @Path("/nodeDelete")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    Response deleteDeviceInfo(@QueryParam("deviceName") String params);

    @POST
    @Path("/portCreate")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    Response createPortInfo(@RequestBody String params);

    @PUT
    @Path("/portUpdate")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    Response updatePortInfo(@RequestBody String params);

    @DELETE
    @Path("/portDelete")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    Response deletePortInfo(@QueryParam("portName") String portName, @QueryParam("deviceName") String deviceName);
    
    @GET
    @Path("/connectedPort")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    Response getConnectedPortInfo(@QueryParam("deviceName") String deviceName);
}
