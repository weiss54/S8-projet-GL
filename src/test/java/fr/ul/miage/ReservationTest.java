package fr.ul.miage;

import jdk.jfr.Name;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.Date;

class ReservationTest {

    @Test
    @Name("Modifer une réservation")
    void modifierReservation() {
        Date date = new Date(2024, 05, 31);
        Time debut = new Time(12,00,00);
        Time fin = new Time(14,00,00);
        var res = new Reservation(date,debut,fin);
        res.setEtat(EtatReservation.CONFIRMEE);
        res.modifierReservation(fin, fin);
    }

    @Test
    @Name("Prolonger une réservation")
    void prolongerReservation() {
    }
}