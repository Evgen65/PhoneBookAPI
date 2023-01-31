package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//{
//        "token": "string"
// string       }
@Getter
@Setter
@ToString
@Builder
public class AuthResponseDTO {

String token;

}
