/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icehockeystats;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Sami
 */
public class DataReader {
    private String fileName;

    public DataReader(String fileName) {
        this.fileName = fileName;
    }
    
    public ObservableList<Player> loadPlayers() throws FileNotFoundException, IOException {
        
        ObservableList<Player> players = FXCollections.observableArrayList();
        
        BufferedReader br = new BufferedReader(new FileReader(this.fileName));
        
        String line;
        
        while((line = br.readLine()) != null) {
            String[] values = line.split(",");            
            Player player = new Player(values[0], values[1], Integer.parseInt(values[2]), values[3], Integer.parseInt(values[4]));
            players.add(player);
        }
        
        return players;
    }
    
}
