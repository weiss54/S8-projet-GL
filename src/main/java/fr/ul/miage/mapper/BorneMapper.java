package fr.ul.miage.mapper;

import fr.ul.miage.Borne;
import fr.ul.miage.dto.BorneDTO;

public class BorneMapper {

    public static BorneDTO toDTO(Borne borne) {
        if (borne == null) {
            return null;
        }
        return new BorneDTO(borne.getNumero(), borne.getEtat());
    }

    public static Borne toEntity(BorneDTO borneDTO) {
        if (borneDTO == null) {
            return null;
        }
        return new Borne(borneDTO.getNumero(), borneDTO.getEtat());
    }

}
