package icehockeystats;

import icehockeystats.dao.DataReader;
import icehockeystats.dao.Database;
import icehockeystats.dao.PenaltyDao;
import icehockeystats.domain.Clock;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

import icehockeystats.dao.*;
import java.sql.SQLException;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException, SQLException {
        launch(args);
        
//        String fileName = "penaltycodes.csv";
//        DataReader reader = new DataReader(fileName);
//        ArrayList<String> penalties = reader.loadPenalties();
//        
//        File tiedosto = new File("icehockeystat.db");        
//        String dbName = "jdbc:sqlite:" ;
//        Database db = new Database("jdbc:sqlite:" + tiedosto.getAbsolutePath());
//        
//        PenaltyDao penaltyDao = new PenaltyDao(db);
//        penaltyDao.savePenaltyCodes(penalties);
        
//        Clock clock = new Clock();
//        
//        for(int i = 0; i < 10; i++) {
//            clock.show();
//            clock.tick();
//            TimeUnit.SECONDS.sleep(1);
//        }
        
    }

}
