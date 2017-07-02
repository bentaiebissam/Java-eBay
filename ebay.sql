-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 24, 2017 at 10:04 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ebay`
--

-- --------------------------------------------------------

--
-- Table structure for table `actions`
--

CREATE TABLE `actions` (
  `idNotification` int(11) NOT NULL,
  `nomProduit` varchar(11) NOT NULL,
  `prix` int(11) NOT NULL,
  `date` varchar(50) NOT NULL,
  `idMember` int(11) NOT NULL,
  `action` varchar(21) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `actions`
--

INSERT INTO `actions` (`idNotification`, `nomProduit`, `prix`, `date`, `idMember`, `action`) VALUES
(44, 'Glace', 2, '2017-04-24T22:40:26.523', 1, 'Acheté immediatement'),
(43, 'koffa', 2, '2017-04-24T22:36:33.494', 0, 'enchere'),
(42, 'koffa', 1, '2017-04-24T22:36:11.268', 0, 'enchere'),
(41, 'chkara', 3, '2017-04-24T18:12:44.470', 2, 'Acheté immediatement'),
(40, 'chkara', 3, '2017-04-24T18:12:44.470', 2, 'Vendu'),
(38, 'tel', 5, '2017-04-24T14:34:28.731', 0, 'enchere'),
(39, 'tel', 7, '2017-04-24T14:36:17.546', 0, 'enchere');

-- --------------------------------------------------------

--
-- Table structure for table `membre`
--

CREATE TABLE `membre` (
  `MembreID` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(11) NOT NULL,
  `solde` int(11) NOT NULL DEFAULT '50'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `membre`
--

INSERT INTO `membre` (`MembreID`, `username`, `password`, `solde`) VALUES
(1, 'admin', 'admin', 8),
(2, 'issam', 'issam', 750),
(4, 'chahra', 'chahra', 50),
(5, 'oki', 'oki', 1),
(6, 'nour', 'nour', 1009),
(7, 'asus', 'asus', 50),
(8, 'nate', 'nate', 50),
(9, 'jee', 'jee', 50);

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

CREATE TABLE `produit` (
  `idProduit` int(11) NOT NULL,
  `nomProduit` varchar(11) NOT NULL,
  `prix` int(11) NOT NULL,
  `dateLimite` varchar(11) NOT NULL,
  `description` text NOT NULL,
  `image` text NOT NULL,
  `category` varchar(11) NOT NULL,
  `idOwner` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `produit`
--

INSERT INTO `produit` (`idProduit`, `nomProduit`, `prix`, `dateLimite`, `description`, `image`, `category`, `idOwner`) VALUES
(2, 'tel', 500, '01/08/2017', 'behi', 'http://tinyurl.com/kzx5epd', ' phone ', 0),
(4, 'koffa', 5, '25', 'koffa traditionelle très bonne occasion', 'http://www.babnet.net/1a/koffa.jpg', ' sac ', 0),
(5, 'shirt ', 20, '31/5/2017', 'shirt noir L', 'http://tinyurl.com/moezlyr', ' vêtements ', 0),
(49, 'Site', 500, '01/01/2018', 'site ebay', 'http://www.babnet.net/1a/koffa.jpg', ' Internet', 2),
(50, 'tel', 500, '01/02/2016', 'behi', 'http://tinyurl.com/moezlyr', ' mekla', 2),
(52, 'Chocolat', 25, '01/01/2018', 'Bonne chocolat', 'http://tinyurl.com/lo3wrso', ' Food', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `actions`
--
ALTER TABLE `actions`
  ADD PRIMARY KEY (`idNotification`);

--
-- Indexes for table `membre`
--
ALTER TABLE `membre`
  ADD PRIMARY KEY (`MembreID`);

--
-- Indexes for table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`idProduit`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `actions`
--
ALTER TABLE `actions`
  MODIFY `idNotification` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;
--
-- AUTO_INCREMENT for table `membre`
--
ALTER TABLE `membre`
  MODIFY `MembreID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `produit`
--
ALTER TABLE `produit`
  MODIFY `idProduit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
