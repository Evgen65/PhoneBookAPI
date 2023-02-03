package contact_rest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthRequestDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static com.jayway.restassured.RestAssured.given;

public class RestRegistrationTest {

    @BeforeMethod
    public void preCondition(){
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }

    @Test
    public void registrationSuccess(){
        int i = new Random().nextInt(1000) + 1000;
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("abc_" + i + "@def.com")
                .password("$Abcdef12345")
                .build();

        String token =
                given()
                        .body(requestDTO)
                        .contentType(ContentType.JSON)
                        .when()
                        .post("user/registration/usernamepassword")
                        .then()
                        .assertThat().statusCode(200)
                        .extract()
                        .path("token")
                ;
        System.out.println(token);
    }
}
