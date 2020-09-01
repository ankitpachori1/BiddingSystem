package bidder.repo;

import bidder.enums.AuctionStatus;
import bidder.models.Auction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AuctionRepo {

    List<Auction> auctionList = new ArrayList<>();

    public void saveAuction(Auction auction){
        auctionList.add(auction);
    }

    public List<Auction> getAllAuctionsByStatus(AuctionStatus status){
        return auctionList.stream().filter(a -> a.getStatus().equals(status)).collect(Collectors.toList());
    }

    public Optional<Auction> getAuctionByItemCode(String itemCode){
        return auctionList.stream().findFirst().filter(a -> a.getItemCode().equals(itemCode));
    }
}
