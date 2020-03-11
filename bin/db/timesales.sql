--DROP TABLE `timesales`;
CREATE TABLE `timesales` (
  `date` date NOT NULL,
  `price` int(10) NOT NULL
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
DEFAULT CHARSET=utf8
;


--
-- 테이블의 덤프 데이터 `timesales`
--

INSERT INTO `timesales` (`date`, `price`) VALUES
('2016-06-02', 7000),
('2016-06-02', 5000),
('2016-05-25', 2000),
('2016-05-25', 1000),
('2016-06-02', 1000),
('2016-06-02', 2000);