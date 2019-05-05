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
    private List<Goal> goals;
    private List<Penalty> penalties;
    private int totalGoals;

    public Team(String name, boolean home) {
        this.name = name;
        this.home = home;
        this.totalGoals = 0;
        this.goals = new ArrayList<>();
        this.penalties = new ArrayList<>();
        this.players = new HashMap<>();        
    }
    
    public void addPlayer(Integer number, Player player) {
        this.players.put(number, player);
    }
    
    public void addGoal(String time, Player scorer, Player assistant1, Player assistant2, String type) {
        this.totalGoals++;
        this.goals.add(new Goal(this.totalGoals, time, scorer, assistant1, assistant2, type));
    }

    public void addPenalty(Player player, String code, String description, String min, String startTime, String endTime) {
        this.penalties.add(new Penalty(player, code, description, min, startTime, endTime));
    }
    
    public List<Goal> getGoals() {
        return this.goals;
    }
    
    public List<Penalty> getPenalties() {
        return this.penalties;
    }
        
    public int getTotalGoals() {
        return this.totalGoals;
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
