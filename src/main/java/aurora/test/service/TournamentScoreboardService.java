package aurora.test.service;

import aurora.test.entity.TournamentScoreboard;
import aurora.test.repository.TournamentScoreboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TournamentScoreboardService {
    private final TournamentScoreboardRepository scoreboardRepository;

    public List<TournamentScoreboard> getScoreboardByTournamentID(Integer tournamnetId){
       return scoreboardRepository.getScoreboardByTournamentId(tournamnetId);

    }
}
