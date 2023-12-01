package vn.edu.tdtu.lab09.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.tdtu.lab09.dto.SignInReqDTO;
import vn.edu.tdtu.lab09.models.UserAccount;
import vn.edu.tdtu.lab09.services.AuthService;
@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserAccount userAccount){
        return ResponseEntity.ok(
                authService.signUpAccount(userAccount)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignInReqDTO sign) throws Exception {
        return ResponseEntity.ok(
                authService.signIn(sign)
        );
    }
}
