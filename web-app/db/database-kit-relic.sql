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

CREATE TABLE Prodotto (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Prezzo DECIMAL(10,2),
    Quantita INT,
    Tipo VARCHAR(50),
    Taglia VARCHAR(10),
    Tendenza BOOLEAN,
    Nuovo BOOLEAN,
    Descrizione TEXT,
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
    FOREIGN KEY (ProdottoID) REFERENCES Prodotto(ID)
);
