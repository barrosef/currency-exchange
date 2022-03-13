package challenge.currencyexchange.resources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.CREATED;

@ApplicationScoped
@Path("convert")
@Tag(name = "convertion", description = "Currency convertion")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CurrencyExchange {

    @POST
    @Operation(summary = "To convert currency",
            description = "Convert from EUR from any currency")
    @APIResponses(value = @APIResponse(responseCode = "201", description = "Currency conversion success",
            content = @Content(mediaType = "application/json")))
    public Response convert() {
        return Response.ok().status(CREATED).build();
    }

}
