package bidder.models;

import bidder.enums.AuctionStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Auction {
    String itemCode;
    Double basePrice;
    Double stepRate;
    AuctionStatus status;
    Bid highestBidder;
    List<Bid> bids;
    LocalDateTime createdAt;
    LocalDateTime endedAt;

    public Auction(String itemCode, Double basePrice, Double stepRate) {
        this.itemCode = itemCode;
        this.basePrice = basePrice;
        this.stepRate = stepRate;
        this.bids = new ArrayList<>();
        this.status = AuctionStatus.RUNNING;
        this.createdAt = LocalDateTime.now();
    }
}
