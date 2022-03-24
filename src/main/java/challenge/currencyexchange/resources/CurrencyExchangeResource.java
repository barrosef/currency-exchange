package challenge.currencyexchange.resources;

import challenge.currencyexchange.service.ExchangeRateService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.ACCEPTED;
import static javax.ws.rs.core.Response.Status.CREATED;

@ApplicationScoped
@Path("convert")
@Tag(name = "convertion", description = "Currency convertion")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CurrencyExchangeResource {

    @Inject
    ExchangeRateService service;

    @HeaderParam("user_login")
    @NotNull
    String requestUserLogin;

    @POST
    @Operation(summary = "To convert currency",
            description = "Convert from EUR from any currency")
    @APIResponses(value = @APIResponse(responseCode = "201", description = "Currency conversion success",
            content = @Content(mediaType = "application/json")))
    public Response postToConvert(@Valid ExchangeRateRequest exchangeRateRequest) {
        return Response
                .ok(service.rate(exchangeRateRequest))
                .status(CREATED)
                .build();
    }

    @GET
    @Operation(summary = "To list user conversions",
            description = "List conversions")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "List user conversions success",
            content = @Content(mediaType = "application/json")))
    public Response getExchangeList() {
        return Response
                .ok(service.list(this.requestUserLogin))
                .status(ACCEPTED)
                .build();
    }
}
