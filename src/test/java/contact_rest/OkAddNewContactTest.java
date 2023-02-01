package contact_rest;

import com.google.gson.Gson;
import dto.ContactDTO;
import dto.ResponseMessageDto;
import manager.ProviderData;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static contact_ok.OkHttpLoginTest.JSON;

public class OkAddNewContactTest {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWJjZEBtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjc1Nzg5ODI4LCJpYXQiOjE2NzUxODk4Mjh9.foLunCalZZ3cCzQC8IJEJ-5OKiY1aU5Y5AnGfi1FZiI";
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    ContactDTO contact = ContactDTO.builder()
            .id("")
            .name("Sebastian")
            .lastName("Pereira")
            .email("sebastian@mail.com")
            .phone("1234282378910")
            .address("Haifa")
            .description("Sebastian's Contact")
            .build();

    @Test()
    public void addNewContactTest() throws IOException {
        RequestBody requestBody = RequestBody.create(gson.toJson(contact), JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization", token)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        ResponseMessageDto messageDto = gson.fromJson(response.body().string(), ResponseMessageDto.class);
        String message = messageDto.getMessage();
        System.out.println(message);
    }

    @Test(dataProvider = "registrationDto", dataProviderClass = ProviderData.class)
    public void addNewContactsTest(ContactDTO contact) throws IOException {
        RequestBody requestBody = RequestBody.create(gson.toJson(contact), JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization", token)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        ResponseMessageDto messageDto = gson.fromJson(response.body().string(), ResponseMessageDto.class);
        String message = messageDto.getMessage();
        System.out.println(message);
    }
}