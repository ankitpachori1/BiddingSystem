package bidder.repo;

import bidder.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepo {

    List<User> users = new ArrayList<>();

    public List<User> createUsers(Integer count){
        for (int i = 0; i <= count; i++) {
            String userId = UUID.randomUUID().toString().substring(0,8);
            User u = new User(userId);
            users.add(u);
        }
        return users;
    }

    public Optional<User> getUser(String userId){
        return users.stream().filter(
                u -> u.getUserId().equals(userId))
                .findFirst();
    }
}
