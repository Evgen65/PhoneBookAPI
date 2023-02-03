package contact_rest;

import com.jayway.restassured.RestAssured;
import dto.ContactDTO;
import dto.GetAllContactsDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class RestGetAllContact {
    String token ="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWJjZEBtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjc2MDM4ODcwLCJpYXQiOjE2NzU0Mzg4NzB9.DS4so16NgkL9bcXj51FPePR_HbXw-zxLy9bwh4Kx_9w";
    @BeforeMethod
    public void preCondition() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }

    @Test
    public void getAllContactsSuccess(){
        GetAllContactsDTO contactsDto =
                given()
                        .header("Authorization", token)
                        .when()
                        .get("contacts")
                        .then()
                        .assertThat().statusCode(200)
                        .extract()
                        .as(GetAllContactsDTO.class);

        List<ContactDTO> list = contactsDto.getContacts();
        for(ContactDTO contactDto : list){
            System.out.println(contactDto.getName());
            System.out.println(contactDto.getLastName());
            System.out.println("==============================");
        }
    }

}
