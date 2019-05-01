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
public class Goal {
    
    private int number;
    private String time;
    private Player scorer;
    private Player assistant1;
    private Player assistant2;
    private String type;

    public Goal(int number, String time, Player scorer, Player assistant1, Player assistant2, String type) {
        this.number = number;
        this.time = time;
        this.scorer = scorer;
        this.assistant1 = assistant1;
        this.assistant2 = assistant2;
        this.type = type;
    }

    public int getNumber() {
        return number;
    }
    
    public String getTime() {
        return this.time;
    }

    public Integer getScorer() {
        return scorer.getNumber();
    }

    public Integer getAssistant1() {
        return assistant1.getNumber();
    }

    public Integer getAssistant2() {
        return assistant2.getNumber();
    }

    public String getType() {
        return type;
    }
    
    
    
}
