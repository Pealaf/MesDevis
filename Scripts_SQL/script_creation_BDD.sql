CREATE TABLE utilisateur(
    id_utilisateur INTEGER NOT NULL,
    nom_utilisateur VARCHAR2(30 char) NOT NULL,
    prenom_utilisateur VARCHAR2(30 char) NOT NULL,
    PRIMARY KEY (id_utilisateur)
 );

CREATE TABLE client(
    id_client INTEGER NOT NULL,
    id_utilisateur INTEGER NOT NULL,
    PRIMARY KEY (id_client),
    FOREIGN KEY(id_utilisateur) REFERENCES utilisateur(id_utilisateur),
    UNIQUE (id_utilisateur)
 );

CREATE TABLE directeur_commercial(
    id_directeur_commercial INTEGER NOT NULL,
    id_utilisateur INTEGER NOT NULL,
    PRIMARY KEY (id_directeur_commercial),
    FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(id_utilisateur),
    UNIQUE (id_utilisateur)
 );

CREATE TABLE commercial(
    id_commercial INTEGER NOT NULL,
    id_utilisateur INTEGER NOT NULL,
    PRIMARY KEY(id_commercial),
    FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(id_utilisateur),
    UNIQUE (id_utilisateur)
 );

CREATE TABLE categorie_article(
    id_categorie_article INTEGER NOT NULL,
    intitule_categorie VARCHAR2(50 char) NOT NULL,
    id_commercial INTEGER NOT NULL,
    PRIMARY KEY (id_categorie_article),
    FOREIGN KEY (id_commercial) REFERENCES commercial(id_commercial)
 );

CREATE TABLE article(
    id_article INTEGER NOT NULL,
    libelle_article VARCHAR2(50 char) NOT NULL,
    prix_ht_article FLOAT NOT NULL,
    id_categorie_article INTEGER NOT NULL,
    PRIMARY KEY (id_article),
    FOREIGN KEY (id_categorie_article) REFERENCES categorie_article(id_categorie_article)
 );

CREATE TABLE devis(
    id_devis INTEGER NOT NULL,
    validation_devis NUMBER(1) NOT NULL,
    paiement_devis NUMBER(1) NOT NULL,
    id_utilisateur INTEGER NOT NULL,
    PRIMARY KEY (id_devis),
    FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(id_utilisateur)
 );

CREATE TABLE contenu_devis(
    id_contenu_devis INTEGER NOT NULL,
    id_devis INTEGER NOT NULL,
    id_article INTEGER NOT NULL,
    quantite_articles INTEGER NOT NULL,
    PRIMARY KEY (id_contenu_devis),
    FOREIGN KEY (id_devis) REFERENCES devis(id_devis),
    FOREIGN KEY (id_article) REFERENCES article(id_article),
    UNIQUE (id_devis, id_article)
 );

CREATE TABLE Panier(
    id_panier INTEGER NOT NULL,
    id_utilisateur INTEGER NOT NULL,
    id_article INTEGER NOT NULL,
    quantite_articles INTEGER NOT NULL,
    PRIMARY KEY (id_panier),
    FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur(id_utilisateur),
    FOREIGN KEY (id_article) REFERENCES Article(id_article),
    UNIQUE (id_utilisateur, id_article)
 );
