package client;

import dto.CustomerDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;

@Path("/customers")
@RegisterRestClient(configKey="client-api")
public interface CustomerClient {

    @GET
    @Path("/{id}")
    CustomerDTO findCustomerById(@PathParam("id") Long id);
}
