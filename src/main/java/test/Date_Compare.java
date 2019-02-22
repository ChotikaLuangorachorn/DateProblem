package test;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;


public class Date_Compare {
    public static String input_file_name = "./date_input.csv";
    public static String output_file_name = "./date_output.csv";
    public static void main(String[] args) throws FileNotFoundException, IOException {
        CSVReader date_input_reader = new CSVReader(input_file_name);
        CSVParser input_csvParser = date_input_reader.get_csvParser();

        CSVReader date_output_reader = new CSVReader(output_file_name);
        CSVParser output_csvParser = date_output_reader.get_csvParser();


//        Compare

        String path = "E:";
        String file3 = "compare.csv";
        ArrayList al1 = new ArrayList();
        ArrayList al2 = new ArrayList();

        BufferedReader CSVFile1 = new BufferedReader(new FileReader(input_file_name));
        String dataRow1 = CSVFile1.readLine();
        while (dataRow1 != null) {
            String[] dataArrayFile1 = dataRow1.split(",",1);
            for (String item1 : dataArrayFile1) {
                al1.add(item1);
            }

            dataRow1 = CSVFile1.readLine(); // Read next line of data.
        }

        CSVFile1.close();

        BufferedReader CSVFile2 = new BufferedReader(new FileReader(output_file_name));
        String dataRow2 = CSVFile2.readLine();
        while (dataRow2 != null) {
            String[] dataArray2 = dataRow2.split(",",1);
            for (String item2 : dataArray2) {
                al2.add(item2);

            }
            dataRow2 = CSVFile2.readLine(); // Read next line of data.
        }
        CSVFile2.close();

        int indA11 = 0;
        for (Object bs:al2) {
            if (al1.get(indA11).equals(bs)){
                al1.set(indA11,"Accept");
            }
            else{
                al1.set(indA11,"Reject");
            }

            indA11++;

        }

        int size = al1.size();
        System.out.println(size);


        try {
            FileWriter writer = new FileWriter(path + file3);
            while (size != 0) {
                size--;
                writer.append("" + al1.get(size));
                writer.append('\n');
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
