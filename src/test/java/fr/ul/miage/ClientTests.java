package fr.ul.miage;

import fr.ul.miage.dto.ClientDTO;
import fr.ul.miage.exception.IdentifiantsIncorrectsException;
import fr.ul.miage.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ClientTests {

    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private ClientService clientService;

    @BeforeEach
    public void setUp() throws SQLException {
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        clientService = new ClientService(mockConnection);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    }

    @Test
    public void testInscrireClient_identifiantExisteDeja() throws SQLException {
        when(mockResultSet.next()).thenReturn(true); // Simulate that the identifiant already exists
        when(mockResultSet.getInt(1)).thenReturn(1);

        assertThrows(SQLException.class, () -> {
            clientService.inscrireClient("user", "password", "Nom", "Prenom", "Adresse", "email@example.com", "0123456789", "1234567890123456");
        });

        verify(mockConnection, times(1)).prepareStatement("SELECT COUNT(*) FROM identification WHERE identifiant = ?");
        verify(mockPreparedStatement, times(1)).setString(1, "user");
        verify(mockPreparedStatement, times(1)).executeQuery();
    }

    @Test
    public void testInscrireClient_success() throws SQLException {
        when(mockResultSet.next()).thenReturn(false).thenReturn(true);
        when(mockResultSet.getLong("id_identification")).thenReturn(1L);

        clientService.inscrireClient("newUser", "newPassword", "Nom", "Prenom", "Adresse", "email@example.com", "0123456789", "1234567890123456");

        InOrder inOrder = inOrder(mockConnection, mockPreparedStatement, mockResultSet);

        inOrder.verify(mockConnection).prepareStatement("SELECT COUNT(*) FROM identification WHERE identifiant = ?");
        inOrder.verify(mockPreparedStatement).setString(1, "newUser");
        inOrder.verify(mockPreparedStatement).executeQuery();

        inOrder.verify(mockConnection).prepareStatement("INSERT INTO identification(identifiant, mot_de_passe, sel, admin) VALUES(?, ?, ?, false)");
        inOrder.verify(mockPreparedStatement).setString(1, "newUser");
        inOrder.verify(mockPreparedStatement).setString(2, "newPassword");
        inOrder.verify(mockPreparedStatement).setString(3, "sel");
        inOrder.verify(mockPreparedStatement).executeUpdate();

        inOrder.verify(mockConnection).prepareStatement("SELECT id_identification FROM identification WHERE identifiant = ?");
        inOrder.verify(mockPreparedStatement).setString(1, "newUser");
        inOrder.verify(mockPreparedStatement).executeQuery();

        inOrder.verify(mockConnection).prepareStatement("INSERT INTO client(nom, prenom, adresse, email, numero_mobile, numero_cb, id_identification) VALUES(?, ?, ?, ?, ?, ?, ?)");
        inOrder.verify(mockPreparedStatement).setString(1, "Nom");
        inOrder.verify(mockPreparedStatement).setString(2, "Prenom");
        inOrder.verify(mockPreparedStatement).setString(3, "Adresse");
        inOrder.verify(mockPreparedStatement).setString(4, "email@example.com");
        inOrder.verify(mockPreparedStatement).setString(5, "0123456789");
        inOrder.verify(mockPreparedStatement).setString(6, "1234567890123456");
        inOrder.verify(mockPreparedStatement).setLong(7, 1L);
        inOrder.verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testConnecterClient_success() throws SQLException, IdentifiantsIncorrectsException {
        // Simulate successful login
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("id_client")).thenReturn(1L);
        when(mockResultSet.getString("nom")).thenReturn("Nom");
        when(mockResultSet.getString("prenom")).thenReturn("Prenom");
        when(mockResultSet.getString("adresse")).thenReturn("Adresse");
        when(mockResultSet.getString("email")).thenReturn("email@example.com");
        when(mockResultSet.getString("numero_mobile")).thenReturn("0123456789");
        when(mockResultSet.getString("numero_cb")).thenReturn("1234567890123456");
        when(mockResultSet.getBoolean("admin")).thenReturn(false);

        ClientDTO client = clientService.connecterClient("user", "password");

        assertNotNull(client);
        assertEquals(1L, client.getIdClient());
        assertEquals("Nom", client.getNom());
        assertEquals("Prenom", client.getPrenom());
        assertEquals("Adresse", client.getAdresse());
        assertEquals("email@example.com", client.getEmail());
        assertEquals("0123456789", client.getNumeroMobile());
        assertEquals("1234567890123456", client.getNumeroCb());
        assertFalse(client.estAdmin());

        verify(mockConnection, times(1)).prepareStatement("SELECT c.id_client, c.nom, c.prenom, c.adresse, c.email, c.numero_mobile, c.numero_cb, i.admin " +
                "FROM client c " +
                "JOIN identification i ON c.id_identification = i.id_identification " +
                "WHERE i.identifiant = ? AND i.mot_de_passe = ?");
        verify(mockPreparedStatement, times(1)).setString(1, "user");
        verify(mockPreparedStatement, times(1)).setString(2, "password");
        verify(mockPreparedStatement, times(1)).executeQuery();
    }

    @Test
    public void testConnecterClient_identifiantsIncorrects() throws SQLException {
        // Simulate incorrect login
        when(mockResultSet.next()).thenReturn(false);

        assertThrows(IdentifiantsIncorrectsException.class, () -> {
            clientService.connecterClient("wrongUser", "wrongPassword");
        });

        verify(mockConnection, times(1)).prepareStatement("SELECT c.id_client, c.nom, c.prenom, c.adresse, c.email, c.numero_mobile, c.numero_cb, i.admin " +
                "FROM client c " +
                "JOIN identification i ON c.id_identification = i.id_identification " +
                "WHERE i.identifiant = ? AND i.mot_de_passe = ?");
        verify(mockPreparedStatement, times(1)).setString(1, "wrongUser");
        verify(mockPreparedStatement, times(1)).setString(2, "wrongPassword");
        verify(mockPreparedStatement, times(1)).executeQuery();
    }
}
