/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icehockeystats.domain;

import java.util.ArrayList;

/**
 *
 * @author Sami
 */
public class Team {

    private String name;
    private boolean home;
    private ArrayList<Player> players;

    public Team(String name, boolean home) {
        this.name = name;
        this.home = home;
        
        this.players = new ArrayList<>();
        
    }
    
    public void addPlayer(Player player) {
        this.players.add(player);
    }
    
}
