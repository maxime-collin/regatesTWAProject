-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mer 25 Mars 2015 à 11:18
-- Version du serveur: 5.5.41-0ubuntu0.14.04.1
-- Version de PHP: 5.5.9-1ubuntu4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `regates`
--

-- --------------------------------------------------------

--
-- Structure de la table `r_bateau`
--

CREATE TABLE IF NOT EXISTS `r_bateau` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero` varchar(255) CHARACTER SET utf8 NOT NULL,
  `nom` varchar(255) CHARACTER SET utf8 NOT NULL,
  `capitaine` varchar(255) CHARACTER SET utf8 NOT NULL,
  `type` varchar(255) CHARACTER SET utf8 NOT NULL,
  `nationalite` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero` (`numero`),
  KEY `capitaine` (`capitaine`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `r_bateau`
--

INSERT INTO `r_bateau`(`id`, `numero`, `nom`, `capitaine`, `type`, `nationalite`) VALUES
(0,'BR42','Globe-Flotteur','FR56','Fireball','French'),
(1,'FRA56','Mer-Sea','FR57','Laser','English'),
(2,'CO55','Optimisme','FR59','Optimist','French'),
(3,'EN23','Catitude','FR56','Laser','French');

-- --------------------------------------------------------

--
-- Structure de la table `r_course`
--

CREATE TABLE IF NOT EXISTS `r_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero` int(11) NOT NULL,
  `dateDebut` date NOT NULL,
  `heureDebut` time NOT NULL,
  `dateFin` date NOT NULL,
  `heureFin` time NOT NULL,
  `coefficient` double NOT NULL,
  `regate` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `regate` (`regate`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `r_course`
--

INSERT INTO `r_course` (`id`, `numero`, `dateDebut`, `heureDebut`, `dateFin`, `heureFin`, `coefficient`, `regate`) VALUES
(0, 1, '2015-01-31', '09:00:00', '2015-01-31', '12:0:00', 0.3, 0),
(1, 2, '2015-01-31', '14:00:00', '2015-01-31', '18:0:00', 0.3, 0),
(2, 3, '2015-02-01', '09:00:00', '2015-02-01', '18:0:00', 0.4, 0),
(3, 1, '2015-04-04', '10:00:00', '2015-04-06', '17:00:00', 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `r_equipage`
--

CREATE TABLE IF NOT EXISTS `r_equipage` (
  `bateau` int(11) NOT NULL,
  `equipier` varchar(255) NOT NULL,
  PRIMARY KEY (`bateau`,`equipier`),
  KEY `bateau` (`bateau`),
  KEY `equipier` (`equipier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `r_equipage`
--

INSERT INTO `r_equipage`(`bateau`, `equipier`) VALUES
(0,'FR56'),
(0,'FR57'),
(1,'FR57'),
(1,'FR58'),
(2,'FR59'),
(3,'FR56'),
(3,'FR57');

-- --------------------------------------------------------

--
-- Structure de la table `r_equipier`
--

CREATE TABLE IF NOT EXISTS `r_equipier` (
  `numeroLicence` varchar(255) CHARACTER SET utf8 NOT NULL,
  `identifiant` varchar(255) CHARACTER SET utf8 NOT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  `motDePasse` varchar(255) CHARACTER SET utf8 NOT NULL,
  `nom` varchar(255) CHARACTER SET utf8 NOT NULL,
  `prenom` varchar(255) CHARACTER SET utf8 NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `nationalite` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`numeroLicence`),
  UNIQUE KEY `identifiant` (`identifiant`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `r_equipier`
--

INSERT INTO `r_equipier` (`numeroLicence`, `identifiant`, `admin`, `motDePasse`, `nom`, `prenom`, `url`, `nationalite`) VALUES
('FR55', 'admin', 1, 'admin', 'admin', 'root', '', 'French'),
('FR56', 'Gwenaelle', 0, 'Gwenaelle', 'Joulie', 'Gwenaelle', 'http://asso.ffv.fr/matchracing/2001/coureurs/fiche_joulie.htm', 'French'),
('FR57', 'Cyrille', 1, 'Cyrille', 'Legloahec', 'Cyrille', 'http://asso.ffv.fr/matchracing/2001/coureurs/fiche_leglohaec.htm', 'English'),
('FR58', 'Claire', 0, 'Claire', 'Pruvot', 'Claire', 'http://www.clairepruvot.com/', 'French'),
('FR59', 'Benoît', 0, 'Benoît', 'Petit', 'Benoît', 'http://asso.ffv.fr/matchracing/2001/coureurs/fiche_petit.htm', 'French');

-- --------------------------------------------------------

--
-- Structure de la table `r_inscriptionCourse`
--

CREATE TABLE IF NOT EXISTS `r_inscriptionCourse` (
  `classement` int(11),
  `dateArrivee` date DEFAULT NULL,
  `heureArrivee` time NOT NULL,
  `course` int(11) NOT NULL,
  `bateau` int(11) NOT NULL,
  PRIMARY KEY (`course`,`bateau`),
  KEY `course` (`course`),
  KEY `bateau` (`bateau`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `r_inscriptionRegate`
--

CREATE TABLE IF NOT EXISTS `r_inscriptionRegate` (
  `classement` int(11) DEFAULT NULL,
  `regate` int(11) NOT NULL,
  `bateau` int(11) NOT NULL,
  PRIMARY KEY (`regate`,`bateau`),
  KEY `regate` (`regate`),
  KEY `bateau` (`bateau`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `r_inscriptionRegate`
--

INSERT INTO `r_inscriptionRegate`(`classement`, `regate`, `bateau`) VALUES 
(1,0,1),
(2,0,3),
(NULL,1,0),
(NULL,1,2);

-- --------------------------------------------------------

--
-- Structure de la table `r_regate`
--

CREATE TABLE IF NOT EXISTS `r_regate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `dateDebut` date NOT NULL,
  `heureDebut` time NOT NULL,
  `dateFin` date NOT NULL,
  `heureFin` time NOT NULL,
  `description` text NOT NULL,
  `niveau` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=21 ;

--
-- Contenu de la table `r_regate`
--

INSERT INTO `r_regate` (`id`, `nom`, `dateDebut`, `heureDebut`, `dateFin`, `heureFin`, `description`, `niveau`, `type`) VALUES
(0, 'Les pieds gelés', '2015-01-31', '09:00:00', '2015-02-01', '18:0:00', '', 'Departementale', 'Laser'),
(1, 'Esperance', '2015-04-04', '10:00:00', '2015-04-06', '17:00:00', '', 'Ligue', 'InterSeries');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `r_bateau`
--
ALTER TABLE `r_bateau`
  ADD CONSTRAINT `r_bateau_ibfk_1` FOREIGN KEY (`capitaine`) REFERENCES `r_equipier` (`numeroLicence`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `r_course`
--
ALTER TABLE `r_course`
  ADD CONSTRAINT `r_course_ibfk_1` FOREIGN KEY (`regate`) REFERENCES `r_regate` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `r_equipage`
--
ALTER TABLE `r_equipage`
  ADD CONSTRAINT `r_equipage_ibfk_1` FOREIGN KEY (`bateau`) REFERENCES `r_bateau` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `r_equipage_ibfk_2` FOREIGN KEY (`equipier`) REFERENCES `r_equipier` (`numeroLicence`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `r_inscriptionCourse`
--
ALTER TABLE `r_inscriptionCourse`
  ADD CONSTRAINT `r_inscriptionCourse_ibfk_1` FOREIGN KEY (`course`) REFERENCES `r_course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `r_inscriptionCourse_ibfk_2` FOREIGN KEY (`bateau`) REFERENCES `r_bateau` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `r_inscriptionRegate`
--
ALTER TABLE `r_inscriptionRegate`
  ADD CONSTRAINT `r_inscriptionRegate_ibfk_1` FOREIGN KEY (`regate`) REFERENCES `r_regate` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `r_inscriptionRegate_ibfk_2` FOREIGN KEY (`bateau`) REFERENCES `r_bateau` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;