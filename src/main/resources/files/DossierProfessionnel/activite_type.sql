-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3307
-- Généré le : lun. 19 juin 2023 à 14:35
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
-- Structure de la table `activite_type`
--

DROP TABLE IF EXISTS `activite_type`;
CREATE TABLE IF NOT EXISTS `activite_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `libelle` varchar(255) NOT NULL,
  `numero_fiche` tinyint(4) NOT NULL,
  `cursus_activite_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl4wf05xa1d8rx18n0d932i301` (`cursus_activite_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `activite_type`
--

INSERT INTO `activite_type` (`id`, `version`, `libelle`, `numero_fiche`, `cursus_activite_type_id`) VALUES
(14, 0, 'Concevoir et développer des composants d\'interface\r\nutilisateur en intégrant les recommandations de\r\nsécurité\r\n', 1, 4),
(15, 0, 'Concevoir et développer la persistance des données\r\nen intégrant les recommandations de sécurité', 2, 4),
(16, 0, 'Concevoir et développer une application multicouche\r\nrépartie en intégrant les recommandations de sécurité\r\n', 3, 4),
(17, 2, 'Administrer et sécuriser les composants constituant l\'infrastructure', 113, 1),
(24, 0, 'Concevoir et développer des composants d\'interface utilisateur en intégrant les recommandations de sécurité.', 5, 6),
(64, 0, 'Concevoir et développer la persistance des données\r\nen intégrant les recommandations de sécurité', 9, 6),
(65, 1, 'Concevoir et développer une application multicouche\r\nrépartie en intégrant les recommandations de sécurité\r\n', 10, 6),
(69, 2, 'Intégrer, administrer et sécuriser une infrastructure distribuée', 50, 1),
(70, 1, 'Faire évoluer et optimiser l\'infrastructure et son niveau de sécurité', 9, 1),
(71, 1, 'Modéliser un projet BIM de niveau 1 (Maquette numérique isolée) ', 60, 2),
(72, 1, 'Modéliser un projet BIM de niveau 2 (Maquette numérique collaborative)', 51, 2),
(74, 1, 'Concevoir les éléments graphiques d\'une interface et de supports de communication', 53, 3),
(75, 1, 'Contribuer à la gestion et au suivi d\'un projet de communication numérique', 54, 3),
(76, 1, 'Réaliser, améliorer et animer des sites web', 55, 3),
(77, 0, 'Concevoir et développer des composants d\'interface utilisateur en intégrant les recommandations de sécurité', 56, 5),
(78, 0, 'Concevoir et développer la persistance des données en intégrant les recommandations de sécurité', 57, 5),
(79, 0, 'Concevoir et développer une application multicouche répartie en intégrant les recommandations de sécurité', 58, 5),
(80, 0, 'Concevoir et développer une application multicouche répartie en intégrant les recommandations de sécurité', 59, 7),
(81, 0, 'Concevoir et développer la persistance des données en intégrant les recommandations de sécurité', 26, 7),
(82, 0, 'Concevoir et développer des composants d\'interface utilisateur en intégrant les recommandations de sécurité', 62, 7),
(83, 0, 'Concevoir et développer des composants d\'interface utilisateur en intégrant les recommandations de sécurité', 64, 8),
(84, 0, ' Concevoir et développer la persistance des données en intégrant les recommandations de sécurité', 62, 8),
(85, 0, 'Concevoir et développer une application multicouche répartie en intégrant les recommandations de sécurité', 65, 8),
(86, 0, 'Concevoir et développer une application multicouche répartie en intégrant les recommandations de sécurité', 46, 9),
(87, 0, 'Concevoir et développer la persistance des données en intégrant les recommandations de sécurité» :-Mettre en place une base de données', 44, 9),
(88, 0, ' Concevoir et développer des composants d\'interface utilisateur en intégrant les recommandations de sécurité', 45, 9),
(89, 0, ' Développer la partie front-end d\'une application web ou web mobile en intégrant les recommandations de sécurité', 66, 11),
(90, 0, 'Développer la partie back-end d\'une application web ou web mobile en intégrant les recommandations de sécurité', 67, 11),
(91, 0, 'Concevoir et préparer la formation', 68, 12),
(92, 0, 'Animer une formation et évaluer les acquis des apprenants', 31, 12),
(93, 0, 'Accompagner les apprenants en formation', 41, 12),
(94, 0, ' Inscrire sa pratique professionnelle dans une démarche de qualité et de responsabilité sociale des entreprises', 29, 12),
(95, 0, ' Inscrire sa pratique professionnelle dans une démarche de qualité et de responsabilité sociale des entreprises', 43, 13),
(96, 0, 'Accompagner les apprenants en formation', 86, 13),
(97, 0, 'Animer une formation et évaluer les acquis des apprenants', 81, 13),
(98, 0, 'Concevoir et préparer la formation', 83, 13),
(99, 0, 'Préparer et effectuer le montage de différents produits courts', 88, 14),
(100, 0, 'Mettre en œuvre des techniques avancées du montage', 19, 14),
(101, 0, 'Elaborer une stratégie commerciale omnicanale pour un secteur géographique défini Assurer une veille commerciale pour analyser l\'état du marché.', 89, 15),
(102, 0, ' Prospecter et négocier une proposition commerciale', 96, 15),
(103, 0, 'Assister les utilisateurs en centre de services', 91, 17),
(104, 0, 'Maintenir, exploiter et sécuriser une infrastructure centralisée', 58, 17),
(105, 0, 'Maintenir et exploiter une infrastructure distribuée et contribuer à sa sécurisation', 49, 17),
(106, 0, 'Administrer les serveurs Linux', 95, 17);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `activite_type`
--
ALTER TABLE `activite_type`
  ADD CONSTRAINT `FKl4wf05xa1d8rx18n0d932i301` FOREIGN KEY (`cursus_activite_type_id`) REFERENCES `cursus` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
