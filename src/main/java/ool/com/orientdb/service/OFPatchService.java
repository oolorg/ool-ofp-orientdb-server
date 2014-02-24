package ool.com.orientdb.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;

@Path("/ofpatch")
public interface OFPatchService {   
    
    @POST
    @Path("/connect")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    Response connectPatchWiring(@RequestBody String params);
    
    @POST
    @Path("/disconnect")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    Response disConnectPatchWiring(@RequestBody String params);
}
