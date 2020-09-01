package bidder.controller;

import bidder.request.UserLoginRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users/initialize")
    public List<String> initializeUsers(){
        return ;
    }

    @PutMapping("user/login")
    public String loginUser(@RequestBody UserLoginRequest request){
        return "Logged in";
    }
}
