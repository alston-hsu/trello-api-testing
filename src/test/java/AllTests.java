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

    @Test
    public void getBoard() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e6886bf8ca2824126963dd9")
        .when()
                .get("/boards/{id}")
        .then();
    }

    @Test
    public void getBoardLabels() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e6886bf8ca2824126963dd9")
        .when()
                .get("/boards/{id}/labels")
        .then();
    }

    @Test
    public void getBoardLists() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e6886bf8ca2824126963dd9")
        .when()
                .get("/boards/{id}/lists")
        .then();
    }

    @Test
    public void updateBoard() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .queryParam("name", "Updated Test Board")
                .pathParam("id", "5e6886bf8ca2824126963dd9")
        .when()
                .put("/boards/{id}")
        .then();
    }

    @Test
    public void updateBoardByAddingEmail() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e6886bf8ca2824126963dd9")
                .queryParam("email", "test@test.com")
        .when()
                .put("/boards/{id}/members")
        .then();
    }

    @Test
    public void updateBoardWithLabel() throws IOException {
        String updatedBoardBody = "{}";

        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e6886bf8ca2824126963dd9")
                .queryParam("name", "Added label")
                .queryParam("color", "purple")
                .body(updatedBoardBody)
        .when()
                .post("/boards/{id}/labels")
        .then();
    }

    @Test
    public void updateBoardWithList() throws IOException {
        String updatedBoardBody = "{}";

        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e6886bf8ca2824126963dd9")
                .queryParam("name", "Posting list to board")
                .body(updatedBoardBody)
        .when()
                .post("/boards/{id}/lists")
        .then();
    }

    @Test
    public void deleteBoard() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e6886bf8ca2824126963dd9")
        .when()
                .delete("/boards/{id}")
        .then();
    }

    @Test
    public void createLabel() throws IOException {
        String newLabelBody = "{}";

        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .queryParam("name", "Test Label")
                .queryParam("color", "green")
                .queryParam("idBoard", "5e6886bf8ca2824126963dd9")
                .body(newLabelBody)
        .when()
                .post("/labels")
        .then();
    }

    @Test
    public void getLabel() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e689aadcecaee2f68ec0de5")
        .when()
                .get("/labels/{id}")
        .then();
    }

    @Test
    public void updateLabel() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e689aadcecaee2f68ec0de5")
                .queryParam("name", "Updated Label")
                .queryParam("color", "red")
        .when()
                .put("/labels/{id}")
        .then();
    }

    @Test
    public void deleteLabel() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e689aadcecaee2f68ec0de5")
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
                .queryParam("idBoard", "5e6886bf8ca2824126963dd9")
                .body(newListBody)
        .when()
                .post("/lists")
        .then();
    }

    @Test
    public void getList() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e6890b01e0d613ce3b6f56b")
        .when()
                .get("/lists/{id}")
        .then();
    }

    @Test
    public void updateList() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e6890b01e0d613ce3b6f56b")
                .queryParam("name", "Updated Test List")
        .when()
                .put("/lists/{id}")
        .then();
    }

    @Test
    public void createCard() throws IOException {
        String newCardBody = "{}";

        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .queryParam("name", "Test Card")
                .queryParam("idList", "5e6890b01e0d613ce3b6f56b")
                .body(newCardBody)
        .when()
                .post("/cards")
        .then();
    }

    @Test
    public void getCard() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e6893ed62abb7460c59441f")
        .when()
                .get("/cards/{id}")
        .then();
    }

    @Test
    public void updateCard() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e6893ed62abb7460c59441f")
                .queryParam("name", "Updated Card")
        .when()
                .put("/cards/{id}")
        .then();
    }

    @Test
    public void deleteCard() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e6893ed62abb7460c59441f")
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
                .queryParam("idCard", "5e6893ed62abb7460c59441f")
                .queryParam("name", "Test Checklist")
                .body(newChecklistBody)
        .when()
                .post("/checklists")
        .then();
    }

    @Test
    public void getChecklist() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e689652032e717c7fe29dcb")
        .when()
                .get("/checklists/{id}")
        .then();
    }

    @Test
    public void updateChecklist() throws IOException {
        given()
                .queryParam("key", TrelloApiConfig.getPropertyValue("api-key"))
                .queryParam("token", TrelloApiConfig.getPropertyValue("token"))
                .pathParam("id", "5e689652032e717c7fe29dcb")
                .queryParam("name", "Updated Checklist")
        .when()
                .put("/checklists/{id}")
        .then();
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
