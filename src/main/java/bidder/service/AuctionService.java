package bidder.service;

import bidder.enums.AuctionStatus;
import bidder.enums.BidStatus;
import bidder.models.Auction;
import bidder.models.Bid;
import bidder.models.User;
import bidder.repo.AuctionRepo;
import bidder.request.CreateAuctionRequest;
import bidder.request.PlaceBidRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {

    @Autowired
    AuctionRepo repo;

    @Autowired
    UserService userService;

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

    public BidStatus placeBid(PlaceBidRequest request){
        Optional<Auction> auction = repo.getAuctionByItemCode(request.getItemCode());
        if(!auction.isPresent())
            return BidStatus.AUCTION_DOESNT_EXIST;
        Optional<User> user = userService.getUser(request.getUserId());
        if(!user.isPresent())
            return BidStatus.USER_DOESNT_EXIST;
        if(!userService.checkLogin(user.get()))
            return BidStatus.USER_NOT_LOGGED_IN;
        Bid highest = auction.get().getHighestBidder();
        if(highest == null){
            if(auction.get().getBasePrice() + auction.get().getStepRate() > request.getAmount()) {
                Bid b = new Bid(request.getUserId(), request.getAmount(), BidStatus.REJECTED);
                auction.get().getBids().add(b);
                return BidStatus.REJECTED;
            }
            else{
                highest = new Bid(request.getUserId(), request.getAmount(), BidStatus.ACCEPTED);
                auction.get().getBids().add(highest);
                auction.get().setHighestBidder(highest);
                return BidStatus.ACCEPTED;
            }
        }
        else{
            if(highest.getAmount() + auction.get().getStepRate() > request.getAmount()){
                Bid b = new Bid(request.getUserId(), request.getAmount(), BidStatus.REJECTED);
                auction.get().getBids().add(b);
                return BidStatus.REJECTED;
            }
            else{
                Bid b = new Bid(request.getUserId(), request.getAmount(), BidStatus.ACCEPTED);
                auction.get().getBids().add(b);
                auction.get().setHighestBidder(b);
                return BidStatus.ACCEPTED;
            }
        }
    }
}
