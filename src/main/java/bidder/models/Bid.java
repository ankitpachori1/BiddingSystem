package bidder.models;

import bidder.enums.BidStatus;
import lombok.Data;

@Data
public class Bid {
    String userId;
    Double amount;
    BidStatus status;
}
