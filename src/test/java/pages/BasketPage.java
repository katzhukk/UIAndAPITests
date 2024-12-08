package pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class BasketPage {

    public BasketPage openPage(){
        open("/profile");
        return this;
    }

    public BasketPage deleteFirstBook(){
        $("#delete-record-undefined").click();
        $("#closeSmallModal-ok").click();
        return this;
    }

    public BasketPage checkEmptyBookList(){
        $(".ReactTable").shouldNotHave(text("Speaking JavaScript"));
        return this;
    }
}
