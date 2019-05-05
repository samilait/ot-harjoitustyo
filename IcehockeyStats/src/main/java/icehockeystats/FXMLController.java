package icehockeystats;

import icehockeystats.dao.DataReader;
import icehockeystats.dao.Database;
import icehockeystats.dao.MatchDao;
import icehockeystats.dao.PenaltyDao;
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
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
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
    
    private Database database;
    private HashMap<String, String> penaltyCodeMap;
    private Clock clock;
    private boolean clockRunning;
    private Match match;
    private boolean isHomeSelected = true;
    
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
    @FXML
    private Button btnAddPenaltyHome;
    @FXML
    private Tab tabPenalty;
    @FXML
    private ComboBox<String> cmbPenaltyReceiver;
    @FXML
    private ComboBox<String> cmbPenaltyDescription;
    @FXML
    private TextField txtPenaltyDescription;
    @FXML
    private TextField txtPenaltyReceiver;
    @FXML
    private Label lbPenaltyTeam;
    @FXML
    private TextField txtPenaltyMin;
    @FXML
    private TextField txtPenaltyStart;
    @FXML
    private TextField txtPenaltyEnd;
    @FXML
    private Button btnSavePenalty;
    @FXML
    private Button btnCancelPenalty;
    @FXML
    private TableView<Penalty> tablePenaltyHome;
    @FXML
    private TableColumn<Penalty, Integer> penaltyRecieverColumnHome;
    @FXML
    private TableColumn<Penalty, String> penaltyMinColumnHome;
    @FXML
    private TableColumn<Penalty, String> penaltyCodeColumnHome;
    @FXML
    private TableColumn<Penalty, String> penaltyStartColumnHome;
    @FXML
    private TableColumn<Penalty, String> penaltyEndColumnHome;
    @FXML
    private TableView<Penalty> tablePenaltyAway;
    @FXML
    private TableColumn<Penalty, Integer> penaltyRecieverColumnAway;
    @FXML
    private TableColumn<Penalty, String> penaltyMinColumnAway;
    @FXML
    private TableColumn<Penalty, String> penaltyCodeColumnAway;
    @FXML
    private TableColumn<Penalty, String> penaltyStartColumnAway;
    @FXML
    private TableColumn<Penalty, String> penaltyEndColumnAway;
    @FXML
    private Button btnAddPenaltyAway;
    @FXML
    private TableView<Goal> tableScoreAway;
    @FXML
    private TableColumn<Goal, Integer> goalColumnAway;
    @FXML
    private TableColumn<Goal, String> goalTimeColumnAway;
    @FXML
    private TableColumn<Goal, Integer> scorerColumnAway;
    @FXML
    private TableColumn<Goal, Integer> assistant1ColumnAway;
    @FXML
    private TableColumn<Goal, Integer> assistant2ColumnAway;
    @FXML
    private TableColumn<Goal, String> goalTypeColumnAway;
    @FXML
    private Button btnAddGoalAway;
    @FXML
    private Button btnEndMatch;
    @FXML
    private TextField txtPeriodLength;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        File dbName = new File("icehockeystat.db");        
        try {
            this.database = new Database("jdbc:sqlite:" + dbName.getAbsolutePath());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
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
        
        // Listeners for Penalty input text fields
        this.txtPenaltyReceiver.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectPenaltyReceiver(newValue);
            }            
        });
        
        this.txtPenaltyMin.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                setPenaltyEndTime();
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
        
        Random r = new Random();
        int id = r.nextInt(10000);
        
        this.match = new Match(id, homeTeam, awayTeam);  // Initialise match
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
        
        // Home team penalties
        this.penaltyRecieverColumnHome.setCellValueFactory(new PropertyValueFactory<Penalty, Integer>("number"));
        this.penaltyMinColumnHome.setCellValueFactory(new PropertyValueFactory<Penalty, String>("min"));
        this.penaltyCodeColumnHome.setCellValueFactory(new PropertyValueFactory<Penalty, String>("code"));
        this.penaltyStartColumnHome.setCellValueFactory(new PropertyValueFactory<Penalty, String>("startTime"));
        this.penaltyEndColumnHome.setCellValueFactory(new PropertyValueFactory<Penalty, String>("endTime"));
        
        // Away team goals
        this.goalColumnAway.setCellValueFactory(new PropertyValueFactory<Goal, Integer>("number"));
        this.goalTimeColumnAway.setCellValueFactory(new PropertyValueFactory<Goal, String>("time"));
        this.scorerColumnAway.setCellValueFactory(new PropertyValueFactory<Goal, Integer>("scorer"));
        this.assistant1ColumnAway.setCellValueFactory(new PropertyValueFactory<Goal, Integer>("assistant1"));
        this.assistant2ColumnAway.setCellValueFactory(new PropertyValueFactory<Goal, Integer>("assistant2"));
        this.goalTypeColumnAway.setCellValueFactory(new PropertyValueFactory<Goal, String>("type"));
        
        // Away team penalties
        this.penaltyRecieverColumnAway.setCellValueFactory(new PropertyValueFactory<Penalty, Integer>("number"));
        this.penaltyMinColumnAway.setCellValueFactory(new PropertyValueFactory<Penalty, String>("min"));
        this.penaltyCodeColumnAway.setCellValueFactory(new PropertyValueFactory<Penalty, String>("code"));
        this.penaltyStartColumnAway.setCellValueFactory(new PropertyValueFactory<Penalty, String>("startTime"));
        this.penaltyEndColumnAway.setCellValueFactory(new PropertyValueFactory<Penalty, String>("endTime"));
        
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
        
        String periodLength = this.txtPeriodLength.getText();
        String currentTime = lbClock.getText().substring(0, 2);
        
        if (currentTime.equals(periodLength)) {
            this.clockRunning = false;
        }
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
        
        this.lbClock.setText("00:00");
        clock.reset();

    }

    @FXML
    private void addGoalHome(ActionEvent event) {
                        
        this.tabMain.getSelectionModel().select(tabGoal);
        
        this.isHomeSelected = true;
        
        // Set team name
        this.lbGoalTeam.setText(this.match.getHomeTeam().getName());
        
        // Set goal time
        int totalMinutes = Integer.parseInt(this.txtPeriodLength.getText()) * (this.match.getPeriod() - 1);
        totalMinutes += Integer.parseInt(this.lbClock.getText().substring(0, 2));
        
        String totalMinutesString = "" + totalMinutes;
        if (totalMinutes < 10) {
            totalMinutesString = "0" + totalMinutes;
        }
        
        String seconds = totalMinutesString + lbClock.getText().substring(2);
        
        txtGoalTime.setText(totalMinutesString + seconds);
        
        List<String> playersNumberName = this.match.getHomeTeam().getPlayersNumberName();
        this.cmbGoalScorer.setItems(FXCollections.observableArrayList(playersNumberName));
        this.cmbGoalAssistant1.setItems(FXCollections.observableArrayList(playersNumberName));
        this.cmbGoalAssistant2.setItems(FXCollections.observableArrayList(playersNumberName));

    }
    
    private void selectGoalScorer(String number) {
        
        Team team;
        if (this.isHomeSelected) {
            team = this.match.getHomeTeam();
        } else {
            team = this.match.getAwayTeam();
        }
        
        Player player = team.getPlayer(Integer.parseInt(number));        
        this.cmbGoalScorer.getSelectionModel().select(player.toString());
    }
    
    private void selectGoalAssistant1(String number) {
        
        Team team;
        if (this.isHomeSelected) {
            team = this.match.getHomeTeam();
        } else {
            team = this.match.getAwayTeam();
        }

        Player player = team.getPlayer(Integer.parseInt(number));        
        this.cmbGoalAssistant1.getSelectionModel().select(player.toString());
    }

    private void selectGoalAssistant2(String number) {

        Team team;
        if (this.isHomeSelected) {
            team = this.match.getHomeTeam();
        } else {
            team = this.match.getAwayTeam();
        }
        
        Player player = team.getPlayer(Integer.parseInt(number));        
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
        
        Team team;
        if (this.isHomeSelected) {
            team = this.match.getHomeTeam();
        } else {
            team = this.match.getAwayTeam();
        }
        
        Player scorer = team.getPlayer(scorerNumber);
        Player assistant1 = team.getPlayer(assistant1Number);
        Player assistant2 = team.getPlayer(assistant2Number);
        
        team.addGoal(time, scorer, assistant1, assistant2, type);
        
        ObservableList<Goal> goals = FXCollections.observableList(team.getGoals());
        
        if (this.isHomeSelected) {
            this.tableScoreHome.setItems(goals);
        } else {
            this.tableScoreAway.setItems(goals);
        }
        
        clearGoal();
        
        this.lbScore.setText(this.match.toString());
        
        this.tabMain.getSelectionModel().select(tabStatistics);
         
    }

    @FXML
    private void cancelGoal(ActionEvent event) {
        
        clearGoal();
        
        this.tabMain.getSelectionModel().select(tabStatistics);
        
    }
    
    public void clearGoal() {

        this.lbGoalTeam.setText("");
        this.txtGoalAssistant1.setText("");
        this.txtGoalAssistant2.setText("");
        this.txtGoalScorer.setText("");
        this.txtGoalTime.setText("");
        this.txtGoalType.setText("");
        
        this.cmbGoalScorer.getItems().removeAll(this.cmbGoalScorer.getItems());
        this.cmbGoalAssistant1.getItems().removeAll(this.cmbGoalAssistant1.getItems());
        this.cmbGoalAssistant2.getItems().removeAll(this.cmbGoalAssistant2.getItems());
        
    }
    
    private void selectPenaltyReceiver(String number) {
        
        Team team;
        if (this.isHomeSelected) {
            team = this.match.getHomeTeam();
        } else {
            team = this.match.getAwayTeam();
        }

        Player player = team.getPlayer(Integer.parseInt(number));        
        this.cmbPenaltyReceiver.getSelectionModel().select(player.toString());
    }
    
    private void setPenaltyEndTime() {
        int penaltyMin = Integer.parseInt(this.txtPenaltyMin.getText());
        int startMin = Integer.parseInt(this.lbClock.getText().substring(0, 2));
        int endMin = startMin + penaltyMin;
        
        String endTime = "";
        if (endMin < 10) {
            endTime = "0" + endMin;
        }
        
        String endSec = this.lbClock.getText().substring(2);
        this.txtPenaltyEnd.setText(endTime + endSec);
        
    }

    @FXML
    private void addPenaltyHome(ActionEvent event) throws SQLException {

        this.tabMain.getSelectionModel().select(tabPenalty);
        
        this.isHomeSelected = true;
        
        // Set team name
        this.lbPenaltyTeam.setText(this.match.getHomeTeam().getName());
        
        int totalMinutes = Integer.parseInt(this.txtPeriodLength.getText()) * (this.match.getPeriod() - 1);
        totalMinutes += Integer.parseInt(this.lbClock.getText().substring(0, 2));
        
        String totalMinutesString = "" + totalMinutes;
        if (totalMinutes < 10) {
            totalMinutesString = "0" + totalMinutes;
        }
        
        String seconds = totalMinutesString + lbClock.getText().substring(2);
        
        this.txtPenaltyStart.setText(totalMinutesString + seconds);
        
        List<String> playersNumberName = this.match.getHomeTeam().getPlayersNumberName();
        this.cmbPenaltyReceiver.setItems(FXCollections.observableArrayList(playersNumberName));
        
        PenaltyDao penaltyDao = new PenaltyDao(database);
        List<PenaltyCode> penaltyCodes = penaltyDao.findAll();
        
        this.penaltyCodeMap = new HashMap<>();
        List<String> penaltyCodeNames = new ArrayList<>();
        
        for (int i = 0; i < penaltyCodes.size(); i++) {
            penaltyCodeMap.put(penaltyCodes.get(i).toString(), penaltyCodes.get(i).getCode());
            penaltyCodeNames.add(penaltyCodes.get(i).toString());
        }
        
        this.cmbPenaltyDescription.setItems(FXCollections.observableArrayList(penaltyCodeNames));
        
    }

    @FXML
    private void setPenaltyCode(ActionEvent event) {
        String description = this.cmbPenaltyDescription.getValue();
        this.txtPenaltyDescription.setText(this.penaltyCodeMap.get(description));        
    }

    @FXML
    private void savePenalty(ActionEvent event) throws SQLException {

        int number = Integer.parseInt(this.txtPenaltyReceiver.getText());
        String code = this.txtPenaltyDescription.getText();
        PenaltyDao penaltyDao = new PenaltyDao(database);
        String description = penaltyDao.findOne(code);
        String min = this.txtPenaltyMin.getText();
        String startTime = this.txtPenaltyStart.getText();
        String endTime = this.txtPenaltyEnd.getText();
        
        Team team;
        if (this.isHomeSelected) {
            team = this.match.getHomeTeam();
        } else {
            team = this.match.getAwayTeam();
        }
        
        Player player = team.getPlayer(number);
        
        team.addPenalty(player, code, description, min, startTime, endTime);
        
        ObservableList<Penalty> penalties = FXCollections.observableList(team.getPenalties());
        
        if (this.isHomeSelected) {
            this.tablePenaltyHome.setItems(penalties);
        } else {
            this.tablePenaltyAway.setItems(penalties);
        }
        
        clearPenalty();
        
        this.tabMain.getSelectionModel().select(tabStatistics);
        
    }

    @FXML
    private void cancelPenalty(ActionEvent event) {

        clearPenalty();
        this.tabMain.getSelectionModel().select(tabStatistics);

    }
    
    public void clearPenalty() {

        this.lbPenaltyTeam.setText("");
        this.txtPenaltyReceiver.setText("");
        this.txtPenaltyMin.setText("");
        this.txtPenaltyDescription.setText("");
        this.txtPenaltyStart.setText("");
        this.txtPenaltyEnd.setText("");
        
        this.cmbPenaltyReceiver.getItems().removeAll(this.cmbPenaltyReceiver.getItems());
        this.cmbPenaltyDescription.getItems().removeAll(this.cmbPenaltyDescription.getItems());
        
    }

    @FXML
    private void addPenaltyAway(ActionEvent event) throws SQLException {

        this.tabMain.getSelectionModel().select(tabPenalty);
        
        this.isHomeSelected = false;
        
        // Set team name
        this.lbPenaltyTeam.setText(this.match.getAwayTeam().getName());

        // Set goal time
        int totalMinutes = Integer.parseInt(this.txtPeriodLength.getText()) * (this.match.getPeriod() - 1);
        totalMinutes += Integer.parseInt(this.lbClock.getText().substring(0, 2));
        
        String totalMinutesString = "" + totalMinutes;
        if (totalMinutes < 10) {
            totalMinutesString = "0" + totalMinutes;
        }
        
        String seconds = totalMinutesString + lbClock.getText().substring(2);
        
        this.txtPenaltyStart.setText(totalMinutesString + seconds);
                
        List<String> playersNumberName = this.match.getAwayTeam().getPlayersNumberName();
        this.cmbPenaltyReceiver.setItems(FXCollections.observableArrayList(playersNumberName));
        
        PenaltyDao penaltyDao = new PenaltyDao(database);
        List<PenaltyCode> penaltyCodes = penaltyDao.findAll();
        
        this.penaltyCodeMap = new HashMap<>();
        List<String> penaltyCodeNames = new ArrayList<>();
        
        for (int i = 0; i < penaltyCodes.size(); i++) {
            penaltyCodeMap.put(penaltyCodes.get(i).toString(), penaltyCodes.get(i).getCode());
            penaltyCodeNames.add(penaltyCodes.get(i).toString());
        }
        
        this.cmbPenaltyDescription.setItems(FXCollections.observableArrayList(penaltyCodeNames));
        
    }

    @FXML
    private void addGoalAway(ActionEvent event) {

        this.tabMain.getSelectionModel().select(tabGoal);
        
        this.isHomeSelected = false;
        
        // Set team name
        this.lbGoalTeam.setText(this.match.getAwayTeam().getName());
        
        // Set goal time
        int totalMinutes = Integer.parseInt(this.txtPeriodLength.getText()) * (this.match.getPeriod() - 1);
        totalMinutes += Integer.parseInt(this.lbClock.getText().substring(0, 2));
        
        String totalMinutesString = "" + totalMinutes;
        if (totalMinutes < 10) {
            totalMinutesString = "0" + totalMinutes;
        }
        
        String seconds = totalMinutesString + lbClock.getText().substring(2);
        
        txtGoalTime.setText(totalMinutesString + seconds);
        
        List<String> playersNumberName = this.match.getAwayTeam().getPlayersNumberName();
        this.cmbGoalScorer.setItems(FXCollections.observableArrayList(playersNumberName));
        this.cmbGoalAssistant1.setItems(FXCollections.observableArrayList(playersNumberName));
        this.cmbGoalAssistant2.setItems(FXCollections.observableArrayList(playersNumberName));
        
    }

    @FXML
    private void saveMatchData(ActionEvent event) throws SQLException {
        
        MatchDao matchDao = new MatchDao(database);
        matchDao.saveMatch(match);
        
    }
        
}
