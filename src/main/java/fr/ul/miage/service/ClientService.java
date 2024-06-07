package fr.ul.miage.service;

import fr.ul.miage.dto.ClientDTO;
import fr.ul.miage.exception.IdentifiantsIncorrectsException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static fr.ul.miage.DatabaseConnection.getConnection;

public class ClientService{

    private Connection connection;

    public ClientService(Connection connection) {
        this.connection = connection;
    }

    public void inscrireClient(String identifiant, String mdp, String nom, String prenom, String adresse,
    String email, String numeroMobile, String numeroCb) throws SQLException {
        if (identifiantExisteDeja(identifiant)) {
            throw new SQLException("L'identifiant existe déjà.");
        }else {
            String query = "INSERT INTO identification(identifiant, mot_de_passe, sel, admin) VALUES(?, ?, ?, false)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, identifiant);
                statement.setString(2, mdp);
                statement.setString(3, "sel");
                statement.executeUpdate();
            }
            query = "SELECT id_identification FROM identification WHERE identifiant = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, identifiant);
                try (ResultSet resultSet = statement.executeQuery()) {
                    resultSet.next();
                    long idIdentification = resultSet.getLong("id_identification");
                    query = "INSERT INTO client(nom, prenom, adresse, email, numero_mobile, numero_cb, id_identification) VALUES(?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement statement2 = connection.prepareStatement(query)) {
                        statement2.setString(1, nom);
                        statement2.setString(2, prenom);
                        statement2.setString(3, adresse);
                        statement2.setString(4, email);
                        statement2.setString(5, numeroMobile);
                        statement2.setString(6, numeroCb);
                        statement2.setLong(7, idIdentification);
                        statement2.executeUpdate();
                    }
                }
            }
        }
    }

    public ClientDTO connecterClient(String login, String motDePasse) throws SQLException, IdentifiantsIncorrectsException {
        String query = "SELECT c.id_client, c.nom, c.prenom, c.adresse, c.email, c.numero_mobile, c.numero_cb, i.admin " +
                "FROM client c " +
                "JOIN identification i ON c.id_identification = i.id_identification " +
                "WHERE i.identifiant = ? AND i.mot_de_passe = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            statement.setString(2, motDePasse);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    long clientId = resultSet.getLong("id_client");
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");
                    String adresse = resultSet.getString("adresse");
                    String email = resultSet.getString("email");
                    String numeroMobile = resultSet.getString("numero_mobile");
                    String numeroCB = resultSet.getString("numero_cb");
                    boolean isAdmin = resultSet.getBoolean("admin");
                    return new ClientDTO(clientId, nom, prenom, adresse, email, numeroMobile, numeroCB, isAdmin);
                } else {
                    throw new IdentifiantsIncorrectsException("Identifiants incorrects.");
                }
            }
        }
    }

    private boolean identifiantExisteDeja(String identifiant) throws SQLException {
        String query = "SELECT COUNT(*) FROM identification WHERE identifiant = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, identifiant);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        }
    }
}

