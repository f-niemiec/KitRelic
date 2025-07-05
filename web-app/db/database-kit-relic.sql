DROP DATABASE IF EXISTS KitRelicDB;
CREATE DATABASE KitRelicDB;
USE KitRelicDB;

CREATE TABLE Utenti (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Password CHAR(128) NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE,
    Nome VARCHAR(100),
    Cognome VARCHAR(100),
    Tipo ENUM('Admin', 'Utente') NOT NULL,
    DataNascita DATE
);

CREATE TABLE Indirizzi (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Tipo ENUM('Billing', 'Shipping') NOT NULL,
    Attivo BOOLEAN,
    Citta VARCHAR(100),
    Via VARCHAR(255),
    Provincia VARCHAR(100),
    Paese VARCHAR(100),
    CodUtente INT,
    FOREIGN KEY (CodUtente) REFERENCES Utenti(ID)
);

/*CVV lasciato in chiaro per semplicità, ma di norma 
anche questo, come password, non viene inserito "direttamente" */
CREATE TABLE MetodoDiPagamento (
    IdCarta INT PRIMARY KEY AUTO_INCREMENT,
    Scadenza DATE,
    NumeroCarta VARCHAR(20),
    CVV VARCHAR(4),
    Proprietario VARCHAR(100),
    IDUtente INT,
    FOREIGN KEY (IDUtente) REFERENCES Utenti(ID)
);

CREATE TABLE Prodotti (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Nome VARCHAR(80),
    Prezzo DECIMAL(10,2),
    Quantita INT,
    Tipo VARCHAR(50),
    Taglia VARCHAR(10),
    Tendenza BOOLEAN,
    Nuovo BOOLEAN,
    Descrizione VARCHAR(300),
    Foto mediumblob DEFAULT NULL
);

CREATE TABLE Ordini (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    IdUtente INT,
    Costo DECIMAL(10,2),
    Data DATE,
    IdCarta INT,
    IDFatturazione INT,
    IDIndirizzo INT,
    FOREIGN KEY (IdUtente) REFERENCES Utenti(ID),
    FOREIGN KEY (IdCarta) REFERENCES MetodoDiPagamento(IdCarta),
    FOREIGN KEY (IDFatturazione) REFERENCES Indirizzi(ID),
    FOREIGN KEY (IDIndirizzo) REFERENCES Indirizzi(ID)
);


CREATE TABLE OggettoOrdine (
    ItemId INT PRIMARY KEY AUTO_INCREMENT,
    OrdineID INT,
    ProdottoID INT,
    Quantita INT,
    NomeProdotto VARCHAR(80),
	PrezzoAcquisto DECIMAL(10,2),
	Taglia VARCHAR(10),
    FOREIGN KEY (OrdineID) REFERENCES Ordini(ID)
);

INSERT INTO Utenti (Password, Email, Nome, Cognome, Tipo, DataNascita)
VALUES (
    'ba97ee796625c49f9b08e42a4a760f648dd5b85ca551450afeefde4986985284cb61383093ebd1549a23d49c649f6ebe66be313402af70dcd44940e06bc093a5',
    'francesconiemiec23@gmail.com',
    'Francesco',
    'Niemiec',
    'Admin',
    '2004-03-23'
);

INSERT INTO Utenti (Password, Email, Nome, Cognome, Tipo, DataNascita)
VALUES (
    'ba97ee796625c49f9b08e42a4a760f648dd5b85ca551450afeefde4986985284cb61383093ebd1549a23d49c649f6ebe66be313402af70dcd44940e06bc093a5',
    'francesconiemiec22@gmail.com',
    'Franek',
    'Tedesco',
    'Utente',
    '2004-03-23'
);

INSERT INTO Prodotti (Nome, Prezzo, Quantita, Tipo, Taglia, Tendenza, Nuovo, Descrizione) VALUES
-- Maglie da calcio
('Maglia Napoli Home 2022 EA7', 79.00, 15, 'Maglia da calcio', 'M', TRUE, TRUE, 'Maglia ufficiale SSC Napoli 2022 con sponsor EA7'),
('Maglia Udinese 2021 Away Macron', 65.00, 10, 'Maglia da calcio', 'L', FALSE, TRUE, 'Maglia bianca Udinese da trasferta 2021-22'),
('Maglia Real Madrid 2019', 85.50, 12, 'Maglia da calcio', 'M', TRUE, FALSE, 'Maglia bianca con dettagli dorati Adidas'),
('Maglia Manchester United 2020', 82.90, 8, 'Maglia da calcio', 'L', TRUE, TRUE, 'Maglia Old Trafford con sponsor Chevrolet'),
('Maglia Ajax 1995 Retro', 89.00, 6, 'Maglia da calcio', 'XL', TRUE, FALSE, 'Replica vintage della maglia Champions 1994-95'),

