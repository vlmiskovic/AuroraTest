package aurora.test.controller;

import aurora.test.entity.Player;
import aurora.test.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/player")
public class PlayerController {
    private final PlayerService playerService;
    @GetMapping
    public ResponseEntity<Optional<Player>>getPlayerById(@RequestParam(value = "player_id") Integer playerId){
        Optional<Player> player=playerService.getPlayer(playerId);
        System.out.println(player.toString());
        return  ResponseEntity.ok().body(player);
    }
    @PostMapping("/create")
    public ResponseEntity<Player>createPlayer(@RequestBody Player player){

        return ResponseEntity.ok().body(playerService.createNewPlayer(player));
    }
    @GetMapping("/getPlayerRanking")
    public ResponseEntity<List<Map<String, Object>>> getPlayerRanking(){
        return ResponseEntity.ok().body(playerService.getPlayerRankedByAccountBalance());
    }
}
