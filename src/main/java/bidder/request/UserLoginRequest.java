package bidder.request;

import lombok.Data;

@Data
public class UserLoginRequest {
    String userId;
    String password;
}
