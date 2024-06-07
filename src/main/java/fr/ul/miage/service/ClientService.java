package fr.ul.miage.service;

import fr.ul.miage.entity.Client;

import java.sql.Connection;

public class ClientService{

    private Connection connection;

    public ClientService(Connection connection) {
        this.connection = connection;
    }

    public void inscrireClient(Client client) {

    }

    public boolean connecterClient(String login, String motDePasse) {

    }
}
