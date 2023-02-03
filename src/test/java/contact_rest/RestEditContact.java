package contact_rest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class RestEditContact {
    String token ="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWJjZEBtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjc2MDM4ODcwLCJpYXQiOjE2NzU0Mzg4NzB9.DS4so16NgkL9bcXj51FPePR_HbXw-zxLy9bwh4Kx_9w";
    String id;
    ContactDTO contactDto;

    @BeforeMethod
    public void preCondition(){
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
        int i = new Random().nextInt(1000) + 1000;

        contactDto = ContactDTO.builder()
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
    public void editContact(){

        contactDto = ContactDTO.builder()
                .id(id)
                .name(contactDto.getName() + "_Edited")
                .lastName(contactDto.getLastName())
                .email(contactDto.getEmail())
                .phone(contactDto.getPhone())
                .address(contactDto.getAddress())
                .description(contactDto.getDescription())
                .build();

        given()
                .body(contactDto)
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .when()
                .put("contacts")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("message", containsString("updated"))
        ;
    }
}
