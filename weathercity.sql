-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 23 fév. 2024 à 01:16
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `weathercity`
--

-- --------------------------------------------------------

--
-- Structure de la table `city`
--

CREATE TABLE `city` (
  `cityId` int(11) NOT NULL,
  `cityName` varchar(255) NOT NULL,
  `currentTemperature` double DEFAULT NULL,
  `currentHumidity` double DEFAULT NULL,
  `currentWindSpeed` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `city`
--

INSERT INTO `city` (`cityId`, `cityName`, `currentTemperature`, `currentHumidity`, `currentWindSpeed`) VALUES
(1, 'London', 18, 65, 12),
(3, 'bnimlal', 50, 20, 45),
(4, 'MEKNES', 12, 34, 34),
(5, 'FES', 12, 33, 33);

--
-- Déclencheurs `city`
--
DELIMITER $$
CREATE TRIGGER `after_city_insert` AFTER INSERT ON `city` FOR EACH ROW BEGIN
    INSERT INTO cityhistory (cityId, eventDate, temperature)
    VALUES (NEW.cityId, NOW(), NEW.currentTemperature);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `cityhistory`
--

CREATE TABLE `cityhistory` (
  `historicalDataId` int(11) NOT NULL,
  `cityId` int(11) NOT NULL,
  `eventDate` date DEFAULT NULL,
  `temperature` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `cityhistory`
--

INSERT INTO `cityhistory` (`historicalDataId`, `cityId`, `eventDate`, `temperature`) VALUES
(1, 1, '2024-02-22', 17.5),
(2, 1, '2024-02-21', 17.8),
(3, 3, '2024-02-22', 50),
(4, 4, '2024-02-22', 12),
(5, 5, '2023-12-12', 23);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`cityId`);

--
-- Index pour la table `cityhistory`
--
ALTER TABLE `cityhistory`
  ADD PRIMARY KEY (`historicalDataId`),
  ADD KEY `fk_city_id` (`cityId`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `city`
--
ALTER TABLE `city`
  MODIFY `cityId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `cityhistory`
--
ALTER TABLE `cityhistory`
  MODIFY `historicalDataId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `cityhistory`
--
ALTER TABLE `cityhistory`
  ADD CONSTRAINT `fk_city_id` FOREIGN KEY (`cityId`) REFERENCES `city` (`cityId`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
