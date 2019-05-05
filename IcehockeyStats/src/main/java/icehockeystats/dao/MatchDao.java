/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icehockeystats.dao;

import icehockeystats.domain.Goal;
import icehockeystats.domain.Match;
import icehockeystats.domain.Penalty;
import icehockeystats.domain.Player;
import icehockeystats.domain.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Sami
 */
public class MatchDao {
    
    private Database database;

    public MatchDao(Database database) {
        this.database = database;
    }

    public void saveMatch(Match match) throws SQLException {
        
        Connection conn = database.getConnection();
        
        // Save match data
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Match"
                    + " (matchId, homeTeam, awayTeam)"
                    + " VALUES (?, ?, ?)");
            stmt.setInt(1, match.getId());
            stmt.setString(2, match.getHomeTeam().getName());
            stmt.setString(3, match.getAwayTeam().getName());

            stmt.executeUpdate();
            stmt.close();
            
            // Save home team and players
            if (!teamDataExists(conn, match.getHomeTeam())) {
                saveTeam(conn, match.getHomeTeam());
            }
            
            savePlayers(conn, match.getHomeTeam());
            
            // Save away team and player
            if (!teamDataExists(conn, match.getAwayTeam())) {
                saveTeam(conn, match.getAwayTeam());
            }
            savePlayers(conn, match.getAwayTeam());

            // Save match goals
            saveGoals(conn, match);
            
            // Save penalties
            savePenalties(conn, match);
            
            conn.close();
    }
    
    public void savePenalties(Connection conn, Match match) throws SQLException {
        
        Team team;
        // Save home team penalties
        team = match.getHomeTeam();
        List<Penalty> penalties = team.getPenalties();
        
        for (int i = 0; i < penalties.size(); i++) {        
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Penalties"
                        + " (matchId, team, player, min, code)"
                        + " VALUES (?, ?, ?, ?, ?)");
                stmt.setInt(1, match.getId());
                stmt.setString(1, team.getName());
                stmt.setInt(1, penalties.get(i).getPlayer().getNumber());
                stmt.setString(1, penalties.get(i).getMin());
                stmt.setString(1, penalties.get(i).getCode());

                stmt.executeUpdate();
                stmt.close();        
        }

        // Save away team penalties
        team = match.getAwayTeam();
        penalties = team.getPenalties();
        
        for (int i = 0; i < penalties.size(); i++) {        
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Penalties"
                        + " (matchId, team, player, min, code)"
                        + " VALUES (?, ?, ?, ?, ?)");
                stmt.setInt(1, match.getId());
                stmt.setString(1, team.getName());
                stmt.setInt(1, penalties.get(i).getPlayer().getNumber());
                stmt.setString(1, penalties.get(i).getMin());
                stmt.setString(1, penalties.get(i).getCode());

                stmt.executeUpdate();
                stmt.close();        
        }
        
    }

    
    public void saveGoals(Connection conn, Match match) throws SQLException {
        
        Team team;
        // Save home team goals
        team = match.getHomeTeam();
        List<Goal> goals = team.getGoals();
        
        for (int i = 0; i < goals.size(); i++) {        
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Goals"
                        + " (matchId, team, scorer, assistant1, assistant2)"
                        + " VALUES (?, ?, ?, ?, ?)");
                stmt.setInt(1, match.getId());
                stmt.setString(1, team.getName());
                stmt.setInt(1, goals.get(i).getScorer());
                stmt.setInt(1, goals.get(i).getAssistant1());
                stmt.setInt(1, goals.get(i).getAssistant2());

                stmt.executeUpdate();
                stmt.close();        
        }

        // Save home team goals
        team = match.getAwayTeam();
        goals = team.getGoals();
        
        for (int i = 0; i < goals.size(); i++) {        
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Goals"
                        + " (matchId, team, scorer, assistant1, assistant2)"
                        + " VALUES (?, ?, ?, ?, ?)");
                stmt.setInt(1, match.getId());
                stmt.setString(1, team.getName());
                stmt.setInt(1, goals.get(i).getScorer());
                stmt.setInt(1, goals.get(i).getAssistant1());
                stmt.setInt(1, goals.get(i).getAssistant2());

                stmt.executeUpdate();
                stmt.close();        
        }
        
    }
    
    public boolean teamDataExists(Connection conn, Team team) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) AS Total FROM Team WHERE name = '" + team.getName() + "'");        
        ResultSet rs = stmt.executeQuery();
        
        if (rs.getInt("Total") == 0) {
            return false;
        } else {
            return true;
        }                

    }
    
    public void saveTeam(Connection conn, Team team) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Team"
                    + " (name)"
                    + " VALUES (?)");
            stmt.setString(1, team.getName());

            stmt.executeUpdate();
            stmt.close();        
    }
    
    public void savePlayers(Connection conn, Team team) throws SQLException {
        Object[] keySet = team.getPlayers().keySet().toArray();
            
        for (int i = 0; i < keySet.length; i++) {
            Player player = team.getPlayers().get(keySet[i]);
            if (!playerDataExists(conn, player)) {
                savePlayer(conn, player);
            }
        }

    }
    
    public boolean playerDataExists(Connection conn, Player player) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) AS Total FROM Player WHERE number = " + player.getNumber()
                + " AND firstName = '" + player.getFirstName() + "'"
                + " AND lastName = '" + player.getLastName() + "'");        
        ResultSet rs = stmt.executeQuery();
        
        if (rs.getInt("Total") == 0) {
            return false;
        } else {
            return true;
        }                        
    }
    
    public void savePlayer(Connection conn, Player player) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Player"
                    + " (number, firstName, lastName, position, line)"
                    + " VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, player.getNumber());
            stmt.setString(2, player.getFirstName());
            stmt.setString(3, player.getLastName());
            stmt.setString(4, player.getPosition());
            stmt.setInt(5, player.getLine());

            stmt.executeUpdate();
            stmt.close();        
        
    }
    

}
