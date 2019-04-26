/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import icehockeystats.dao.DataReader;
import icehockeystats.domain.Player;
import java.io.IOException;
import javafx.collections.ObservableList;
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
public class DaoTest {
    
    DataReader dataReader;
    
    public DaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.dataReader = new DataReader("home.txt");
    }
    
    @After
    public void tearDown() {
    }
    
    // Test player list reader
    @Test
    public void readTeamName() throws IOException {
        String teamName = this.dataReader.loadTeamName();
        assertEquals("Harjun Kiekko", teamName);
    }
    
    @Test
    public void readPlayers() throws IOException {
        ObservableList<Player> players = this.dataReader.loadPlayers();        
        assertEquals("Topi", players.get(0).getFirstName());
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
