--DROP TABLE `stats`;
CREATE TABLE `stats` (
  `date` date NOT NULL,
  `sales` int(11) NOT NULL,
  `profit` int(11) NOT NULL
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
DEFAULT CHARSET=utf8
;


--
-- 테이블의 덤프 데이터 `stats`
--

INSERT INTO `stats` (`date`, `sales`, `profit`) VALUES
('2020-05-31', 49000, 46000),
('2020-05-30', 29000, 20000),
('2020-05-29', 78000, 70000),
('2020-05-28', 52000, 50000),
('2020-05-27', 38000, 30000),
('2020-05-26', 49000, 47000),
('2020-05-25', 79000, 78000),
('2020-05-24', 56000, 50000),
('2020-05-23', 34000, 30000),
('2020-05-22', 89000, 88000),
('2020-05-21', 57000, 50000),
('2020-05-20', 89000, 80000),
('2020-05-19', 67000, 60000),
('2020-05-18', 40000, 38000),
('2020-05-17', 62000, 60000),
('2020-05-16', 48000, 40000),
('2020-05-15', 120000, 100000),
('2020-05-14', 67000, 50000),
('2020-05-13', 26000, 20000),
('2020-05-11', 22000, 20000),
('2020-05-10', 55000, 50000),
('2020-05-09', 49000, 40000),
('2020-05-08', 32000, 30000),
('2020-05-07', 100000, 98000),
('2020-05-06', 70000, 68000),
('2020-05-05', 33000, 30000),
('2020-05-04', 52000, 50000),
('2020-05-03', 48000, 40000),
('2020-05-12', 48000, 40000),
('2020-05-02', 30000, 28000),
('2020-05-01', 68000, 60000),
('2020-06-01', 75000, 45000),
('2020-06-02', 90000, 78000),
('2020-06-03', 90000, 80000),
('2020-06-04', 50000, 40000),
('2020-06-05', 30000, 20000),
('2020-06-06', 60000, 50000),
('2020-06-07', 60000, 50000),
('2020-06-08', 40000, 30000),
('2020-06-09', 70000, 60000),
('2020-06-10', 30000, 20000),
('2020-06-11', 80000, 70000),
('2020-06-12', 50000, 32000),
('2020-06-13', 80000, 70000),
('2020-06-14', 300000, 280000),
('2020-06-15', 280000, 200000),
('2020-06-16', 50000, 40000),
('2020-06-17', 80000, 70000),
('2020-06-18', 90000, 80000),
('2020-06-19', 40000, 30000),
('2020-01-13', 1000000, 890000),
('2020-07-20', 7000000, 6800000),
('2020-10-12', 890000, 700000),
('2020-06-23', 13000, 13000);