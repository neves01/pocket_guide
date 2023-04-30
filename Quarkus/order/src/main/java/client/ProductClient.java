package client;

import dto.ProductDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/products")
@RegisterRestClient(configKey = "product-api")
public interface ProductClient {

    @GET
    @Path("/{id}")
    ProductDTO getProductById(@PathParam("id") Long id);
}
