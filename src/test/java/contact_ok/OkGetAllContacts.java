package contact_ok;

import com.google.gson.Gson;
import dto.ContactDTO;
import dto.GetAllContactsDTO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class OkGetAllContacts {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWJjZEBtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjc1Nzg5ODI4LCJpYXQiOjE2NzUxODk4Mjh9.foLunCalZZ3cCzQC8IJEJ-5OKiY1aU5Y5AnGfi1FZiI";

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    @Test
    public void getAllContacts() throws IOException {
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization", token)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());

        GetAllContactsDTO contacts = gson.fromJson(response.body().string(), GetAllContactsDTO.class);
        for (ContactDTO contactDto : contacts.getContacts()){

            System.out.println(contactDto.getId());
            System.out.println(contactDto.getEmail());
            System.out.println(contactDto.getPhone());
            System.out.println("=======================");
        }
    }
}

