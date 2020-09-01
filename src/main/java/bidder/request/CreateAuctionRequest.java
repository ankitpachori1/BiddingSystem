package bidder.request;

import lombok.Data;

@Data
public class CreateAuctionRequest {
    String itemCode;
    Double basePrice;
    Double stepRate;
}
