/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sami
 */
public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.paate = new Kassapaate();
        this.kortti = new Maksukortti(1000);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void oikeaRahamaara() {
         assertEquals(100000, paate.kassassaRahaa());
     }
     
    @Test
     public void oikeaMaaraMyytyjaLounaita() {
         assertEquals(0, paate.maukkaitaLounaitaMyyty());
     }
     
    @Test
     public void syoEdullisestiOikeinKassaan() {
         int vaihtoRaha = paate.syoEdullisesti(240);
         assertEquals(100000 + 240, paate.kassassaRahaa());
     }

    @Test
     public void syoEdullisestiOikeinMyytyjaLounaita() {
         int vaihtoRaha = paate.syoEdullisesti(240);
         assertEquals(1, paate.edullisiaLounaitaMyyty());
     }

    @Test
     public void syoEdullisestiKassaEiMuutu() {
         int vaihtoRaha = paate.syoEdullisesti(200);
         assertEquals(100000, paate.kassassaRahaa());
     }

    @Test
     public void syoEdullisestiLounaidenMaaraEiMuutu() {
         int vaihtoRaha = paate.syoEdullisesti(200);
         assertEquals(0, paate.edullisiaLounaitaMyyty());
     }

         @Test
     public void syoMaukkaastiOikeinKassaan() {
         int vaihtoRaha = paate.syoMaukkaasti(400);
         assertEquals(100000 + 400, paate.kassassaRahaa());
     }

    @Test
     public void syoMaukkaastiOikeinMyytyjaLounaita() {
         int vaihtoRaha = paate.syoMaukkaasti(400);
         assertEquals(1, paate.maukkaitaLounaitaMyyty());
     }

    @Test
     public void syoMaukkaastiKassaEiMuutu() {
         int vaihtoRaha = paate.syoMaukkaasti(200);
         assertEquals(100000, paate.kassassaRahaa());
     }

    @Test
     public void syoMaukkaastiLounaidenMaaraEiMuutu() {
         int vaihtoRaha = paate.syoMaukkaasti(200);
         assertEquals(0, paate.maukkaitaLounaitaMyyty());
     }
     
     // Testtaa kortti: edullinen
    @Test
     public void syoEdullisestiKortillaRahaa() {         
         assertEquals(true, paate.syoEdullisesti(kortti));
     }

    @Test
     public void syoEdullisestiKortillaLounaatKasvaa() {
         boolean onnistuu = paate.syoEdullisesti(kortti);
         assertEquals(1, paate.edullisiaLounaitaMyyty());
     }
     
    @Test
     public void syoEdullisestiKortillaEiRahaaRahaEiMuutu() {
         kortti.otaRahaa(800);
         boolean onnistuu = paate.syoEdullisesti(kortti);
         assertEquals(200, kortti.saldo());
     }

    @Test
     public void syoEdullisestiKortillaEiRahaaLounaatEiMuutu() {
         kortti.otaRahaa(800);
         boolean onnistuu = paate.syoEdullisesti(kortti);
         assertEquals(0, paate.edullisiaLounaitaMyyty());
     }
     
     // Testtaa kortti: maukas
    @Test
     public void syoMaukkaastiKortillaRahaa() {         
         assertEquals(true, paate.syoMaukkaasti(kortti));
     }

    @Test
     public void syoMaukkaastiKortillaLounaatKasvaa() {
         boolean onnistuu = paate.syoMaukkaasti(kortti);
         assertEquals(1, paate.maukkaitaLounaitaMyyty());
     }
     
    @Test
     public void syoMaukkaastiKortillaEiRahaaRahaEiMuutu() {
         kortti.otaRahaa(800);
         boolean onnistuu = paate.syoMaukkaasti(kortti);
         assertEquals(200, kortti.saldo());
     }

    @Test
     public void syoMaukkaastiKortillaEiRahaaLounaatEiMuutu() {
         kortti.otaRahaa(800);
         boolean onnistuu = paate.syoMaukkaasti(kortti);
         assertEquals(0, paate.maukkaitaLounaitaMyyty());
     }

    @Test
     public void rahanLatausKortilleKortinSaldo() {
         paate.lataaRahaaKortille(kortti, 500);
         assertEquals(1000 + 500, kortti.saldo());
     }

    @Test
     public void rahanLatausKortilleKassaRaha() {
         paate.lataaRahaaKortille(kortti, 500);
         assertEquals(100000 + 500, paate.kassassaRahaa());
     }

}
