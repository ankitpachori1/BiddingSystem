package bidder.controller;

import bidder.enums.UserStatus;
import bidder.request.UserLoginRequest;
import bidder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("users/initialize/{count}")
    public List<String> initializeUsers(@PathVariable("count") Integer count){
        return service.initializeUsers(count);
    }

    @PutMapping("user/login")
    public UserStatus loginUser(@RequestBody UserLoginRequest request){
        return service.loginUser(request);
    }
}
