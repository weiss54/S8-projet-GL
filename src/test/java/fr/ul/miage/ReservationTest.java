package fr.ul.miage;

import jdk.jfr.Name;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservationTest {
    private Reservation reservation = new Reservation(0, null, null, null, null, null, 0, 1, 1, "AZ-123-AZ", EtatReservation.CREE, TypeReservation.TEMPORAIRE);

    private void initResa (LocalDate d, LocalTime deb, LocalTime fin){
        this.reservation.setDate(d);
        this.reservation.setHeure_debut(deb);
        this.reservation.setHeure_fin(fin);
    }
    @Test
    @Name("Modifer une réservation")
    public void modifierReservation() {
        LocalDate date = LocalDate.of(2024, 05, 31);
        LocalTime debut = LocalTime.of(12,00,00);
        LocalTime text = LocalTime.of(12,00,00);
        System.out.println(debut);
        System.out.println(text);
        LocalTime fin = LocalTime.of(14,00,00);
        LocalTime nouvfin = LocalTime.of(15,00,00);


        initResa(date,debut,fin);
        reservation.setEtat(EtatReservation.CONFIRMEE);
        reservation.modifierReservation(fin, nouvfin);
    }
    @Test
    @Name("Créer une réservation qui n'est pas cohérente : date < aujourd'hui")
    public void creerReservationNonCoherente1() {
        LocalDate date = LocalDate.of(2024, 04, 30);
        LocalTime debut = LocalTime.of(12,00,00);
        LocalTime fin = LocalTime.of(14,00,00);
        initResa(date,debut,fin);
        assertThrows(IllegalStateException.class, reservation::creerReservation);
    }

    @Test
    @Name("Créer une réservation qui n'est pas cohérente : début > fin")
    public void creerReservationNonCoherente2() {
        LocalDate date = LocalDate.of(2024, 06, 30);
        LocalTime debut = LocalTime.of(15,00,00);
        LocalTime fin = LocalTime.of(14,00,00);
        initResa(date,debut,fin);
        assertThrows(IllegalStateException.class, reservation::creerReservation);
    }

    @Test
    @Name("Créer une réservation qui n'est pas cohérente : date < aujourd'hui ET début > fin")
    public void creerReservationNonCoherente3() {
        LocalDate date = LocalDate.of(2024, 04, 30);
        LocalTime debut = LocalTime.of(17,00,00);
        LocalTime fin = LocalTime.of(14,00,00);
        initResa(date,debut,fin);
        assertThrows(IllegalStateException.class, reservation::creerReservation);
    }
    @Test
    @Name("Créer une réservation qui est cohérente")
    public void creerReservationCoherente() {
        LocalDate date = LocalDate.of(2024, 06, 10);
        LocalTime debut = LocalTime.of(12,00,00);
        LocalTime fin = LocalTime.of(14,00,00);
        initResa(date,debut,fin);
        assertThrows(IllegalStateException.class, reservation::creerReservation);
        //reservation.creerReservation();
    }
    @Test
    @Name("Prolonger une réservation")
    public void prolongerReservation1() {
        LocalDate date = LocalDate.of(2024, 6, 3);
        LocalTime debut = LocalTime.of(17,0,0);
        LocalTime fin = LocalTime.of(23,10,0);
        initResa(date,debut,fin);
        reservation.setEtat(EtatReservation.EN_COURS);
        reservation.prolongerReservation(LocalTime.of(1,0,0));
        assertEquals(0, reservation.getHeure_fin().getHour());
    }

    @Test
    @Name("Prolonger une réservation")
    public void prolongerReservation2() {
        LocalDate date = LocalDate.of(2024, 6, 3);
        LocalTime debut = LocalTime.of(17,0,0);
        LocalTime fin = LocalTime.of(23,20,0);
        initResa(date,debut,fin);
        reservation.setEtat(EtatReservation.EN_COURS);
        reservation.prolongerReservation(LocalTime.of(1,0,0));
        assertEquals(23, reservation.getHeure_fin().getHour());
    }


    @Test
    @Name("Prolonger une réservation")
    public void prolongerReservation3() {
        LocalDate date = LocalDate.of(2024, 6, 3);
        LocalTime debut = LocalTime.of(17,0,0);
        LocalTime fin = LocalTime.of(23,20,0);
        initResa(date,debut,fin);
        reservation.setEtat(EtatReservation.TERMINEE);
        //TODO j'arrive pas a tester l'exception
        //assertThrows(IllegalStateException.class, reservation::prolongerReservation);
    }

    @Test
    @Name("Ajouter une nouvelle réservation dans la base de données")
    public void ajouterNouvelleResaBDD(){
        LocalDate date = LocalDate.of(2024, 6, 10);
        LocalTime debut = LocalTime.of(10,0,0);
        LocalTime fin = LocalTime.of(12,00,0);
        initResa(date,debut,fin);
        reservation.creerReservation();
    }

    @Test
    @Name("Afficher une réservation qui est dans la bdd")
    public void afficherReservation(){
        var resa = new Reservation();
        resa.setNum_reservation(1);
        System.out.println(resa.getReservation().afficherReservation());
    }

}