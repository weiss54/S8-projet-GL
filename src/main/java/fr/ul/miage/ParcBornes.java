package fr.ul.miage;

import java.util.ArrayList;
import java.util.List;

public class ParcBornes {

    private List<Borne> bornes;

    public ParcBornes() {
        this.bornes = new ArrayList<>();
        //TODO: il faudra récupérer les bornes existantes dans la base de données
    }

    public boolean ajouterBorne(Borne borne) {
        for (Borne b : bornes) {
            if (b.getNumero() == borne.getNumero()) {
                return false;
            }
        }
        bornes.add(borne);
        return true;
    }

    public String afficherListeBornes() {
        StringBuilder sb = new StringBuilder();
        sb.append("Liste des bornes :\n");
        for (Borne borne : bornes) {
            sb.append(borne).append("\n");
        }
        return sb.toString();
    }

    public List<Borne> getBornes() {
        return bornes;
    }

}
