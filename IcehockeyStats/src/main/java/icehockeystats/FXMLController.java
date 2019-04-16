package icehockeystats;

import icehockeystats.domain.Player;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLController implements Initializable {
    
    @FXML
    private TableView<Player> tableHome;
    @FXML
    private TableColumn<Player, Integer> numberColumnHome;
    @FXML
    private TableColumn<Player, String> nameColumnHome;
    @FXML
    private TableColumn<Player, String> positionColumnHome;
    @FXML
    private TableColumn<Player, Integer> lineColumnHome;
    @FXML
    private TableView<Player> tableAway;
    @FXML
    private TableColumn<Player, Integer> numberColumnAway;
    @FXML
    private TableColumn<Player, String> nameColumnAway;
    @FXML
    private TableColumn<Player, String> positionColumnAway;
    @FXML
    private TableColumn<Player, Integer> lineColumnAway;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        // Home team players
        numberColumnHome.setCellValueFactory(new PropertyValueFactory<Player, Integer>("number"));
        nameColumnHome.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        positionColumnHome.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
        lineColumnHome.setCellValueFactory(new PropertyValueFactory<Player, Integer>("line"));
        
        DataReader dr = new DataReader("home.txt");
        
        try {
            tableHome.setItems(dr.loadPlayers());
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Away team players
        numberColumnAway.setCellValueFactory(new PropertyValueFactory<Player, Integer>("number"));
        nameColumnAway.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        positionColumnAway.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
        lineColumnAway.setCellValueFactory(new PropertyValueFactory<Player, Integer>("line"));
        
        dr = new DataReader("away.txt");
        
        try {
            tableAway.setItems(dr.loadPlayers());
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
}
