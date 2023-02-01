package contact_ok;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTest {
    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    @Test
    public void registrationPositiveTest() throws IOException {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("reg" + i + "@mail.com")
                .password("Abcd1234$")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/registration/usernamepassword")
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();
        AuthResponseDTO responseDTO = gson.fromJson(responseJson, AuthResponseDTO.class);
        System.out.println(responseDTO.getToken());
        System.out.println(response.code());
        Assert.assertTrue(response.isSuccessful());

    }
}





