import bidder.enums.BidStatus;
import bidder.models.Auction;
import bidder.models.User;
import bidder.repo.AuctionRepo;
import bidder.request.PlaceBidRequest;
import bidder.service.AuctionService;
import bidder.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TestFlow {

    @Mock
    UserService userService;

    @Mock
    AuctionRepo repo;

    @InjectMocks
    AuctionService auctionService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPlaceBidFlow_ACCEPTED(){
        Auction auction = new Auction("auction123", 100.0, 20.0);
        User user = new User("user123");
        when(repo.getAuctionByItemCode(anyString())).thenReturn(Optional.of(auction));
        when(userService.getUser(anyString())).thenReturn(Optional.of(user));
        when(userService.checkLogin(any())).thenReturn(true);
        PlaceBidRequest request = new PlaceBidRequest("auction123","user123", 120.0);
        BidStatus actual = auctionService.placeBid(request);
        assertEquals(actual, BidStatus.ACCEPTED);
    }

    @Test
    public void testPlaceBidFlow_REJECTED(){
        Auction auction = new Auction("auction123", 100.0, 20.0);
        User user = new User("user123");
        when(repo.getAuctionByItemCode(anyString())).thenReturn(Optional.of(auction));
        when(userService.getUser(anyString())).thenReturn(Optional.of(user));
        when(userService.checkLogin(any())).thenReturn(true);
        PlaceBidRequest request = new PlaceBidRequest("auction123","user123", 110.0);
        BidStatus actual = auctionService.placeBid(request);
        assertEquals(actual, BidStatus.REJECTED);
    }

    @Test
    public void testPlaceBidFlow_AUCTION_DOESNT_EXISTS(){
        when(repo.getAuctionByItemCode(anyString())).thenReturn(Optional.empty());
        PlaceBidRequest request = new PlaceBidRequest("auction123","user123", 120.0);
        BidStatus actual = auctionService.placeBid(request);
        assertEquals(actual, BidStatus.AUCTION_DOESNT_EXIST);
    }

    @Test
    public void testPlaceBidFlow_USER_DOESNT_EXISTS(){
        Auction auction = new Auction("auction123", 100.0, 20.0);
        when(repo.getAuctionByItemCode(anyString())).thenReturn(Optional.of(auction));
        when(userService.getUser(anyString())).thenReturn(Optional.empty());
        PlaceBidRequest request = new PlaceBidRequest("auction123","user123", 120.0);
        BidStatus actual = auctionService.placeBid(request);
        assertEquals(actual, BidStatus.USER_DOESNT_EXIST);
    }

    @Test
    public void testPlaceBidFlow_USER_NOT_LOGGED_IN(){
        Auction auction = new Auction("auction123", 100.0, 20.0);
        User user = new User("user123");
        when(repo.getAuctionByItemCode(anyString())).thenReturn(Optional.of(auction));
        when(userService.getUser(anyString())).thenReturn(Optional.of(user));
        when(userService.checkLogin(any())).thenReturn(false);
        PlaceBidRequest request = new PlaceBidRequest("auction123","user123", 120.0);
        BidStatus actual = auctionService.placeBid(request);
        assertEquals(actual, BidStatus.USER_NOT_LOGGED_IN);
    }
}
