package helpers;

import api.AuthorizationApi;
import io.qameta.allure.Step;
import models.LoginResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback{
    public static LoginResponseModel loginModel;

    @Step("Авторизация пользователя")
    @Override
    public void beforeEach (ExtensionContext context){
        loginModel = AuthorizationApi.getAuthCookie();
        open("/images/Toolsqa.jpg");
        getWebDriver().manage().addCookie(new Cookie("userID", loginModel.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginModel.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginModel.getExpires()));
    }
}