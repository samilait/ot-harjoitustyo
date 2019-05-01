package icehockeystats;

import icehockeystats.dao.DataReader;
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
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.Pane;
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
    @FXML
    private Label lbScore;
    @FXML
    private Label lbPeriod;
    @FXML
    private Button btnStartMatch;
    @FXML
    private Button btnStartPeriod;
    @FXML
    private TableView<Goal> tableScoreHome;
    @FXML
    private TableColumn<Goal, Integer> goalColumnHome;
    @FXML
    private TableColumn<Goal, String> goalTimeColumnHome;
    @FXML
    private TableColumn<Goal, Integer> scorerColumnHome;
    @FXML
    private TableColumn<Goal, Integer> assistant1ColumnHome;
    @FXML
    private TableColumn<Goal, Integer> assistant2ColumnHome;
    @FXML
    private TableColumn<Goal, String> goalTypeColumnHome;
    @FXML
    private Button btnAddGoalHome;
    @FXML
    private Tab tabStatistics;
    @FXML
    private Tab tabGoal;
    @FXML
    private TabPane tabMain;
    @FXML
    private Label lbGoalTeam;
    @FXML
    private TextField txtGoalAssistant1;
    @FXML
    private TextField txtGoalAssistant2;
    @FXML
    private TextField txtGoalType;
    @FXML
    private TextField txtGoalScorer;
    @FXML
    private TextField txtGoalTime;
    @FXML
    private ComboBox<String> cmbGoalScorer;
    @FXML
    private ComboBox<String> cmbGoalAssistant1;
    @FXML
    private ComboBox<String> cmbGoalAssistant2;
    @FXML
    private Button btnGoalSave;
    @FXML
    private Button btnGoalCancel;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        // Listeners for Goal input text fields
        this.txtGoalScorer.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectGoalScorer(newValue);
            }            
        });
        
        this.txtGoalAssistant1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectGoalAssistant1(newValue);
            }            
        });
        
        this.txtGoalAssistant2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectGoalAssistant2(newValue);
            }            
        });


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
            
            for (int i = 0; i < playerList.size(); i++) {
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
            
            for (int i = 0; i < playerList.size(); i++) {
                Player player = playerList.get(i);
                awayTeam.addPlayer(player.getNumber(), player);
            }

            tableAway.setItems(playerList);
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.match = new Match(homeTeam, awayTeam);  // Initialise match
        lbScore.setText(this.match.toString());      // Set initial score
        lbPeriod.setText("Erä: " + Integer.toString(match.getPeriod()));
        
        // Buttons: active / inactive
        btnStartPeriod.setDisable(true);
        btnStartClock.setDisable(true);
        btnStopClock.setDisable(true);
        
        // Home team goals
        this.goalColumnHome.setCellValueFactory(new PropertyValueFactory<Goal, Integer>("number"));
        this.goalTimeColumnHome.setCellValueFactory(new PropertyValueFactory<Goal, String>("time"));
        this.scorerColumnHome.setCellValueFactory(new PropertyValueFactory<Goal, Integer>("scorer"));
        this.assistant1ColumnHome.setCellValueFactory(new PropertyValueFactory<Goal, Integer>("assistant1"));
        this.assistant2ColumnHome.setCellValueFactory(new PropertyValueFactory<Goal, Integer>("assistant2"));
        this.goalTypeColumnHome.setCellValueFactory(new PropertyValueFactory<Goal, String>("type"));

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

    @FXML
    private void startMatch(ActionEvent event) {
        this.match.setStartTime(new Date());
        btnStartPeriod.setDisable(false);
    }

    @FXML
    private void startPeriod(ActionEvent event) {
        match.addPeriod();
        lbPeriod.setText("Erä: " + Integer.toString(match.getPeriod()));
        btnStartClock.setDisable(false);
        btnStopClock.setDisable(false);

    }

    @FXML
    private void addGoalHome(ActionEvent event) {
                        
        this.tabMain.getSelectionModel().select(tabGoal);
        
        // Set team name
        this.lbGoalTeam.setText(this.match.getHomeTeam().getName());
        
        // Set goal time
        txtGoalTime.setText(lbClock.getText());
        
        List<String> playersNumberName = this.match.getHomeTeam().getPlayersNumberName();
        this.cmbGoalScorer.setItems(FXCollections.observableArrayList(playersNumberName));
        this.cmbGoalAssistant1.setItems(FXCollections.observableArrayList(playersNumberName));
        this.cmbGoalAssistant2.setItems(FXCollections.observableArrayList(playersNumberName));

    }
    
    private void selectGoalScorer(String number) {
        Player player = this.match.getHomeTeam().getPlayer(Integer.parseInt(number));        
        this.cmbGoalScorer.getSelectionModel().select(player.toString());
    }
    
    private void selectGoalAssistant1(String number) {
        Player player = this.match.getHomeTeam().getPlayer(Integer.parseInt(number));        
        this.cmbGoalAssistant1.getSelectionModel().select(player.toString());
    }

    private void selectGoalAssistant2(String number) {
        Player player = this.match.getHomeTeam().getPlayer(Integer.parseInt(number));        
        this.cmbGoalAssistant2.getSelectionModel().select(player.toString());
    }

    @FXML
    private void setGoalScorerNumber(ActionEvent event) {
        String number = this.cmbGoalScorer.getValue().substring(1, 3).trim();
        this.txtGoalScorer.setText(number);
    }

    @FXML
    private void setGoalAssstant1Number(ActionEvent event) {
        String number = this.cmbGoalAssistant1.getValue().substring(1, 3).trim();
        this.txtGoalAssistant1.setText(number);
    }

    @FXML
    private void setGoalAssstant2Number(ActionEvent event) {
        String number = this.cmbGoalAssistant2.getValue().substring(1, 3).trim();
        this.txtGoalAssistant2.setText(number);
    }

    @FXML
    private void saveGoal(ActionEvent event) {
        
        String time = this.txtGoalTime.getText();
        int scorerNumber = Integer.parseInt(this.txtGoalScorer.getText());
        int assistant1Number = Integer.parseInt(this.txtGoalAssistant1.getText());
        int assistant2Number = Integer.parseInt(this.txtGoalAssistant2.getText());
        String type = this.txtGoalType.getText();
        
        Player scorer = this.match.getHomeTeam().getPlayer(scorerNumber);
        Player assistant1 = this.match.getHomeTeam().getPlayer(assistant1Number);
        Player assistant2 = this.match.getHomeTeam().getPlayer(assistant2Number);
        
        this.match.getHomeTeam().addGoal(time, scorer, assistant1, assistant2, type);
        
        ObservableList<Goal> goals = FXCollections.observableList(this.match.getHomeTeam().getGoals());
        
        this.tableScoreHome.setItems(goals);
         
    }

    @FXML
    private void cancelGoal(ActionEvent event) {
    }
}
