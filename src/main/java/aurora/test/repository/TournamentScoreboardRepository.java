package aurora.test.repository;

import aurora.test.entity.Tournament;
import aurora.test.entity.TournamentScoreboard;
import jakarta.persistence.EntityResult;
import jakarta.persistence.FieldResult;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.SqlResultSetMappings;
import org.hibernate.annotations.JavaTypeRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Repository
public interface TournamentScoreboardRepository extends JpaRepository<TournamentScoreboard,Integer> {
/* @Procedure("get_tournament_scoreboard",)*/
    @Query(value = "call get_tournament_scoreboard(?1);",nativeQuery = true)
    List<TournamentScoreboard> getScoreboardByTournamentId(@Param("tournament_id") Integer tournamentId);

}
