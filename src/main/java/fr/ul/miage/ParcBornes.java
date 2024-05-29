package fr.ul.miage;

import java.util.ArrayList;
import java.util.List;

public class ParcBornes {

    private List<Borne> bornes;

    public ParcBornes() {
        this.bornes = new ArrayList<>();
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

    public List<Borne> getBornes() {
        return bornes;
    }

}
