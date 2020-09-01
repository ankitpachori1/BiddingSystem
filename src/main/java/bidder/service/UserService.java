package bidder.service;

import bidder.enums.UserStatus;
import bidder.models.User;
import bidder.request.UserLoginRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserService {

    List<User> users = new ArrayList<>();

    public List<String> initializeUsers(Integer count){
        createUsers(count);
        return users.stream().map(u -> u.getUserId()).collect(Collectors.toList());
    }

    private void createUsers(Integer count){
        for (int i = 0; i <= count; i++) {
            String userId = UUID.randomUUID().toString().substring(0,8);
            User u = new User(userId);
            users.add(u);
        }
    }

    public UserStatus loginUser(UserLoginRequest request){
        if(users == null){
            return UserStatus.USER_NOT_PRESENT;
        }
        Optional<User> user = users.stream().filter(
                u -> u.getUserId().equals(request.getUserId()))
                .findFirst();
        if(!user.isPresent()){
            return UserStatus.USER_NOT_PRESENT;
        }
        if(!user.get().getPassword().equals(request.getPassword())){
            return UserStatus.WRONG_CREDENTIALS;
        }
        user.get().loginUser();
        return UserStatus.LOGGED_IN;
    }

}
