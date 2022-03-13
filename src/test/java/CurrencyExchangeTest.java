import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class CurrencyExchangeTest {

    @Test
    public void testConvertEndpoint() {
        given()
                .when().get("/convert")
                .then()
                .statusCode(is(Response.Status.ACCEPTED))
                ;
    }
}