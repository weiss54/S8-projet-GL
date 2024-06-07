CREATE TABLE identification (
                                id_identification SERIAL PRIMARY KEY,
                                identifiant VARCHAR(255) NOT NULL,
                                mot_de_passe VARCHAR(255) NOT NULL,
                                sel VARCHAR(255) NOT NULL,
                                admin BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE client (
                        id_client SERIAL PRIMARY KEY,
                        nom VARCHAR(255) NOT NULL,
                        prenom VARCHAR(255) NOT NULL,
                        adresse TEXT NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        numero_mobile VARCHAR(20) NOT NULL,
                        numero_cb VARCHAR(20) NOT NULL,
                        id_identification INT NOT NULL,
                        CONSTRAINT fk_identification
                            FOREIGN KEY(id_identification)
                                REFERENCES identification(id_identification)
);


INSERT INTO identification (identifiant, mot_de_passe, sel, admin)
VALUES
    ('user', 'user', 'sel123', false),
    ('admin', 'admin', 'sel456', true),
    ('alexis', 'alexis', 'sel456', false);

INSERT INTO client (nom, prenom, adresse, email, numero_mobile, numero_cb, id_identification)
VALUES
    ('FAYNOT', 'Marine', '123 Rue de la Liberté', 'john.doe@example.com', '0123456789', '1234567890123456', 1),
    ('WEISS', 'Lucas', '456 Avenue des Roses', 'alice.smith@example.com', '9876543210', '9876543210987654', 2),
    ('LOPES-VAZ', 'Alexis', '896 Avenue des Trucs', 'azert.www@example.com', '5866543022', '9577654327444653', 3);
