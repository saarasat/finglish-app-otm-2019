/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 *
 * @author saarasat
 */
public class KassapaateTest {
    
    Kassapaate kassapaate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test 
    public void konstruktoriAsettaaRahatOikein() {
        assertEquals(kassapaate.kassassaRahaa(), 100000);
    }
    
    @Test
    public void konstuktoriAsettaaMaukkaatLounaatOikein() {
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 0);
    }
    
    @Test
    public void konstuktoriAsettaaEdullisetLounaatOikein() {
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 0);
    }
    
    @Test
    public void kateisostostaOikeaVaihtorahaEdullistenOsaltaKunMaksuRiittava() {
        assertEquals(kassapaate.syoEdullisesti(250), 10);
    }

    @Test
    public void kateisostostaOikeaMaaraLounaitaEdullistenOsaltaKunMaksuRiittava() {
        kassapaate.syoEdullisesti(240);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 1);
    }
    
    @Test
    public void kateisostoEiToimiEdullistenOsaltaKunMaksuEiRiita() {
        assertEquals(kassapaate.syoEdullisesti(230), 230);
    }

    @Test
    public void kateisostostaOikeaVaihtorahaMaukkaidenOsaltaKunMaksuRiittava() {
        assertEquals(kassapaate.syoMaukkaasti(410), 10);
    }

    @Test
    public void kateisostostaOikeaMaaraLounaitaMaukkaidenOsaltaKunMaksuRiittava() {
        kassapaate.syoMaukkaasti(4000);
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 1);
    }
    
    @Test
    public void kateisostoEiToimiMaukkaidenOsaltaKunMaksuEiRiita() {
        assertEquals(kassapaate.syoMaukkaasti(350), 350);
    }
    
    @Test
    public void korttiostostaVeloitettuOikeinEdullistenOsaltaKunMaksuRiittava() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(kortti.saldo(), 760);
    }

    @Test
    public void korttiostostaOikeaMaaraLounaitaEdullistenOsaltaKunMaksuRiittava() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 1);
    }
    
    @Test
    public void korttiostoEiLisaaMyytyjaEdullistenOsaltaKunMaksuEiRiita() {
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 4);
    }
    @Test
    public void korttiostoEiVahennaKassastaEdullistenOsaltaKunMaksuEiRiita() {
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(kassapaate.kassassaRahaa(), 100000);
    }
    
    @Test
    public void korttiostostaVeloitettuOikeinMaukkaidenOsaltaKunMaksuRiittava() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(kortti.saldo(), 600);
    }

    @Test
    public void korttiostostaOikeaMaaraLounaitaMaukkaidenOsaltaKunMaksuRiittava() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(),1);
    }
    
    @Test
    public void korttiostoEiLisaaMyytyjaMaukkaidenOsaltaKunMaksuEiRiita() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 2);
    }
    
    @Test
    public void korttiostoEiVahennaKassastaMaukkaidenOsaltaKunMaksuEiRiita() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(kassapaate.kassassaRahaa(), 100000);
    }

    
    @Test
    public void kassanRahamaaraEiMuutuKortillaMaksettaessa() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(kassapaate.kassassaRahaa(), 100000);
    }

    @Test
    public void kortinSaldoMuuttuuRahaaLadattaessa() {
        kassapaate.lataaRahaaKortille(kortti, 1000);
        assertEquals(kortti.saldo(), 2000);
    }

    @Test
    public void kassanRahamaaraMuuttuuLadattaessaRahaaKortille() {
        kassapaate.lataaRahaaKortille(kortti, 1000);
        assertEquals(kassapaate.kassassaRahaa(), 101000);
    }

    @Test
    public void kortilleEiVoiLadataNegatiivistaMaaraa() {
        kassapaate.lataaRahaaKortille(kortti, -1000);
        assertEquals(kassapaate.kassassaRahaa(), 100000);
    }


    
    
}
