/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icehockeystats.dao;

import icehockeystats.domain.PenaltyCode;
import java.util.*;
import java.sql.*;

/**
 *
 * @author Sami
 */
public class PenaltyDao {
    
    private Database database;

    public PenaltyDao(Database database) {
        this.database = database;
    }
    
    public String findOne(String code) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PenaltyCodes WHERE code = '" + code + "'");
        
        ResultSet rs = stmt.executeQuery();
        
        return rs.getString("description");
                
    }
    
    public List<PenaltyCode> findAll() throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PenaltyCodes");
        
        ResultSet rs = stmt.executeQuery();
        
        List<PenaltyCode> penaltyCodes = new ArrayList<>();
        
        while (rs.next()) {
            PenaltyCode p = new PenaltyCode(rs.getString("code"), rs.getString("description"));            
            penaltyCodes.add(p);
        }
        
        return penaltyCodes;
        
    }
    
    public void savePenaltyCodes(ArrayList<String> penaltyCodes) throws SQLException {

        Connection conn = database.getConnection();
        
        for (int i = 0; i < penaltyCodes.size(); i++) {
            String[] values = penaltyCodes.get(i).split(",");
            String code = values[0];
            String description = values[1];
            
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO PenaltyCodes"
                    + " (code, description)"
                    + " VALUES (?, ?)");
            stmt.setString(1, code);
            stmt.setString(2, description);

            stmt.executeUpdate();
            stmt.close();
            
        }
        
        conn.close();
        
    }
//    public HashMap<String, String> findCodesAndDescriptions() throws SQLException {
//        Connection conn = database.getConnection();
//    }
}
