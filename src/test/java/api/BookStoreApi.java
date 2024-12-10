package api;

import io.qameta.allure.Step;
import models.BookAddModel;
import models.IsbnBookModel;
import data.TestData;
import models.LoginResponseModel;

import java.util.List;


import static data.TestData.userId;
import static io.restassured.RestAssured.given;
import static specs.ResponceSpecs.*;

public class BookStoreApi {

    @Step("Удаление всех книги из корзины")
    public static void deleteAllBooksFromBasket(){

 /*        given(loginRequestSpec)
                    .header("Authorization", "Bearer " + AuthorizationApi.getAuthCookie().getToken())
                    .queryParam("UserId", userId)
                 .when()
                     .delete("/BookStore/v1/Book")
                 .then()
                    .spec(responseSpec204)
                    .extract().response();
*/
        given(loginRequestSpec)
                .header("Authorization", "Bearer " + AuthorizationApi.getAuthCookie().getUserId())
                .queryParam("UserId", userId)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(responseSpec204)
                .extract().response();
    }


    @Step("Добавление книги в корзину")
    public static void addBookToBasket(String ibsn, String userId){

        IsbnBookModel isbnModel = new IsbnBookModel(ibsn);
        BookAddModel request = new BookAddModel(AuthorizationApi.getAuthCookie().getUserId(), List.of(isbnModel));
/*
        given(loginRequestSpec)
                    .queryParam("UserId", userId)
                .when()
                    .delete("/BookStore/v1/Book")
                .then()
                    .spec(responseSpec200)
                    .extract().response();

       */
        given(loginRequestSpec)
                    .header("Authorization", "Bearer " + AuthorizationApi.getAuthCookie().getToken())
                    .body(request)
                 .when()
                    .delete("/BookStore/v1/Book")
                 .then()
                    .spec(responseSpec200)
                    .extract().response();

    }
}
