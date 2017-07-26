/**
 * Created by lucy on 18/07/17.
 */
import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WeatherData {

    public CSVRecord getColdestOfTwo(CSVRecord currentRow, CSVRecord coldestSoFar){
        if(coldestSoFar==null){
            coldestSoFar=currentRow;
        }
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            if(currentTemp<coldestTemp && currentTemp>-999){
                coldestSoFar=currentRow;
            }
        }
        return coldestSoFar;
    }

    public CSVRecord coldestHourInFile (CSVParser parser){
        CSVRecord coldestSoFar=null;
        for (CSVRecord currentRow: parser){
           coldestSoFar=getColdestOfTwo(currentRow, coldestSoFar);
        }
        return coldestSoFar;
    }

    public void testColdestInDay(){
        FileResource fr = new FileResource("nc_weather/2015/weather-2015-02-02.csv");
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was "+ coldest.get("TemperatureF") + " at "+ coldest.get("TimeEST") );
    }

    public CSVRecord coldestInManyDays(){
        CSVRecord coldestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar);
        }
        return coldestSoFar;
    }

   /* public String fileWithColdestTemperature(){
        String nameOfFile = coldestInManyDays()
    }*/
   public String fileWithColdestTemperature(){
       DirectoryResource dr = new DirectoryResource();
       String fileName = null;
       CSVRecord coldestSoFar = null;
       for (File f: dr.selectedFiles()){
           FileResource fr = new FileResource(f);
           CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
           coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar);
           Path p = Paths.get(String.valueOf(f));
           fileName = p.toString();
       }
       return fileName;
   }

   public void testFileWithColdestTemperature(){
       String coldestTempFileName = fileWithColdestTemperature();
       System.out.println("Coldest day was in file "+ coldestTempFileName);
       FileResource file = new FileResource(coldestTempFileName);
       CSVRecord coldest = coldestHourInFile(file.getCSVParser());
       System.out.println("Coldest temperature on that day was "+ coldest.get("TemperatureF"));
       System.out.println("All the temperatures on the coldest day were: " );


   }
}
