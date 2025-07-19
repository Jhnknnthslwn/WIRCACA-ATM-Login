-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 19, 2025 at 11:30 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `studentdata`
--

-- --------------------------------------------------------

--
-- Table structure for table `studentdata`
--

CREATE TABLE `studentdata` (
  `id` int(11) NOT NULL,
  `studentid` varchar(100) NOT NULL,
  `Firstname` varchar(45) DEFAULT NULL,
  `Surname` varchar(45) DEFAULT NULL,
  `Section` varchar(45) DEFAULT NULL,
  `Gender` varchar(45) DEFAULT NULL,
  `Mobile` varchar(45) DEFAULT NULL,
  `Date` varchar(45) DEFAULT NULL,
  `TimeIn` varchar(45) DEFAULT NULL,
  `FCL` varchar(45) DEFAULT NULL,
  `PRACRES` varchar(45) DEFAULT NULL,
  `EMPTECH` varchar(45) DEFAULT NULL,
  `PHILOSOPHY` varchar(45) DEFAULT NULL,
  `ANIMATION` varchar(45) DEFAULT NULL,
  `PROGRAMMING1` varchar(45) DEFAULT NULL,
  `WIRCACA` varchar(45) DEFAULT NULL,
  `PEH` varchar(45) DEFAULT NULL,
  `Timeout` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `studentdata`
--

INSERT INTO `studentdata` (`id`, `studentid`, `Firstname`, `Surname`, `Section`, `Gender`, `Mobile`, `Date`, `TimeIn`, `FCL`, `PRACRES`, `EMPTECH`, `PHILOSOPHY`, `ANIMATION`, `PROGRAMMING1`, `WIRCACA`, `PEH`, `Timeout`) VALUES
(424, 'UPHMO - 5567935', 'John', 'Silawan', 'ICT 12-01', 'Male', '0974841258', '2021-04-05', '00:00:00', 'Sick', 'Sick', 'Sick', 'Sick', 'Sick', 'Sick', 'Sick', 'Sick', '00:00:00'),
(451, 'UPHMO - 3999643', 'John ', 'Cecogo', 'ICT 12-04', 'Male', '09219331', '2021-04-05', '00:00:00', 'Absent ', 'Absent ', 'Absent ', 'Absent ', 'Absent ', 'Absent ', 'Absent ', 'Absent ', '00:00:00'),
(456, 'UPHMO - 5279755', 'Iayasuke', 'Arris', 'Abm 1', 'Female', '32313414', '2021-04-05', '22:12:00', 'Excused', 'Excused', 'Excused', 'Excused', 'Excused', 'Excused', 'Excused', 'Excused', '08:00:00'),
(457, 'UPHMO - 4093184', 'John Kenneth ', 'Pogi', 'ICT 12-03', 'Male', '0978455631', '2021-04-05', '06:00:00', 'Present', 'Present', 'Present', 'Present', 'Excused', 'Excused', 'Present', 'Present', '19:52:00'),
(458, 'UPHMO - 4307851', 'John Kenneth', 'Pogi', 'ICT 12-03', 'Male', '091743040', '2021-04-16', '00:00:00', 'Sick', 'Sick', 'Sick', 'Sick', 'Sick', 'Sick', 'Sick', 'Sick', '00:00:00'),
(465, 'UPHMO - 3191066', 'John ', 'Silawan', 'ICT 12-01', 'Male', '14141', '2021-04-18', '16:00:00', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', '18:00:00'),
(466, 'UPHMO - 2359217', 'Andrew ', 'Tugdang', 'ICT 12-03', 'Male', '0985156344', '2021-04-27', '15:00:00', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', '18:00:00'),
(467, 'UPHMO - 6227693', 'Lance', 'Jocson', 'ICT 12-02', 'Male', '0953474513', '2021-04-27', '06:00:00', 'Present', 'Present', 'Present', 'Present', 'Present', 'Excused', 'Excused', 'Present', '19:00:00'),
(468, 'UPHMO - 7459759', 'Jermaine ', 'Acosta', 'ICT 12-03', 'Male', '0915755377', '2021-04-27', '07:00:00', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', '19:00:00'),
(469, 'UPHMO - 4116253', 'Eus Raphael', 'Pastrana', 'ICT 12-03', 'Male', '0918056788', '2021-04-27', '05:00:00', 'Present', 'Excused', 'Present', 'Present', 'Present', 'Excused', 'Excused', 'Present', '17:00:00'),
(470, 'UPHMO - 4158344', 'Marie', 'Soriano', 'ICT 12-03', 'Female', '0945578513', '2021-04-28', '10:00:00', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', '19:00:00'),
(471, 'UPHMO - 2086722', 'Eus Raphael', 'Pastrana', 'ICT 12-03', 'Male', '0978845221', '2021-04-28', '05:00:00', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', '19:00:00'),
(472, 'UPHMO - 3599764', 'Lance ', 'Premia', 'ICT 12-01', 'Male', '0978451135', '2021-04-28', '06:00:00', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', '18:00:00'),
(473, 'UPHMO - 6788324', 'Syra Alexis', 'Cavadero', 'HE 12-01', 'Female', '0978154565', '2021-04-27', '06:00:00', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', '17:00:00'),
(474, 'UPHMO - 970334', 'Julyan', 'Buelva', 'HE 12-01', 'Female', '0978455525', '2021-04-27', '08:00:00', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', '18:00:00'),
(475, 'UPHMO - 2803578', 'Martin Ray', 'Animas', 'HE 12-01', 'Male', '0956525356', '2021-04-27', '06:00:00', 'Excused', 'Present', 'Excused', 'Present', 'Absent ', 'Present', 'Present', 'Present', '20:00:00'),
(476, 'UPHMO - 7856386', 'Eizer John', 'Justo', 'HE 12-02', 'Male', '0987546155', '2021-04-27', '06:00:00', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', '18:00:00'),
(477, 'UPHMO - 7804828', 'Jacob', 'Igancio', 'HE 12-02', 'Male', '0954615852', '2021-04-27', '07:00:00', 'Excused', 'Present', 'Present', 'Excused', 'Present', 'Present', 'Excused', 'Present', '19:00:00'),
(478, 'UPHMO - 7289560', 'Shane', 'Diaz', 'HE 12-02', 'Male', '0988800391', '2021-04-26', '06:00:00', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', '19:00:00'),
(479, 'UPHMO - 1134671', 'Joyce Marie', 'Ricafranca', 'HE 12-03', 'Female', '0998775521', '2021-04-27', '06:00:00', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', '06:00:00'),
(483, 'UPHMO - 6092264', 'Kenneth Jonathan', 'Kyle', 'ICT 12-03', 'Female', '0975421566', '2021-04-30', '22:09:11', 'Excused', 'Excused', 'Excused', 'Excused', 'Excused', 'Excused', 'Excused', 'Excused', '08:09:00'),
(486, 'UPHMO - 5840802', 'kevin', 'silawan', 'ict', 'Male', '23123123123', '2025-07-19', '00:00:00', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', 'Present', '00:00:00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `studentdata`
--
ALTER TABLE `studentdata`
  ADD PRIMARY KEY (`id`,`studentid`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`),
  ADD UNIQUE KEY `studentid_UNIQUE` (`studentid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `studentdata`
--
ALTER TABLE `studentdata`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=487;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
