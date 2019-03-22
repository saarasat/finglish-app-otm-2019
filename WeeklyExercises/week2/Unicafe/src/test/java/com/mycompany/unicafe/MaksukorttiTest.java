package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }
 
    @Test
    public void saldoPalautetaanOikein() {
        assertEquals(kortti.saldo(), 1000);
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        String vastaus = kortti.toString();
        assertEquals("saldo: 10.0", vastaus);
    }
    
    @Test
    public void kortinLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(1000);
        String vastaus = kortti.toString();
        assertEquals("saldo: 20.0", vastaus);
    }
    
    @Test
    public void rahanOttaminenPienentaaSaldoaOikein() {
        kortti.otaRahaa(1000);
        String vastaus = kortti.toString();
        assertEquals("saldo: 0.0", vastaus);
    }
    
    @Test
    public void saldoEiMuutuJosEiTarpeeksiOtettavaa() {
        kortti.otaRahaa(1010);
        String vastaus = kortti.toString();
        assertEquals("saldo: 10.0", vastaus);
    }
    
        
    @Test
    public void otaRahaaPalauttaaTrueJosRahatRiittivat() {
        boolean vastaus = kortti.otaRahaa(900);
        assertEquals(true, vastaus);
    }
    
    @Test
    public void otaRahaaPalauttaaFalseJosRahatEivatRiita() {
        boolean vastaus = kortti.otaRahaa(1100);
        assertEquals(false, vastaus);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
}
