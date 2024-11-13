package aurora.test.repository;

import aurora.test.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {
    @Modifying
    @Transactional
    @Query(value = "update players p set p.account_balance=?1 where p.player_id=?2",nativeQuery = true)
    void updateAccountBalance(Double newBalance,Integer playerId);

    @Query(value="with cte_players AS (SELECT p.player_id AS player_id,p.player_name AS player_name,p.player_email AS player_email,p.account_balance AS account_balance,p.dt_created AS dt_created,p.dt_updated AS dt_updated ,row_number() over (ORDER BY p.account_balance DESC) AS ranking FROM players p) SELECT * FROM cte_players p ORDER BY p.ranking; ", nativeQuery=true)
    List<Map<String, Object>> getPlayersRankedByAccoutnBalance();
}
