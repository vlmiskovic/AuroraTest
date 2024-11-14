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
        try {
            Optional<Tournament> t = tournamentRepository.findTournamentById(tournamentId);
            if(t.isEmpty()){
                throw  new RuntimeException("Tournament with this id does not exists");
            }
            return t;
        }catch (Exception e){
            throw new ApiExceptions(e.getMessage());
        }

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
