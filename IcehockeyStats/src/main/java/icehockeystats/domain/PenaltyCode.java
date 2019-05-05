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
public class PenaltyCode {
    
    private String code;
    private String description;

    public PenaltyCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.description + " (" + this.code + ")";
    }
    
    
    
}
