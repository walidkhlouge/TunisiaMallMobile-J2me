-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Lun 16 Mai 2016 à 00:07
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `tunisiamallhwi`
--

-- --------------------------------------------------------

--
-- Structure de la table `abonnement`
--

CREATE TABLE IF NOT EXISTS `abonnement` (
  `login` varchar(40) NOT NULL,
  `nomEnseigne` varchar(40) NOT NULL,
  PRIMARY KEY (`login`,`nomEnseigne`),
  KEY `nomEnseigneAbonnement` (`nomEnseigne`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `boutique`
--

CREATE TABLE IF NOT EXISTS `boutique` (
  `idBoutique` int(11) NOT NULL AUTO_INCREMENT,
  `intitule` varchar(40) NOT NULL,
  `nomEnseigne` varchar(40) NOT NULL,
  `loginResponsableBoutique` varchar(40) DEFAULT NULL,
  `url` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`idBoutique`),
  KEY `idEnseigne` (`nomEnseigne`,`loginResponsableBoutique`),
  KEY `loginEnseigne` (`nomEnseigne`),
  KEY `loginResponsableBoutique` (`loginResponsableBoutique`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

-- --------------------------------------------------------

--
-- Structure de la table `carte_fidelite`
--

CREATE TABLE IF NOT EXISTS `carte_fidelite` (
  `idCarteFidelite` int(11) NOT NULL AUTO_INCREMENT,
  `nomEnseigne` varchar(40) NOT NULL,
  `loginClient` varchar(40) NOT NULL,
  `pointFidele` int(11) NOT NULL,
  `reductionFixe` float DEFAULT NULL,
  `pointFideleFixe` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCarteFidelite`),
  KEY `idEnseigne` (`nomEnseigne`,`loginClient`),
  KEY `loginClient` (`loginClient`),
  KEY `nomEnseigne` (`nomEnseigne`),
  KEY `loginClient_2` (`loginClient`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Structure de la table `catalogue`
--

CREATE TABLE IF NOT EXISTS `catalogue` (
  `idCatalogue` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(40) NOT NULL,
  `intitule` varchar(50) NOT NULL,
  `enseigne` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idCatalogue`),
  KEY `fk_catEnseigne` (`enseigne`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE IF NOT EXISTS `commande` (
  `idCommande` int(11) NOT NULL AUTO_INCREMENT,
  `loginClient` varchar(40) NOT NULL,
  `idProduit` int(11) NOT NULL,
  `adresseLivraison` varchar(40) NOT NULL,
  `quantite` int(11) NOT NULL,
  `taille` varchar(52) NOT NULL,
  `timeLivraison` datetime NOT NULL,
  PRIMARY KEY (`idCommande`),
  KEY `idClient` (`loginClient`,`idProduit`),
  KEY `loginClient` (`loginClient`),
  KEY `loginClient_2` (`loginClient`),
  KEY `idProduit` (`idProduit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `commentaireens`
--

CREATE TABLE IF NOT EXISTS `commentaireens` (
  `idCommentaire` int(11) NOT NULL AUTO_INCREMENT,
  `client` varchar(20) NOT NULL,
  `enseigne` varchar(20) NOT NULL,
  `Commentaire` text NOT NULL,
  PRIMARY KEY (`idCommentaire`),
  KEY `idEnseigne` (`enseigne`),
  KEY `idClient` (`client`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `commentairemall`
--

CREATE TABLE IF NOT EXISTS `commentairemall` (
  `idCommentaireM` int(11) NOT NULL AUTO_INCREMENT,
  `client` varchar(20) NOT NULL,
  `Commentaire` text NOT NULL,
  PRIMARY KEY (`idCommentaireM`),
  KEY `client` (`client`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

-- --------------------------------------------------------

--
-- Structure de la table `commentaireproduit`
--

CREATE TABLE IF NOT EXISTS `commentaireproduit` (
  `idCommentaireProd` int(11) NOT NULL AUTO_INCREMENT,
  `client` varchar(20) NOT NULL,
  `idProduit` int(20) NOT NULL,
  `commentaire` text NOT NULL,
  PRIMARY KEY (`idCommentaireProd`),
  KEY `produit` (`idProduit`),
  KEY `client` (`client`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE IF NOT EXISTS `compte` (
  `login` varchar(40) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `nom` varchar(40) NOT NULL,
  `prenom` varchar(40) NOT NULL,
  `adresseMail` varchar(40) NOT NULL,
  `type` varchar(20) NOT NULL,
  `sexe` varchar(20) NOT NULL,
  `DateNaissance` date NOT NULL,
  `etat` varchar(20) NOT NULL,
  `photo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `compte`
--

INSERT INTO `compte` (`login`, `pwd`, `nom`, `prenom`, `adresseMail`, `type`, `sexe`, `DateNaissance`, `etat`, `photo`) VALUES
('azab', '$2a$10$1NkxuPeYEQrJQgu21ejEN.fRrKYlHYTGKUGI1xtp3BpRjgforT9/m', 'Kaddour', 'Nada', 'azab@za3ka.yedek', 'Client', 'Femme', '2016-05-07', 'Not Activated', NULL),
('aze', '$2a$10$k7vcqwSNAh1bCAE2lBz34uk1PLYpGB29BorhXbjuehmKt.VMpETXG', 'dqsdqsdqdz', 'zaezaez', '', 'ResponsableEnseigne', 'Homme', '0000-00-00', 'Not Activated', NULL),
('azee', '$2a$10$i48Y.q5KtL06M8S2pKPdn.6xM4H//AxztqnKb6LlnCIXy5iVDlnge', 'azdqsd', 'qsdqs', 'dsq@de.re', 'Client', 'Homme', '2016-05-03', 'Not Activated', NULL),
('azeeeeeeeee', '$2a$10$VVWo.suYM3T1N4hIYVW6pO3yo/QJLkfLtBIxcZ3HKqN9HYPQQlO3W', 'qsd', 'dddd', '', 'ResponsableEnseigne', 'Homme', '0000-00-00', 'Not Activated', NULL),
('azerty', '$2a$10$4Aj6kDdSaBBtGFKp8L8MIeT.rUyYuhzzZdi4iQTR421M/I0vKA/.O', 'azer', 'azer', '', 'ResponsableEnseigne', 'Homme', '0000-00-00', 'Not Activated', NULL),
('lolo', '$2a$10$KjWEw8vyY258BprjnlYYk.vIP79e6ajrTsSFKSpJwdGBEJteKs0sq', 'x<wx', 'sqddds', '', 'ResponsableEnseigne', 'Homme', '0000-00-00', 'Not Activated', NULL),
('popo', '$2a$10$lMz1PhhELHW6q8hOwIVhGeX7deHVJ1lISVwBHfdWP2XlM0KghXfyC', 'kkkk', 'lll', '', 'ResponsableEnseigne', 'Homme', '0000-00-00', 'Not Activated', NULL),
('qsddd', '$2a$10$mdvQZwGzZqx77f2SpSqN4umOXB6DsD.WqXSmqgYapLVzs9gw5UZ7m', 'adsqd', 'qsd', '', 'Client', 'Homme', '0000-00-00', 'Not Activated', NULL),
('qsdqqqq', '$2a$10$2On4fSxrMQswtKqARKVXjOvlwWZu9fLmHpnYvQ7pMd5Qpvd9TqZoW', 'qsd', 'qsd', '', 'ResponsableEnseigne', 'Homme', '0000-00-00', 'Not Activated', NULL),
('qsdqsd', '$2a$10$k.x0oLhxZDiwLt1AOriNGOcGKnZZyUvv22770at9UJmoV3QxdM6Ke', 'qds', 'fdfd', '', 'ResponsableEnseigne', 'Homme', '0000-00-00', 'Not Activated', NULL),
('qsdqsdqsd', '$2a$10$INzvRc91jUUnzo/xx/X8VuxIKtFVEiHcXuxrG0HMoINDjoJbj1ZBa', 'qsd', 'qsdqsd', '', 'Client', 'Homme', '0000-00-00', 'Not Activated', NULL),
('qsdzezezrer', '$2a$10$HZ0cKVu3Q.aLMcmpzmlB6.b1djEA3k0vvTZN7uRnsTAvgwPWyTTqW', 'qsd', 'qsd', '', 'Client', 'Homme', '0000-00-00', 'Not Activated', NULL),
('qsdzzzz', '$2a$10$meV1uVz93MrviJHhbGe5S.q.V5tw418GkYbJkqne9RSKYxW/5T9r6', 'aze', 'rert', '', 'ResponsableEnseigne', 'Homme', '0000-00-00', 'Not Activated', NULL),
('selim', '$2a$10$AQ.Tv8nG6Yqtax/yNuUxIejW8DkwXCawe.QiGxKxarYKrT.UKJ1UO', 'qsdqsd', 'qsdazedqs', '', 'Client', 'Homme', '2016-05-03', 'Not Activated', NULL),
('test', 'dsf', 'dsfds', 'tes@azd.fr', 'butterfly.jpg', 'ResponsableBoutique', 'Homme', '2016-05-12', 'Not Activated', NULL),
('zaboureux', '$2a$10$5Rpswe3psD3L4jmJr7jjaeIf/Uk7ahkpooDvcAdgZbFkmVvO.4eZm', 'Loued', 'Selim', 'zabourex@gmail.com', 'Client', 'Homme', '2016-05-05', 'Not Activated', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `emplacementpublicataire`
--

CREATE TABLE IF NOT EXISTS `emplacementpublicataire` (
  `idEmplacementPublicitaire` int(11) NOT NULL,
  `idPackPublicataire` int(11) NOT NULL,
  PRIMARY KEY (`idEmplacementPublicitaire`),
  KEY `idPackPublicataire` (`idPackPublicataire`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `enseigne`
--

CREATE TABLE IF NOT EXISTS `enseigne` (
  `nomEnseigne` varchar(40) NOT NULL,
  `loginResponsableEnseigne` varchar(40) DEFAULT NULL,
  `adresse` varchar(40) NOT NULL,
  `url` varchar(99) NOT NULL,
  `info` text,
  `idPack` int(11) DEFAULT NULL,
  PRIMARY KEY (`nomEnseigne`),
  KEY `idResponsableEnseigne` (`loginResponsableEnseigne`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `notificationenseigne`
--

CREATE TABLE IF NOT EXISTS `notificationenseigne` (
  `idNotificationE` int(100) NOT NULL AUTO_INCREMENT,
  `lien` varchar(100) NOT NULL,
  `intitule` varchar(200) NOT NULL,
  PRIMARY KEY (`idNotificationE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `packpublicitaire`
--

CREATE TABLE IF NOT EXISTS `packpublicitaire` (
  `idPackPublicatire` int(11) NOT NULL,
  `idCatalogueGeneral` int(11) NOT NULL,
  `prix` float NOT NULL,
  `nomEnseigne` varchar(40) NOT NULL,
  PRIMARY KEY (`idPackPublicatire`),
  KEY `idCatalogueGeneral` (`idCatalogueGeneral`,`nomEnseigne`),
  KEY `nomEnseigne` (`nomEnseigne`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `packspub`
--

CREATE TABLE IF NOT EXISTS `packspub` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(120) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prix` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `packspub`
--

INSERT INTO `packspub` (`id`, `description`, `nom`, `prix`) VALUES
(1, '5 Emplacements', 'Premium', 100),
(2, '5 Emplacement\r\n+ 2 affiches', 'VIP', 200);

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

CREATE TABLE IF NOT EXISTS `panier` (
  `loginClient` varchar(20) NOT NULL,
  `idProduit` int(11) NOT NULL,
  `nombre` int(11) NOT NULL,
  `taille` varchar(11) NOT NULL,
  PRIMARY KEY (`loginClient`,`idProduit`,`taille`),
  KEY `produitpanier` (`idProduit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `prodcat`
--

CREATE TABLE IF NOT EXISTS `prodcat` (
  `idProduit` int(11) NOT NULL,
  `idCatalogue` int(11) NOT NULL,
  PRIMARY KEY (`idProduit`,`idCatalogue`),
  KEY `idcatalogueProdcat` (`idCatalogue`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE IF NOT EXISTS `produit` (
  `idProduit` int(11) NOT NULL AUTO_INCREMENT,
  `nomEnseigne` varchar(40) DEFAULT NULL,
  `idBoutique` int(11) DEFAULT NULL,
  `nom` varchar(40) DEFAULT NULL,
  `quantite` int(11) DEFAULT NULL,
  `prixVenteUnitaire` float DEFAULT NULL,
  `tauxReduction` float DEFAULT NULL,
  `reference` varchar(100) DEFAULT NULL,
  `info` text,
  `url` varchar(40) DEFAULT NULL,
  `ptsfidelite` int(11) DEFAULT NULL,
  PRIMARY KEY (`idProduit`),
  KEY `idEnseigne` (`nomEnseigne`,`idBoutique`),
  KEY `nomEnseigne` (`nomEnseigne`),
  KEY `idBoutique` (`idBoutique`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=88 ;

-- --------------------------------------------------------

--
-- Structure de la table `produittaille`
--

CREATE TABLE IF NOT EXISTS `produittaille` (
  `idProduit` int(11) NOT NULL,
  `taille` varchar(11) NOT NULL DEFAULT '0',
  `quantite` int(11) NOT NULL,
  PRIMARY KEY (`idProduit`,`taille`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `profileenseigne`
--

CREATE TABLE IF NOT EXISTS `profileenseigne` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` int(11) NOT NULL,
  `info` int(11) DEFAULT NULL,
  `nomEnseigne` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_9022486F4F65FF6D` (`nomEnseigne`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `reclamationens`
--

CREATE TABLE IF NOT EXISTS `reclamationens` (
  `idReclamation` int(11) NOT NULL AUTO_INCREMENT,
  `client` varchar(20) NOT NULL,
  `enseigne` varchar(20) NOT NULL,
  `reclamation` text NOT NULL,
  PRIMARY KEY (`idReclamation`),
  KEY `client` (`client`,`enseigne`),
  KEY `enseigne` (`enseigne`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `reclamationmall`
--

CREATE TABLE IF NOT EXISTS `reclamationmall` (
  `idReclamationMall` int(11) NOT NULL AUTO_INCREMENT,
  `client` varchar(20) NOT NULL,
  `reclamation` text NOT NULL,
  PRIMARY KEY (`idReclamationMall`),
  KEY `client` (`client`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `locked` tinyint(1) NOT NULL,
  `expired` tinyint(1) NOT NULL,
  `expires_at` datetime DEFAULT NULL,
  `confirmation_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `credentials_expired` tinyint(1) NOT NULL,
  `credentials_expire_at` datetime DEFAULT NULL,
  `facebook_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `facebook_access_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `google_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `google_access_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `twitter_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `twitter_access_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nom` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `instagram_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `instagram_access_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `github_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `github_access_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `windows_live_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `windows_live_access_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `linkedin_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `linkedin_access_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_8D93D64992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_8D93D649A0D96FBF` (`email_canonical`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=41 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `locked`, `expired`, `expires_at`, `confirmation_token`, `password_requested_at`, `roles`, `credentials_expired`, `credentials_expire_at`, `facebook_id`, `facebook_access_token`, `google_id`, `google_access_token`, `twitter_id`, `twitter_access_token`, `nom`, `prenom`, `instagram_id`, `instagram_access_token`, `github_id`, `github_access_token`, `windows_live_id`, `windows_live_access_token`, `linkedin_id`, `linkedin_access_token`) VALUES
(18, '1703083663301874', '1703083663301874', 'soumaas@outlook.fr', 'walid.khlouge@esprit.tn', 1, 'thcafor0ytc4c0sgw0w8048ggsg8gwc', '1703083663301874', '2016-05-09 22:01:38', 0, 0, NULL, NULL, NULL, 'a:0:{}', 0, NULL, NULL, 'EAADh5AEx5BQBAAq5J7P0tsaEfSqrP3G16EA40pSUQuOivPpQ73Qi5zWkiID5OFVUhCRuFG5zW8Dt1SZAsZBCkN7bJ7jZBUYCAiT9Y5UCvzk4ZArcAq9YVjjwZAWEmHMsPf9NyEtuQgh9DvA8mUY5bOLncH1Hpm1IPEyIjIbsZC2gZDZD', NULL, NULL, NULL, NULL, 'Amous', 'Bassem', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(22, '10208999497250486', '10208999497250486', 'souma.as@hotmail.fr', 'souma.as@hotmail.fr', 1, '9wz27dbanfkkc0w408g0gcwws04w4kg', '10208999497250486', '2016-05-10 13:08:37', 0, 0, NULL, NULL, NULL, 'a:0:{}', 0, NULL, NULL, 'EAADh5AEx5BQBAI2K5RfUh2y70ADf4peWhdiaBtiTVZAJdW8RZAtqYTxRX9pvdKQ0lWgAwir6Rhpf6U0wFKeJPJKZBgv4FkiXVqqznsjdfYW5JObxjOuzqzgBctTDoil3G1yPJsw01sK9e1ipdFd8cWyZAudLq8YZD', '108693018276952230773', 'ya29.CjPeAuuXhzGFaKZ3QB49AfxqbAqGxM2rDhy2VPIQqca7-OB5zAfJeaglemStUyiLmBkBvVg', NULL, NULL, 'Amous', 'Bassem', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(24, '108693018276952230773', '108693018276952230773', 'bassem.amous@esprit.tn', 'bassem.amous@esprit.tn', 1, 'ocfwtle9les0o8co408koc484osgw0k', '108693018276952230773', '2016-05-10 13:20:04', 0, 0, NULL, NULL, NULL, 'a:0:{}', 0, NULL, NULL, NULL, NULL, 'ya29.CjHeAmSovuqtKzT2M5mxvuLKOCTfJNLXOr-JccIJsyn2sjYXUXJzJZK1CmWiSPNl8wkB', NULL, NULL, 'Bassem', 'AMOUS', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(26, 'azerty', 'azerty', '', '', 1, '.rUyYuhzzZdi4iQTR421M/I0vKA/.O', '$2a$10$4Aj6kDdSaBBtGFKp8L8MIeT.rUyYuhzzZdi4iQTR421M/I0vKA/.O', NULL, 0, 0, NULL, NULL, NULL, 'a:0:{}', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'azer', 'azer', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(37, 'mrt', 'mrt', 'mrt@gmail.com', 'mrt@gmail.com', 1, '$2a$10$5Rpswe3psD3L4jmJr7jjae', '$2a$10$5Rpswe3psD3L4jmJr7jjaeIf/Uk7ahkpooDvcAdgZbFkmVvO.4eZm', NULL, 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:11:"ROLE_CLIENT";}', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Loued', 'Selim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(38, 'azab', 'azab', 'xa@za3ka.zae', 'wxw@mrrt.www', 1, '$2a$10$1NkxuPeYEQrJQgu21ejEN.', '$2a$10$1NkxuPeYEQrJQgu21ejEN.fRrKYlHYTGKUGI1xtp3BpRjgforT9/m', NULL, 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:11:"ROLE_CLIENT";}', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Kaddour', 'Nada', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(39, 'azee', 'azee', 'dsq@de.re', 'dsq@de.re', 1, '$2a$10$i48Y.q5KtL06M8S2pKPdn.', '$2a$10$i48Y.q5KtL06M8S2pKPdn.6xM4H//AxztqnKb6LlnCIXy5iVDlnge', NULL, 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:11:"ROLE_CLIENT";}', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'azdqsd', 'qsdqs', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(40, 'test', 'test', 'tes@azd.fr', 'tes@azd.fr', 1, '$2a$10$SU8HC2a24vXiH9sJiuCDve', '$2a$10$SU8HC2a24vXiH9sJiuCDveL5GQc7duCCPmw0wd8hluWhKask3Xy4m', NULL, 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:11:"ROLE_CLIENT";}', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'dsf', 'dsfds', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `abonnement`
--
ALTER TABLE `abonnement`
  ADD CONSTRAINT `nomEnseigneAbonnement` FOREIGN KEY (`nomEnseigne`) REFERENCES `enseigne` (`nomEnseigne`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `boutique`
--
ALTER TABLE `boutique`
  ADD CONSTRAINT `EnseigneBoutique` FOREIGN KEY (`nomEnseigne`) REFERENCES `enseigne` (`nomEnseigne`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `RespBoutiqueBoutique` FOREIGN KEY (`loginResponsableBoutique`) REFERENCES `compte` (`login`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `carte_fidelite`
--
ALTER TABLE `carte_fidelite`
  ADD CONSTRAINT `loginClient` FOREIGN KEY (`loginClient`) REFERENCES `compte` (`login`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `nomEnseigneCarteFidelite` FOREIGN KEY (`nomEnseigne`) REFERENCES `enseigne` (`nomEnseigne`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `catalogue`
--
ALTER TABLE `catalogue`
  ADD CONSTRAINT `fk_catEnseigne` FOREIGN KEY (`enseigne`) REFERENCES `enseigne` (`nomEnseigne`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `idProduitCommande` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`idProduit`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `loginClientCommande` FOREIGN KEY (`loginClient`) REFERENCES `compte` (`login`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `commentaireens`
--
ALTER TABLE `commentaireens`
  ADD CONSTRAINT `clientCommentaireEns` FOREIGN KEY (`client`) REFERENCES `compte` (`login`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `EnsCommentaireEns` FOREIGN KEY (`enseigne`) REFERENCES `enseigne` (`nomEnseigne`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `commentairemall`
--
ALTER TABLE `commentairemall`
  ADD CONSTRAINT `clientCommentairMall` FOREIGN KEY (`client`) REFERENCES `compte` (`login`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `commentaireproduit`
--
ALTER TABLE `commentaireproduit`
  ADD CONSTRAINT `clientCommentaireProd` FOREIGN KEY (`client`) REFERENCES `compte` (`login`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `produitCommentaitprod` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`idProduit`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `emplacementpublicataire`
--
ALTER TABLE `emplacementpublicataire`
  ADD CONSTRAINT `idpackpublicataireEmplacementPub` FOREIGN KEY (`idPackPublicataire`) REFERENCES `packpublicitaire` (`idPackPublicatire`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `enseigne`
--
ALTER TABLE `enseigne`
  ADD CONSTRAINT `loginResponsableEnseigneEnseigne` FOREIGN KEY (`loginResponsableEnseigne`) REFERENCES `compte` (`login`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `packpublicitaire`
--
ALTER TABLE `packpublicitaire`
  ADD CONSTRAINT `nomEnseignepackPub` FOREIGN KEY (`nomEnseigne`) REFERENCES `enseigne` (`nomEnseigne`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `packpublicitaire_ibfk_1` FOREIGN KEY (`idCatalogueGeneral`) REFERENCES `catalogue` (`idCatalogue`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `panier`
--
ALTER TABLE `panier`
  ADD CONSTRAINT `loginClientpanier` FOREIGN KEY (`loginClient`) REFERENCES `compte` (`login`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `produitpanier` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`idProduit`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `prodcat`
--
ALTER TABLE `prodcat`
  ADD CONSTRAINT `idcatalogueProdcat` FOREIGN KEY (`idCatalogue`) REFERENCES `catalogue` (`idCatalogue`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idProduitProdcat` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`idProduit`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `idBoutiqueProduit` FOREIGN KEY (`idBoutique`) REFERENCES `boutique` (`idBoutique`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `nomEnseigneProduit` FOREIGN KEY (`nomEnseigne`) REFERENCES `enseigne` (`nomEnseigne`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `produittaille`
--
ALTER TABLE `produittaille`
  ADD CONSTRAINT `idProduitTableTaille` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`idProduit`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reclamationens`
--
ALTER TABLE `reclamationens`
  ADD CONSTRAINT `clientReclmation` FOREIGN KEY (`client`) REFERENCES `compte` (`login`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `enseignereclamationEnseigne` FOREIGN KEY (`enseigne`) REFERENCES `enseigne` (`nomEnseigne`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reclamationmall`
--
ALTER TABLE `reclamationmall`
  ADD CONSTRAINT `clientReclamation` FOREIGN KEY (`client`) REFERENCES `compte` (`login`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
