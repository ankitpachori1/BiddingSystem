package bidder.service;

import bidder.enums.BidStatus;
import bidder.enums.UserStatus;
import bidder.models.User;
import bidder.repo.UserRepo;
import bidder.request.UserLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<String> initializeUsers(Integer count){
        List<User> users = userRepo.createUsers(count);
        return users.stream().map(u -> u.getUserId()).collect(Collectors.toList());
    }

    public UserStatus loginUser(UserLoginRequest request){
        Optional<User> user = getUser(request.getUserId());
        if(!user.isPresent()){
            return UserStatus.USER_NOT_PRESENT;
        }
        if(!user.get().getPassword().equals(request.getPassword())){
            return UserStatus.WRONG_CREDENTIALS;
        }
        user.get().loginUser();
        return UserStatus.LOGGED_IN;
    }

    public Optional<User> getUser(String userId){
        return userRepo.getUser(userId);
    }

    public Boolean checkLogin(User u){
        return u.getIsLoggedIn();
    }

}
