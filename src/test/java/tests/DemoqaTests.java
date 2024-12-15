package tests;

import api.BookStoreApi;
import data.TestData;
import helpers.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.BasketPage;


public class DemoqaTests extends TestBase{
    BasketPage basketPage = new BasketPage();
    BookStoreApi bookStoreApi = new BookStoreApi();

    @Test
    @WithLogin
    @DisplayName("Успешное удаление книги из корзины авторизованного пользователя")
    void deleteBookFromProfileTest(){
        bookStoreApi.deleteAllBooksFromBasket();
        bookStoreApi.addBookToBasket(TestData.isbn);

        basketPage.openPage()
                  .deleteFirstBook()
                  .openPage()
                  .checkEmptyBookList();
    }
}
