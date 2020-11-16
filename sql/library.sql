-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 16 Lis 2020, 21:31
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
('akowalska@gmail.com', 'akowalska', 'akowalska', 'Agnieszka', 'Kowalska', 931654356),
('anowak@o2.pl', 'anowak', 'anowak', 'Adam', 'Nowak', 987653043),
('bkowalska@gmail.com', 'bkowalska', 'bkowalska', 'Brygida', 'Kowalska', 771654377),
('bnowak@onet.pl', 'bnowak', 'bnowak', 'Barbara', 'Nowak', 870917874),
('cnowak@o2.pl', 'cnowak', 'cnowak', 'Celina', 'Nowak', 977653043),
('dnowak@onet.pl', 'dnowak', 'dnowak', 'Danuta', 'Nowak', 850917834),
('enowak@o2.pl', 'enowak', 'enowak', 'Ewa', 'Nowak', 12345551),
('fnowak@o2.pl', 'fnowak', 'fnowak', 'Franciszek', 'Nowak', 12344414444);

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

--
-- Zrzut danych tabeli `book`
--

INSERT INTO `book` (`isbn`, `title`, `author`, `category`, `publisher`, `number_of_pages`) VALUES
(1000000, 'Lalka', 'Bolesław Prus', 'Powieść', 'Zielona Sowa', 650),
(1000001, 'Antek', 'Bolesław Prus', 'Nowela', 'Zielona Sowa', 30),
(1000002, 'Katarynka', 'Bolesław Prus', 'Nowela', 'Greg', 25),
(1000003, 'Krzyżacy', 'Henryk Sienkiewicz', 'Powieść', 'Greg', 490),
(1000004, 'Noce i dnie', 'Maria Dąbrowska', 'Powieść', 'Greg', 490),
(1000005, 'Quo vadis', 'Henryk Sienkiewicz', 'Powieść', 'Greg', 410),
(1000123, 'qqwe', 'qweqweq', 'qweqe', 'asdadasd', 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `book_item`
--

CREATE TABLE `book_item` (
  `signature` bigint(20) UNSIGNED NOT NULL,
  `book_id` bigint(20) UNSIGNED NOT NULL,
  `book_lending_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `book_item`
--

INSERT INTO `book_item` (`signature`, `book_id`, `book_lending_id`) VALUES
(1000, 1000001, 1),
(1001, 1000001, NULL),
(1002, 1000002, NULL),
(1003, 1000002, NULL),
(1004, 1000002, NULL),
(1005, 1000002, NULL),
(1006, 1000003, NULL),
(1007, 1000003, NULL),
(1008, 1000004, NULL),
(1009, 1000004, NULL),
(1010, 1000005, NULL),
(1011, 1000005, NULL),
(12000000, 1000001, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `book_lending`
--

CREATE TABLE `book_lending` (
  `book_lending_id` bigint(20) NOT NULL,
  `book_item_id` bigint(20) UNSIGNED NOT NULL,
  `client_id` bigint(20) NOT NULL,
  `lended_from` date NOT NULL,
  `lended_to` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `book_lending`
--

INSERT INTO `book_lending` (`book_lending_id`, `book_item_id`, `client_id`, `lended_from`, `lended_to`) VALUES
(1, 1000, 1, '2020-11-01', '2020-11-30');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `book_reservation`
--

CREATE TABLE `book_reservation` (
  `book_reservation_id` bigint(20) NOT NULL,
  `book_item_id` bigint(20) UNSIGNED NOT NULL,
  `client_id` bigint(20) NOT NULL,
  `date_from` date NOT NULL,
  `date_to` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `book_reservation`
--

INSERT INTO `book_reservation` (`book_reservation_id`, `book_item_id`, `client_id`, `date_from`, `date_to`) VALUES
(1, 1000, 3, '2020-11-02', '2020-11-18'),
(18, 1010, 4, '2021-02-12', '2021-03-12'),
(20, 1000, 4, '2021-02-01', '2021-03-01');

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
(1, 'anowak@o2.pl'),
(2, 'bnowak@onet.pl'),
(3, 'cnowak@o2.pl'),
(4, 'dnowak@onet.pl'),
(5, 'enowak@o2.pl'),
(6, 'fnowak@o2.pl');

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
(1, 'akowalska@gmail.com'),
(2, 'bkowalska@gmail.com');

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
  ADD KEY `book_item_id` (`book_item_id`),
  ADD KEY `client_id` (`client_id`);

--
-- Indeksy dla tabeli `book_reservation`
--
ALTER TABLE `book_reservation`
  ADD PRIMARY KEY (`book_reservation_id`),
  ADD KEY `book_item_id` (`book_item_id`),
  ADD KEY `client_id` (`client_id`);

--
-- Indeksy dla tabeli `client`
--
ALTER TABLE `client`
  ADD UNIQUE KEY `library_card` (`library_card`),
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
  MODIFY `book_lending_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `book_reservation`
--
ALTER TABLE `book_reservation`
  MODIFY `book_reservation_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT dla tabeli `client`
--
ALTER TABLE `client`
  MODIFY `library_card` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `book_item`
--
ALTER TABLE `book_item`
  ADD CONSTRAINT `book_item_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`isbn`),
  ADD CONSTRAINT `book_item_ibfk_2` FOREIGN KEY (`book_lending_id`) REFERENCES `book_lending` (`book_lending_id`);

--
-- Ograniczenia dla tabeli `book_lending`
--
ALTER TABLE `book_lending`
  ADD CONSTRAINT `book_lending_ibfk_2` FOREIGN KEY (`book_item_id`) REFERENCES `book_item` (`signature`),
  ADD CONSTRAINT `book_lending_ibfk_3` FOREIGN KEY (`client_id`) REFERENCES `client` (`library_card`);

--
-- Ograniczenia dla tabeli `book_reservation`
--
ALTER TABLE `book_reservation`
  ADD CONSTRAINT `book_reservation_ibfk_3` FOREIGN KEY (`book_item_id`) REFERENCES `book_item` (`signature`),
  ADD CONSTRAINT `book_reservation_ibfk_4` FOREIGN KEY (`client_id`) REFERENCES `client` (`library_card`);

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
