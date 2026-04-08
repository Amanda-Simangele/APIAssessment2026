package RequestBuilder;

import Common.Authorization;
import Common.DataGenerator;
import Common.URIs;
import PayloadBuilder.NdosiPayload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class NdosiRequest {

    public static String authToken;
    public static String userId;

    // Method to create a new user registration and extract the user ID from the response
    public static Response createNdosiResponse(){
      Response response =  RestAssured.given()
                .baseUri(URIs.baseURL)
                .basePath("/register")
                .contentType(ContentType.JSON)
                .log().all()
                .body(NdosiPayload.createUserPayload())
                .post()
                .then()
                .extract().response();

        userId = response.jsonPath().getString("data.id");
        return response;
    }
  // Method to log in as the newly registered user
     public static Response loginNdosiResponse(){
               return  RestAssured.given()
                .baseUri(URIs.baseURL)
                .basePath("/login")
                .contentType(ContentType.JSON)
                .log().all()
                .body(NdosiPayload.loginPayload(DataGenerator.email, Authorization.password))
                .post()
                .then()
                .extract().response();
    }
  // Method to log in as an admin user and extract the authentication token from the response
    public static Response loginAdminResponse() {
        Response response = RestAssured.given()
                .baseUri(URIs.baseURL)
                .basePath("/login")
                .contentType(ContentType.JSON)
                .log().all()
                .body(NdosiPayload.loginPayload("admin@gmail.com", "@12345678"))
                .post()
                .then()
                .extract().response();

        authToken = response.jsonPath().getString("data.token");
        return response;
    }
// Method to approve the newly registered user using the admin token and user ID
    public static Response approveUserRegistrationResponse() {
        return RestAssured.given()
                .baseUri(URIs.baseURL)
                .basePath("/admin/users/" + userId + "/approve")
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .log().all()
                .put()
                .then()
                .extract().response();
    }
// Method to update the user's role to "admin" using the admin token and user ID
     public static Response updateUserRoleResponse(){
        return RestAssured.given()
                .baseUri(URIs.baseURL)
                .basePath("/admin/users/"+userId+"/role")
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .log().all()
                .body(NdosiPayload.updateUserRolePayload("admin"))
                .put()
                .then()
                .extract().response();
    }

    // Method to retrieve the user's profile information using the admin token and user ID
    public static Response getUserProfileResponse(){
        return RestAssured.given()
                .baseUri(URIs.baseURL)
                .basePath("/admin/users/"+userId)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .log().all()
                .get()
                .then()
                .extract().response();
    }

    // Method to retrieve the user's profile information using the admin token and user ID
    public static Response deleteUserResponse(){
        return RestAssured.given()
                .baseUri(URIs.baseURL)
                .basePath("/admin/users/"+userId)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken)
                .log().all()
                .delete()
                .then()
                .extract().response();
    }
}
