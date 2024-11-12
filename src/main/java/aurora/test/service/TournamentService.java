package aurora.test.service;

import aurora.test.entity.Tournament;
import aurora.test.exceptions.ApiExceptions;
import aurora.test.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    public Optional<Tournament> getTournamentById(Integer tournamentId){

        Optional<Tournament> t=tournamentRepository.findTournamentById(tournamentId);
        return t;
    }

    public Tournament createNewTournament(Tournament tournament){
        return tournamentRepository.save(tournament);
    }
    public void distributePrize(Integer tournamentId){
        try {
            tournamentRepository.distributePrize(tournamentId);
        }catch (Exception e){
            throw new ApiExceptions(e.getMessage());
        }

    }
}
