package api;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api/auth";

    public ValidatableResponse registerUser(String requestBody) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(BASE_URL + "/register")
                .then();
    }
//    public ValidatableResponse deleteUser(String email) {
//        return RestAssured.given()
//                .header("Content-Type", "application/json")
//                .when()
//                .delete(BASE_URL + "/user", email) // Проверьте маршрут, если это необходимо
//                .then();
//    }
//    public ValidatableResponse deleteUser() {
//        return RestAssured
//                .given()
//                .log().all()
//                .when()
//                .delete(BASE_URL + "/user")
//                .then()
//                .log().all();
//    }
public String getToken(String email, String password) {
    String requestBody = String.format("{ \"email\": \"%s\", \"password\": \"%s\" }", email, password);
    ValidatableResponse response = given()
            .contentType("application/json")
            .body(requestBody)
            .when()
            .post(BASE_URL + "/login")
            .then();

    return response.extract().path("accessToken");
}



    public ValidatableResponse deleteUser(String token, String password) {
        return RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .delete(BASE_URL + "/user")
                .then();
    }
}

