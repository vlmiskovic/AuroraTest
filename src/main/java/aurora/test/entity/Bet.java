package aurora.test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Entity
@Table(name = "bet")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bet {
    @Id
    @Column(name = "bet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer betId;
    @ManyToOne
    @JoinColumn(name = "player_id",nullable = false)
    private Player player;
    @ManyToOne
    @JoinColumn(name = "tournament_id",nullable = false)
    private Tournament tournament;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "odds")
    private Float odds;
    @Column(name = "result")
    private Boolean result;
    @Column(name = "dt_created")
    private Timestamp dtCreated;
    @Column(name = "dt_updated")
    private Timestamp dtUpdated;

}