-- Canotte NBA
('Canotta OKC Thunder Home Gilgeous-Alexander', 84.99, 14, 'Canotta NBA', 'L', TRUE, TRUE, 'Canotta blu Oklahoma #2 della stagione attuale'),
('Canotta Miami Heat Retro Wade', 89.00, 10, 'Canotta NBA', 'M', TRUE, FALSE, 'Canotta retrò Miami Heat #3'),
('Canotta Boston Celtics Jayson Tatum', 79.50, 18, 'Canotta NBA', 'S', FALSE, TRUE, 'Maglia verde Boston Celtics #0'),
('Canotta Golden State Curry Icon', 83.00, 20, 'Canotta NBA', 'M', TRUE, TRUE, 'Canotta ufficiale Warriors #30 Icon Edition'),
('Canotta Dallas Mavericks Dončić', 85.00, 16, 'Canotta NBA', 'L', TRUE, FALSE, 'Canotta blu Mavericks #77 di Luka'),

-- Pantaloncini sportivi
('Shorts PSG Allenamento 2022', 42.00, 20, 'Pantaloncini sportivi', 'M', TRUE, TRUE, 'Shorts neri con logo Jordan x PSG'),
('Shorts Juventus Home 2021', 39.99, 25, 'Pantaloncini sportivi', 'L', FALSE, TRUE, 'Shorts ufficiali Juventus in tessuto AEROREADY'),
('Shorts Lakers Replica', 49.00, 18, 'Pantaloncini sportivi', 'XL', TRUE, FALSE, 'Shorts viola Lakers con dettagli dorati'),
('Shorts Real Madrid Training', 37.50, 22, 'Pantaloncini sportivi', 'M', TRUE, TRUE, 'Shorts da allenamento RM con logo Adidas'),
('Shorts FC Barcelona Away', 41.99, 12, 'Pantaloncini sportivi', 'L', FALSE, TRUE, 'Shorts gialli Barça con bande blaugrana'),

-- Palloni
('Pallone Champions League Adidas 2023', 49.00, 30, 'Palloni', 'Unica', TRUE, TRUE, 'Pallone ufficiale UCL con motivo a stelle'),
('Pallone Premier League Nike Flight', 45.90, 28, 'Palloni', 'Unica', TRUE, FALSE, 'Pallone inglese con tecnologia Aerowsculpt'),
('Pallone NBA Wilson Replica Game', 39.99, 20, 'Palloni', 'Unica', TRUE, TRUE, 'Replica ufficiale della palla da gioco NBA'),
('Pallone La Liga Puma Accelerate', 43.00, 18, 'Palloni', 'Unica', FALSE, TRUE, 'Pallone usato in campionato spagnolo'),
('Pallone Nike Street Playground', 29.00, 25, 'Palloni', 'Unica', TRUE, FALSE, 'Palla da basket outdoor con grip rinforzato'),

-- Giacche sportive
('Giacca Chelsea Pre-Match 2023', 74.50, 12, 'Giacche sportive', 'M', TRUE, TRUE, 'Giacca slim fit blu con dettagli Nike'),
('Giacca Lakers Zip Retro', 88.90, 10, 'Giacche sportive', 'L', TRUE, FALSE, 'Giacca gialla e viola con logo storico'),
('Giacca Bayern Monaco Allenamento', 69.00, 14, 'Giacche sportive', 'M', FALSE, TRUE, 'Track jacket con 3 strisce rosse e nere'),
('Giacca PSG Jordan Windbreaker', 92.00, 8, 'Giacche sportive', 'L', TRUE, TRUE, 'Giacca tecnica antivento nero/rosa PSG x Jordan'),
('Giacca Napoli 1987 Kappa Rétro', 99.00, 6, 'Giacche sportive', 'XL', TRUE, FALSE, 'Giaccone vintage con logo Mars anni ’80');

