/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icehockeystats.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Sami
 */
public class Team {

    private String name;
    private boolean home;
    private HashMap<Integer, Player> players;
    private int goals;

    public Team(String name, boolean home) {
        this.name = name;
        this.home = home;
        this.goals = 0;
        this.players = new HashMap<>();        
    }
    
    public void addPlayer(Integer number, Player player) {
        this.players.put(number, player);
    }
    
    public void addGoal(Integer scorer, Integer assistant1, Integer assistant2) {
        this.goals++;
        this.players.get(scorer).addGoal();
        this.players.get(assistant1).addPass();
        this.players.get(assistant2).addPass();
    }
    
    public int getGoals() {
        return this.goals;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Player getPlayer(int number) {
        return this.players.get(number);
    }        
    
    public HashMap<Integer, Player> getPlayers() {
        return this.players;
    }
    
    public List<String> getPlayersNumberName() {
        List<String> numberNames = new ArrayList<>();
        
        Object[] numbers = this.players.keySet().toArray();
        
        for (int i = 0; i < this.players.size(); i++) {
            int number = Integer.parseInt(numbers[i].toString());            
            Player player = this.players.get(number);
            numberNames.add("#" + player.getNumber() + " " + player.getFirstName() + " " + player.getLastName());
        }
        
        return numberNames;
    }
}
