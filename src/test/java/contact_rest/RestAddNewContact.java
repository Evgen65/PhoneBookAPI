package contact_rest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import kotlin.random.Random;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class RestAddNewContact {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWJjZEBtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjc2MDM4ODcwLCJpYXQiOjE2NzU0Mzg4NzB9.DS4so16NgkL9bcXj51FPePR_HbXw-zxLy9bwh4Kx_9w";
    @BeforeMethod
    public void preCondition() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }
    @Test
    public void addNewContactTest() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        ContactDTO contactDto = ContactDTO.builder()
                .name("John")
                .lastName("Snow")
                .email("john_" + i + "@mail.com")
                .phone("1234567" + i)
                .address("Winterfell")
                .description("Nigth watch")
                .build();
        given()
                .header("Authorization", token)
                .body(contactDto)
                .contentType(ContentType.JSON)
                .when()
                .post("contacts")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("message", containsString("added"))
        ;
    }
}

