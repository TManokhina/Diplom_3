package com.example.diplom_3.api.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpHeaders.AUTHORIZATION;


public class UserClient {
    public static final String REGISTRATION_USER_PATH = "/api/auth/register";
    public static final String AUTHORIZATION_USER_PATH = "/api/auth/login";
    public static final String UPDATING_USER_DATA_PATH = "/api/auth/user";
    String BASE_URI_PATH = "https://stellarburgers.nomoreparties.site";
    String accessToken;

    public void setUpApi() {
        RestAssured.baseURI = BASE_URI_PATH;
    }

    public Response createUserResponse(UserCredentials userCredentials) {
        return given()
                .contentType(JSON)
                .and()
                .body(userCredentials)
                .post(REGISTRATION_USER_PATH);
    }

    public Response authUserResponse(UserCredentials userCredentials) {
        return given()
                .contentType(JSON)
                .and()
                .body(userCredentials)
                .post(AUTHORIZATION_USER_PATH);
    }

    public Response updatingUserDataResponse(String accessToken, String jsonUpdatingData) {
        return given()
                .contentType(JSON)
                .header(AUTHORIZATION, accessToken)
                .and()
                .body(jsonUpdatingData)
                .patch(UPDATING_USER_DATA_PATH);
    }

    public Response updatingUserDataResponseWithoutToken(String jsonUpdatingData) {
        return given()
                .contentType(JSON)
                .and()
                .body(jsonUpdatingData)
                .patch(UPDATING_USER_DATA_PATH);
    }

    public void tearDownApi() {
        if (accessToken != null) {
            with().contentType(JSON).header(AUTHORIZATION, accessToken).delete(UPDATING_USER_DATA_PATH);
        }
    }

    public void deleteRegisteredUser(String name, String email, String password) {
        RestAssured.baseURI = BASE_URI_PATH;
        UserCredentials user = new UserCredentials();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        Response createUserResponse = given()
                .contentType(JSON)
                .and()
                .body(user)
                .post(AUTHORIZATION_USER_PATH);
        accessToken = createUserResponse.as(User.class).getAccessToken();
        if (accessToken != null) {
            with().contentType(JSON).header(AUTHORIZATION, accessToken).delete(UPDATING_USER_DATA_PATH);
        }
    }
}
