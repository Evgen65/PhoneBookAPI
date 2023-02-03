package contact_ok;

import com.google.gson.Gson;
import dto.ContactDTO;
import dto.ErrorDTO;
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
            .email("sebastia@nmail.com")
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
        Object message = messageDto.getMessage();
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
        Assert.assertEquals(response.code(),200);
        ResponseMessageDto responseMessageDto = gson.fromJson(response.body().string(), ResponseMessageDto.class);
        String message = responseMessageDto.getMessage();
        Assert.assertTrue(message.contains("Contact was added!"));
        String id = message.substring(message.lastIndexOf(' ')+1);
        System.out.println(id);
    }
    @Test()
    public void addNewContactNegativeFormatEmailTest() throws IOException {
        ContactDTO contact = ContactDTO.builder()
                .id("")
                .name("123456789")
                .lastName("Pereira")
                .email("sebastian.com")
                .phone("1234282378910")
                .address("Haifa")
                .description("Sebastian's Contact")
                .build();
        RequestBody requestBody = RequestBody.create(gson.toJson(contact), JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization", token)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        ErrorDTO errorDTO=gson.fromJson(response.body().string(),ErrorDTO.class);
        String message=errorDTO.getMessage().toString();
        String error =errorDTO.getError().toString();
        Assert.assertEquals(response.code(), 400);
    }
}