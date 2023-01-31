package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//{
//        "contacts": [
//        {
//        "id": "string",
//        "name": "string",
//        "lastName": "string",
//        "email": "string",
//        "phone": "951868838952087",
//        "address": "string",
//        "description": "string"
//        }
//        ]
//        }
@Getter
@Setter
@ToString
@Builder
public class ContactDTO {
    String id;
    String name;
    String lastName;
    String email;
    String phone;
    String address;
    String description;
}
