package contact_rest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class RestDeleteContact {

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWJjQGRlZi5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY3NTc4NzcxOSwiaWF0IjoxNjc1MTg3NzE5fQ.kNxBwJ6gEX2VJzh9F9kxTiiseXz9ZyU54s58oTbzM-w";

    String id;

    @BeforeMethod
    public void preCondition(){
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
        int i = new Random().nextInt(1000) + 1000;

        ContactDTO contactDto = ContactDTO.builder()
                .name("John")
                .lastName("Snow")
                .email("john_" + i + "@mail.com")
                .phone("1234567" + i)
                .address("Winterfell")
                .description("Nigth watch")
                .build();

        String message = given()
                .header("Authorization", token)
                .body(contactDto)
                .contentType(ContentType.JSON)
                .when()
                .post("contacts")
                .then()
                .extract()
                .path("message")
                ;
        id = message.substring(message.lastIndexOf(' ')+1);
    }

    @Test
    public void deleteById(){
        given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .when()
                .delete("contacts/" + id)
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("message", containsString("deleted"))
        ;
    }
}
