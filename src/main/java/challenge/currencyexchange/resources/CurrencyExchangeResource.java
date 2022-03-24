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

    @POST
    @Operation(summary = "To convert currency",
            description = "Convert from EUR from any currency")
    @APIResponses(value = @APIResponse(responseCode = "201", description = "Currency conversion success",
            content = @Content(mediaType = "application/json")))
    public Response postToConvert(@Valid ExchangeRateRequest exchangeRateRequest,
                                  @NotNull @HeaderParam("user_login") String requestUserLogin) {
        try {
            exchangeRateRequest.setRequestUserLogin(requestUserLogin);
            return Response
                    .ok(service.rate(exchangeRateRequest))
                    .status(CREATED)
                    .build();

        } catch (WebApplicationException e) {
            return this.handleException(e);
        }
    }

    @GET
    @Operation(summary = "To list user conversions",
            description = "List conversions")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "List user conversions success",
            content = @Content(mediaType = "application/json")))
    public Response getExchangeList(
            @NotNull @HeaderParam("user_login") String requestUserLogin) {
        try {
            return Response
                    .ok(service.list(requestUserLogin))
                    .status(ACCEPTED)
                    .build();

        } catch(WebApplicationException e) {
            return this.handleException(e);
        }
    }

    private Response handleException(WebApplicationException e) {
        return Response.ok(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(e.getResponse().getStatus())
                        .build())
                .status(e.getResponse().getStatus())
                .build();
    }
}
