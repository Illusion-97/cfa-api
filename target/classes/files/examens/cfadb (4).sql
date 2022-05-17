-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 03 mai 2022 à 15:16
-- Version du serveur :  10.3.12-MariaDB
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `cfadb`
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `activite_type`
--

INSERT INTO `activite_type` (`id`, `version`, `libelle`, `numero_fiche`, `cursus_activite_type_id`) VALUES
(4, 0, 'Concevoir et développer des composants d\'interface\r\nutilisateur en intégrant les recommandations de\r\nsécurité\r\n', 1, 1),
(7, 0, 'Concevoir et développer la persistance des données\r\nen intégrant les recommandations de sécurité', 2, 1),
(8, 0, 'Concevoir et développer une application multicouche\r\nrépartie en intégrant les recommandations de sécurité\r\n', 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

DROP TABLE IF EXISTS `adresse`;
CREATE TABLE IF NOT EXISTS `adresse` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `code_postal` varchar(255) NOT NULL,
  `numero` int(11) NOT NULL,
  `rue` varchar(255) NOT NULL,
  `ville` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `adresse`
--

INSERT INTO `adresse` (`id`, `version`, `code_postal`, `numero`, `rue`, `ville`) VALUES
(1, 0, '44200', 12, 'rue Gaetan Rondeau', 'Nantes'),
(2, 0, '75015', 11, 'rue Antoine Bourdelle', 'Paris'),
(18, 0, '85500', 7, 'Rue', 'Jacques Monod');

-- --------------------------------------------------------

--
-- Structure de la table `cef`
--

DROP TABLE IF EXISTS `cef`;
CREATE TABLE IF NOT EXISTS `cef` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `centre_formation_id` bigint(20) DEFAULT NULL,
  `entreprise_id` bigint(20) DEFAULT NULL,
  `utilisateur_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa2elsvxg5yd58b1y42gb0xl4p` (`centre_formation_id`),
  KEY `FKs9nldcuuxbm1bmsny5156ic3f` (`entreprise_id`),
  KEY `FK8i4pmgylhbb1d3a2y9rua18vh` (`utilisateur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `cef`
--

INSERT INTO `cef` (`id`, `version`, `centre_formation_id`, `entreprise_id`, `utilisateur_id`) VALUES
(1, 0, 1, NULL, 3);

-- --------------------------------------------------------

--
-- Structure de la table `centre_formation`
--

DROP TABLE IF EXISTS `centre_formation`;
CREATE TABLE IF NOT EXISTS `centre_formation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `country_code` varchar(4) NOT NULL,
  `id_dg2` bigint(20) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `adresse_id` bigint(20) DEFAULT NULL,
  `entreprise_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoph0u2310r6vavjnewil8jjww` (`adresse_id`),
  KEY `FKmtswmoxbs5fep0poo8t6860e8` (`entreprise_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `centre_formation`
--

INSERT INTO `centre_formation` (`id`, `version`, `country_code`, `id_dg2`, `nom`, `adresse_id`, `entreprise_id`) VALUES
(1, 0, 'FR', 1, 'Bordeaux', NULL, NULL),
(2, 0, 'FR', 2, 'Lille', NULL, NULL),
(3, 0, 'FR', 3, 'Lyon', NULL, NULL),
(4, 0, 'FR', 4, 'Marseille', NULL, NULL),
(5, 0, 'FR', 5, 'Montpellier', NULL, NULL),
(6, 0, 'FR', 6, 'Nantes', NULL, NULL),
(7, 0, 'FR', 7, 'Paris', NULL, NULL),
(8, 0, 'FR', 8, 'Rennes', NULL, NULL),
(9, 0, 'FR', 9, 'Strasbourg (OLD)', NULL, NULL),
(10, 0, 'FR', 10, 'Toulouse', NULL, NULL),
(11, 0, 'FR', 11, 'Sur site', NULL, NULL),
(12, 0, 'FR', 12, 'Nice', NULL, NULL),
(13, 0, 'BE', 13, 'Bruxelles', NULL, NULL),
(14, 0, 'LU', 14, 'Luxembourg', NULL, NULL),
(15, 0, 'CH', 15, 'Geneve', NULL, NULL),
(16, 0, 'FR', 17, 'Distance', NULL, NULL),
(17, 0, 'FR', 21, 'Aix en Provence', NULL, NULL),
(18, 0, 'FR', 23, 'Amiens', NULL, NULL),
(19, 0, 'FR', 25, 'France entière', NULL, NULL),
(20, 0, 'FR', 27, 'Paris 2', NULL, NULL),
(21, 0, 'FR', 28, 'Nantes 2', NULL, NULL),
(22, 0, 'FR', 29, 'Strasbourg', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `cerfa`
--

DROP TABLE IF EXISTS `cerfa`;
CREATE TABLE IF NOT EXISTS `cerfa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `assurance_chomage` varchar(255) DEFAULT NULL,
  `autre` varchar(255) DEFAULT NULL,
  `caisse_de_retraite` varchar(255) DEFAULT NULL,
  `cfa_entreprise` varchar(255) DEFAULT NULL,
  `cfa_responsable` varchar(255) DEFAULT NULL,
  `cfa_siret` varchar(255) DEFAULT NULL,
  `cfa_uai` varchar(255) DEFAULT NULL,
  `code_idcc_convention` varchar(255) DEFAULT NULL,
  `code_rncp` varchar(255) DEFAULT NULL,
  `commune_naissance` varchar(255) DEFAULT NULL,
  `complement_apprentit` varchar(255) DEFAULT NULL,
  `complement_employeur` varchar(255) DEFAULT NULL,
  `complement_representant` varchar(255) DEFAULT NULL,
  `complement_responsable` varchar(255) DEFAULT NULL,
  `contrat_num` varchar(255) DEFAULT NULL,
  `contrat_type` varchar(255) DEFAULT NULL,
  `convention_collective_applicable` varchar(255) DEFAULT NULL,
  `date_avenant` date DEFAULT NULL,
  `date_conclusion` date DEFAULT NULL,
  `date_de_naissance` varchar(255) DEFAULT NULL,
  `date_de_naissance_deuxieme_tuteur` varchar(255) DEFAULT NULL,
  `date_de_naissance_premier_tuteur` varchar(255) DEFAULT NULL,
  `date_debut_contrat` date DEFAULT NULL,
  `date_debut_formation` date DEFAULT NULL,
  `date_decision` date DEFAULT NULL,
  `date_examen` date DEFAULT NULL,
  `date_fin_contrat` date DEFAULT NULL,
  `departement_naissance` varchar(255) DEFAULT NULL,
  `dernier_diplome` varchar(255) DEFAULT NULL,
  `derniere_classe_suivi` varchar(255) DEFAULT NULL,
  `derogation_type` varchar(255) DEFAULT NULL,
  `diplome_code` varchar(255) DEFAULT NULL,
  `diplome_le_plus_eleve_obtenu` varchar(255) DEFAULT NULL,
  `diplome_vise` varchar(255) DEFAULT NULL,
  `effectif_entreprise` varchar(255) DEFAULT NULL,
  `egilibilite_fonction` varchar(255) DEFAULT NULL,
  `email_apprenti` varchar(255) DEFAULT NULL,
  `email_employeur` varchar(255) DEFAULT NULL,
  `employeur_prive_ou_public` varchar(255) DEFAULT NULL,
  `employeur_specifique` varchar(255) DEFAULT NULL,
  `employeur_type` varchar(255) DEFAULT NULL,
  `faita` varchar(255) DEFAULT NULL,
  `formation_duree` varchar(255) DEFAULT NULL,
  `handicape` varchar(255) DEFAULT NULL,
  `heure_travail` varchar(255) DEFAULT NULL,
  `intitule_precis_dernier_diplome` varchar(255) DEFAULT NULL,
  `intitule_precis_diplome_vise` varchar(255) DEFAULT NULL,
  `logement` varchar(255) DEFAULT NULL,
  `machine_risque` varchar(255) DEFAULT NULL,
  `minute_travail` varchar(255) DEFAULT NULL,
  `mode_contractuel_apprentissage` varchar(255) DEFAULT NULL,
  `naf` varchar(255) DEFAULT NULL,
  `nationalite` varchar(255) DEFAULT NULL,
  `nir_apprenti` varchar(255) DEFAULT NULL,
  `nom_deuxieme_tuteur` varchar(255) DEFAULT NULL,
  `nom_employeur` varchar(255) DEFAULT NULL,
  `nom_naissance_apprenti` varchar(255) DEFAULT NULL,
  `nom_organisme` varchar(255) DEFAULT NULL,
  `nom_premier_tuteur` varchar(255) DEFAULT NULL,
  `nom_representant` varchar(255) DEFAULT NULL,
  `nourriture` varchar(255) DEFAULT NULL,
  `num_avenant` varchar(255) DEFAULT NULL,
  `num_depot` varchar(255) DEFAULT NULL,
  `prenom_apprenti` varchar(255) DEFAULT NULL,
  `prenom_deuxieme_tuteur` varchar(255) DEFAULT NULL,
  `prenom_employeur` varchar(255) DEFAULT NULL,
  `prenom_premier_tuteur` varchar(255) DEFAULT NULL,
  `prenom_representant` varchar(255) DEFAULT NULL,
  `reception_dossier` date DEFAULT NULL,
  `regime_social` varchar(255) DEFAULT NULL,
  `salaire_brut` varchar(255) DEFAULT NULL,
  `sexe` varchar(255) DEFAULT NULL,
  `siret_etablissement` varchar(255) DEFAULT NULL,
  `siret_organisme` varchar(255) DEFAULT NULL,
  `situation_avant_contrat` varchar(255) DEFAULT NULL,
  `sportifs` varchar(255) DEFAULT NULL,
  `tel_apprenti` varchar(255) DEFAULT NULL,
  `tel_employeur` varchar(255) DEFAULT NULL,
  `validation_employeur` varchar(255) DEFAULT NULL,
  `adresse_apprenti_id` bigint(20) DEFAULT NULL,
  `adresse_employeur_id` bigint(20) DEFAULT NULL,
  `adresse_representant_id` bigint(20) DEFAULT NULL,
  `adresse_responsable_id` bigint(20) DEFAULT NULL,
  `etudiant_id` bigint(20) DEFAULT NULL,
  `remuneration1_id` bigint(20) DEFAULT NULL,
  `remuneration2_id` bigint(20) DEFAULT NULL,
  `remuneration3_id` bigint(20) DEFAULT NULL,
  `remuneration4_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK59lvi67stxclu1qi7xcnyn2ck` (`adresse_apprenti_id`),
  KEY `FK5uclvt6uc9e2qhro3p01ixive` (`adresse_employeur_id`),
  KEY `FKfrq9kq7hd4an1ygahipo9nq8u` (`adresse_representant_id`),
  KEY `FKt97kxbiftvvgjyn5ud5e5br4n` (`adresse_responsable_id`),
  KEY `FKs69obwmyeupfu4nko8n98xjmg` (`etudiant_id`),
  KEY `FKlkgv2obeikkra8w1hbvibjua2` (`remuneration1_id`),
  KEY `FKqdd8g2fpm2ipyk8q41ucifp5i` (`remuneration2_id`),
  KEY `FK3t58jqpuv3ga8ua2ojrbxaost` (`remuneration3_id`),
  KEY `FK8rbxne83rxwm1iekev9q2umu0` (`remuneration4_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `competence_professionnelle`
--

DROP TABLE IF EXISTS `competence_professionnelle`;
CREATE TABLE IF NOT EXISTS `competence_professionnelle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `libelle` varchar(255) NOT NULL,
  `numero_fiche` tinyint(4) NOT NULL,
  `activite_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcq22jysorvpnk8hn2n2qroqjs` (`activite_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `competence_professionnelle`
--

INSERT INTO `competence_professionnelle` (`id`, `version`, `libelle`, `numero_fiche`, `activite_type_id`) VALUES
(1, 0, 'Maquetter une application', 1, 4),
(2, 0, 'Développer une interface utilisateur de type desktop', 2, 4),
(4, 0, 'Développer des composants d’accès aux données', 3, 4),
(5, 0, 'Développer la partie front-end d’une interface utilisateur\r\nweb', 4, 4),
(6, 0, 'Développer la partie back-end d’une interface utilisateur\r\nweb\r\n', 5, 4),
(7, 0, 'Concevoir une base de données', 6, 7),
(8, 0, 'Mettre en place une base de données', 7, 7),
(9, 0, 'Développer des composants dans le langage d’une\r\nbase de données', 8, 7),
(10, 0, 'Collaborer à la gestion d’un projet informatique et à\r\nl’organisation de l’environnement de développement', 9, 8),
(11, 0, 'Concevoir une application\r\n', 10, 8),
(12, 0, 'Développer des composants métier', 11, 8),
(13, 0, 'Construire une application organisée en couches\r\n', 12, 8),
(14, 0, 'Développer une application mobile', 13, 8),
(15, 0, 'Préparer et exécuter les plans de tests d’une\r\napplication', 14, 8),
(16, 0, 'Préparer et exécuter le déploiement d’une application', 15, 8);

-- --------------------------------------------------------

--
-- Structure de la table `conge`
--

DROP TABLE IF EXISTS `conge`;
CREATE TABLE IF NOT EXISTS `conge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `justificatif` varchar(255) DEFAULT NULL,
  `motif` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `utilisateur_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh21c9wcn1wbund2qg3kpdvg6x` (`utilisateur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `conge`
--

INSERT INTO `conge` (`id`, `version`, `date_debut`, `date_fin`, `justificatif`, `motif`, `status`, `type`, `utilisateur_id`) VALUES
(1, 0, '2021-10-25', '2021-11-08', NULL, 'Covid-19', 0, 0, 2);

-- --------------------------------------------------------

--
-- Structure de la table `contrat`
--

DROP TABLE IF EXISTS `contrat`;
CREATE TABLE IF NOT EXISTS `contrat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `etudiant_id` bigint(20) DEFAULT NULL,
  `maitre_apprentissage_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1m4npdy8chhq7yd8ih3ry57o0` (`etudiant_id`),
  KEY `FKf2svp5m02cjyci1px4nymtdy4` (`maitre_apprentissage_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `cursus`
--

DROP TABLE IF EXISTS `cursus`;
CREATE TABLE IF NOT EXISTS `cursus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `description` text DEFAULT NULL,
  `duree` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `cursus`
--

INSERT INTO `cursus` (`id`, `version`, `description`, `duree`, `titre`) VALUES
(1, 0, NULL, 0, 'CDA'),
(2, 0, NULL, 0, 'titre cursus 1'),
(3, 0, NULL, 0, 'titre cursus 2');

-- --------------------------------------------------------

--
-- Structure de la table `cursus_formations`
--

DROP TABLE IF EXISTS `cursus_formations`;
CREATE TABLE IF NOT EXISTS `cursus_formations` (
  `cursus_lst_id` bigint(20) NOT NULL,
  `formations_id` bigint(20) NOT NULL,
  KEY `FKd8om68mg9ldqs1557m2f7mili` (`formations_id`),
  KEY `FKdkq5y9v43gq7mk27s11p9m10b` (`cursus_lst_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `cursus_formations`
--

INSERT INTO `cursus_formations` (`cursus_lst_id`, `formations_id`) VALUES
(3, 1),
(3, 2),
(3, 3),
(3, 4);

-- --------------------------------------------------------

--
-- Structure de la table `devoir`
--

DROP TABLE IF EXISTS `devoir`;
CREATE TABLE IF NOT EXISTS `devoir` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `consigne` text NOT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `intervention_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKte741higntmil69njc6eek4me` (`intervention_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `devoir`
--

INSERT INTO `devoir` (`id`, `version`, `consigne`, `date_debut`, `date_fin`, `intervention_id`) VALUES
(2, 0, '', NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Structure de la table `devoir_etudiant`
--

DROP TABLE IF EXISTS `devoir_etudiant`;
CREATE TABLE IF NOT EXISTS `devoir_etudiant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `date_rendu` datetime DEFAULT NULL,
  `piece_jointe` varchar(255) DEFAULT NULL,
  `devoir_id` bigint(20) DEFAULT NULL,
  `etudiant_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5cmdlicpd87owi6dipnxnibs0` (`devoir_id`),
  KEY `FK3vnboq5nyted2pb35fgk2simb` (`etudiant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `devoir_etudiant`
--

INSERT INTO `devoir_etudiant` (`id`, `version`, `date_rendu`, `piece_jointe`, `devoir_id`, `etudiant_id`) VALUES
(1, 0, '2022-04-20 09:24:04', 'k,,kml', 2, 1),
(2, 0, '2022-04-20 09:24:04', 'REAC_CDA_V03_03052018 (4).pdf', 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `dossier_professionnel`
--

DROP TABLE IF EXISTS `dossier_professionnel`;
CREATE TABLE IF NOT EXISTS `dossier_professionnel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `cursus_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8sg5c9oe1k2q5x8ur4d6nwjns` (`cursus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `dossier_professionnel`
--

INSERT INTO `dossier_professionnel` (`id`, `version`, `nom`, `cursus_id`) VALUES
(1, 0, 'Convention-CCP-2022.pdf', NULL);

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
  PRIMARY KEY (`id`),
  KEY `FKemg71j4bbuekflck1lgkea5aw` (`projet_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `dossier_projet`
--

INSERT INTO `dossier_projet` (`id`, `version`, `nom`, `projet_id`) VALUES
(1, 0, 'Convention-CCP-2022.pdf', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `entreprise`
--

DROP TABLE IF EXISTS `entreprise`;
CREATE TABLE IF NOT EXISTS `entreprise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `effectif_total` varchar(255) NOT NULL,
  `employeur_type` varchar(255) NOT NULL,
  `naf` varchar(255) NOT NULL,
  `raison_sociale` varchar(255) NOT NULL,
  `siret` varchar(255) NOT NULL,
  `adresse_siege_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp4nf2k1tciaujn3i64o28jvuv` (`adresse_siege_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `entreprise`
--

INSERT INTO `entreprise` (`id`, `version`, `effectif_total`, `employeur_type`, `naf`, `raison_sociale`, `siret`, `adresse_siege_id`) VALUES
(1, 0, '200', 'Employeur type test', 'Naf test', 'DAWAN', '42998754800162', 2);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
CREATE TABLE IF NOT EXISTS `etudiant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `utilisateur_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk1k5fhsjak7v0uk031i5geqj9` (`utilisateur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`id`, `version`, `utilisateur_id`) VALUES
(1, 0, 2),
(2, 0, 11),
(3, 0, 12),
(4, 0, 13);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant_dossier_professionnel`
--

DROP TABLE IF EXISTS `etudiant_dossier_professionnel`;
CREATE TABLE IF NOT EXISTS `etudiant_dossier_professionnel` (
  `etudiant_id` bigint(20) NOT NULL,
  `dossier_professionnel_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_qigkwh2irlc1kos21r5anfffw` (`dossier_professionnel_id`),
  KEY `FKkth85jvff1q6w21b2b188cxwj` (`etudiant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `etudiant_dossier_professionnel`
--

INSERT INTO `etudiant_dossier_professionnel` (`etudiant_id`, `dossier_professionnel_id`) VALUES
(2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant_dossier_projet`
--

DROP TABLE IF EXISTS `etudiant_dossier_projet`;
CREATE TABLE IF NOT EXISTS `etudiant_dossier_projet` (
  `etudiant_id` bigint(20) NOT NULL,
  `dossier_projet_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_nw8agkuv7iu2ggdnar412t7gk` (`dossier_projet_id`),
  KEY `FK8a09qhuh5ouhcvqmgi8650mus` (`etudiant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `etudiant_dossier_projet`
--

INSERT INTO `etudiant_dossier_projet` (`etudiant_id`, `dossier_projet_id`) VALUES
(2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `examen`
--

DROP TABLE IF EXISTS `examen`;
CREATE TABLE IF NOT EXISTS `examen` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `date_examen` date NOT NULL,
  `descriptif` longtext DEFAULT NULL,
  `duree` double NOT NULL,
  `piece_jointe` varchar(255) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `promotion_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcu3jbla2430ilxs1vtroa8wnx` (`promotion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `examen`
--

INSERT INTO `examen` (`id`, `version`, `date_examen`, `descriptif`, `duree`, `piece_jointe`, `titre`, `promotion_id`) VALUES
(1, 0, '2022-05-11', 'QCM Java', 5, 'BIASAL_1_Feres.pdf', 'Java', 1),
(3, 0, '2022-03-17', 'Java Approfondissement1 ', 5.5, 'Url=>pieceJointe', 'Java1', 12),
(4, 0, '2022-03-17', 'Java Approfondissement ', 5.5, 'Url=>pieceJointe', 'Java', 1),
(64, 0, '2023-03-16', 'ladernièreFormation', 8, 'REAC_CDA_V03_03052018 (6).pdf', 'Examen', 1),
(65, 0, '2023-03-16', 'ladernièreFormation', 8, 'REAC_CDA_V03_03052018 (6).pdf', 'ExamenTEst', 1),
(66, 0, '2023-03-16', 'ladernièreFormation', 8, 'REAC_CDA_V03_03052018 (6).pdf', 'ExamenTEst', 1),
(67, 0, '2023-03-16', 'ladernièreFormation', 8, 'REAC_CDA_V03_03052018 (6).pdf', 'ExamenTEst', 1),
(68, 0, '2023-03-16', 'ladernièreFormation', 8, 'REAC_CDA_V03_03052018 (6).pdf', 'ExamenTEst', 1);

-- --------------------------------------------------------

--
-- Structure de la table `examen_activite_types`
--

DROP TABLE IF EXISTS `examen_activite_types`;
CREATE TABLE IF NOT EXISTS `examen_activite_types` (
  `examens_id` bigint(20) NOT NULL,
  `activite_types_id` bigint(20) NOT NULL,
  KEY `FKc9of6442gb0yft5ip2dmcmmtd` (`activite_types_id`),
  KEY `FKcbwo3566j35fe752px7d2c274` (`examens_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `examen_activite_types`
--

INSERT INTO `examen_activite_types` (`examens_id`, `activite_types_id`) VALUES
(4, 4),
(4, 7),
(1, 4),
(1, 7),
(1, 8),
(64, 4),
(64, 7),
(65, 4),
(65, 7),
(66, 4),
(66, 7),
(67, 4),
(67, 7),
(68, 4),
(68, 7);

-- --------------------------------------------------------

--
-- Structure de la table `examen_competences_professionnelles`
--

DROP TABLE IF EXISTS `examen_competences_professionnelles`;
CREATE TABLE IF NOT EXISTS `examen_competences_professionnelles` (
  `examens_id` bigint(20) NOT NULL,
  `competences_professionnelles_id` bigint(20) NOT NULL,
  PRIMARY KEY (`examens_id`,`competences_professionnelles_id`),
  KEY `FKo674b4ab3qchvdm8y4nubuxhb` (`competences_professionnelles_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `examen_competences_professionnelles`
--

INSERT INTO `examen_competences_professionnelles` (`examens_id`, `competences_professionnelles_id`) VALUES
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 11),
(1, 13),
(4, 1),
(4, 8),
(64, 1),
(64, 2),
(64, 7),
(64, 8),
(65, 1),
(65, 2),
(65, 7),
(65, 8),
(66, 1),
(66, 2),
(66, 7),
(66, 8),
(67, 1),
(67, 2),
(67, 7),
(67, 8),
(68, 1),
(68, 2),
(68, 7),
(68, 8);

-- --------------------------------------------------------

--
-- Structure de la table `fiche_entreprise`
--

DROP TABLE IF EXISTS `fiche_entreprise`;
CREATE TABLE IF NOT EXISTS `fiche_entreprise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `activite_description` varchar(255) DEFAULT NULL,
  `chiffre_affaire_annuel` varchar(255) DEFAULT NULL,
  `client_type` varchar(255) DEFAULT NULL,
  `formation_profil` varchar(255) DEFAULT NULL,
  `historique` varchar(255) DEFAULT NULL,
  `metiers_exerces` varchar(255) DEFAULT NULL,
  `nb_salarie` varchar(255) DEFAULT NULL,
  `nom_dirigeant` varchar(255) DEFAULT NULL,
  `organisation_type` varchar(255) DEFAULT NULL,
  `secteur_activite` varchar(255) DEFAULT NULL,
  `entreprise_id` bigint(20) DEFAULT NULL,
  `etudiant_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoghjxedm7lt9jj9wa87gx53uc` (`entreprise_id`),
  KEY `FKkf8agof5yl9vu92kq1ucij1no` (`etudiant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `fiche_poste`
--

DROP TABLE IF EXISTS `fiche_poste`;
CREATE TABLE IF NOT EXISTS `fiche_poste` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `composition_service` varchar(255) DEFAULT NULL,
  `intitule` varchar(255) DEFAULT NULL,
  `mission` varchar(255) DEFAULT NULL,
  `mission_principale` varchar(255) DEFAULT NULL,
  `nature` varchar(255) DEFAULT NULL,
  `positionnement` varchar(255) DEFAULT NULL,
  `etudiant_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnwc2e36dq47dqonb078jn3bhw` (`etudiant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `formateur`
--

DROP TABLE IF EXISTS `formateur`;
CREATE TABLE IF NOT EXISTS `formateur` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `entreprise_id` bigint(20) DEFAULT NULL,
  `utilisateur_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp51cpnxbsdwp19se9c0l84bro` (`entreprise_id`),
  KEY `FKrvq3k2ovvyaqts9n49b8ytnsm` (`utilisateur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `formateur`
--

INSERT INTO `formateur` (`id`, `version`, `entreprise_id`, `utilisateur_id`) VALUES
(1, 0, NULL, 4);

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

DROP TABLE IF EXISTS `formation`;
CREATE TABLE IF NOT EXISTS `formation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `contenu` varchar(1024) DEFAULT NULL,
  `duration` varchar(255) NOT NULL,
  `id_dg2` bigint(20) DEFAULT NULL,
  `slug` varchar(255) NOT NULL,
  `titre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `formation`
--

INSERT INTO `formation` (`id`, `version`, `contenu`, `duration`, `id_dg2`, `slug`, `titre`) VALUES
(1, 0, 'La formation : Java Initiation + Approfondissement représente le point de départ de votre apprentissage. Elle s\'adresse à des développeurs ayant déjà des bases d\'algorithmique et des connaissances sur un langage de programmation.', '', 1, '', 'JAVA'),
(2, 0, 'Initiation gitlab / ligne de commande bash linux', '', 2, '', 'Usine logicielle'),
(3, 0, 'Administration Postgres', '', 3, '', 'Postgres SQL'),
(4, 0, '', '', 4, '', 'Spring MVC'),
(5, 0, NULL, '100.00', 101232, 'titre-professionnel-administrateur-d-infrastructures-securisees-tp-01352m01', 'Titre professionnel Administrateur d\'infrastructures sécurisées (TP-01352m01)'),
(6, 0, NULL, '100.00', 101234, 'titre-professionnel-assistant-e-commercial-e-tp-01285m02', 'Titre professionnel Assistant(e) commercial(e) (TP-01285m02)'),
(7, 0, NULL, '100.00', 101230, 'titre-professionnel-bim-modeleur-du-batiment-tp-01361m01', 'Titre professionnel BIM modeleur du batîment (TP-01361m01)'),
(8, 0, NULL, '100.00', 101503, 'titre-professionnel-concepteur-designer-ui-tp-01411m01', 'Titre professionnel Concepteur Designer UI (TP-01411m01)'),
(9, 0, NULL, '100.00', 101229, 'titre-professionnel-concepteur-developpeur-d-applications-tp-01281m03', 'Titre professionnel Concepteur Développeur d\'Applications (TP-01281m03)'),
(10, 0, NULL, '100.00', 101184, 'titre-professionnel-designer-web-tp-00469m05', 'Titre professionnel Designer Web (TP-00469m05)'),
(11, 0, NULL, '100.00', 101235, 'titre-professionnel-developpeur-web-et-web-mobile-tp-01280m03', 'Titre professionnel Développeur web et web mobile (TP-01280m03)'),
(12, 0, NULL, '100.00', 101233, 'titre-professionnel-formateur-professionnel-d-adultes-tp-00350m06', 'Titre professionnel Formateur professionnel d\'adultes (TP-00350m06)'),
(13, 0, NULL, '100.00', 101236, 'titre-professionnel-monteur-monteuse-audiovisiuel-tp-01236m04', 'Titre professionnel Monteur / monteuse audiovisuel (TP-01236m04))'),
(14, 0, NULL, '100.00', 101266, 'titre-professionnel-negociateur-technico-commercial-tp-00338m8', 'Titre professionnel Négociateur technico-commercial (TP-00338m8)'),
(15, 0, NULL, '80.00', 101193, 'titre-professionnel-technicien-d-assistance-en-informatique-tp-00476m07', 'Titre professionnel Technicien d\'assistance en informatique (TP-00476m07)'),
(16, 0, NULL, '100.00', 101231, 'titre-professionnel-technicien-superieur-systemes-et-reseaux-tp-01351m01', 'Titre professionnel Technicien supérieur systèmes et réseaux (TP-01351m01)');

-- --------------------------------------------------------

--
-- Structure de la table `groupe_etudiant`
--

DROP TABLE IF EXISTS `groupe_etudiant`;
CREATE TABLE IF NOT EXISTS `groupe_etudiant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `groupe_etudiant`
--

INSERT INTO `groupe_etudiant` (`id`, `version`, `nom`) VALUES
(1, 0, '1');

-- --------------------------------------------------------

--
-- Structure de la table `groupe_etudiant_etudiants`
--

DROP TABLE IF EXISTS `groupe_etudiant_etudiants`;
CREATE TABLE IF NOT EXISTS `groupe_etudiant_etudiants` (
  `groupes_id` bigint(20) NOT NULL,
  `etudiants_id` bigint(20) NOT NULL,
  KEY `FKpg1ue094ie4xh4x3n05lgv2e` (`etudiants_id`),
  KEY `FK42l5yutnq8bg4tvfdxntlx0s2` (`groupes_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `groupe_etudiant_etudiants`
--

INSERT INTO `groupe_etudiant_etudiants` (`groupes_id`, `etudiants_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `intervention`
--

DROP TABLE IF EXISTS `intervention`;
CREATE TABLE IF NOT EXISTS `intervention` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `note_info_personnel` varchar(255) DEFAULT NULL,
  `formation_id` bigint(20) DEFAULT NULL,
  `intervention_mere_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK77ggtrufswhjnpr6ik2ayd66c` (`formation_id`),
  KEY `FK6hamywu6944nnls61cgs6afn6` (`intervention_mere_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `intervention`
--

INSERT INTO `intervention` (`id`, `version`, `date_debut`, `date_fin`, `note_info_personnel`, `formation_id`, `intervention_mere_id`) VALUES
(1, 0, '2021-10-25', '2021-10-29', NULL, 1, NULL),
(2, 0, '2021-11-03', '2021-11-05', NULL, 2, NULL),
(3, 0, '2021-11-10', '2021-11-12', NULL, 3, NULL),
(4, 0, '2021-11-17', '2021-11-19', NULL, 4, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `intervention_formateurs`
--

DROP TABLE IF EXISTS `intervention_formateurs`;
CREATE TABLE IF NOT EXISTS `intervention_formateurs` (
  `interventions_id` bigint(20) NOT NULL,
  `formateurs_id` bigint(20) NOT NULL,
  KEY `FK9bgk5dqfwt6elqkolh71gaqej` (`formateurs_id`),
  KEY `FK6ymhuumlv6u3n6xaug3f4184w` (`interventions_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `intervention_formateurs`
--

INSERT INTO `intervention_formateurs` (`interventions_id`, `formateurs_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1);

-- --------------------------------------------------------

--
-- Structure de la table `intervention_promotions`
--

DROP TABLE IF EXISTS `intervention_promotions`;
CREATE TABLE IF NOT EXISTS `intervention_promotions` (
  `interventions_id` bigint(20) NOT NULL,
  `promotions_id` bigint(20) NOT NULL,
  KEY `FKmcjufiqw7n0nrixtk7okb9hx` (`promotions_id`),
  KEY `FKr0il7igkwlkd5aorm4ah8x4eb` (`interventions_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `intervention_promotions`
--

INSERT INTO `intervention_promotions` (`interventions_id`, `promotions_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1);

-- --------------------------------------------------------

--
-- Structure de la table `maitre_apprentissage`
--

DROP TABLE IF EXISTS `maitre_apprentissage`;
CREATE TABLE IF NOT EXISTS `maitre_apprentissage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `entreprise_id` bigint(20) DEFAULT NULL,
  `utilisateur_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh1qktnnwl0fq095v7bxem6neb` (`entreprise_id`),
  KEY `FKt7iy3trbpqf8i3n8wme8qx1x4` (`utilisateur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `maitre_apprentissage`
--

INSERT INTO `maitre_apprentissage` (`id`, `version`, `entreprise_id`, `utilisateur_id`) VALUES
(1, 0, 1, 4);

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

DROP TABLE IF EXISTS `note`;
CREATE TABLE IF NOT EXISTS `note` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `note_obtenue` double NOT NULL,
  `satisfaction` varchar(255) DEFAULT NULL,
  `etudiant_note_id` bigint(20) DEFAULT NULL,
  `examen_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi3dn4105svs3a0y62yshrmaua` (`etudiant_note_id`),
  KEY `FKqeexjhoqpiewuw1wo7orh8ktt` (`examen_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `note`
--

INSERT INTO `note` (`id`, `version`, `note_obtenue`, `satisfaction`, `etudiant_note_id`, `examen_id`) VALUES
(1, 0, 16.5, 'OUI', 1, 4),
(2, 0, 16.5, 'OUI', 2, 4),
(3, 0, 8.5, 'NON', 3, 4),
(13, 0, 17, 'OUI', 4, 4),
(29, 0, 0, NULL, 2, 64),
(30, 0, 0, NULL, 1, 64),
(31, 0, 0, NULL, 3, 64),
(32, 0, 0, NULL, 1, 65),
(33, 0, 0, NULL, 3, 65),
(34, 0, 0, NULL, 2, 65),
(35, 0, 0, NULL, 3, 66),
(36, 0, 0, NULL, 1, 66),
(37, 0, 0, NULL, 2, 66),
(38, 0, 0, NULL, 2, 67),
(39, 0, 0, NULL, 3, 67),
(40, 0, 0, NULL, 1, 67),
(41, 0, 0, NULL, 2, 68),
(42, 0, 0, NULL, 1, 68),
(43, 0, 0, NULL, 3, 68);

-- --------------------------------------------------------

--
-- Structure de la table `passage_examen`
--

DROP TABLE IF EXISTS `passage_examen`;
CREATE TABLE IF NOT EXISTS `passage_examen` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `examen_id` bigint(20) DEFAULT NULL,
  `intervention_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9ihmsevcure66yyfpd4mko3vl` (`examen_id`),
  KEY `FKj8b6rqicnig8xwwl933d4y5vl` (`intervention_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `passage_examen`
--

INSERT INTO `passage_examen` (`id`, `version`, `date_debut`, `date_fin`, `examen_id`, `intervention_id`) VALUES
(1, 0, '2021-10-25', '2021-10-25', 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `positionnement`
--

DROP TABLE IF EXISTS `positionnement`;
CREATE TABLE IF NOT EXISTS `positionnement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `niveau_debut` varchar(255) DEFAULT NULL,
  `niveau_fin` varchar(255) DEFAULT NULL,
  `etudiant_id` bigint(20) DEFAULT NULL,
  `intervention_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4nt68quqv4sxfwn09ln5wt3iw` (`etudiant_id`),
  KEY `FKlw6tb0tq8304u8teq3d0x40ca` (`intervention_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `positionnement`
--

INSERT INTO `positionnement` (`id`, `version`, `niveau_debut`, `niveau_fin`, `etudiant_id`, `intervention_id`) VALUES
(1, 0, 'AUCUNECONNAISSANCE', 'ENCOURSDACQUISITION', 1, 1),
(2, 0, 'AUCUNECONNAISSANCE', 'ENCOURSDACQUISITION', 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `projet`
--

DROP TABLE IF EXISTS `projet`;
CREATE TABLE IF NOT EXISTS `projet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `groupe_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqf4p3ktsa8gjmytpgwx2fd6nk` (`groupe_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `projet`
--

INSERT INTO `projet` (`id`, `version`, `description`, `nom`, `groupe_id`) VALUES
(1, 0, 'Description', 'Projet Nom', 1);

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE IF NOT EXISTS `promotion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  `cef_id` bigint(20) DEFAULT NULL,
  `centre_formation_id` bigint(20) DEFAULT NULL,
  `cursus_id` bigint(20) DEFAULT NULL,
  `referent_pedagogique_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9u5jtq6e3tf4ull9h212smqd9` (`cef_id`),
  KEY `FK6fwqcy9xbmule71h1vgo6r557` (`centre_formation_id`),
  KEY `FKij3ht1gimy6e0o66m5bhtmg7t` (`cursus_id`),
  KEY `FKsq7140tt77odsbi8ve5pkr9ik` (`referent_pedagogique_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `promotion`
--

INSERT INTO `promotion` (`id`, `version`, `date_debut`, `date_fin`, `nom`, `cef_id`, `centre_formation_id`, `cursus_id`, `referent_pedagogique_id`) VALUES
(1, 0, '2021-01-01', '2021-12-31', 'Concepteur Developpeur d\'Applications NANTES 2021', 1, 1, 1, 4),
(2, 0, '2021-01-01', '2021-12-31', 'Concepteur Developpeur d\'Applications PARIS 2021', 1, 1, 2, 4),
(3, 0, '2021-01-01', '2021-12-31', 'Concepteur Developpeur d\'Applications AMIENS 2021', 1, 1, 3, 4),
(12, 0, '2022-01-01', '2022-12-31', 'Concepteur Developpeur d\'Applications NANTES 2022', NULL, NULL, 1, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `promotion_etudiants`
--

DROP TABLE IF EXISTS `promotion_etudiants`;
CREATE TABLE IF NOT EXISTS `promotion_etudiants` (
  `promotions_id` bigint(20) NOT NULL,
  `etudiants_id` bigint(20) NOT NULL,
  KEY `FK7jutuwjxuyj0gw5riqfcmh8cu` (`etudiants_id`),
  KEY `FKmcaob1f6ti7ulog2k15aa3hm2` (`promotions_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `promotion_etudiants`
--

INSERT INTO `promotion_etudiants` (`promotions_id`, `etudiants_id`) VALUES
(2, 1),
(3, 1),
(1, 1),
(1, 2),
(1, 3),
(12, 4);

-- --------------------------------------------------------

--
-- Structure de la table `remuneration`
--

DROP TABLE IF EXISTS `remuneration`;
CREATE TABLE IF NOT EXISTS `remuneration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `pourcentage` varchar(255) DEFAULT NULL,
  `smic_ou_smc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `signature`
--

DROP TABLE IF EXISTS `signature`;
CREATE TABLE IF NOT EXISTS `signature` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `piece_jointe` varchar(255) NOT NULL,
  `utilisateur_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo578vflrvlsxenwbgkw136eoj` (`utilisateur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `signature`
--

INSERT INTO `signature` (`id`, `version`, `piece_jointe`, `utilisateur_id`) VALUES
(1, 0, 'Rien', 1),
(2, 0, '76019AUWT72924892981220413025015985782206 (2).pdf', NULL),
(3, 0, '76019AUWT72924892981220413025015985782206 (2).pdf', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `support_cours`
--

DROP TABLE IF EXISTS `support_cours`;
CREATE TABLE IF NOT EXISTS `support_cours` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `piece_jointe` varchar(255) NOT NULL,
  `titre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `support_cours_interventions`
--

DROP TABLE IF EXISTS `support_cours_interventions`;
CREATE TABLE IF NOT EXISTS `support_cours_interventions` (
  `supports_cours_id` bigint(20) NOT NULL,
  `interventions_id` bigint(20) NOT NULL,
  KEY `FKerev1cf6i4l63crg9wkg0sa0l` (`interventions_id`),
  KEY `FKdo8sr6wal54xab4gjgpusnpwr` (`supports_cours_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `civilite` varchar(255) NOT NULL,
  `date_de_naissance` date DEFAULT NULL,
  `login` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `adresse_id` bigint(20) DEFAULT NULL,
  `cef_id` bigint(20) DEFAULT NULL,
  `etudiant_id` bigint(20) DEFAULT NULL,
  `formateur_id` bigint(20) DEFAULT NULL,
  `maitre_apprentissage_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdvxe31y87uhakroedfn9vxgdy` (`adresse_id`),
  KEY `FKl76ecx5l7w6wo0slu91vbive8` (`cef_id`),
  KEY `FKjfbmjulv47mayggeqcsw576cq` (`etudiant_id`),
  KEY `FKmnq3097ucopqv2hgxqwd2do18` (`formateur_id`),
  KEY `FK402esxc4bg5refg7j7ca7b982` (`maitre_apprentissage_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `version`, `civilite`, `date_de_naissance`, `login`, `nom`, `password`, `prenom`, `telephone`, `adresse_id`, `cef_id`, `etudiant_id`, `formateur_id`, `maitre_apprentissage_id`) VALUES
(1, 0, 'Mr', '2021-10-25', 'admin@dawan.fr', 'Admin', 'ee1067d2c54d8b095bb7b3937aa40968cc3475e4360433a8bf816217e823271fcc9e7222dd9e57afb5675d999b88f49574ed8e6a3833b1437910e9aba7b6575f', 'Admin', '06.12.80.45.99', NULL, NULL, NULL, NULL, NULL),
(2, 0, 'Mr', '2021-10-25', 'tbillon@dawan.fr', 'Billon', 'ee1067d2c54d8b095bb7b3937aa40968cc3475e4360433a8bf816217e823271fcc9e7222dd9e57afb5675d999b88f49574ed8e6a3833b1437910e9aba7b6575f', 'Tanguy', '0612804599', 1, NULL, 1, NULL, NULL),
(3, 0, 'Mme', '2021-10-25', 'cef@dawan.fr', 'Baron Gomez', 'ee1067d2c54d8b095bb7b3937aa40968cc3475e4360433a8bf816217e823271fcc9e7222dd9e57afb5675d999b88f49574ed8e6a3833b1437910e9aba7b6575f', 'Laurence', '06.12.80.45.96', 1, 1, NULL, NULL, NULL),
(4, 0, 'Mr', '2021-10-25', 'formateur@dawan.fr', 'Menut', 'ee1067d2c54d8b095bb7b3937aa40968cc3475e4360433a8bf816217e823271fcc9e7222dd9e57afb5675d999b88f49574ed8e6a3833b1437910e9aba7b6575f', 'Stéphane', '06.12.80.45.95', 1, NULL, NULL, 1, 1),
(5, 0, 'Mr', '2021-10-25', 'jmerckling@dawan.fr', 'Merckling', '1d35d6a6918050ac6e50d9f7ea6461078942416a1567a3e4037fc81576794eab65f437c6320dceeb4ddf39358c20a61b139831e2b6980e205ae13beacb153e8c', 'Jerome', '06.12.80.45.99', 2, NULL, NULL, NULL, NULL),
(6, 0, 'Mr', '2021-10-25', 'mderkaoui@dawan.fr', 'Derkaoui', '7ce8bbf2425a23ccc8d61737f9f81551b7fd882078ce76a18dd08adbd9b51514eccf9b395b12f7b6792aa9003bbc94866ff5ec111f700365f61f8eb53d119dc4', 'Mohamed', '06.12.80.45.99', 2, NULL, NULL, NULL, NULL),
(11, 0, 'M', '1997-02-12', 'ksydaphasavanh@dawan.fr', 'Sydaphasavanh', 'weeDs2bC3J', 'Kanha', '0000000000', NULL, NULL, 2, NULL, NULL),
(12, 0, 'Mme', '2013-10-06', 'lloosemoreBIS0@hexun.com', 'Loosemore', '228de1505133b121317b2b9fda84a31946dc858c22804269f3404ad57a806b97c5650683d2fa8cd69cbe57f6a39114631522c2ce868910dd3b9b63c4eef6d83b', 'Laurens', '2173821232', NULL, NULL, 3, NULL, NULL),
(13, 0, 'Mr', '2020-07-07', 'feresbengamra@gmail.com', 'Ben Gamra', '123456', 'Feres', '0656252569', 18, NULL, 4, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur_role`
--

DROP TABLE IF EXISTS `utilisateur_role`;
CREATE TABLE IF NOT EXISTS `utilisateur_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `intitule` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateur_role`
--

INSERT INTO `utilisateur_role` (`id`, `version`, `intitule`) VALUES
(1, 0, 'ETUDIANT'),
(2, 0, 'FORMATEUR'),
(3, 0, 'ADMIN'),
(4, 0, 'CEF'),
(5, 0, 'MAITREAPPRENTISSAGE');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur_roles`
--

DROP TABLE IF EXISTS `utilisateur_roles`;
CREATE TABLE IF NOT EXISTS `utilisateur_roles` (
  `utilisateurs_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  KEY `FKd1ijmfvnnou0dhk5uthjv4cji` (`roles_id`),
  KEY `FKirehnt4bumoo8qvpb6rvtom54` (`utilisateurs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateur_roles`
--

INSERT INTO `utilisateur_roles` (`utilisateurs_id`, `roles_id`) VALUES
(1, 3),
(5, 3),
(6, 3),
(4, 2),
(3, 4),
(2, 1),
(11, 1),
(12, 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `activite_type`
--
ALTER TABLE `activite_type`
  ADD CONSTRAINT `FKl4wf05xa1d8rx18n0d932i301` FOREIGN KEY (`cursus_activite_type_id`) REFERENCES `cursus` (`id`);

--
-- Contraintes pour la table `cef`
--
ALTER TABLE `cef`
  ADD CONSTRAINT `FK8i4pmgylhbb1d3a2y9rua18vh` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FKa2elsvxg5yd58b1y42gb0xl4p` FOREIGN KEY (`centre_formation_id`) REFERENCES `centre_formation` (`id`),
  ADD CONSTRAINT `FKs9nldcuuxbm1bmsny5156ic3f` FOREIGN KEY (`entreprise_id`) REFERENCES `entreprise` (`id`);

--
-- Contraintes pour la table `centre_formation`
--
ALTER TABLE `centre_formation`
  ADD CONSTRAINT `FKmtswmoxbs5fep0poo8t6860e8` FOREIGN KEY (`entreprise_id`) REFERENCES `entreprise` (`id`),
  ADD CONSTRAINT `FKoph0u2310r6vavjnewil8jjww` FOREIGN KEY (`adresse_id`) REFERENCES `adresse` (`id`);

--
-- Contraintes pour la table `cerfa`
--
ALTER TABLE `cerfa`
  ADD CONSTRAINT `FK3t58jqpuv3ga8ua2ojrbxaost` FOREIGN KEY (`remuneration3_id`) REFERENCES `remuneration` (`id`),
  ADD CONSTRAINT `FK59lvi67stxclu1qi7xcnyn2ck` FOREIGN KEY (`adresse_apprenti_id`) REFERENCES `adresse` (`id`),
  ADD CONSTRAINT `FK5uclvt6uc9e2qhro3p01ixive` FOREIGN KEY (`adresse_employeur_id`) REFERENCES `adresse` (`id`),
  ADD CONSTRAINT `FK8rbxne83rxwm1iekev9q2umu0` FOREIGN KEY (`remuneration4_id`) REFERENCES `remuneration` (`id`),
  ADD CONSTRAINT `FKfrq9kq7hd4an1ygahipo9nq8u` FOREIGN KEY (`adresse_representant_id`) REFERENCES `adresse` (`id`),
  ADD CONSTRAINT `FKlkgv2obeikkra8w1hbvibjua2` FOREIGN KEY (`remuneration1_id`) REFERENCES `remuneration` (`id`),
  ADD CONSTRAINT `FKqdd8g2fpm2ipyk8q41ucifp5i` FOREIGN KEY (`remuneration2_id`) REFERENCES `remuneration` (`id`),
  ADD CONSTRAINT `FKs69obwmyeupfu4nko8n98xjmg` FOREIGN KEY (`etudiant_id`) REFERENCES `etudiant` (`id`),
  ADD CONSTRAINT `FKt97kxbiftvvgjyn5ud5e5br4n` FOREIGN KEY (`adresse_responsable_id`) REFERENCES `adresse` (`id`);

--
-- Contraintes pour la table `competence_professionnelle`
--
ALTER TABLE `competence_professionnelle`
  ADD CONSTRAINT `FKcq22jysorvpnk8hn2n2qroqjs` FOREIGN KEY (`activite_type_id`) REFERENCES `activite_type` (`id`);

--
-- Contraintes pour la table `conge`
--
ALTER TABLE `conge`
  ADD CONSTRAINT `FKh21c9wcn1wbund2qg3kpdvg6x` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `contrat`
--
ALTER TABLE `contrat`
  ADD CONSTRAINT `FK1m4npdy8chhq7yd8ih3ry57o0` FOREIGN KEY (`etudiant_id`) REFERENCES `etudiant` (`id`),
  ADD CONSTRAINT `FKf2svp5m02cjyci1px4nymtdy4` FOREIGN KEY (`maitre_apprentissage_id`) REFERENCES `maitre_apprentissage` (`id`);

--
-- Contraintes pour la table `cursus_formations`
--
ALTER TABLE `cursus_formations`
  ADD CONSTRAINT `FKd8om68mg9ldqs1557m2f7mili` FOREIGN KEY (`formations_id`) REFERENCES `formation` (`id`),
  ADD CONSTRAINT `FKdkq5y9v43gq7mk27s11p9m10b` FOREIGN KEY (`cursus_lst_id`) REFERENCES `cursus` (`id`);

--
-- Contraintes pour la table `devoir`
--
ALTER TABLE `devoir`
  ADD CONSTRAINT `FKte741higntmil69njc6eek4me` FOREIGN KEY (`intervention_id`) REFERENCES `intervention` (`id`);

--
-- Contraintes pour la table `devoir_etudiant`
--
ALTER TABLE `devoir_etudiant`
  ADD CONSTRAINT `FK3vnboq5nyted2pb35fgk2simb` FOREIGN KEY (`etudiant_id`) REFERENCES `etudiant` (`id`),
  ADD CONSTRAINT `FK5cmdlicpd87owi6dipnxnibs0` FOREIGN KEY (`devoir_id`) REFERENCES `devoir` (`id`);

--
-- Contraintes pour la table `dossier_professionnel`
--
ALTER TABLE `dossier_professionnel`
  ADD CONSTRAINT `FK8sg5c9oe1k2q5x8ur4d6nwjns` FOREIGN KEY (`cursus_id`) REFERENCES `cursus` (`id`);

--
-- Contraintes pour la table `dossier_projet`
--
ALTER TABLE `dossier_projet`
  ADD CONSTRAINT `FKemg71j4bbuekflck1lgkea5aw` FOREIGN KEY (`projet_id`) REFERENCES `projet` (`id`);

--
-- Contraintes pour la table `entreprise`
--
ALTER TABLE `entreprise`
  ADD CONSTRAINT `FKp4nf2k1tciaujn3i64o28jvuv` FOREIGN KEY (`adresse_siege_id`) REFERENCES `adresse` (`id`);

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `FKk1k5fhsjak7v0uk031i5geqj9` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `etudiant_dossier_professionnel`
--
ALTER TABLE `etudiant_dossier_professionnel`
  ADD CONSTRAINT `FK3t9omrw2ldyfffdgthny78dn2` FOREIGN KEY (`dossier_professionnel_id`) REFERENCES `dossier_professionnel` (`id`),
  ADD CONSTRAINT `FKkth85jvff1q6w21b2b188cxwj` FOREIGN KEY (`etudiant_id`) REFERENCES `etudiant` (`id`);

--
-- Contraintes pour la table `etudiant_dossier_projet`
--
ALTER TABLE `etudiant_dossier_projet`
  ADD CONSTRAINT `FK8a09qhuh5ouhcvqmgi8650mus` FOREIGN KEY (`etudiant_id`) REFERENCES `etudiant` (`id`),
  ADD CONSTRAINT `FKl9eyfyiia04wuguawk8ixi71d` FOREIGN KEY (`dossier_projet_id`) REFERENCES `dossier_projet` (`id`);

--
-- Contraintes pour la table `examen`
--
ALTER TABLE `examen`
  ADD CONSTRAINT `FKcu3jbla2430ilxs1vtroa8wnx` FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`id`);

--
-- Contraintes pour la table `examen_activite_types`
--
ALTER TABLE `examen_activite_types`
  ADD CONSTRAINT `FKc9of6442gb0yft5ip2dmcmmtd` FOREIGN KEY (`activite_types_id`) REFERENCES `activite_type` (`id`),
  ADD CONSTRAINT `FKcbwo3566j35fe752px7d2c274` FOREIGN KEY (`examens_id`) REFERENCES `examen` (`id`);

--
-- Contraintes pour la table `examen_competences_professionnelles`
--
ALTER TABLE `examen_competences_professionnelles`
  ADD CONSTRAINT `FKo674b4ab3qchvdm8y4nubuxhb` FOREIGN KEY (`competences_professionnelles_id`) REFERENCES `competence_professionnelle` (`id`),
  ADD CONSTRAINT `FKyg8brlry2ju91v119bws5co8` FOREIGN KEY (`examens_id`) REFERENCES `examen` (`id`);

--
-- Contraintes pour la table `fiche_entreprise`
--
ALTER TABLE `fiche_entreprise`
  ADD CONSTRAINT `FKkf8agof5yl9vu92kq1ucij1no` FOREIGN KEY (`etudiant_id`) REFERENCES `etudiant` (`id`),
  ADD CONSTRAINT `FKoghjxedm7lt9jj9wa87gx53uc` FOREIGN KEY (`entreprise_id`) REFERENCES `entreprise` (`id`);

--
-- Contraintes pour la table `fiche_poste`
--
ALTER TABLE `fiche_poste`
  ADD CONSTRAINT `FKnwc2e36dq47dqonb078jn3bhw` FOREIGN KEY (`etudiant_id`) REFERENCES `etudiant` (`id`);

--
-- Contraintes pour la table `formateur`
--
ALTER TABLE `formateur`
  ADD CONSTRAINT `FKp51cpnxbsdwp19se9c0l84bro` FOREIGN KEY (`entreprise_id`) REFERENCES `entreprise` (`id`),
  ADD CONSTRAINT `FKrvq3k2ovvyaqts9n49b8ytnsm` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `groupe_etudiant_etudiants`
--
ALTER TABLE `groupe_etudiant_etudiants`
  ADD CONSTRAINT `FK42l5yutnq8bg4tvfdxntlx0s2` FOREIGN KEY (`groupes_id`) REFERENCES `groupe_etudiant` (`id`),
  ADD CONSTRAINT `FKpg1ue094ie4xh4x3n05lgv2e` FOREIGN KEY (`etudiants_id`) REFERENCES `etudiant` (`id`);

--
-- Contraintes pour la table `intervention`
--
ALTER TABLE `intervention`
  ADD CONSTRAINT `FK6hamywu6944nnls61cgs6afn6` FOREIGN KEY (`intervention_mere_id`) REFERENCES `intervention` (`id`),
  ADD CONSTRAINT `FK77ggtrufswhjnpr6ik2ayd66c` FOREIGN KEY (`formation_id`) REFERENCES `formation` (`id`);

--
-- Contraintes pour la table `intervention_formateurs`
--
ALTER TABLE `intervention_formateurs`
  ADD CONSTRAINT `FK6ymhuumlv6u3n6xaug3f4184w` FOREIGN KEY (`interventions_id`) REFERENCES `intervention` (`id`),
  ADD CONSTRAINT `FK9bgk5dqfwt6elqkolh71gaqej` FOREIGN KEY (`formateurs_id`) REFERENCES `formateur` (`id`);

--
-- Contraintes pour la table `intervention_promotions`
--
ALTER TABLE `intervention_promotions`
  ADD CONSTRAINT `FKmcjufiqw7n0nrixtk7okb9hx` FOREIGN KEY (`promotions_id`) REFERENCES `promotion` (`id`),
  ADD CONSTRAINT `FKr0il7igkwlkd5aorm4ah8x4eb` FOREIGN KEY (`interventions_id`) REFERENCES `intervention` (`id`);

--
-- Contraintes pour la table `maitre_apprentissage`
--
ALTER TABLE `maitre_apprentissage`
  ADD CONSTRAINT `FKh1qktnnwl0fq095v7bxem6neb` FOREIGN KEY (`entreprise_id`) REFERENCES `entreprise` (`id`),
  ADD CONSTRAINT `FKt7iy3trbpqf8i3n8wme8qx1x4` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `FKi3dn4105svs3a0y62yshrmaua` FOREIGN KEY (`etudiant_note_id`) REFERENCES `etudiant` (`id`),
  ADD CONSTRAINT `FKqeexjhoqpiewuw1wo7orh8ktt` FOREIGN KEY (`examen_id`) REFERENCES `examen` (`id`);

--
-- Contraintes pour la table `passage_examen`
--
ALTER TABLE `passage_examen`
  ADD CONSTRAINT `FK9ihmsevcure66yyfpd4mko3vl` FOREIGN KEY (`examen_id`) REFERENCES `examen` (`id`),
  ADD CONSTRAINT `FKj8b6rqicnig8xwwl933d4y5vl` FOREIGN KEY (`intervention_id`) REFERENCES `intervention` (`id`);

--
-- Contraintes pour la table `positionnement`
--
ALTER TABLE `positionnement`
  ADD CONSTRAINT `FK4nt68quqv4sxfwn09ln5wt3iw` FOREIGN KEY (`etudiant_id`) REFERENCES `etudiant` (`id`),
  ADD CONSTRAINT `FKlw6tb0tq8304u8teq3d0x40ca` FOREIGN KEY (`intervention_id`) REFERENCES `intervention` (`id`);

--
-- Contraintes pour la table `projet`
--
ALTER TABLE `projet`
  ADD CONSTRAINT `FKqf4p3ktsa8gjmytpgwx2fd6nk` FOREIGN KEY (`groupe_id`) REFERENCES `groupe_etudiant` (`id`);

--
-- Contraintes pour la table `promotion`
--
ALTER TABLE `promotion`
  ADD CONSTRAINT `FK6fwqcy9xbmule71h1vgo6r557` FOREIGN KEY (`centre_formation_id`) REFERENCES `centre_formation` (`id`),
  ADD CONSTRAINT `FK9u5jtq6e3tf4ull9h212smqd9` FOREIGN KEY (`cef_id`) REFERENCES `cef` (`id`),
  ADD CONSTRAINT `FKij3ht1gimy6e0o66m5bhtmg7t` FOREIGN KEY (`cursus_id`) REFERENCES `cursus` (`id`),
  ADD CONSTRAINT `FKsq7140tt77odsbi8ve5pkr9ik` FOREIGN KEY (`referent_pedagogique_id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `promotion_etudiants`
--
ALTER TABLE `promotion_etudiants`
  ADD CONSTRAINT `FK7jutuwjxuyj0gw5riqfcmh8cu` FOREIGN KEY (`etudiants_id`) REFERENCES `etudiant` (`id`),
  ADD CONSTRAINT `FKmcaob1f6ti7ulog2k15aa3hm2` FOREIGN KEY (`promotions_id`) REFERENCES `promotion` (`id`);

--
-- Contraintes pour la table `signature`
--
ALTER TABLE `signature`
  ADD CONSTRAINT `FKo578vflrvlsxenwbgkw136eoj` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `support_cours_interventions`
--
ALTER TABLE `support_cours_interventions`
  ADD CONSTRAINT `FKdo8sr6wal54xab4gjgpusnpwr` FOREIGN KEY (`supports_cours_id`) REFERENCES `support_cours` (`id`),
  ADD CONSTRAINT `FKerev1cf6i4l63crg9wkg0sa0l` FOREIGN KEY (`interventions_id`) REFERENCES `intervention` (`id`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `FK402esxc4bg5refg7j7ca7b982` FOREIGN KEY (`maitre_apprentissage_id`) REFERENCES `maitre_apprentissage` (`id`),
  ADD CONSTRAINT `FKdvxe31y87uhakroedfn9vxgdy` FOREIGN KEY (`adresse_id`) REFERENCES `adresse` (`id`),
  ADD CONSTRAINT `FKjfbmjulv47mayggeqcsw576cq` FOREIGN KEY (`etudiant_id`) REFERENCES `etudiant` (`id`),
  ADD CONSTRAINT `FKl76ecx5l7w6wo0slu91vbive8` FOREIGN KEY (`cef_id`) REFERENCES `cef` (`id`),
  ADD CONSTRAINT `FKmnq3097ucopqv2hgxqwd2do18` FOREIGN KEY (`formateur_id`) REFERENCES `formateur` (`id`);

--
-- Contraintes pour la table `utilisateur_roles`
--
ALTER TABLE `utilisateur_roles`
  ADD CONSTRAINT `FKd1ijmfvnnou0dhk5uthjv4cji` FOREIGN KEY (`roles_id`) REFERENCES `utilisateur_role` (`id`),
  ADD CONSTRAINT `FKirehnt4bumoo8qvpb6rvtom54` FOREIGN KEY (`utilisateurs_id`) REFERENCES `utilisateur` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
