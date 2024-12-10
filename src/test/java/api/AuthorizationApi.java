package api;

import data.TestData;
import io.restassured.response.Response;
import models.LoginRequestModel;
import models.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static specs.ResponceSpecs.loginRequestSpec;
import static specs.ResponceSpecs.responseSpec200;

public class AuthorizationApi {
    public static LoginResponseModel getAuthCookie(){
        LoginRequestModel request = new LoginRequestModel(TestData.login, TestData.password);

        return given()
                            .spec(loginRequestSpec)
                            .body(request)
                        .when()
                            .post("/Account/v1/Login")
                        .then()
                            .spec(responseSpec200)
                        .extract().as(LoginResponseModel.class);
    }
}
