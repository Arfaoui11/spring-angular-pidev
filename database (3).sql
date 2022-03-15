-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 09 oct. 2021 à 02:03
-- Version du serveur : 10.4.19-MariaDB
-- Version de PHP : 7.4.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `database`
--
CREATE DATABASE IF NOT EXISTS `database` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `database`;

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE `clients` (
  `idCli` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `phoneNumbre` int(11) NOT NULL,
  `Cin` int(11) NOT NULL,
  `dateNaissance` date NOT NULL,
  `dateReservation` date NOT NULL DEFAULT current_timestamp(),
  `pos_map` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`idCli`, `firstname`, `lastname`, `phoneNumbre`, `Cin`, `dateNaissance`, `dateReservation`, `pos_map`) VALUES
('AAYNBP', 'C:\\Users\\LEGION-5\\AppData\\Roaming\\Microsoft\\Windows\\Network Shortcuts\\mahdqrcode.png', 'Tripoli', 777, 555, '2021-10-20', '2021-10-05', ''),
('CRDCRW', 'C:\\Users\\LEGION-5\\AppData\\Roaming\\Microsoft\\Windows\\Network Shortcuts\\mahdqrcode.png', 'Tripoli', 1998, 555, '2021-10-13', '2021-10-05', ''),
('FYVCJL', 'C:\\Users\\LEGION-5\\Documents\\1.jpg', 'Tripoli', 2453, 12354, '2021-10-12', '2021-10-08', 'LatLng(36.79977, 10.17573) المدينة'),
('QOPXRL', 'C:\\Users\\LEGION-5\\Desktop\\144fceb9931b418ebcfa211bb676dd7f.png', 'Dubai', 777, 555, '2021-10-19', '2021-10-08', 'http://null'),
('VGIJGL', 'C:\\Users\\LEGION-5\\AppData\\Roaming\\Microsoft\\Windows\\Network Shortcuts\\mahdqrcode.png', 'Tripoli', 777, 555, '2021-10-20', '2021-10-05', '');

-- --------------------------------------------------------

--
-- Structure de la table `employee`
--

CREATE TABLE `employee` (
  `idc` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` int(100) NOT NULL,
  `age` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `employee`
--

INSERT INTO `employee` (`idc`, `nom`, `prenom`, `age`) VALUES
(1, 'mahdi', 3, 20),
(2, 'madi', 4, 9),
(3, 'hamdi', 55, 66);

-- --------------------------------------------------------

--
-- Structure de la table `gtransport`
--

CREATE TABLE `gtransport` (
  `id` varchar(255) NOT NULL,
  `reference` int(11) NOT NULL,
  `typee` varchar(255) NOT NULL,
  `availablity` varchar(255) NOT NULL,
  `driver` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `offre`
--

CREATE TABLE `offre` (
  `idoffre` varchar(255) NOT NULL,
  `id_reservation` varchar(255) NOT NULL,
  `date_validite` date NOT NULL,
  `taux_de_remise` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `Path_image` varchar(255) NOT NULL,
  `Path_video` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `pilote`
--

CREATE TABLE `pilote` (
  `idpilote` int(11) NOT NULL,
  `idc` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `pilote`
--

INSERT INTO `pilote` (`idpilote`, `idc`, `nom`, `prenom`) VALUES
(1, 1, 'arfa', 'ssda'),
(2, 2, 'km', 'kjb'),
(6, 3, 'asd', 'asd');

-- --------------------------------------------------------

--
-- Structure de la table `transport`
--

CREATE TABLE `transport` (
  `availablity` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `idCli` varchar(255) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `idCli`, `lastname`, `email`, `gender`, `password`) VALUES
(1, 'arfaoui', 'mahdi', 'arfa', 'Male', '12345'),
(2, 'arfaoui', 'mahdi', 'arfa', 'Male', '12345'),
(3, 'arfaoui', 'madi', 'mahdi.arfaoui@gmail.com', 'Male', '123456789'),
(4, 'mahdi', 'arfaoui', 'Mahdi@gmail.com', 'Male', '12345');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`idCli`),
  ADD KEY `Cin` (`Cin`);

--
-- Index pour la table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`idc`);

--
-- Index pour la table `gtransport`
--
ALTER TABLE `gtransport`
  ADD UNIQUE KEY `id` (`id`);

--
-- Index pour la table `pilote`
--
ALTER TABLE `pilote`
  ADD PRIMARY KEY (`idpilote`),
  ADD KEY `idc` (`idc`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `firstname` (`idCli`),
  ADD KEY `firstname_2` (`idCli`),
  ADD KEY `idCli` (`idCli`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `pilote`
--
ALTER TABLE `pilote`
  ADD CONSTRAINT `pilote_ibfk_1` FOREIGN KEY (`idc`) REFERENCES `employee` (`idc`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
