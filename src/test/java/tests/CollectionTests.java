package tests;

import org.junit.jupiter.api.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.format;

public class CollectionTests {

    @Test
    void addBookToCollectionTest(){

        String authData = "{\"userName\": \"testtestov31\", \"password\": \"Testtestov31_%\"}";

        Response authResponse = given()
                    .log().uri()
                    .log().method()
                    .log().body()
                    .contentType(JSON)
                     .body(authData)
                .when()
                    .post("/Account/v1/Login")
                .then()
                    .log().status()
                    .log().body()
                    .statusCode(200)
                    .extract().response();

        String isbn = "9781449365035";
        String bookData = format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
                authResponse.path("userId") , isbn);

        Response addBookResponse = given()
                    .log().uri()
                    .log().method()
                    .log().body()
                    .contentType(JSON)
                    .header("Authorization", "Bearer " + authResponse.path("token"))
                    .body(bookData)
                .when()
                    .delete("/BookStore/v1/Book")
                .then()
                    .log().status()
                    .log().body()
                    .statusCode(200)
                    .extract().response();

    }

}
