package bidder.service;

import bidder.enums.AuctionStatus;
import bidder.models.Auction;
import bidder.repo.AuctionRepo;
import bidder.request.CreateAuctionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionService {

    @Autowired
    AuctionRepo repo;

    public AuctionStatus startAuction(CreateAuctionRequest request){
        if(repo.getAuctionByItemCode(request.getItemCode()).isPresent()){
            return AuctionStatus.AUCTION_ALREADY_EXISTS_FOR_ITEM_CODE;
        }
        Auction auction = new Auction(request.getItemCode(), request.getBasePrice(), request.getStepRate());
        repo.saveAuction(auction);
        return auction.getStatus();
    }

    public List<Auction> getAllAuctionsByStatus(AuctionStatus status){
        return repo.getAllAuctionsByStatus(status);
    }

}
