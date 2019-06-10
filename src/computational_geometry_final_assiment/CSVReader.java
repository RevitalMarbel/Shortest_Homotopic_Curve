package computational_geometry_final_assiment;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    public static void main(String[] args) 
    {
        String csvFile = "users2.csv";
        String line = "";
        String cvsSplitBy = " ";
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
        {
            line = br.readLine();
            
            line = br.readLine();
            String[] data = line.split(cvsSplitBy);
                //here do something
            

        } catch (IOException e) 
        {
            e.printStackTrace();
        }

    }

}