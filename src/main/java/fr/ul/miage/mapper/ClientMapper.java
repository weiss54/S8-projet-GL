package fr.ul.miage.mapper;

import fr.ul.miage.dto.ClientDTO;
import fr.ul.miage.entity.Client;

public class ClientMapper {

    public ClientMapper() {
    }

    public ClientDTO toClientDTO(Client client) {
        return new ClientDTO(client.getId(), client.getNom(), client.getPrenom(), client.getAdresse(), client.getEmail(), client.getNumeroMobile(), client.getNumeroCb(), client.estAdmin());
    }

    public Client toClient(ClientDTO clientDTO) {
        return new Client(clientDTO.getIdClient(), clientDTO.getNom(), clientDTO.getPrenom(), clientDTO.getAdresse(), clientDTO.getEmail(), clientDTO.getNumeroMobile(), clientDTO.getNumeroCb(), clientDTO.estAdmin());
    }
}
