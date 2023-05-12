-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3307
-- Généré le : jeu. 04 mai 2023 à 10:27
-- Version du serveur :  10.6.5-MariaDB
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `cfadb`
--

-- --------------------------------------------------------

--
-- Structure de la table `dossier_projet`
--

DROP TABLE IF EXISTS `dossier_projet`;
CREATE TABLE IF NOT EXISTS `dossier_projet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `projet_id` bigint(20) DEFAULT NULL,
  `etudiant_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKemg71j4bbuekflck1lgkea5aw` (`projet_id`),
  KEY `FKo1sxu7ris4umpm70o4sokijfk` (`etudiant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `dossier_projet`
--

INSERT INTO `dossier_projet` (`id`, `version`, `nom`, `projet_id`, `etudiant_id`) VALUES
(1, 0, '<p>dthgf</p>', NULL, NULL);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `dossier_projet`
--
ALTER TABLE `dossier_projet`
  ADD CONSTRAINT `FKemg71j4bbuekflck1lgkea5aw` FOREIGN KEY (`projet_id`) REFERENCES `projet` (`id`),
  ADD CONSTRAINT `FKo1sxu7ris4umpm70o4sokijfk` FOREIGN KEY (`etudiant_id`) REFERENCES `etudiant` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
