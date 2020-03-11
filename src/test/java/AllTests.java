import config.TrelloApiConfig;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

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
        .then()
                .body(matchesJsonSchemaInClasspath("BoardJsonSchema.json"));
    }

    @Test
    public void getBoard() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e69208a46b821054e0ff39f")
        .when()
                .get("/boards/{id}")
        .then()
                .body("name", equalTo("Test Board"));
    }

    @Test
    public void getBoardLabels() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e69208a46b821054e0ff39f")
        .when()
                .get("/boards/{id}/labels")
        .then()
                .body("color[0]", equalTo("green"));
    }

    @Test
    public void getBoardLists() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e69208a46b821054e0ff39f")
        .when()
                .get("/boards/{id}/lists")
        .then()
                .body("name[0]", equalTo("To Do"));
    }

    @Test
    public void updateBoard() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .queryParam("name", "Updated Test Board")
                .pathParam("id", "5e69208a46b821054e0ff39f")
        .when()
                .put("/boards/{id}")
        .then()
                .body("name", equalTo("Updated Test Board"));
    }

    //rerun in 60 min
    @Test
    public void updateBoardByAddingEmail() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e69208a46b821054e0ff39f")
                .queryParam("email", "test123@test.com")
        .when()
                .put("/boards/{id}/members")
        .then()
                .body("members.fullName", equalTo("test123"));
    }

    @Test
    public void createLabelForBoard() throws IOException {
        String updatedBoardBody = "{}";

        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e69208a46b821054e0ff39f")
                .queryParam("name", "Added label")
                .queryParam("color", "purple")
                .body(updatedBoardBody)
        .when()
                .post("/boards/{id}/labels")
        .then()
                .body("name", equalTo("Added label"))
                .body("color", equalTo("purple"));
    }

    @Test
    public void createListForBoard() throws IOException {
        String updatedBoardBody = "{}";

        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e69208a46b821054e0ff39f")
                .queryParam("name", "Posting list to board")
                .body(updatedBoardBody)
        .when()
                .post("/boards/{id}/lists")
        .then()
                .body("name", equalTo("Posting list to board"));
    }

    @Test
    public void deleteBoard() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e692b2d1192db84e3292c02")
        .when()
                .delete("/boards/{id}")
        .then()
                .body("_value", equalTo(null));
    }

    @Test
    public void createLabel() throws IOException {
        String newLabelBody = "{}";

        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .queryParam("name", "Test Label")
                .queryParam("color", "green")
                .queryParam("idBoard", "5e69208a46b821054e0ff39f")
                .body(newLabelBody)
        .when()
                .post("/labels")
        .then()
                .body("name", equalTo("Test Label"))
                .body("color", equalTo("green"));
    }

    @Test
    public void getLabel() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e692a805a3e6e89eca8212e")
        .when()
                .get("/labels/{id}")
        .then()
                .body("name", equalTo("Test Label"));
    }

    @Test
    public void updateLabel() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e692a805a3e6e89eca8212e")
                .queryParam("name", "Updated Label")
                .queryParam("color", "red")
        .when()
                .put("/labels/{id}")
        .then()
                .body("name", equalTo("Updated Label"))
                .body("color", equalTo("red"));
    }

    @Test
    public void deleteLabel() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e692c44fa989861d0329e38")
        .when()
                .delete("/labels/{id}")
        .then();
    }

    @Test
    public void createList() throws IOException {
        String newListBody = "{}";

        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .queryParam("name", "Test List")
                .queryParam("idBoard", "5e69208a46b821054e0ff39f")
                .body(newListBody)
        .when()
                .post("/lists")
        .then()
                .body("name", equalTo("Test List"));
    }

    @Test
    public void getList() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e692cbde36e6261ae3a5526")
        .when()
                .get("/lists/{id}")
        .then()
                .body("name", equalTo("Test List"));
    }

    @Test
    public void updateList() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e692cbde36e6261ae3a5526")
                .queryParam("name", "Updated Test List")
        .when()
                .put("/lists/{id}")
        .then()
                .body("name", equalTo("Updated Test List"));
    }

    @Test
    public void createCard() throws IOException {
        String newCardBody = "{}";

        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .queryParam("name", "Test Card")
                .queryParam("idList", "5e692cbde36e6261ae3a5526")
                .body(newCardBody)
        .when()
                .post("/cards")
        .then()
                .body(matchesJsonSchemaInClasspath("CardJsonSchema.json"));
    }

    @Test
    public void createChecklistForCard() throws IOException {
        String newChecklistForCardBody = "{}";

        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e692f309835dc0f39e42aca")
                .queryParam("name", "Adding checklist for card")
                .body(newChecklistForCardBody)
        .when()
                .post("/cards/{id}/checklists")
        .then()
                .body("name", equalTo("Adding checklist for card"));
    }

    @Test
    public void createLabelForCard() throws IOException {
        String newChecklistForCardBody = "{}";

        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e692f309835dc0f39e42aca")
                .queryParam("color", "blue")
                .queryParam("name", "Blue label")
                .body(newChecklistForCardBody)
        .when()
                .post("/cards/{id}/labels")
        .then()
                .body("color", equalTo("blue"))
                .body("name", equalTo("Blue label"));
    }

    @Test
    public void getCard() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e692f309835dc0f39e42aca")
        .when()
                .get("/cards/{id}")
        .then()
                .body("name", equalTo("Test Card"));
    }

    @Test
    public void getBoardOfCard() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e692f309835dc0f39e42aca")
        .when()
                .get("/cards/{id}/board")
        .then()
                .body("name", equalTo("Updated Test Board"));
    }

    @Test
    public void getChecklistOfCard() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e692f309835dc0f39e42aca")
        .when()
                .get("/cards/{id}/checklists")
        .then()
                .body("name", equalTo("Adding checklist for card"));
    }

    @Test
    public void getListOfCard() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e692f309835dc0f39e42aca")
        .when()
                .get("/cards/{id}/list")
        .then()
                .body("name", equalTo("Updated Test List"));
    }

    @Test
    public void updateCard() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e692f309835dc0f39e42aca")
                .queryParam("name", "Updated Card")
        .when()
                .put("/cards/{id}")
        .then()
                .body("name", equalTo("Updated Card"));
    }

    @Test
    public void deleteCard() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e692f309835dc0f39e42aca")
        .when()
                .delete("/cards/{id}")
        .then();
    }

    @Test
    public void createChecklist() throws IOException {
        String newChecklistBody = "{}";

        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .queryParam("idCard", "5e692f309835dc0f39e42aca")
                .queryParam("name", "Test Checklist")
                .body(newChecklistBody)
        .when()
                .post("/checklists")
        .then()
                .body("name", equalTo("Test Checklist"));
    }

    @Test
    public void getChecklist() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e6932695ad5087e99608837")
        .when()
                .get("/checklists/{id}")
        .then()
                .body("name", equalTo("Test Checklist"));
    }

    @Test
    public void getBoardOfChecklist() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e6932695ad5087e99608837")
        .when()
                .get("/checklists/{id}/board")
        .then()
                .body("name", equalTo("Updated Test Board"));
    }

    @Test
    public void getCardsOfChecklist() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e6932695ad5087e99608837")
        .when()
                .get("/checklists/{id}/cards")
        .then()
                .body("name", equalTo("Updated Card"));
    }

    @Test
    public void updateChecklist() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e6932695ad5087e99608837")
                .queryParam("name", "Updated Checklist")
        .when()
                .put("/checklists/{id}")
        .then()
                .body("name", equalTo("Updated Checklist"));
    }

    @Test
    public void deleteChecklist() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e689652032e717c7fe29dcb")
        .when()
                .delete("/checklists/{id}")
        .then();
    }

    @Test
    public void markNotificationsAsRead() throws IOException {
        String notificationAsReadBody = "{}";

        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .body(notificationAsReadBody)
        .when()
                .post("/notifications/all/read")
        .then();
    }
}
