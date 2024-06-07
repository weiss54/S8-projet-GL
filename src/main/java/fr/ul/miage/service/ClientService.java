package fr.ul.miage.service;

import fr.ul.miage.DatabaseConnection;
import fr.ul.miage.dto.ClientDTO;
import fr.ul.miage.entity.Client;
import fr.ul.miage.exception.IdentifiantsIncorrectsException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static fr.ul.miage.DatabaseConnection.getConnection;

public class ClientService{

    private Connection connection;

    public ClientService() {
        this.connection = getConnection();
    }

    public void inscrireClient(Client client) throws SQLException {
        String query = "INSERT INTO client (nom, prenom, adresse, email, numero_mobile, numero_cb, id_identification) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client.getNom());
            statement.setString(2, client.getPrenom());
            statement.setString(3, client.getAdresse());
            statement.setString(4, client.getEmail());
            statement.setString(5, client.getNumeroMobile());
            statement.setString(6, client.getNumeroCb());
            //statement.setInt(7, client.getIdentification());
            statement.executeUpdate();
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
}

