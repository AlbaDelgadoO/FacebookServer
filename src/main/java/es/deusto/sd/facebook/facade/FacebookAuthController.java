package es.deusto.sd.facebook.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.deusto.sd.facebook.entity.FacebookUser;
import es.deusto.sd.facebook.service.FacebookAuthService;

@RestController
@RequestMapping("/auth/facebook")
public class FacebookAuthController {

    @Autowired
    private FacebookAuthService facebookAuthService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        if (facebookAuthService.validate(email, password)) {
            return ResponseEntity.ok("Facebook login successful!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed!");
    }
}