/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icehockeystats.domain;

/**
 *
 * @author Sami
 */
public class Penalty {

    private String code;
    private String description;
    private String min;
    private Player player;
    private String startTime;
    private String endTime;

    public Penalty(Player player, String code, String description, String min, String startTime, String endTime) {
        this.code = code;
        this.description = description;
        this.min = min;
        this.player = player;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getMin() {
        return min;
    }

    public Player getPlayer() {
        return player;
    }
    
    public int getNumber() {
        return this.player.getNumber();
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    
    
    
    
}
