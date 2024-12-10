package tests;

import api.AuthorizationApi;
import api.BookStoreApi;
import data.TestData;
import helpers.WithLogin;
import models.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.BasketPage;

import static data.TestData.userId;

public class DemoqaTests extends TestBase{
    BasketPage basketPage = new BasketPage();
    LoginResponseModel loginModel = new LoginResponseModel();

    @Test
    @WithLogin
    @DisplayName("Успешное удаление книги из корзины авторизованного пользователя")
    void deleteBookFromProfileTest(){
        BookStoreApi.deleteAllBooksFromBasket();
        BookStoreApi.addBookToBasket(TestData.isbn, loginModel.getUserId());

        basketPage.openPage()
                  .deleteFirstBook()
                  .checkEmptyBookList();
    }
}
