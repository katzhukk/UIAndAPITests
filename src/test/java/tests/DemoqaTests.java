package tests;

import api.BookStoreApi;
import data.TestData;
import helpers.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.BasketPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class DemoqaTests extends TestBase{
    BasketPage basketPage = new BasketPage();



    @Test
    @WithLogin
    @DisplayName("Успешное удаление книги из корзины авторизованного пользователя")
    void deleteBookFromProfileTest(){
        BookStoreApi.deleteAllBooksFromBasket();
        BookStoreApi.addBookToBasket(TestData.isbn);

        basketPage.openPage()
                  .deleteFirstBook()
                  .checkEmptyBookList();
    }
}
