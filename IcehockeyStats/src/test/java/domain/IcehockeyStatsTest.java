package domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import icehockeystats.domain.Clock;
import icehockeystats.domain.Match;
import icehockeystats.domain.Player;
import icehockeystats.domain.Team;
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
public class IcehockeyStatsTest {
    
    Player player;
    Team team;
    Match match;
    Clock clock;
    
    public IcehockeyStatsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.player = new Player("Sami", "Laitinen", 37, "OL", 1);
        this.team = new Team("Leijonat", true);
        Team away = new Team("Tre Kronor", false);
        this.match = new Match(this.team, away);
        this.clock = new Clock();        
    }
    
    @After
    public void tearDown() {
    }

    // Player tests
    @Test
    public void getNameCorrect() {
        assertEquals("Sami Laitinen", player.getName());
    }

    @Test
    public void playerGoals() {
        this.player.addGoal();
        assertEquals(1, player.getGoals());
    }

    @Test
    public void playerPasses() {
        this.player.addPass();
        assertEquals(1, player.getPasses());
    }

    @Test
    public void playerTotalPoints() {
        this.player.addGoal();
        this.player.addPass();
        assertEquals(2, player.getTotalPoints());
    }

    @Test
    public void goalieSaves() {
        this.player.addSave();
        assertEquals(1, player.getSaves());
    }

    // Team tests
    @Test
    public void teamPlayer() {
        this.team.addPlayer(this.player.getNumber(), this.player);
        assertEquals("Sami", this.team.getPlayer(37).getFirstName());
    }

    @Test
    public void teamGoals() {
        this.team.addPlayer(this.player.getNumber(), this.player);
        this.team.addPlayer(4, new Player("Miro", "Heiskanen", 4, "VP", 1));
        this.team.addPlayer(8, new Player("Teemu", "Selänne", 8, "VL", 1));
        this.team.addGoal(37, 8, 4);
        assertEquals(1, this.team.getGoals());
    }
    
    // Match tests
    @Test
    public void matchPeriod() {
        this.match.addPeriod();
        assertEquals(1, this.match.getPeriod());
    }
    
    @Test
    public void matchScore() {
        assertEquals("0 - 0", this.match.toString());
    }
    
    // Clock
    @Test
    public void showTime() {
        this.clock.tick();
        assertEquals("00:01", this.clock.show());
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
