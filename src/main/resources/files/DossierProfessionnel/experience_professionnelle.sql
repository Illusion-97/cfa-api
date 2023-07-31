-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3307
-- Généré le : lun. 17 juil. 2023 à 09:45
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
-- Structure de la table `experience_professionnelle`
--

DROP TABLE IF EXISTS `experience_professionnelle`;
CREATE TABLE IF NOT EXISTS `experience_professionnelle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `collaborateur` varchar(255) DEFAULT NULL,
  `contexte` text DEFAULT NULL,
  `information` text DEFAULT NULL,
  `moyen_utilise` text DEFAULT NULL,
  `tache_realisee` text DEFAULT NULL,
  `competence_professionnelle_id` bigint(20) DEFAULT NULL,
  `dossier_professionnel_id` bigint(20) DEFAULT NULL,
  `etudiant_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK25f0ty8nr7hax4ax9i2jgj2g4` (`competence_professionnelle_id`),
  KEY `FKs5nvl3f94lc6bmb0mot2w7m14` (`dossier_professionnel_id`),
  KEY `FK6gbetvutjem3ar0068ptifj7w` (`etudiant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `experience_professionnelle`
--

INSERT INTO `experience_professionnelle` (`id`, `version`, `collaborateur`, `contexte`, `information`, `moyen_utilise`, `tache_realisee`, `competence_professionnelle_id`, `dossier_professionnel_id`, `etudiant_id`) VALUES
(1, 0, '<p>groupe</p>', '<p>contexte</p>', '<p>pas d\'information complémentaires</p>', '<p>figma</p>', '<p>maquettage</p>', 20, 1, 226),
(2, 0, '<p>collab2</p>', '<p>contexte2</p>', '<p>information2</p>', '<p>moy2</p>', '<p>tache2</p>', 17, 1, 226),
(3, 0, '<p>collab1</p>', '<p>context1</p>', '<p>information1</p>', '<p>moy1</p>', '<p>tache1</p>', 21, 1, 226);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `experience_professionnelle`
--
ALTER TABLE `experience_professionnelle`
  ADD CONSTRAINT `FK25f0ty8nr7hax4ax9i2jgj2g4` FOREIGN KEY (`competence_professionnelle_id`) REFERENCES `competence_professionnelle` (`id`),
  ADD CONSTRAINT `FK6gbetvutjem3ar0068ptifj7w` FOREIGN KEY (`etudiant_id`) REFERENCES `etudiant` (`id`),
  ADD CONSTRAINT `FKs5nvl3f94lc6bmb0mot2w7m14` FOREIGN KEY (`dossier_professionnel_id`) REFERENCES `dossier_professionnel` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
