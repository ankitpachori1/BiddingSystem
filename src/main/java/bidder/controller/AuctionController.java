package bidder.controller;

import bidder.enums.AuctionStatus;
import bidder.models.Auction;
import bidder.request.CreateAuctionRequest;
import bidder.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuctionController {

    @Autowired
    AuctionService service;

    @PostMapping("auction/start")
    public AuctionStatus startAuction(@RequestBody CreateAuctionRequest request){
        return service.startAuction(request);
    }

    @GetMapping("auction/{status}")
    public List<Auction> getAllAuctionsByStatus(@PathVariable("status") AuctionStatus status){
        return service.getAllAuctionsByStatus(status);
    }

}
