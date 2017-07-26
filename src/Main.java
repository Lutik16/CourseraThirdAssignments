/**
 * Created by lucy on 22/05/17.
 */
import edu.duke.*;
import org.apache.commons.csv.*;

import java.util.WeakHashMap;

public class Main {
    public static void main (String[] args){
       // CSVExport test = new CSVExport();
       // test.tester();
        WeatherData test = new WeatherData();
        //test.testColdestInDay();
        test.testFileWithColdestTemperature();
    }
}
