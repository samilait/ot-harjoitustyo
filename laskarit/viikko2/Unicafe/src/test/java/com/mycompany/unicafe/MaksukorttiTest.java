package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoOikeinAlussa() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void lataaOikein() {
        kortti.lataaRahaa(100);
        assertEquals("saldo: 1.10", kortti.toString());
    }

    @Test
    public void saldoVaheneeOikein() {
        kortti.otaRahaa(1);
        assertEquals("saldo: 0.9", kortti.toString());
    }
    
    @Test
    public void saldoEiVaheneJosEiRahaaTarpeeksi() {
        kortti.otaRahaa(15);
        assertEquals("saldo: 0.10", kortti.toString());
    }

    @Test
    public void tosiJosEiRahaaTarpeeksi() {
        assertEquals(false, kortti.otaRahaa(15));
    }
    
}
