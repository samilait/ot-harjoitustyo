/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icehockeystats.domain;

import java.util.concurrent.TimeUnit;
/**
 *
 * @author Sami
 */
public class Clock {
    
    private int seconds;
    private int minutes;
    private int hours;

    public Clock() {
        this.seconds = 0;
        this.minutes = 0;
        this.hours = 0;
    }        
    
    public void tick() {
        this.seconds++;
        this.minutes += this.seconds / 60;
        this.hours += this.minutes / 60;
        this.seconds = this.seconds % 60;
        this.minutes = this.minutes % 60;
        this.hours = this.hours % 24;
    }
    
    public String show() {
        
        String digitMinutes = "" + this.minutes;
        String digitSeconds = "" + this.seconds;
        
        if(this.minutes < 10) {
            digitMinutes = "0" + this.minutes;
        }
        
        if(this.seconds < 10) {
            digitSeconds = "0" + this.seconds;
        }
        
        return "" + digitMinutes + ":" + digitSeconds;
    }
    
    public void reset() {
        this.seconds = 0;
        this.minutes = 0;
        this.hours = 0;        
    }
    
}
