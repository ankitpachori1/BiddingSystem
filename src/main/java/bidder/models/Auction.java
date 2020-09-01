package bidder.models;

import bidder.enums.AuctionStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Auction {
    String itemCode;
    Double basePrice;
    Double stepRate;
    AuctionStatus status;
    Bid highestBidder;
    List<Bid> bids;
    Date createdAt;
    Date endedAt;
}
