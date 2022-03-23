package challenge.currencyexchange.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;


@Path(ExchangeRatesClient.REST_SERVER_PATH)
@RegisterRestClient
public interface ExchangeRatesClient {
    String REST_SERVER_PATH = "/latest";
    String ACCESS_KEY_PARAM = "access_key";
    String BASE_PARAM = "base";
    String SYMBOLS_PARAM = "symbols";

    @GET
    ExchangeRatesDTO getRatesExchangeRates(@QueryParam(ACCESS_KEY_PARAM) String param,
                                           @QueryParam(BASE_PARAM) String base,
                                           @QueryParam(SYMBOLS_PARAM) List<String> symbols);
}
