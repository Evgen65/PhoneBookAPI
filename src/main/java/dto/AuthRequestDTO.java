package dto;
//    {
//            "username": "string",
//            "password": "s~S)Zz?.RT+0!stEg\\R9RYO\"u\"yz,bjd}t~3t$frL]4mFex-,/:"
//
//            }

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder

public class AuthRequestDTO {
    String username;
    String  password;

}