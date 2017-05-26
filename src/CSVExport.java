/**
 * Created by lucy on 26/05/17.
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVExport {

    public String countryInfo (CSVParser parser, String country){
        String countryInformatuon = new String();
        for (CSVRecord record: parser) {
            String countryOfInterest =record.get("Country");
            if(countryOfInterest.contains(country)){
                String export = record.get("Exports");
                String value = record.get("Value (dollars)");
                String countryInformation = countryOfInterest+ ": "+export+ ": "+value;
                System.out.println(countryInformation);
            }
        }
        return countryInformatuon;
    }

    public void tester(){
        FileResource fr =new FileResource();
        CSVParser parser = fr.getCSVParser();
        String info = countryInfo(parser,"France");
        if(info.isEmpty()){
            System.out.println("Not found");
        }
    }
}
