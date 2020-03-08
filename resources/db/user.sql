--DROP TABLE `user`;
CREATE TABLE `user` (
    `id` INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY
    , `pc_number` INT(3)
    , `name` VARCHAR(20) NOT NULL
    , `user_id` VARCHAR(20) NOT NULL
    , `password` VARCHAR(500) NOT NULL
    , `sex` VARCHAR(10) NOT NULL
    , `email` VARCHAR(50)
    , `prepayment` tinyint(1) NOT NULL DEFAULT 0
    , `remain_tiem` TIME DEFAULT 0
    , `use_time` TIME DEFAULT 0
    , `cumulative_amount` int(20) DEFAULT 0
    , `cumulative_time` TIME DEFAULT 0
    , `date_of_birth` VARCHAR(8) 
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
