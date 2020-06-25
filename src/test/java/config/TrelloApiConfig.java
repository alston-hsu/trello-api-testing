package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TrelloApiConfig {

    public static Properties prop = new Properties();
    RequestSpecification trello_requestSpec;
    ResponseSpecification trello_responseSpec;

    /*public static String getPropertyValue(String propertyKey) throws IOException {
        InputStream file = new FileInputStream(".secret/creds.properties");
        prop.load(file);
        return prop.getProperty(propertyKey);
    }*/

    @BeforeClass
    public void setup() {
        trello_requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.trello.com")
                .setBasePath("/1")
                .addFilter(new ResponseLoggingFilter())
                .build();

        trello_responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

        RestAssured.requestSpecification = trello_requestSpec;
        RestAssured.responseSpecification = trello_responseSpec;
    }
}
