package aurora.test.service;

import aurora.test.entity.Bet;
import aurora.test.exceptions.ApiExceptions;
import aurora.test.repository.BetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BetService {
    private final BetRepository betRepository;
    private final PlayerService playerService;

    public Bet placeBet(Bet bet){
        Bet b= new Bet();
        try {


        if(playerService.checkBalance(bet.getPlayer().getPlayerId(),bet.getAmount())){
            b=betRepository.save(bet);
            playerService.updateCurrentAccountBalance(bet.getAmount(),bet.getPlayer().getPlayerId());
        }else{
            throw new RuntimeException("Player does not  have sufficient funds to place bet");
        }

        return b;
        }catch (Exception e){
            throw new ApiExceptions("Error occurred while trying to place bet, please try latter:"+e.getMessage());
        }
    }
}
