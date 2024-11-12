package aurora.test.repository;

import aurora.test.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {
    @Query(value = "select      t1_0.tournament_id, t1_0.dt_created, t1_0.dt_updated,  t1_0.end_date,   t1_0.finished,   t1_0.prize_distributed,  t1_0.prize_pool,   t1_0.start_date,  t1_0.tournament_name from tournaments t1_0 where t1_0.tournament_id=?1",nativeQuery = true)
     Optional<Tournament> findTournamentById(Integer id);
    @Procedure("distribute_prize")
    void distributePrize(@Param("tournament_id") Integer tournamentId);
}
