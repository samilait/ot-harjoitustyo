/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icehockeystats.dao;

import icehockeystats.domain.Player;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Data reader for player lists
 * @author Sami
 */
public class DataReader {
    private String fileName;

    /**
     * Data reader constructor
     * @param fileName team name + playerlist file name
     */
    public DataReader(String fileName) {
        this.fileName = fileName;
    }
    
    /**
     * Load playerlist
     * @return Return list of players
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public ObservableList<Player> loadPlayers() throws FileNotFoundException, IOException {
        
        ObservableList<Player> players = FXCollections.observableArrayList();
        
        BufferedReader br = new BufferedReader(new FileReader(this.fileName));
        
        String line;
        String header = br.readLine();
        
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");            
            Player player = new Player(values[0], values[1], Integer.parseInt(values[2]), values[3], Integer.parseInt(values[4]));
            players.add(player);
        }
        
        return players;
    }
    
    /**
     * Load team name
     * @return Return team name
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public String loadTeamName() throws FileNotFoundException, IOException {

        BufferedReader br = new BufferedReader(new FileReader(this.fileName));
        
        return br.readLine();
        
    }
    
}
