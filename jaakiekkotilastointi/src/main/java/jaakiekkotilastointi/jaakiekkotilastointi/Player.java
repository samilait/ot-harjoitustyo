/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaakiekkotilastointi.jaakiekkotilastointi;

import java.util.ArrayList;

/**
 *
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
    
    
    
}
