package bidder.request;

import lombok.Data;

@Data
public class PlaceBidRequest {
    String itemCode;
    String userId;
    Double amount;
}
