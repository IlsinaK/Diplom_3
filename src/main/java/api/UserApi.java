package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api/auth";

    @Step("Регистрация пользователя")
    public ValidatableResponse registerUser(String requestBody) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(BASE_URL + "/register")
                .then();
    }


    @Step("Получение токена пользователя")
    public String getToken(String email, String password) {
        UserDataLombok userData = new UserDataLombok(email, password, null);

        String requestBody = userData.toString();

        ValidatableResponse response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(BASE_URL + "/login")
                .then();

        return response.extract().path("accessToken").toString();
    }


    @Step("Удаление пользователя с токеном")
    public ValidatableResponse deleteUser(String token) {
        return RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .delete(BASE_URL + "/user")
                .then();
    }
}

