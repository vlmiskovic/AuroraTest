package aurora.test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name="players")
public class Player {
    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerId;

    @Column(name = "player_name",nullable = false)
    private String playerName;

    @Column(name="player_email",nullable = false,unique = true)
    private String playerEmail;

    @Column(name = "account_balance")
    private float accountBallance;

    @Column(name = "dt_created")
    private Timestamp dtCreated;
    @Column(name = "dt_updated")
    private Timestamp dtUpdated;

}
