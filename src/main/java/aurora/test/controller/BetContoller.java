package aurora.test.controller;

import aurora.test.entity.Bet;
import aurora.test.service.BetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/bet")
public class BetContoller {
    private final BetService betService;
    @PostMapping
    public ResponseEntity<Bet> placeBet(@RequestBody Bet bet){
        return ResponseEntity.ok().body(betService.placeBet(bet));
    }
}
