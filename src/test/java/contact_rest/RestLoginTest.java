package contact_rest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class RestLoginTest {
    @BeforeMethod
    public void preCondition(){
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }

    @Test
    public void loginSuccess(){
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("abcd@mail.com")
                .password("Abcd1234$")
                .build();

        AuthResponseDTO responseDTO = given()
                .body(requestDTO)
                .contentType("application/json")
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(AuthResponseDTO.class)
                ;

        System.out.println(responseDTO.getToken());
    }

    @Test
    public void loginWrongEmail(){
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("abcdef.com")
                .password("$Abcdef12345")
                .build();

        ErrorDTO errorDTO = given()
                .body(requestDTO)
                .contentType("application/json")
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(401)
                .extract()
                .as(ErrorDTO.class);

        Object message = errorDTO.getMessage();
        Assert.assertEquals(errorDTO.getStatus(),401);

    }

    @Test
    public void loginWrongPassword(){
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("abdef.com")
                .password("Abcdef12345")
                .build();

        given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(401)
                .assertThat().body("message",containsString("incorrect") )
        ;
    }
}
