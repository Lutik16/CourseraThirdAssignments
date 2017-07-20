/**
 * Created by lucy on 26/05/17.
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVExport {

    public String countryInfo (CSVParser parser, String country){
        String countryInformation = "not found";
        for (CSVRecord record: parser) {
            String countryOfInterest = record.get("Country");
            if (countryOfInterest.contains(country)) {
                String export = record.get("Exports");
                String value = record.get("Value (dollars)");
                countryInformation = countryOfInterest + ": "+export+ ": "+value;
                break;
            }
        }
        return countryInformation;
    }

    public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record: parser){
            String export = record.get("Exports");
            if( export.contains(exportItem1)&& export.contains(exportItem2)){
                String country =record.get ("Country");
                System.out.println(country);
            }
        }
    }

    public int numberOfExporters (CSVParser parser, String exportItem){
        int numberOfCountries=0;

        for(CSVRecord record: parser){
            String export = record.get("Exports");
//            System.out.println(export+"\n");

            if (export.contains(exportItem)){
                numberOfCountries += 1;
            }
        }
        return numberOfCountries;
    }

    public void bigExporters (CSVParser parser, String amount){
        for(CSVRecord record:parser){
            String value = record.get("Value (dollars)");
            if(value.length()>amount.length()){
                String country =record.get("Country");
                System.out.println(country+""+value);
            }
        }
    }

    public void tester(){
        FileResource fr =new FileResource();
        CSVParser parser = fr.getCSVParser();
//        String countryInfo = countryInfo(parser,"Nauru");
//        System.out.println(countryInfo);
//        listExportersTwoProducts(parser, "diamonds", "gold");
        bigExporters(parser, "$999,999,999,999");
//        int numberOfExporters=numberOfExporters(parser, "sugar");
//        System.out.println("Number of exporters is "+ numberOfExporters);
    }
}
