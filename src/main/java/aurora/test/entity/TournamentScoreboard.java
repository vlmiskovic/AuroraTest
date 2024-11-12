package aurora.test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "tournament_scoreboard")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TournamentScoreboard {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id",nullable = false)
    @Fetch(FetchMode.JOIN)
    private Player player;
    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "tournament_id",nullable = false)
    private Tournament tournament;
    @Column(name = "ranking")
    private Integer ranking;
    @Column(name = "prize")
    private double prize;
    @Column(name = "dt_created")
    private Timestamp dtCreated;
    @Column(name = "dt_updated")
    private Timestamp dtUpdated;
}
