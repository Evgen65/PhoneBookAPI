import com.google.gson.Gson;
import dto.ContactDTO;
import dto.ResponseMessageDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class OkEditContactTest {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWJjZEBtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjc1Nzg5ODI4LCJpYXQiOjE2NzUxODk4Mjh9.foLunCalZZ3cCzQC8IJEJ-5OKiY1aU5Y5AnGfi1FZiI";
    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    ContactDTO contact = ContactDTO.builder()
            .id("96112e6c-3bb8-4035-ae37-afc5d4f747ab")
            .name("Jack")
            .lastName("Edited")
            .email("name12823@mail.com")
            .phone("1234282378910")
            .address("Haifa")
            .description("Edited Contact")
            .build();

    @Test
    public void editById() throws IOException {
        RequestBody requestBody = RequestBody.create(gson.toJson(contact), JSON);

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts/")
                .addHeader("Authorization", token)
                .put(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        ResponseMessageDto messageDto = gson.fromJson(response.body().string(), ResponseMessageDto.class);
        String message = messageDto.getMessage();
        System.out.println(message);
    }
}