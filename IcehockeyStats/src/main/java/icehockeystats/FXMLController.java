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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import icehockeystats.domain.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.TitledPane;
//import javafx.concurrent.Task;
//import javafx.concurrent.WorkerStateEvent;
//import javafx.event.EventHandler;

public class FXMLController implements Initializable {
    
    private Clock clock;
    private boolean clockRunning;
    private Match match;
//    static Thread thread = new Thread();
    
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
    @FXML
    private Label lbClock;
    @FXML
    private Button btnStartClock;
    @FXML
    private Button btnStopClock;
    @FXML
    private Label lbRosterAwayTeamName;
    @FXML
    private Label lbRosterHomeTeamName;
    @FXML
    private Label lbStatusAwayTeamName;
    @FXML
    private Label lbStatusHomeTeamName;
    @FXML
    private TitledPane tpPenaltiesAway;
    @FXML
    private TitledPane tpGoalsHome;
    @FXML
    private TitledPane tpPenaltiesHome;
    @FXML
    private TitledPane tpGoalsAway;
    @FXML
    private TitledPane tpGoaliesHome;
    @FXML
    private TitledPane tpGoaliesAway;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        
        // Initialise clock
//        this.thread = new Thread();
        this.clock = new Clock();
        this.clockRunning = true;
        
        // Home team players
        numberColumnHome.setCellValueFactory(new PropertyValueFactory<Player, Integer>("number"));
        nameColumnHome.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        positionColumnHome.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
        lineColumnHome.setCellValueFactory(new PropertyValueFactory<Player, Integer>("line"));
        
        DataReader dr = new DataReader("home.txt");
        
        // Set team names
        String homeTeamName = "";
        try {
            homeTeamName = dr.loadTeamName();
            lbRosterHomeTeamName.setText(homeTeamName);
            lbStatusHomeTeamName.setText(homeTeamName);
            tpGoalsHome.setText("Maalit (" + homeTeamName + ")");
            tpPenaltiesHome.setText("Rangaistukset (" + homeTeamName + ")");
            tpGoaliesHome.setText("Maalivahdit (" + homeTeamName + ")");
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Team homeTeam = new Team(homeTeamName, true);
        
        ObservableList<Player> playerList;
        try {
            playerList = dr.loadPlayers();
            
            for(int i = 0; i < playerList.size(); i++) {
                Player player = playerList.get(i);
                homeTeam.addPlayer(player.getNumber(), player);
            }

            tableHome.setItems(playerList);
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
                        

        // Away team players
        numberColumnAway.setCellValueFactory(new PropertyValueFactory<Player, Integer>("number"));
        nameColumnAway.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        positionColumnAway.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
        lineColumnAway.setCellValueFactory(new PropertyValueFactory<Player, Integer>("line"));
        
        dr = new DataReader("away.txt");

        // Set team names
        String awayTeamName = "";
        
        try {
            awayTeamName = dr.loadTeamName();
            lbRosterAwayTeamName.setText(awayTeamName);
            lbStatusAwayTeamName.setText(awayTeamName);
            tpGoalsAway.setText("Maalit (" + awayTeamName + ")");
            tpPenaltiesAway.setText("Rangaistukset (" + awayTeamName + ")");
            tpGoaliesAway.setText("Maalivahdit (" + awayTeamName + ")");

        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Team awayTeam = new Team(awayTeamName, false);
        
        try {
            playerList = dr.loadPlayers();
            
            for(int i = 0; i < playerList.size(); i++) {
                Player player = playerList.get(i);
                awayTeam.addPlayer(player.getNumber(), player);
            }

            tableAway.setItems(playerList);
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.match = new Match(homeTeam, awayTeam);

    }    

    @FXML
    private void startClock(ActionEvent event) {
        
        // Start clock        
        this.clockRunning = true;
        
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        clockTick();
                    }
                };

                while (clockRunning) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    if (clockRunning) {
                        Platform.runLater(updater);
                    }
                }
            }

        });
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();      
        
    }

    @FXML
    private void stopClock(ActionEvent event) {
        this.clockRunning = false;
    }
    
    private void clockTick() {
        // Clock tick one second and output time to clock display
        clock.tick();
        lbClock.setText(clock.show());
    }
}
