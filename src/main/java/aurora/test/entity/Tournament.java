package aurora.test.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "tournaments")
@Getter
@Setter
public class Tournament {
    @Id
    @Column(name = "tournament_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tournamentId;
    @Column(name = "tournament_name")
    private String tournamentName;
    @Column(name = "prize_pool")
    private Double prizePool;
    @Column(name = "start_date")
    private Date start_date;
    @Column(name = "end_date")
    private Date end_date;
    @Column(name = "finished")
    private Boolean finished;
    @Column(name = "prize_distributed")
    private Boolean prizeDistributed;
    @Column(name = "dt_created")
    private Timestamp dtCreated;
    @Column(name = "dt_updated")
    private Timestamp dtUpdated;
}
