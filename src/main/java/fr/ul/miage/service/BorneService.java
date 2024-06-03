package fr.ul.miage.service;

import fr.ul.miage.Borne;
import fr.ul.miage.DatabaseConnection;
import fr.ul.miage.EtatBorne;
import fr.ul.miage.dto.BorneDTO;
import fr.ul.miage.mapper.BorneMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorneService {

    private final Connection connection;

    public BorneService() {
        this.connection = DatabaseConnection.getConnection();
    }

    public BorneDTO getBorneById(int id) throws SQLException {
        String query = "SELECT * FROM borne WHERE numero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Borne borne = new Borne(rs.getInt("numero"), EtatBorne.valueOf(rs.getString("etat")));
                    return BorneMapper.toDTO(borne);
                }
            }
        }
        return null;
    }

    public List<BorneDTO> getAllBornes() throws SQLException {
        List<BorneDTO> borneDTOList = new ArrayList<>();
        String query = "SELECT * FROM borne ORDER BY numero";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Borne borne = new Borne(rs.getInt("numero"), EtatBorne.valueOf(rs.getString("etat")));
                borneDTOList.add(BorneMapper.toDTO(borne));
            }
        }
        return borneDTOList;
    }

    public void createBorne(BorneDTO borneDTO) throws SQLException {
        String query = "INSERT INTO borne (numero, etat) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            Borne borne = BorneMapper.toEntity(borneDTO);
            stmt.setInt(1, borne.getNumero());
            stmt.setString(2, borne.getEtat().name());
            stmt.executeUpdate();
        }
    }

    public void updateBorne(BorneDTO borneDTO) throws SQLException {
        String query = "UPDATE borne SET etat = ? WHERE numero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            Borne borne = BorneMapper.toEntity(borneDTO);
            stmt.setString(1, borne.getEtat().name());
            stmt.setInt(2, borne.getNumero());
            stmt.executeUpdate();
        }
    }

}
