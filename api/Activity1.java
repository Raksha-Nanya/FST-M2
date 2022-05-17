package activities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Activity1 {
    String BaserURI = "https://petstore.swagger.io/v2/pet";

    @Test
    @Order(1)
    public void AddPet(){
        String request = "{\"id\": 67587 , \"name\" : \"Adya\" , \"status\": \"sold\"}";
        Response response = given().contentType(ContentType.JSON).body(request).when().put(BaserURI);

        response.then().body("id",equalTo(67587));
        response.then().body("name",equalTo("Adya"));
        response.then().body("status",equalTo("sold"));

    }
    @Test
    @Order(2)
    public void GetPet(){
        Response response = given().contentType(ContentType.JSON).when()
                .pathParam("id","67587").get(BaserURI+"/{id}");

        response.then().body("id",equalTo(67587));
        response.then().body("name",equalTo("Adya"));
        response.then().body("status",equalTo("sold"));

    }

    @Test
    @Order(3)
    public void deletePet() {
        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .when().pathParam("petId", "77232") // Set path parameter
                        .delete(BaserURI + "/{petId}"); // Send DELETE request

        // Assertion
        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo("77232"));
    }

}
