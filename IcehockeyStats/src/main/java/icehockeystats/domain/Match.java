/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icehockeystats.domain;

import java.util.Date;

/**
 *
 * @author Sami
 */
public class Match {
    
    private int id;
    private Team homeTeam;
    private Team awayTeam;
    private Date startTime;
    private Date endTime;
    private int audience;
    private int period;
    private int periodLength;

    public Match(int id, Team homeTeam, Team awayTeam) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.period = 0;
        this.periodLength = 15;
    }
        
    public int getId() {
        return this.id;
    }
    
    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getAudience() {
        return audience;
    }

    public void setAudience(int audience) {
        this.audience = audience;
    }

    public void addPeriod() {
        this.period++;
    }
    
    public int getPeriod() {
        return this.period;
    }
    
    @Override
    public String toString() {
        return this.homeTeam.getTotalGoals() + " - " + this.awayTeam.getTotalGoals();
    }
    
    
    
    
    
    
}
