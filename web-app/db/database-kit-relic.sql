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

CREATE TABLE Indirizzo (
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
    Principale BOOLEAN,
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
    IDIndirizzo INT,
    FOREIGN KEY (IdUtente) REFERENCES Utenti(ID),
    FOREIGN KEY (IdCarta) REFERENCES MetodoDiPagamento(IdCarta),
    FOREIGN KEY (IDIndirizzo) REFERENCES Indirizzo(ID)
);

CREATE TABLE OggettoOrdine (
    ItemId INT PRIMARY KEY AUTO_INCREMENT,
    OrdineID INT,
    ProdottoID INT,
    Quantita INT,
    FOREIGN KEY (OrdineID) REFERENCES Ordini(ID),
    FOREIGN KEY (ProdottoID) REFERENCES Prodotti(ID)
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

