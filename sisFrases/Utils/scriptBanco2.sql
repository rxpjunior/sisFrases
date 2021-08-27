--
-- File generated with SQLiteStudio v3.3.3 on qui jun 24 17:02:26 2021
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: autor
DROP TABLE IF EXISTS autor;

CREATE TABLE autor (
    autorId   INTEGER PRIMARY KEY AUTOINCREMENT
                      NOT NULL,
    autorNome VARCHAR UNIQUE
                      NOT NULL
);


-- Table: frase
DROP TABLE IF EXISTS frase;

CREATE TABLE frase (
    fraseId       INTEGER PRIMARY KEY AUTOINCREMENT
                          NOT NULL,
    fraseTexto    VARCHAR UNIQUE
                          NOT NULL,
    frase_livroId INTEGER REFERENCES livro (livroId) 
                          NOT NULL
);


-- Table: livro
DROP TABLE IF EXISTS livro;

CREATE TABLE livro (
    livroId       INTEGER PRIMARY KEY AUTOINCREMENT
                          NOT NULL,
    livroTitulo   VARCHAR NOT NULL
                          UNIQUE,
    livro_autorId INTEGER REFERENCES autor (autorId) 
                          NOT NULL
);


COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
