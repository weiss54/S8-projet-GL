package fr.ul.miage;

import fr.ul.miage.dto.BorneDTO;
import fr.ul.miage.mapper.BorneMapper;
import fr.ul.miage.service.BorneService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParcBornes {

    private final BorneService borneService = new BorneService();

    public ParcBornes() {
    }

    public boolean ajouterBorne(Borne borne) {
        for (Borne b : getBornes()) {
            if (b.getNumero() == borne.getNumero()) {
                return false;
            }
        }
        try {
            borneService.createBorne(BorneMapper.toDTO(borne));
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public String afficherListeBornes() {
        StringBuilder sb = new StringBuilder();
        sb.append("Liste des bornes :\n");
        List<Borne> bornes = getBornes();
        if (bornes.isEmpty()) {
            sb.append("Aucune borne\n");
        } else {
            for (Borne b : bornes) {
                sb.append(b).append("\n");
            }
        }
        return sb.toString();
    }


    public List<Borne> getBornes() {
        try {
            List<BorneDTO> bornes = borneService.getAllBornes();
            List<Borne> listeBornes = new ArrayList<>();
            for (BorneDTO borne : bornes) {
                listeBornes.add(BorneMapper.toEntity(borne));
            }
            return listeBornes;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public Borne getBorne(int numero) {
        for (Borne b : getBornes()) {
            if (b.getNumero() == numero) {
                return b;
            }
        }
        return null;
    }

    public String modifierBorne(int numBorne, int choix) {
        Borne borne = getBorne(numBorne);
        if (borne != null && (borne.getEtat() == EtatBorne.INDISPONIBLE || borne.getEtat() == EtatBorne.DISPONIBLE)){
            borne.setEtat(choix == 1 ? EtatBorne.DISPONIBLE : EtatBorne.INDISPONIBLE);
            try {
                borneService.updateBorne(BorneMapper.toDTO(borne));
                return "Borne modifiée avec succès.";
            } catch (SQLException e) {
                return "Impossible de modifier la borne.";
            }
        } else {
            return "Impossible de modifier cette borne. Elle est actuellement en état: " + borne.getEtat();
        }
    }
}
