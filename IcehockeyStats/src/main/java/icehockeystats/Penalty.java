/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icehockeystats;

/**
 *
 * @author Sami
 */
public class Penalty {

    private int code;
    private String penaltyType;
    private int minutes1;
    private int minutes2;
    private int player;

    public Penalty(int code, String penaltyType, int minutes1, int minutes2, int player) {
        this.code = code;
        this.penaltyType = penaltyType;
        this.minutes1 = minutes1;
        this.minutes2 = minutes2;
        this.player = player;
    }
    
}
