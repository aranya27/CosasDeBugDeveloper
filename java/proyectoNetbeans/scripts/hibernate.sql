-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 24-12-2012 a las 14:19:13
-- Versión del servidor: 5.1.44
-- Versión de PHP: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `hibernate`
--
create database hibernate;
use hibernate;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `EVENTS`
--

CREATE TABLE IF NOT EXISTS `EVENTS` (
  `EVENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EVENT_DATE` date NOT NULL,
  `title` text NOT NULL,
  PRIMARY KEY (`EVENT_ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;


