package com.laioffer.cmtyMgmtSys.controller;

import com.laioffer.cmtyMgmtSys.entity.ConfirmationToken;
import com.laioffer.cmtyMgmtSys.entity.User;
import com.laioffer.cmtyMgmtSys.service.ConfirmationTokenService;
import com.laioffer.cmtyMgmtSys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    private ConfirmationTokenService confirmationTokenService;

    @GetMapping("/sign-in")
    String signIn() {

        return "sign-in";



    }

    @GetMapping("/sign-up")
    String signUp() {

        return "sign-up";
    }

    @PostMapping("/signup")
    String signUp(@RequestBody User user) {

        userService.signUpUser(user);

        return "redirect:/login";
    }


    /*
    @GetMapping("/confirm")
    String confirmMail(@RequestParam("token") String token) {

        Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);

        optionalConfirmationToken.ifPresent(userService::confirmUser);

        return "/sign-in";
    }
     */
}
