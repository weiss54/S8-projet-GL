package fr.ul.miage;

import jdk.jfr.Name;
import org.junit.jupiter.api.Test;

import static fr.ul.miage.EtatBorne.*;
import static org.junit.jupiter.api.Assertions.*;

public class ParcBornesTest {

    @Test
    @Name("Test l'ajout d'une borne")
    public void test1() {
        ParcBornes parc = new ParcBornes();
        assertEquals(0, parc.getBornes().size());
        boolean res = parc.ajouterBorne(new Borne(1, DISPONIBLE));
        assertTrue(res);
        assertEquals(1, parc.getBornes().size());
    }

    @Test
    @Name("Test ajout d'une borne dont le numéro existe déjà")
    public void test2() {
        ParcBornes parc = new ParcBornes();
        boolean res = parc.ajouterBorne(new Borne(1, DISPONIBLE));
        assertTrue(res);
        assertEquals(1, parc.getBornes().size());
        res = parc.ajouterBorne(new Borne(1, DISPONIBLE));
        assertFalse(res);
        assertEquals(1, parc.getBornes().size());
    }

}
