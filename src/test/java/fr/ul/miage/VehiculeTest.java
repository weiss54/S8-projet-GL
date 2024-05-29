package fr.ul.miage;

import jdk.jfr.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VehiculeTest {
    @Test
    @Name("Tester la vérification de la plaque d'immatriculation")
    public void test1(){
        var vehicule = new Vehicule("EE-753-SA");
        assertTrue(vehicule.verifierFormatImmatriculation(vehicule.getImmatriculation()));
        assertFalse(vehicule.verifierFormatImmatriculation("1232dedea"));
    }
}
