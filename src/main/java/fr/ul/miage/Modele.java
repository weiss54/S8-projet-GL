package fr.ul.miage;

import fr.ul.miage.entity.Client;

public class Modele {

    private ParcBornes parc;

    private Client clientConnected;

    public Modele() {
        this.parc = new ParcBornes();
    }

    public ParcBornes getParc() {
        return parc;
    }

    public void setClient(Client client) {
        this.clientConnected = client;
    }
}