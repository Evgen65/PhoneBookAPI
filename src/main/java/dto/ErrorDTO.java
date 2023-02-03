package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//{
//        "timestamp": "2023-01-31T16:17:10.296Z",
//        "status": 0,
//        "error": "string",
//        "message": {},
//        "path": "string"
//        }
@Getter
@Setter
@ToString
@Builder
public class ErrorDTO {

    int status;
    Object error;
    Object message;


}
