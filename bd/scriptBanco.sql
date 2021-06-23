CREATE TABLE autor (
    autorId   INTEGER PRIMARY KEY AUTOINCREMENT
                      NOT NULL,
    autorNome VARCHAR UNIQUE
                      NOT NULL
);


CREATE TABLE livro (
    livroId       INTEGER PRIMARY KEY AUTOINCREMENT
                          NOT NULL,
    livroTitulo   VARCHAR NOT NULL
                          UNIQUE,
    livro_autorId INTEGER REFERENCES autor (autorId) 
                          NOT NULL
);


CREATE TABLE frase (
    fraseId       INTEGER PRIMARY KEY AUTOINCREMENT
                          NOT NULL,
    fraseTexto    VARCHAR UNIQUE
                          NOT NULL,
    frase_livroId INTEGER REFERENCES livro (livroId) 
                          NOT NULL
);

