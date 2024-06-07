package fr.ul.miage.mapper;

import fr.ul.miage.Reservation;
import fr.ul.miage.dto.ReservationDTO;

public class ReservationMapper {
    public static ReservationDTO toDTO(Reservation reservation){
        if(reservation==null){
            return null;
        }
        return new ReservationDTO(reservation.getNum_reservation(), reservation.getDate(), reservation.getHeure_debut(), reservation.getHeure_fin(), reservation.getHeure_arrivee(), reservation.getHeure_depart(), reservation.getProlongee(), reservation.getId_client(), reservation.getId_borne(), reservation.getImmatriculation_voiture());
    }


    public static Reservation toEntity(ReservationDTO reservation){
        if(reservation==null){
            return null;
        }
        return new Reservation(reservation.getNum_reservation(), reservation.getDate(), reservation.getHeure_debut(), reservation.getHeure_fin(), reservation.getHeure_arrivee(), reservation.getHeure_depart(), reservation.getProlongee(), reservation.getId_client(), reservation.getId_borne(), reservation.getImmatriculation_voiture());
    }
}
