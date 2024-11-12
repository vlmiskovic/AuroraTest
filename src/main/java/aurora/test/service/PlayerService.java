package aurora.test.service;

import aurora.test.entity.Player;
import aurora.test.exceptions.ApiExceptions;
import aurora.test.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public Optional<Player> getPlayer(Integer player_id){
        Optional<Player> player=playerRepository.findById(player_id);
        return  player;
    }
    public Player createNewPlayer(Player player){
        try { return playerRepository.save(player);
        }
        catch (Exception e) {
            throw new ApiExceptions(e.getMessage());
        }
    }
    public List<Map<String, Object>> getPlayerRankedByAccountBalance(){
        try {
            return playerRepository.getPlayersRankedByAccoutnBalance();
        }catch (Exception e){
            throw new ApiExceptions(e.getMessage());
        }

    }
    public void updateCurrentAccountBalance(Double transaction_value, Integer playerId){
        try {
            Player np = new Player();
            Optional<Player> p = playerRepository.findById(playerId);
            if (p.isPresent()) {
                np = p.get();
            } else {
                return;
            }

            double newBalance = np.getAccountBallance() - transaction_value;
            playerRepository.updateAccountBalance(newBalance, np.getPlayerId());
        }catch (Exception e){
            throw new ApiExceptions("Error ocured while trying to update players account balance");
        }
    }
    public Boolean checkBalance(Integer playerId,Double transaction_value){
        Player np=new Player();
        Optional<Player> p = playerRepository.findById(playerId);
        if(p.isPresent())
        {
            np=p.get();
        }
        else{
            return false;
        }
        double newBalance=np.getAccountBallance() -transaction_value;
        return newBalance >= 0;
    }
}

