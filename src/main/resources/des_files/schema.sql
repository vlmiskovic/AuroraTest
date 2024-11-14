CREATE DATABASE `aurora_new`;

USE `aurora_new`;



CREATE TABLE if NOT EXISTS `players`
(`player_id` INT NOT NULL AUTO_INCREMENT
,`player_name` VARCHAR(300) NOT NULL
,`player_email` VARCHAR(300) NOT NULL
,account_balance DECIMAL(10,4) DEFAULT 0.0000
,`dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
,`dt_updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE current_timestamp
,PRIMARY KEY (`player_id`) USING BTREE
,UNIQUE INDEX `u_ind_player_email`(`player_email`) USING BTREE
,INDEX `player_name_index` (`player_name`) USING btree
);

CREATE TABLE if NOT EXISTS `tournaments`
( `tournament_id` INT NOT NULL AUTO_INCREMENT
,`tournament_name` VARCHAR(300) NOT NULL
,`prize_pool` DECIMAL(12,4) NOT NULL DEFAULT 0.0000
,`start_date` DATEtime NOT NULL
,`end_date` DATEtime NOT NULL
,`finished` TINYINT DEFAULT 0
,`prize_distributed` TINYINT DEFAULT 0
,`dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
,`dt_updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE current_timestamp
,PRIMARY KEY (`tournament_id`) USING btree,
  CONSTRAINT `tounrament_distribute_prize` CHECK (((`prize_distributed` >= 0) and (`prize_distributed` <= 1)))

);



CREATE TABLE if NOT EXISTS `bet`
(`bet_id` INT NOT NULL AUTO_INCREMENT
,`player_id` INT NOT NULL
,`tournament_id` INT NOT NULL
,`amount` DECIMAL(12,4) NOT NULL
,`odds`  DECIMAL (7,4) NOT NULL
,`result` TINYINT  DEFAULT NULL
,`dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
,`dt_updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
,PRIMARY KEY (`bet_id`) USING BTREE
,CONSTRAINT `player_bet_key` FOREIGN KEY (`player_id`) REFERENCES`players`(`player_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
,CONSTRAINT `tournament_bet_key` FOREIGN KEY (`tournament_id`) REFERENCES `tournaments`(`tournament_id`) ON UPDATE RESTRICT ON DELETE restrict
);


CREATE TABLE if NOT EXISTS `tournament_scoreboard`
(`board_id` INT NOT NULL AUTO_INCREMENT
,`tournament_id` INT NOT NULL
,`player_id` INT NOT NULL
,`ranking` TINYINT NOT NULL
,`prize` DECIMAL(12,4) DEFAULT 0.0000
,`dt_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
,`dt_updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
,PRIMARY KEY (`board_id`) USING BTREE
,CONSTRAINT `torunament_scoreboard_id` FOREIGN KEY (`tournament_id`) REFERENCES `tournaments`(`tournament_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
,CONSTRAINT `player_scoreboard_id` FOREIGN KEY (`player_id`) REFERENCES `players`(`player_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
,INDEX `ranking_index` (`tournament_id`,`ranking`) USING btree
);


