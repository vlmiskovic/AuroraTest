

CREATE  PROCEDURE if not exists `distribute_prize`(IN `tournament_id` INT)
BEGIN DECLARE exit handler FOR SQLEXCEPTION BEGIN ROLLBACK;

RESIGNAL;

END;

SET
    @tournament_id = tournament_id;

SELECT
    t.prize_distributed INTO @is_prize_distributed
FROM
    tournaments t
WHERE
    t.tournament_id = @tournament_id;

if not EXISTS (
    SELECT
        *
    from
        tournaments t
    WHERE
        t.tournament_id = @tournament_id
) then SIGNAL SQLSTATE '45000'
SET
    MESSAGE_TEXT = 'Tournament with id does not exists',
    MYSQL_ERRNO = 0001;

END if;

if @is_prize_distributed = 1 then SIGNAL SQLSTATE '45000'
SET
    MESSAGE_TEXT = 'Tournament is already finalized prize pool is distributed',
    MYSQL_ERRNO = 0002;

END if;

START TRANSACTION;

INSERT INTO
    tournament_scoreboard (`tournament_id`, `player_id`, `ranking`, `prize`)
SELECT
    any_value(b.tournament_id) AS tournament_id,
    any_value(b.player_id) AS player_id,
    row_number() OVER (
        ORDER BY
             round(SUM(amount * odds), 4) desc,
            SUM(b.result) asc,
            MAX(b.amount) asc
    ) AS ranking,
(
        case
            row_number() OVER (
                ORDER BY
                     round(SUM(amount * odds), 4) desc,
            SUM(b.result) asc,
            MAX(b.amount) asc
            )
            when 1 then any_value(t.prize_pool) * 0.5
            when 2 then any_value(t.prize_pool) * 0.3
            when 3 then any_value(t.prize_pool) * 0.2
            ELSE 0
        END
    ) AS prize
FROM
    bet b
    JOIN tournaments t ON t.tournament_id = b.tournament_id
    AND t.tournament_id = @tournament_id
WHERE
    b.result = 1
GROUP BY
    b.player_id;

UPDATE
    players p
    join tournament_scoreboard ts ON ts.player_id = p.player_id
SET
    p.account_balance = p.account_balance + ts.prize
WHERE
    ts.tournament_id = @tournament_id
    and ts.prize != 0
    AND ts.ranking <= 3;

UPDATE
    tournaments t
SET
    t.prize_distributed = 1
WHERE
    t.tournament_id = @tournament_id;

COMMIT;

END
//


CREATE   PROCEDURE if NOT exists `get_tournament_scoreboard`(
	IN `tournament_id` INT
)
LANGUAGE SQL
NOT DETERMINISTIC
READS SQL DATA
SQL SECURITY DEFINER
COMMENT ''
BEGIN

SET @tournament_id=tournament_id;
if (SELECT t.prize_distributed  FROM tournaments t WHERE t.tournament_id=@tournament_id)=1 then

	SELECT ts.board_id AS board_id
	, ts.dt_created AS dt_created
    ,ts.dt_updated AS dt_updated
    ,any_value(p.player_id) AS player_id
    ,ts.prize AS prize
    ,ts.ranking AS ranking
	 ,ANY_value(t.tournament_id) AS tournament_id
	FROM tournament_scoreboard ts
	JOIN tournaments t on t.tournament_id=ts.tournament_id
	JOIN players p ON p.player_id=ts.player_id
	WHERE ts.tournament_id= @tournament_id;


else



    SELECT 0 AS board_id
    ,    NOW() AS dt_created
    ,NOW() AS dt_updated
    ,any_value(p.player_id) AS player_id
    ,0 AS prize
    ,row_number() OVER (
        ORDER BY
             round(SUM(amount * odds), 4) desc,
            SUM(b.result) asc,
            MAX(b.amount) asc
    ) AS ranking
      ,ANY_value(t.tournament_id) AS tournament_id
FROM
    bet b
    JOIN tournaments t ON t.tournament_id = b.tournament_id
    JOIN players p ON p.player_id=b.player_id
    AND t.tournament_id =@tournament_id
WHERE
    b.result = 1
GROUP BY
    b.player_id;
   END if;


END
//
