package fr.ul.miage;

import jdk.jfr.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TarifTest {
    @Test
    @Name("Test ...")
    public void test1(){
        var tarif = new Tarif();
        assertEquals(5.0, tarif.getTarif_base());
    }
}
