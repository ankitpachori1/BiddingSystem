package bidder.models;

import lombok.Data;

@Data
public class User {
    String userId;
    String password;
    Boolean isLoggedIn;

    public User(String userId){
        this.userId = userId;
        this.password = "pass123";
        this.isLoggedIn = false;
    }

    public void loginUser(){
        this.isLoggedIn = true;
    }

}
