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
        try {
            Optional<Player> player = playerRepository.findById(player_id);
            if(player.isEmpty()){
                throw new RuntimeException("Player with this id does not exists");
            }
            return player;
            }catch (Exception e) {
                throw new ApiExceptions(e.getMessage());
            }
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

            Optional<Player> player = playerRepository.findById(playerId);
            if(player.isEmpty()) {
              throw new RuntimeException("Player with this id does not exists");
            }

            double newBalance = player.get().getAccountBallance() - transaction_value;
            playerRepository.updateAccountBalance(newBalance, player.get().getPlayerId());
        }catch (Exception e){
            throw new ApiExceptions(e.getMessage());
        }
    }
    public Boolean checkBalance(Integer playerId,Double transaction_value){
        try {
        Optional<Player> player = playerRepository.findById(playerId);
        if(!player.isPresent())
        {
            throw new RuntimeException("Player with this id does not exists");
        }
        return (player.get().getAccountBallance() -transaction_value)>= 0;
        }catch (Exception e){
            throw new ApiExceptions(e.getMessage());
        }
    }
}

