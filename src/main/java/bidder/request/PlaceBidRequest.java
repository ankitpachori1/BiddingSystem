package bidder.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaceBidRequest {
    String itemCode;
    String userId;
    Double amount;
}
