package aurora.test.controller;

import aurora.test.entity.TournamentScoreboard;
import aurora.test.service.TournamentScoreboardService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Controller
@AllArgsConstructor
@RequestMapping("/api/scoreboard")
public class TournamentScoreboardController {
    private final TournamentScoreboardService tournamentScoreboardService;
    @GetMapping()
    public ResponseEntity<List<TournamentScoreboard>> getScoreboardByTournamentId(@RequestParam("tournament_id") Integer tournament_id){
        return ResponseEntity.ok().body(tournamentScoreboardService.getScoreboardByTournamentID(tournament_id));
    }

}
