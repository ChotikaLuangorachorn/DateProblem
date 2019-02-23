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

        String file3 = "./compare.csv";
        ArrayList arrayListInput = new ArrayList();
        ArrayList arrayListOutput = new ArrayList();

        for (CSVRecord input:input_csvParser) {
//            System.out.println(input.toString());
            arrayListInput.add(input.toString());
        }
        for (CSVRecord output:output_csvParser){
            arrayListOutput.add(output.toString());
        }

        int indA11 = 0;
        for (Object bs:arrayListOutput) {
            if (arrayListInput.get(indA11).equals(bs)){
                arrayListInput.set(indA11,"Accept");
            }
            else{
                arrayListInput.set(indA11,"Reject");
            }
            indA11++;
        }

        int size = arrayListInput.size();
        //Compare
        try {


                FileWriter writer = new FileWriter(file3);
                for (int i = 0; i < size; i++) {
                    if (i==0){
                        writer.append("id,result"+"\n");
                        continue;
                    }
                    writer.append(i +","+ arrayListInput.get(i));
                    writer.append('\n');
                }
                writer.flush();
                writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
