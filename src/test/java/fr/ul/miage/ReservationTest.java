package fr.ul.miage;

import jdk.jfr.Name;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    @Test
    @Name("Modifer une réservation")
    void modifierReservation() {
        LocalDate date = LocalDate.of(2024, 05, 31);
        LocalTime debut = LocalTime.of(12,00,00);
        LocalTime text = LocalTime.of(12,00,00);
        System.out.println(debut);
        System.out.println(text);
        LocalTime fin = LocalTime.of(14,00,00);
        LocalTime nouvfin = LocalTime.of(15,00,00);

        var res = new Reservation(date,debut,fin);
        res.setEtat(EtatReservation.CONFIRMEE);
        res.modifierReservation(fin, nouvfin);
    }
    @Test
    @Name("Créer une réservation qui n'est pas cohérente : date < aujourd'hui")
    void creerReservationNonCoherente1() {
        LocalDate date = LocalDate.of(2024, 04, 30);
        LocalTime debut = LocalTime.of(12,00,00);
        LocalTime fin = LocalTime.of(14,00,00);
        var reservation = new Reservation(date,debut,fin);
        assertThrows(IllegalStateException.class, reservation::creerReservation);
    }

    @Test
    @Name("Créer une réservation qui n'est pas cohérente : début > fin")
    void creerReservationNonCoherente2() {
        LocalDate date = LocalDate.of(2024, 06, 30);
        LocalTime debut = LocalTime.of(15,00,00);
        LocalTime fin = LocalTime.of(14,00,00);
        var reservation = new Reservation(date,debut,fin);
        assertThrows(IllegalStateException.class, reservation::creerReservation);
    }

    @Test
    @Name("Créer une réservation qui n'est pas cohérente : date < aujourd'hui ET début > fin")
    void creerReservationNonCoherente3() {
        LocalDate date = LocalDate.of(2024, 04, 30);
        LocalTime debut = LocalTime.of(17,00,00);
        LocalTime fin = LocalTime.of(14,00,00);
        var reservation = new Reservation(date,debut,fin);
        assertThrows(IllegalStateException.class, reservation::creerReservation);
    }
    @Test
    @Name("Créer une réservation qui est cohérente")
    void creerReservationCoherente() {
        LocalDate date = LocalDate.of(2024, 06, 10);
        LocalTime debut = LocalTime.of(12,00,00);
        LocalTime fin = LocalTime.of(14,00,00);
        var reservation = new Reservation(date,debut,fin);
        assertThrows(IllegalStateException.class, reservation::creerReservation);
        //reservation.creerReservation();
    }
    @Test
    @Name("Prolonger une réservation")
    void prolongerReservation1() {
        LocalDate date = LocalDate.of(2024, 6, 3);
        LocalTime debut = LocalTime.of(17,0,0);
        LocalTime fin = LocalTime.of(23,10,0);
        var reservation = new Reservation(date,debut,fin);
        reservation.setEtat(EtatReservation.EN_COURS);
        reservation.prolongerReservation(LocalTime.of(1,0,0));
        assertEquals(0, reservation.getHeure_fin().getHour());
    }

    @Test
    @Name("Prolonger une réservation")
    void prolongerReservation2() {
        LocalDate date = LocalDate.of(2024, 6, 3);
        LocalTime debut = LocalTime.of(17,0,0);
        LocalTime fin = LocalTime.of(23,20,0);
        var reservation = new Reservation(date,debut,fin);
        reservation.setEtat(EtatReservation.EN_COURS);
        reservation.prolongerReservation(LocalTime.of(1,0,0));
        assertEquals(23, reservation.getHeure_fin().getHour());
    }


    @Test
    @Name("Prolonger une réservation")
    void prolongerReservation3() {
        LocalDate date = LocalDate.of(2024, 6, 3);
        LocalTime debut = LocalTime.of(17,0,0);
        LocalTime fin = LocalTime.of(23,20,0);
        var reservation = new Reservation(date,debut,fin);
        reservation.setEtat(EtatReservation.TERMINEE);
        //TODO j'arrive pas a tester l'exception
        //assertThrows(IllegalStateException.class, reservation::prolongerReservation);
    }
}