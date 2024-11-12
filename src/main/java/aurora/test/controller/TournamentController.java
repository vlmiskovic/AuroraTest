package aurora.test.controller;

import aurora.test.entity.Tournament;
import aurora.test.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.ResourceBundle;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/tournament")
public class TournamentController {
    private final TournamentService tournamentService;
    @GetMapping
    public ResponseEntity<Optional<Tournament>>getTournamentById(@RequestParam(value = "tournament_id") Integer tournamentId){
        return ResponseEntity.ok().body(tournamentService.getTournamentById(tournamentId));
    }
    @PostMapping("/create")
    public ResponseEntity<Tournament> createNewTournament(@RequestBody Tournament tournament){
        return ResponseEntity.ok().body(tournamentService.createNewTournament(tournament));
    }
    @PostMapping("/distributePrize")
    public ResponseEntity<String> distributePrize(@RequestParam(value = "tournament_id") Integer tournamentId){
        tournamentService.distributePrize(tournamentId);
        return ResponseEntity.ok().body("Prizes are distributed, tournament is finished");
    }

}
