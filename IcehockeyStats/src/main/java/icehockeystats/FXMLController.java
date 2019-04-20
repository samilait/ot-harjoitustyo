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
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
//import javafx.concurrent.Task;
//import javafx.concurrent.WorkerStateEvent;
//import javafx.event.EventHandler;

public class FXMLController implements Initializable {
    
    private Clock clock;
    private boolean clockRunning;
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

    @FXML
    private void startClock(ActionEvent event) {
        
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
                    Platform.runLater(updater);
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
        clock.tick();
        lbClock.setText(clock.show());
    }
}
