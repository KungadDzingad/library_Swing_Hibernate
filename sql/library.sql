-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 10 Lis 2020, 13:57
-- Wersja serwera: 10.4.14-MariaDB
-- Wersja PHP: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `library`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `account`
--

CREATE TABLE `account` (
  `mail` varchar(50) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `pesel` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `account`
--

INSERT INTO `account` (`mail`, `login`, `password`, `name`, `last_name`, `pesel`) VALUES
('agap@o2.pl', 'agaPawlowska', 'aagusia99', 'Witold', 'Gombrowicz', 987653043),
('krzysztofkowalski@interia.pl', 'krzysiu1234', 'krzycha1234', 'Krzysztof', 'Kowalski', 123456789);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `book`
--

CREATE TABLE `book` (
  `isbn` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL,
  `publisher` varchar(50) NOT NULL,
  `number_of_pages` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `book_item`
--

CREATE TABLE `book_item` (
  `signature` bigint(20) UNSIGNED NOT NULL,
  `book_id` bigint(20) UNSIGNED NOT NULL,
  `book_lending_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `book_lending`
--

CREATE TABLE `book_lending` (
  `book_lending_id` int(11) NOT NULL,
  `book_item_id` bigint(20) UNSIGNED NOT NULL,
  `client_id` bigint(20) NOT NULL,
  `lended_from` date NOT NULL,
  `lended_to` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `book_reservation`
--

CREATE TABLE `book_reservation` (
  `book_item_id` bigint(20) UNSIGNED NOT NULL,
  `client_id` bigint(20) NOT NULL,
  `date_from` date NOT NULL,
  `date_to` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `client`
--

CREATE TABLE `client` (
  `library_card` bigint(20) NOT NULL,
  `account_id` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `client`
--

INSERT INTO `client` (`library_card`, `account_id`) VALUES
(1, 'agap@o2.pl');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `worker`
--

CREATE TABLE `worker` (
  `worker_id` bigint(20) NOT NULL,
  `account_id` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `worker`
--

INSERT INTO `worker` (`worker_id`, `account_id`) VALUES
(1, 'krzysztofkowalski@interia.pl');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`mail`);

--
-- Indeksy dla tabeli `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`isbn`);

--
-- Indeksy dla tabeli `book_item`
--
ALTER TABLE `book_item`
  ADD PRIMARY KEY (`signature`),
  ADD KEY `book_id` (`book_id`),
  ADD KEY `book_lending_id` (`book_lending_id`);

--
-- Indeksy dla tabeli `book_lending`
--
ALTER TABLE `book_lending`
  ADD PRIMARY KEY (`book_lending_id`),
  ADD KEY `client_id` (`client_id`),
  ADD KEY `book_item_id` (`book_item_id`);

--
-- Indeksy dla tabeli `book_reservation`
--
ALTER TABLE `book_reservation`
  ADD PRIMARY KEY (`book_item_id`,`client_id`),
  ADD KEY `client_id` (`client_id`);

--
-- Indeksy dla tabeli `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`library_card`),
  ADD KEY `account_id` (`account_id`);

--
-- Indeksy dla tabeli `worker`
--
ALTER TABLE `worker`
  ADD PRIMARY KEY (`worker_id`),
  ADD KEY `account_id` (`account_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `book_lending`
--
ALTER TABLE `book_lending`
  MODIFY `book_lending_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `book_item`
--
ALTER TABLE `book_item`
  ADD CONSTRAINT `book_item_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`isbn`),
  ADD CONSTRAINT `book_item_ibfk_2` FOREIGN KEY (`book_lending_id`) REFERENCES `book_lending` (`book_lending_id`) ON DELETE SET NULL;

--
-- Ograniczenia dla tabeli `book_lending`
--
ALTER TABLE `book_lending`
  ADD CONSTRAINT `book_lending_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `client` (`library_card`),
  ADD CONSTRAINT `book_lending_ibfk_2` FOREIGN KEY (`book_item_id`) REFERENCES `book_item` (`signature`);

--
-- Ograniczenia dla tabeli `book_reservation`
--
ALTER TABLE `book_reservation`
  ADD CONSTRAINT `book_reservation_ibfk_1` FOREIGN KEY (`book_item_id`) REFERENCES `book` (`isbn`),
  ADD CONSTRAINT `book_reservation_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `client` (`library_card`);

--
-- Ograniczenia dla tabeli `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`mail`);

--
-- Ograniczenia dla tabeli `worker`
--
ALTER TABLE `worker`
  ADD CONSTRAINT `worker_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`mail`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
