package manager;

import dto.ContactDTO;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProviderData {
    @DataProvider
    public Iterator<Object[]> registrationDto() {
        List<Object[]> list = new ArrayList<>();
        int i = (int) ((System.currentTimeMillis() / 500) % 3600);
        list.add(new Object[]{ContactDTO.builder()
                .id("")
                .name("Jonn" + i)
                .lastName("Add" + i)
                .email("add" + i + "@mail.com")
                .phone("123" + i + "8910")
                .address("Haifa")
                .description("Added Contact")
                .build()});
        list.add(new Object[]{ContactDTO.builder()
                .id("")
                .name("Tommy" + i)
                .lastName("Add" + i)
                .email("tom" + i + "@mail.com")
                .phone("432" + i + "8910")
                .address("Tel Aviv")
                .description("Tomas's Contact")
                .build()});
        list.add(new Object[]{ContactDTO.builder()
                .id("")
                .name("Bobby" + i)
                .lastName("Add" + i)
                .email("bob" + i + "@mail.com")
                .phone("852" + i + "8910")
                .address("Bat Yam")
                .description("Bobby's Contact")
                .build()});
        return list.iterator();

    }
}