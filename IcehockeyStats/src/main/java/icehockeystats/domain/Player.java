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

/**
 * Luokka sisältää pelaajan tiedot ja toiminnallisuuden pelaajan pelitapahtumien tilastointiin
 * @author Sami
 */
public class Player {

    private String firstName;
    private String lastName;
    private int number;
    private String position;
    private int line;
    private long goals;
    private long passes;
    private long saves;
    private ArrayList<Penalty> penalties;

    public Player(String firstName, String lastName, int number, String position, int line) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.position = position;
        this.line = line;
        this.goals = 0;
        this.passes = 0;
        this.saves = 0;
        this.penalties = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }

    public int getLine() {
        return line;
    }

    public long getGoals() {
        return goals;
    }

    public long getPasses() {
        return passes;
    }

    public long getSaves() {
        return saves;
    }

    /**
     * Get player penalties
     * @return list of penalties
     */
    public ArrayList<Penalty> getPenalties() {
        return penalties;
    }
    
    /**
     * Get full player name
     * @return first name + last name
     */
    public String getName() {
        return firstName + " " + lastName;
    }
    
    public void addGoal() {
        this.goals++;
    }
    
    public void addPass() {
        this.passes++; 
    }
    
    public void addSave() {
        this.saves++;
    }
    
    public void addPenalty(Penalty penalty) {
        this.penalties.add(penalty);
    }
    
    /**
     * Get total points
     * @return total points: goals + passes
     */
    public long getTotalPoints() {
        return this.goals + this.passes;
    }

    @Override
    public String toString() {
        return "#" + this.number + " " + this.firstName + " " + this.lastName;
    }
    
    
    
}
