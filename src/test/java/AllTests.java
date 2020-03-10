import config.TrelloApiConfig;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;

public class AllTests extends TrelloApiConfig {

    @Test
    public void createBoard() throws IOException {
        String newBoardBody = "{}";
        given()
                .queryParam("name", "Test Board")
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .body(newBoardBody)
        .when()
                .post("/boards/")
        .then();
    }
}
