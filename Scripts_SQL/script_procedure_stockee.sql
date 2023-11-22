CREATE OR REPLACE PROCEDURE InitialiserCatalogue IS
    idCategorie INTEGER;
    prix DECIMAL(10,2);

BEGIN
    -- Boucle FOR de 1 à 1000
    FOR i IN 1..1000 LOOP
            idCategorie := TRUNC(DBMS_RANDOM.VALUE(1, 21));
            prix := DBMS_RANDOM.VALUE(0.00, 400.00);
            INSERT INTO ARTICLE VALUES(i, 'ArticleN°' || TO_CHAR(i), prix, idCategorie);
        END LOOP;

    COMMIT;
END InitialiserCatalogue;