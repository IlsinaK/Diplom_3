package api;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api/auth";
    private final Gson gson = new Gson();


    @Step("Регистрация пользователя")
    public ValidatableResponse registerUser(UserDataLombok userData) {
        String requestBody = gson.toJson(userData);

        return RestAssured
                .given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(BASE_URL + "/register")
                .then();
    }


    @Step("Получение токена пользователя")
    public String getToken(UserLogin userLogin) {
        String requestBody = gson.toJson(userLogin);
        Map<String, Object> response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(BASE_URL + "/login")
                .then()
                .extract()
                .as(Map.class);
        return (String) response.get("accessToken");
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

