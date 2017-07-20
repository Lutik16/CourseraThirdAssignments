/**
 * Created by lucy on 18/07/17.
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WeatherData {

    public CSVRecord coldestHourInFile (CSVParser parser){
        CSVRecord coldestSoFar=null;
        for (CSVRecord currentRow: parser){
            if (coldestSoFar==null){
                coldestSoFar=currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currentTemp < coldestTemp){
                    coldestSoFar = currentRow;
                }
            }
        }
        return coldestSoFar;
    }

    public void testColdestInDay(){
        FileResource fr = new FileResource("nc_weather/2015/weather-2015-02-02.csv");
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was "+ coldest.get("TemperatureF") + " at "+ coldest.get("TimeEST") );
    }
}
