package api;

import io.qameta.allure.Step;
import models.BookAddModel;
import models.IsbnBookModel;
import data.TestData;
import models.LoginResponseModel;

import java.util.List;

import static helpers.LoginExtension.loginModel;
import static io.restassured.RestAssured.given;
import static specs.ResponceSpecs.*;

public class BookStoreApi {

    @Step("Удаление всех книги из корзины")
    public BookStoreApi deleteAllBooksFromBasket(){

        given(loginRequestSpec)
                    .header("Authorization", "Bearer " + AuthorizationApi.getAuthCookie().getToken())
                    .queryParam("UserId", AuthorizationApi.getAuthCookie().getUserId())
                .when()
                    .delete("/BookStore/v1/Books")
                .then()
                    .spec(responseSpec204)
                    .extract().response();
        return this;
    }


    @Step("Добавление книги в корзину")
    public BookStoreApi addBookToBasket(String ibsn){
        IsbnBookModel isbnModel = new IsbnBookModel(ibsn);
        BookAddModel request = new BookAddModel(AuthorizationApi.getAuthCookie().getUserId(), List.of(isbnModel));

        given(loginRequestSpec)
                    .header("Authorization", "Bearer " + AuthorizationApi.getAuthCookie().getToken())
                    .body(request)
                .when()
                    .post("/BookStore/v1/Books")
                .then()
                    .spec(responseSpec201)
                    .extract().response();
        return this;
    }
}
