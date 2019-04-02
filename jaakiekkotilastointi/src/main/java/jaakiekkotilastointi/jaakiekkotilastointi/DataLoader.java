/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaakiekkotilastointi.jaakiekkotilastointi;

import java.io.*;
/**
 *
 * @author Sami
 */
public class DataLoader {
    
    public void LoadPlayers(String fileName) throws IOException {
        
        File file = new File(fileName);
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        String row;
        while ((row = br.readLine()) != null) {
            String[] splitRow = row.split(",");
            String a = splitRow[0];
        }
        
    }
    
}
