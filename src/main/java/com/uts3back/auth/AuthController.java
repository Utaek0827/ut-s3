package com.uts3back.auth;

import com.uts3back.dto.UsersDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/SignIn")
    public String signIn(
            ){
        System.out.println("ㅁㄴㅇㄹㅁ");
        return "email+password";
    }

    @PostMapping("/SignUp")
    public String signUp(
            @RequestBody UsersDTO SignUpUsers
            ){

        System.out.println(SignUpUsers);
        return "email+password";
    }

}

